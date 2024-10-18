package com.example.pdfgenerator.LLM.Configuration;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LLMConfiguration {

    @Bean
    ChatClient chatClient(ChatClient.Builder builder){
        return builder.build();
    }
}
