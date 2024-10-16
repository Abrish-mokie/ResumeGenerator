package com.example.pdfgenerator.Description.Controller;


import com.example.pdfgenerator.Description.DTO.DescriptionDTORequest;
import com.example.pdfgenerator.Description.DTO.DescriptionDTOResponse;
import com.example.pdfgenerator.Description.Model.Description;
import com.example.pdfgenerator.Description.Service.DescriptionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/description")
@RequiredArgsConstructor
@Tag(name = "Description", description = "For managing resume descriptions field")
public class DescriptionController {

    private final DescriptionService descriptionService;

    @PostMapping("/save")
    public ResponseEntity<Void> save(DescriptionDTORequest dto){
        descriptionService.save(dto);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<DescriptionDTOResponse> get(@PathVariable Long id){
        return ResponseEntity.ok(descriptionService.getById(id));
    }

    @GetMapping("/get")
    public ResponseEntity<List<DescriptionDTOResponse>> getAll(){
        return ResponseEntity.ok(descriptionService.getAll());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        descriptionService.delete(id);
        return ResponseEntity.ok().build();
    }


}

