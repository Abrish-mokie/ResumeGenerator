package com.example.pdfgenerator.Service;

import com.example.pdfgenerator.Experience.DTO.RequestExprienceDTO;
import com.example.pdfgenerator.Experience.Model.Experiences;
import com.example.pdfgenerator.Experience.Repository.ExperiencesRepository;
import com.example.pdfgenerator.Experience.Service.Mapper.ExprienceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DataManipulation {

    private final ExprienceMapper exprienceMapper;
    private final ExperiencesRepository exprepo;

    public void postExprience(RequestExprienceDTO exprience){
        Experiences exp = exprienceMapper.toExprience(exprience);
        exprepo.save(exp);
    }
}
