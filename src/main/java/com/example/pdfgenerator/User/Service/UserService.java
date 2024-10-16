package com.example.pdfgenerator.User.Service;

import com.example.pdfgenerator.User.Repository.UserRepository;
import com.example.pdfgenerator.User.DTO.UserDTO;
import com.example.pdfgenerator.User.Model.Candidate;
import com.example.pdfgenerator.User.Service.Mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository user;
    private final Mapper mapper;

    public Candidate save(UserDTO dto){
        return user.save(mapper.toUser(dto));
    }

    public Optional<Candidate> get(String name){
        return user.findByName(name);
    }

    public List<Candidate> getAll(){
        return user.findAll();
    }

    public void delete(Long id){
        user.deleteById(id);
    }
}
