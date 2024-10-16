package com.example.pdfgenerator.Skills.DTO;

import java.util.List;

public record RequestSkillsDTO(
        Long userId,
        String title,
        List<String> skills
) {
}
