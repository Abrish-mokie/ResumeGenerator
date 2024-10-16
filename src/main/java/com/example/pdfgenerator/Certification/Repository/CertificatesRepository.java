package com.example.pdfgenerator.Certification.Repository;

import com.example.pdfgenerator.Certification.Model.Certificates;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificatesRepository extends JpaRepository<Certificates,Long> {
}
