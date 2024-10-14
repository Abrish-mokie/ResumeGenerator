package com.example.pdfgenerator.Service;

import com.example.pdfgenerator.DTO.ProfessionalExprience;
import com.example.pdfgenerator.Model.Experiences;
import com.example.pdfgenerator.Repository.ExperiencesRepository;
import com.example.pdfgenerator.Service.Mapper.ExprienceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DataManipulation {

    private final ExprienceMapper exprienceMapper;
    private final ExperiencesRepository exprepo;

    public void postExprience(ProfessionalExprience exprience){
        Experiences exp = exprienceMapper.toExprience(exprience);
        exprepo.save(exp);
    }
}
