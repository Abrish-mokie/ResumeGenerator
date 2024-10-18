package com.example.pdfgenerator.Experience.Controller;

import com.example.pdfgenerator.Experience.DTO.RequestExprienceDTO;
import com.example.pdfgenerator.Experience.DTO.ResponseExprienceDTO;
import com.example.pdfgenerator.Experience.Service.ExprienceService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/experience")
@RestController
@RequiredArgsConstructor
@Tag(name = "Experience Controller", description = "For managing experience entries")
public class ExprienceController {

    private final ExprienceService exprienceService;

    @PostMapping("/save")
    public ResponseEntity<Void> save(RequestExprienceDTO dto){
        exprienceService.save(dto);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ResponseExprienceDTO> get(@PathVariable Long id){
        return ResponseEntity.ok(exprienceService.getById(id));
    }

    @GetMapping("/get")
    public ResponseEntity<List<ResponseExprienceDTO>> getAll(){
        return ResponseEntity.ok(exprienceService.getAll());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        exprienceService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/patch/{id}")
    public ResponseEntity<Void> patch(@PathVariable Long id,@RequestBody Map<String,Object> value){
        exprienceService.patch(id,value);
        return ResponseEntity.ok().build();
    }
}