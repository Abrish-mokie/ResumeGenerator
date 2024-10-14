package com.example.pdfgenerator.DTO;

import java.util.List;

public record ProfessionalExprience(
        String userName,
        String title,
        String subTitle,
        List<String> responsibilities
) {
}
