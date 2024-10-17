package com.example.pdfgenerator.Experience.Service.Mapper;

import com.example.pdfgenerator.Experience.DTO.RequestExprienceDTO;
import com.example.pdfgenerator.Experience.DTO.ResponseExprienceDTO;
import com.example.pdfgenerator.Experience.Model.Experiences;
import com.example.pdfgenerator.User.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExprienceMapper {

    public final UserRepository repo;

    public Experiences toExprience(RequestExprienceDTO dto){
        return Experiences.builder()
                .user(repo.findById(dto.userId()).orElseThrow())
                .duration(dto.duration())
                .location(dto.location())
                .title(dto.title())
                .subTitle(dto.subTitle())
                .responsibilities(dto.responsibilities())
                .build();
    }

    public ResponseExprienceDTO fromExprience(Experiences model){
        return new ResponseExprienceDTO(
                model.getId(),
                model.getTitle(),
                model.getSubTitle(),
                model.getLocation(),
                model.getDuration(),
                model.getResponsibilities()
        );
    }
}