package com.example.pdfgenerator.Education.Service;


import com.example.pdfgenerator.Education.DTO.RequestEducationDTO;
import com.example.pdfgenerator.Education.DTO.ResponseEducationDTO;
import com.example.pdfgenerator.Education.Model.Education;
import com.example.pdfgenerator.Education.Repository.EducationRepository;
import com.example.pdfgenerator.Education.Service.Mapper.EducationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EducationService {

    private final EducationRepository repo;
    private final EducationMapper mapper;

    public void save(RequestEducationDTO dto){
        repo.save(mapper.toEducation(dto));
    }

    public List<ResponseEducationDTO> getAll(){
        return repo.findAll().stream().map(mapper::fromEducation).toList();
    }

    public ResponseEducationDTO getById(Long id){
        return mapper.fromEducation(repo.getReferenceById(id));
    }

    public void delete(Long id){
        repo.deleteById(id);
    }

}
