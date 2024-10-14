package com.example.pdfgenerator.Service.Mapper;


import com.example.pdfgenerator.DTO.ProfessionalExprience;
import com.example.pdfgenerator.Model.Experiences;
import com.example.pdfgenerator.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExprienceMapper {

    private final UserRepository user;

    public Experiences toExprience(ProfessionalExprience exprience){
        return Experiences.builder()
                .title(exprience.title())
                .responsibilities(exprience.responsibilities())
                .subTitle(exprience.subTitle())
                .user(user.findByName(exprience.userName()))
                .build();
    }

    public ProfessionalExprience fromExprience(Experiences experiences){
        return new ProfessionalExprience(
                experiences.getUser().getName(),
                experiences.getTitle(),
                experiences.getSubTitle(),
                experiences.getResponsibilities()
        );
    }

}
