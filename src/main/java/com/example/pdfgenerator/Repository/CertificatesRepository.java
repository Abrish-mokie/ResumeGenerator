package com.example.pdfgenerator.Repository;

import com.example.pdfgenerator.Model.Certificates;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificatesRepository extends JpaRepository<Certificates,Long> {
}
