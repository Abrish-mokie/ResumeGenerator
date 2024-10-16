package com.example.pdfgenerator.Experience.DTO;

import java.util.List;

public record RequestExprienceDTO(
        Long userId,
        String userName,
        String title,
        String subTitle,
        List<String> responsibilities
) {
}
