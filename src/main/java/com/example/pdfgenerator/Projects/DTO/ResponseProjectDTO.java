package com.example.pdfgenerator.Projects.DTO;


import java.util.List;

public record ResponseProjectDTO(
        Long id,
        String title,
        String description,
        String duration,
        List<String> projectObjectives
) {
}
