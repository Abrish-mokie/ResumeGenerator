package com.example.pdfgenerator.User.Controller;


import com.example.pdfgenerator.User.DTO.RequestUserDTO;
import com.example.pdfgenerator.User.DTO.ResponseUserDTO;
import com.example.pdfgenerator.User.Model.Candidate;
import com.example.pdfgenerator.User.Service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Tag(name="User management", description = "All methods for dealing with a user")
public class userController {

    private final UserService service;

    @PostMapping("/save")
    public ResponseEntity<Candidate> save(RequestUserDTO dto){
        service.save(dto);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/get/{name}")
    public ResponseEntity<ResponseUserDTO> get(@PathVariable String name){
        return ResponseEntity.ok(service.getByName(name));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ResponseUserDTO>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
