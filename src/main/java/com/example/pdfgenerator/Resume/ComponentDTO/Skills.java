package com.example.pdfgenerator.Resume.ComponentDTO;

import java.util.List;

public record Skills(
        String title,
        List<String> skills
) {
}

