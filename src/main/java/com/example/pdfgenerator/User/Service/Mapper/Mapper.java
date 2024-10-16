package com.example.pdfgenerator.User.Service.Mapper;

import com.example.pdfgenerator.User.DTO.UserDTO;
import com.example.pdfgenerator.User.Model.Candidate;
import org.springframework.stereotype.Service;

@Service
public class Mapper {

    public Candidate toUser(UserDTO dto){
        return Candidate.builder()
                .name(dto.name())
                .title(dto.title())
                .build();
    }

    public UserDTO fromUser(Candidate user){
        return new UserDTO(user.getName(),user.getTitle());
    }
}
