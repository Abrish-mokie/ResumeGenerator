package com.example.pdfgenerator.Experience.DTO;

import java.util.List;

public record ResponseExprienceDTO(
        String title,
        String subTitle,
        List<String> responsibilities
) {
}
