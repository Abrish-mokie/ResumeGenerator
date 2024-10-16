package com.example.pdfgenerator.Skills.Service;

import com.example.pdfgenerator.Projects.DTO.RequestProjectsDTO;
import com.example.pdfgenerator.Projects.DTO.ResponseProjectDTO;
import com.example.pdfgenerator.Projects.Repository.ProjectsRepository;
import com.example.pdfgenerator.Projects.Service.Mapper.ProjectMapper;
import com.example.pdfgenerator.Skills.DTO.RequestSkillsDTO;
import com.example.pdfgenerator.Skills.DTO.ResponseSkillsDTO;
import com.example.pdfgenerator.Skills.Repository.SkillsRepository;
import com.example.pdfgenerator.Skills.Service.Mapper.SkillsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SkillsService {

    private final SkillsRepository repo;
    private final SkillsMapper mapper;

    public void save(RequestSkillsDTO dto){
        repo.save(mapper.toSkills(dto));
    }

    public List<ResponseSkillsDTO> getAll(){
        return repo.findAll().stream().map(mapper::fromSkills).toList();
    }

    public ResponseSkillsDTO getById(Long id){
        return mapper.fromSkills(repo.getReferenceById(id));
    }

    public void delete(Long id){
        repo.deleteById(id);
    }
}
