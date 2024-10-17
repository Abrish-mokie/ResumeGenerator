package com.example.pdfgenerator.Certification.Service.Mapper;

import com.example.pdfgenerator.Certification.DTO.RequestCertificateDTO;
import com.example.pdfgenerator.Certification.DTO.ResponseCertificateDTO;
import com.example.pdfgenerator.Certification.Model.Certificates;
import com.example.pdfgenerator.User.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CertificateMapper {

    private final UserRepository repo;


    public Certificates toCertificate(RequestCertificateDTO dto){
        return Certificates.builder()
                .user(repo.findById(dto.userId()).orElseThrow())
                .description(dto.Description())
                .build();
    }

    public ResponseCertificateDTO fromCertificate(Certificates model){
        return new ResponseCertificateDTO(model.getId(),model.getDescription());
    }
}
