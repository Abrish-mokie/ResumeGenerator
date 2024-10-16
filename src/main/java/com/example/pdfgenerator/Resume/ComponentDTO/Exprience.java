package com.example.pdfgenerator.Resume.ComponentDTO;

import java.util.List;

public record Exprience(
        String userName,
        String title,
        String subTitle,
        List<String> responsibilities
) {
}
