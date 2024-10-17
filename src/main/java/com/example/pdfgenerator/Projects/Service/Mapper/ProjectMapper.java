package com.example.pdfgenerator.Projects.Service.Mapper;

import com.example.pdfgenerator.Experience.DTO.RequestExprienceDTO;
import com.example.pdfgenerator.Experience.DTO.ResponseExprienceDTO;
import com.example.pdfgenerator.Experience.Model.Experiences;
import com.example.pdfgenerator.Projects.DTO.RequestProjectsDTO;
import com.example.pdfgenerator.Projects.DTO.ResponseProjectDTO;
import com.example.pdfgenerator.Projects.Model.Projects;
import com.example.pdfgenerator.User.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectMapper {

    private final UserRepository repo;

    public Projects toProjects(RequestProjectsDTO dto){
        return Projects.builder()
                .user(repo.findById(dto.userId()).orElseThrow())
                .title(dto.title())
                .description(dto.description())
                .duration(dto.duration())
                .projectObjectives(dto.projectObjectives())
                .build();
    }

    public ResponseProjectDTO fromProjects(Projects model){
        return new ResponseProjectDTO(model.getId(), model.getTitle(),model.getDescription(),model.getDescription(),model.getProjectObjectives());
    }
}

