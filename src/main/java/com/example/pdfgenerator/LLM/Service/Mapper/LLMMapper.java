package com.example.pdfgenerator.LLM.Service.Mapper;

import com.example.pdfgenerator.LLM.DTO.RequestLLMDTO;
import com.example.pdfgenerator.LLM.DTO.ResponseLLMDTO;
import com.example.pdfgenerator.LLM.Model.PromptTemplate;
import com.example.pdfgenerator.LLM.Repository.LLMRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LLMMapper {

    private final LLMRepository repo;

    public PromptTemplate toPromptTemplate(String prompt){
        return PromptTemplate.builder()
                .template(prompt)
                .build();
    }

    public ResponseLLMDTO fromPromptTemplate(PromptTemplate model){
        return new ResponseLLMDTO(model.getId(),model.getTemplate());
    }
}
