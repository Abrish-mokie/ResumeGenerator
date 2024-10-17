package com.example.pdfgenerator.User.Service;

import com.example.pdfgenerator.Description.DTO.DescriptionDTORequest;
import com.example.pdfgenerator.Description.DTO.DescriptionDTOResponse;
import com.example.pdfgenerator.User.DTO.RequestUserDTO;
import com.example.pdfgenerator.User.DTO.ResponseUserDTO;
import com.example.pdfgenerator.User.Model.Candidate;
import com.example.pdfgenerator.User.Repository.UserRepository;
import com.example.pdfgenerator.User.Service.Mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repo;
    private final UserMapper mapper;

    public void save(RequestUserDTO dto){
        repo.save(mapper.toUser(dto));
    }

    public List<ResponseUserDTO> getAll(){
        return repo.findAll().stream().map(mapper::fromUser).toList();
    }

    public ResponseUserDTO getById(Long id){
        return mapper.fromUser(repo.getReferenceById(id));
    }

    public void delete(Long id){
        repo.deleteById(id);
    }

    public ResponseUserDTO getByName(String name){
        Optional<Candidate> Candidate = repo.findByName(name);
        return mapper.fromUser(Candidate.orElseThrow());
    }
}
