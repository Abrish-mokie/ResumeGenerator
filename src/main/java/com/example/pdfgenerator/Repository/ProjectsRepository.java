package com.example.pdfgenerator.Repository;

import com.example.pdfgenerator.Model.Projects;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectsRepository extends JpaRepository<Projects,Long> {
}
