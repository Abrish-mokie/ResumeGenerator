package com.example.pdfgenerator.Service;

import com.example.pdfgenerator.DTO.Description;
import com.example.pdfgenerator.DTO.Resume;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class LLMService {

    private final ChatClient chatClient;

    @Value("classpath:/prompts/description_generate.st")
    private Resource initialPrompt;
    @Value("classpath:/prompts/RestOfresume.st")
    private Resource restGeneration;
    @Value("classpath:/prompts/initial_test.st")
    private Resource testing;
    @Value("classpath:/prompts/system.st")
    private Resource system;


    public LLMService(ChatClient.Builder chatBuilder){
        log.info(String.valueOf(system));

        this.chatClient = chatBuilder
                .defaultAdvisors(new MessageChatMemoryAdvisor(new InMemoryChatMemory()))
                .build();
    }


    public String initialPrompt( String requiredMessage, String jobDescription, String userProvidedInfo) {
        PromptTemplate promptTemplate = new PromptTemplate(initialPrompt);
        Map<String, Object> promptParameters = new HashMap<>();
        promptParameters.put("required_generation", requiredMessage);
        promptParameters.put("Job_description", jobDescription);
        promptParameters.put("User_provided_info", userProvidedInfo);
        Prompt prompt = promptTemplate.create(promptParameters);

        Description description = this.chatClient
                .prompt(prompt)
                .call()
                .entity(Description.class);

        return description.Description();
    }

    public String restOfgeneration(String message){
        PromptTemplate promptTemplate = new PromptTemplate(restGeneration);
        Map<String, Object> promptParameters = new HashMap<>();
        promptParameters.put("neededGeneration", message);
        Prompt prompt = promptTemplate.create(promptParameters);

        return this.chatClient
                .prompt(prompt)
                .call()
                .content();

    }

    public void testing(HttpServletResponse response) throws IOException {

        PromptTemplate promptTemplate = new PromptTemplate(testing);
        Prompt prompt = promptTemplate.create();


        Resume resume = this.chatClient.prompt(prompt).call().entity(Resume.class);
        log.info(resume.toString());
        PdfGeneratorService pdfResume = new PdfGeneratorService(
                resume.getDescription(),
                resume.getSkills(),
                resume.getProfessionalExperiences(),
                resume.getCertifications(),
                resume.getProjects(),
                resume.getEducations()
        );

        pdfResume.export(response);
    }

}
