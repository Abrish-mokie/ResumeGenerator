package com.example.pdfgenerator.Projects.DTO;


import java.util.List;

public record ResponseProjectDTO(
        String title,
        String description,
        List<String> projectObjectives
) {
}
