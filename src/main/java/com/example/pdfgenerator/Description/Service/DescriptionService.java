package com.example.pdfgenerator.Description.Service;


import com.example.pdfgenerator.Description.DTO.DescriptionDTORequest;
import com.example.pdfgenerator.Description.DTO.DescriptionDTOResponse;
import com.example.pdfgenerator.Description.Repository.DescriptionRepository;
import com.example.pdfgenerator.Description.Service.Mapper.DescriptionMappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DescriptionService {

    private final DescriptionRepository repo;
    private final DescriptionMappers mapper;

    public void save(DescriptionDTORequest dto){
        repo.save(mapper.toDescription(dto));
    }

    public List<DescriptionDTOResponse> getAll(){
        return repo.findAll().stream().map(mapper::fromDescription).toList();
    }

    public DescriptionDTOResponse getById(Long id){
        return mapper.fromDescription(repo.getReferenceById(id));
    }

    public void delete(Long id){
        repo.deleteById(id);
    }

}
