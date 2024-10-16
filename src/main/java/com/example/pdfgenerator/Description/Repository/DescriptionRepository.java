package com.example.pdfgenerator.Description.Repository;

import com.example.pdfgenerator.Description.Model.Description;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DescriptionRepository extends JpaRepository<Description,Long> {
}
