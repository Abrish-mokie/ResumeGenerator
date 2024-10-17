package com.example.pdfgenerator.Education.Service.Mapper;

import com.example.pdfgenerator.Education.DTO.RequestEducationDTO;
import com.example.pdfgenerator.Education.DTO.ResponseEducationDTO;
import com.example.pdfgenerator.Education.Model.Education;
import com.example.pdfgenerator.User.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EducationMapper {

    public final UserRepository repo;

    public Education toEducation(RequestEducationDTO dto){
        return Education.builder()
                .user(repo.findById(dto.userId()).orElseThrow())
                .title(dto.title())
                .description(dto.description())
                .build();
    }

    public ResponseEducationDTO fromEducation(Education model){
        return new ResponseEducationDTO(model.getId(), model.getTitle(),model.getDescription());
    }
}
