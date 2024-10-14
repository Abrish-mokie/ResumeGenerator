package com.example.pdfgenerator.DTO;

import java.util.List;

public record Projects(
        String title,
        String description,
        List<String> projectObjectives
) {
}
