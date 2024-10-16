package com.example.pdfgenerator.Skills.Service.Mapper;

import com.example.pdfgenerator.Skills.DTO.RequestSkillsDTO;
import com.example.pdfgenerator.Skills.DTO.ResponseSkillsDTO;
import com.example.pdfgenerator.Skills.Model.Skills;
import com.example.pdfgenerator.User.Repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class SkillsMapper {

    private UserRepository repo;

    public Skills toSkills(RequestSkillsDTO dto){
        return Skills.builder()
                .user(repo.findById(dto.userId()).orElseThrow())
                .title(dto.title())
                .skills(dto.skills())
                .build();
    }

    public ResponseSkillsDTO fromSkills(Skills model){
        return new ResponseSkillsDTO(model.getTitle(),model.getSkills());
    }
}
