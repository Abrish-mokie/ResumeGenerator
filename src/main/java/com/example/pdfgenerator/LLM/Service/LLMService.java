package com.example.pdfgenerator.LLM.Service;

import com.example.pdfgenerator.Resume.Model.Resume;
import com.example.pdfgenerator.Resume.Service.PdfGeneratorService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
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
@RequiredArgsConstructor
public class LLMService {

    private final ChatClient chatClient;
    private final LLMRepoServices repo;
    private final ResumeTemplateCreator resumeCreator;


    @Value("classpath:/prompts/initial_test.st")
    private Resource testing;
    @Value("classpath:/prompts/Resume_Template.st")
    private Resource resumeTemplate;
    @Value("classpath:/prompts/Prompt_Template.st")
    private Resource prompt;



//    public LLMService(ChatClient.Builder chatBuilder, LLMRepoServices repo){
//        log.info(String.valueOf(system));
//
//        this.chatClient = chatBuilder
//                .defaultAdvisors(new MessageChatMemoryAdvisor(new InMemoryChatMemory()))
//                .defaultSystem()
//                .build();
//        this.repo = repo;
//    }


//    public String initialPrompt( String requiredMessage, String jobDescription, String userProvidedInfo) {
//        PromptTemplate promptTemplate = new PromptTemplate(initialPrompt);
//        Map<String, Object> promptParameters = new HashMap<>();
//        promptParameters.put("required_generation", requiredMessage);
//        promptParameters.put("Job_description", jobDescription);
//        promptParameters.put("User_provided_info", userProvidedInfo);
//        Prompt prompt = promptTemplate.create(promptParameters);
//
//        DescriptionDTOResponse description = this.chatClient
//                .prompt(prompt)
//                .call()
//                .entity(DescriptionDTOResponse.class);
//
//        return description.Description();
//    }
//
//    public String restOfgeneration(String message){
//        PromptTemplate promptTemplate = new PromptTemplate(restGeneration);
//        Map<String, Object> promptParameters = new HashMap<>();
//        promptParameters.put("neededGeneration", message);
//        Prompt prompt = promptTemplate.create(promptParameters);
//
//        return this.chatClient
//                .prompt(prompt)
//                .call()
//                .content();
//
//    }

    public void testing(HttpServletResponse response) throws IOException {

        PromptTemplate promptTemplate = new PromptTemplate(testing);
        Prompt prompt = promptTemplate.create();


        Resume resume = this.chatClient.prompt(prompt).call().entity(Resume.class);
        log.info(resume.toString());
        PdfGeneratorService pdfResume = new PdfGeneratorService(
                resume.getUser(),
                resume.getDescription(),
                resume.getSkills(),
                resume.getProfessionalExperiences(),
                resume.getCertifications(),
                resume.getProjects(),
                resume.getEducations()
        );

        pdfResume.export(response);
    }

    public void generateResume(HttpServletResponse response,Long id,String jobDescription) throws IOException {

        PromptTemplate promptTemplate = new PromptTemplate(prompt);
        Map<String,Object> populate = new HashMap<>();
        populate.put("description",jobDescription);
        populate.put("resume",resumeCreator.resumeTemplate());
        Prompt promptGPT = promptTemplate.create(populate);

        com.example.pdfgenerator.Resume.DTO.Resume resume = chatClient.prompt(promptGPT).system(repo.getById(id).promptTemplate())
                .call().entity(com.example.pdfgenerator.Resume.DTO.Resume.class);
        log.info("\n{}", resumeCreator.resumeTemplateFromResume(resume));
        PdfGeneratorService pdfGeneratorService = new PdfGeneratorService(resume);
        pdfGeneratorService.export(response);
    }


}
