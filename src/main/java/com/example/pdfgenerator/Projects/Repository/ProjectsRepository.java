package com.example.pdfgenerator.Projects.Repository;

import com.example.pdfgenerator.Projects.Model.Projects;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectsRepository extends JpaRepository<Projects,Long> {
}
