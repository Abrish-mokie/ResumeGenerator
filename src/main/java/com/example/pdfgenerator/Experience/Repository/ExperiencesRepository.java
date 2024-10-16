package com.example.pdfgenerator.Experience.Repository;

import com.example.pdfgenerator.Experience.Model.Experiences;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExperiencesRepository extends JpaRepository<Experiences,Long> {
}
