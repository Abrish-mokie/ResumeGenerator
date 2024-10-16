package com.example.pdfgenerator.Resume.ComponentDTO;

import java.util.List;

public record Project(
        String title,
        String description,
        List<String> projectObjectives
) {
}

