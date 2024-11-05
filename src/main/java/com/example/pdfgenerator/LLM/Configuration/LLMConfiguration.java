package com.example.pdfgenerator.LLM.Configuration;

import com.example.pdfgenerator.LLM.Advisor.ReReadingAdvisor;
import com.example.pdfgenerator.LLM.Advisor.SimpleLoggerAdvisor;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class LLMConfiguration {

    private final SimpleLoggerAdvisor simpleLoggerAdvisor;
    private final ReReadingAdvisor reReadingAdvisor;

    @Bean
    ChatClient chatClient(ChatClient.Builder builder){
        return builder
                .defaultAdvisors(simpleLoggerAdvisor,reReadingAdvisor)
                .build();
    }
}
