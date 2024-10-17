package com.example.pdfgenerator.Resume.ComponentDTO;

import java.util.List;

public record Exprience(
        String title,
        String subTitle,
        String location,
        String duration,
        List<String> responsibilities
) {
}
