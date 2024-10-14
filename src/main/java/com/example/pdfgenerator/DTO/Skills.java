package com.example.pdfgenerator.DTO;

import java.util.List;

public record Skills(
        String title,
        List<String> skills
) {
}
