package com.example.pdfgenerator.Education.Controller;


import com.example.pdfgenerator.Education.DTO.RequestEducationDTO;
import com.example.pdfgenerator.Education.DTO.ResponseEducationDTO;
import com.example.pdfgenerator.Education.Service.EducationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/education")
@RestController
@RequiredArgsConstructor
@Tag(name = "Education Controller", description = "Managing education entries")
public class EducationController {

    private final EducationService educationService;

    @PostMapping("/save")
    public ResponseEntity<Void> save(RequestEducationDTO dto){
        educationService.save(dto);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ResponseEducationDTO> get(@PathVariable Long id){
        return ResponseEntity.ok(educationService.getById(id));
    }

    @GetMapping("/get")
    public ResponseEntity<List<ResponseEducationDTO>> getAll(){
        return ResponseEntity.ok(educationService.getAll());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        educationService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/patch/{id}")
    public ResponseEntity<Void> patch(@PathVariable Long id,@RequestBody Map<String,Object> value){
        educationService.patch(id,value);
        return ResponseEntity.ok().build();
    }


}
