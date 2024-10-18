package com.example.pdfgenerator.Certification.Controller;

import com.example.pdfgenerator.Certification.DTO.RequestCertificateDTO;
import com.example.pdfgenerator.Certification.DTO.ResponseCertificateDTO;
import com.example.pdfgenerator.Certification.Service.CertificateService;
import com.example.pdfgenerator.Description.DTO.DescriptionDTORequest;
import com.example.pdfgenerator.Description.DTO.DescriptionDTOResponse;
import com.example.pdfgenerator.Description.Service.DescriptionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/certificate")
@RequiredArgsConstructor
@Tag(name = "Certificate", description = "For managing resume certificate entries")
public class CertificateController {

    private final CertificateService certificateService;

    @PostMapping("/save")
    public ResponseEntity<Void> save(RequestCertificateDTO dto){
        certificateService.save(dto);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ResponseCertificateDTO> get(@PathVariable Long id){
        return ResponseEntity.ok(certificateService.getById(id));
    }

    @GetMapping("/get")
    public ResponseEntity<List<ResponseCertificateDTO>> getAll(){
        return ResponseEntity.ok(certificateService.getAll());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        certificateService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/patch/{id}")
    public ResponseEntity<Void> patch(@PathVariable Long id,@RequestBody Map<String,Object> value){
        certificateService.patch(id,value);
        return ResponseEntity.ok().build();
    }

}
