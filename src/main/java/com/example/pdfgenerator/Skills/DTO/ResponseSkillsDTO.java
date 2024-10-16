package com.example.pdfgenerator.Skills.DTO;

import java.util.List;

public record ResponseSkillsDTO(
        String title,
        List<String> skills
) {
}
