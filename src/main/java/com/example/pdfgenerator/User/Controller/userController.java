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
import java.util.Map;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Tag(name="User management", description = "All methods for dealing with a user")
public class userController {

    private final UserService userService;

    @PostMapping("/save")
    public ResponseEntity<Candidate> save(RequestUserDTO dto){
        userService.save(dto);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/get/{name}")
    public ResponseEntity<ResponseUserDTO> get(@PathVariable String name){
        return ResponseEntity.ok(userService.getByName(name));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ResponseUserDTO>> getAll(){
        return ResponseEntity.ok(userService.getAll());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/patch/{id}")
    public ResponseEntity<Void> patch(@PathVariable Long id,@RequestBody Map<String,Object> value){
        userService.patch(id,value);
        return ResponseEntity.ok().build();
    }

}
