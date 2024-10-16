package com.example.pdfgenerator.User.Controller;


import com.example.pdfgenerator.User.DTO.UserDTO;
import com.example.pdfgenerator.User.Model.Candidate;
import com.example.pdfgenerator.User.Service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Tag(name="User management", description = "All methods for dealing with a user")
public class userController {

    private final UserService service;

    @PostMapping("/save")
    public ResponseEntity<Candidate> save(UserDTO dto){
        service.save(dto);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/get/{name}")
    public ResponseEntity<Candidate> get(@PathVariable String name){
        Optional<Candidate> Candidate = service.get(name);
        return Candidate.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Candidate>> getAll(){
        List<Candidate> Candidate = service.getAll();
        return ResponseEntity.ok(Candidate);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
