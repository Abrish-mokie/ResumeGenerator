package com.example.pdfgenerator.Experience.DTO;

import java.util.List;

public record ResponseExprienceDTO(
        Long id,
        String title,
        String subTitle,
        String location,
        String duration,
        List<String> responsibilities
) {
}
