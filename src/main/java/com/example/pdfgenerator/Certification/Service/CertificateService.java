package com.example.pdfgenerator.Certification.Service;

import com.example.pdfgenerator.Certification.DTO.RequestCertificateDTO;
import com.example.pdfgenerator.Certification.DTO.ResponseCertificateDTO;
import com.example.pdfgenerator.Certification.Repository.CertificatesRepository;
import com.example.pdfgenerator.Certification.Service.Mapper.CertificateMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CertificateService {

    private final CertificatesRepository repo;
    private final CertificateMapper mapper;

    public void save(RequestCertificateDTO dto){
        repo.save(mapper.toCertificate(dto));
    }

    public List<ResponseCertificateDTO> getAll(){
        return repo.findAll().stream().map(mapper::fromCertificate).toList();
    }

    public ResponseCertificateDTO getById(Long id){
        return mapper.fromCertificate(repo.getReferenceById(id));
    }

    public void delete(Long id){
        repo.deleteById(id);
    }

}
