package com.example.pdfgenerator.Description.Service.Mapper;

import com.example.pdfgenerator.Description.DTO.DescriptionDTORequest;
import com.example.pdfgenerator.Description.DTO.DescriptionDTOResponse;
import com.example.pdfgenerator.Description.Model.Description;
import com.example.pdfgenerator.User.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DescriptionMappers {

    private final UserRepository repo;


    public Description toDescription(DescriptionDTORequest dto){
        return Description.builder()
                .user(repo.findById(dto.userId()).orElseThrow())
                .description(dto.Description())
                .build();
    }

    public DescriptionDTOResponse fromDescription(Description model){
        return new DescriptionDTOResponse(model.getDescription());
    }
}
