package com.example.pdfgenerator.Skills.Controller;


import com.example.pdfgenerator.Skills.DTO.RequestSkillsDTO;
import com.example.pdfgenerator.Skills.DTO.ResponseSkillsDTO;
import com.example.pdfgenerator.Skills.Service.SkillsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/skills")
@RestController
@RequiredArgsConstructor
@Tag(name = "Skills Controller", description = "For managing Skills entries")
public class SkillsController {

    private final SkillsService skillsService;

    @PostMapping("/save")
    public ResponseEntity<Void> save(RequestSkillsDTO dto){
        skillsService.save(dto);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ResponseSkillsDTO> get(@PathVariable Long id){
        return ResponseEntity.ok(skillsService.getById(id));
    }

    @GetMapping("/get")
    public ResponseEntity<List<ResponseSkillsDTO>> getAll(){
        return ResponseEntity.ok(skillsService.getAll());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        skillsService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/patch/{id}")
    public ResponseEntity<Void> patch(@PathVariable Long id,@RequestBody Map<String,Object> value){
        skillsService.patch(id,value);
        return ResponseEntity.ok().build();
    }
}
