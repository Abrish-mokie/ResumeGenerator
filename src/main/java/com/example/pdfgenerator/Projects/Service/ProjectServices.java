package com.example.pdfgenerator.Projects.Service;

import com.example.pdfgenerator.Projects.DTO.RequestProjectsDTO;
import com.example.pdfgenerator.Projects.DTO.ResponseProjectDTO;
import com.example.pdfgenerator.Projects.Repository.ProjectsRepository;
import com.example.pdfgenerator.Projects.Service.Mapper.ProjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectServices {

    private final ProjectsRepository repo;
    private final ProjectMapper mapper;

    public void save(RequestProjectsDTO dto){
        repo.save(mapper.toProjects(dto));
    }

    public List<ResponseProjectDTO> getAll(){
        return repo.findAll().stream().map(mapper::fromProjects).toList();
    }

    public ResponseProjectDTO getById(Long id){
        return mapper.fromProjects(repo.getReferenceById(id));
    }

    public void delete(Long id){
        repo.deleteById(id);
    }
}
