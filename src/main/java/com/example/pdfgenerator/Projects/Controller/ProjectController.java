package com.example.pdfgenerator.Projects.Controller;


import com.example.pdfgenerator.Experience.DTO.RequestExprienceDTO;
import com.example.pdfgenerator.Experience.DTO.ResponseExprienceDTO;
import com.example.pdfgenerator.Experience.Service.ExprienceService;
import com.example.pdfgenerator.Projects.DTO.RequestProjectsDTO;
import com.example.pdfgenerator.Projects.DTO.ResponseProjectDTO;
import com.example.pdfgenerator.Projects.Service.ProjectServices;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/project")
@RestController
@RequiredArgsConstructor
@Tag(name = "Project Controller", description = "For managing project entries")
public class ProjectController {

    private final ProjectServices projectServices;

    @PostMapping("/save")
    public ResponseEntity<Void> save(RequestProjectsDTO dto){
        projectServices.save(dto);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ResponseProjectDTO> get(@PathVariable Long id){
        return ResponseEntity.ok(projectServices.getById(id));
    }

    @GetMapping("/get")
    public ResponseEntity<List<ResponseProjectDTO>> getAll(){
        return ResponseEntity.ok(projectServices.getAll());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        projectServices.delete(id);
        return ResponseEntity.ok().build();
    }
}
