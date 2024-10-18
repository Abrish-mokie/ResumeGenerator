package com.example.pdfgenerator.LLM.Repository;

import com.example.pdfgenerator.LLM.Model.PromptTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LLMRepository extends JpaRepository<PromptTemplate,Long> {
}