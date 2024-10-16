package com.example.pdfgenerator.Projects.DTO;

import java.util.List;

public record RequestProjectsDTO(
        Long userId,
        String title,
        String description,
        List<String> projectObjectives
) {
}
