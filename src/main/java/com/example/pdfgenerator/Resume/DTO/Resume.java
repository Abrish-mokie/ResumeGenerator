package com.example.pdfgenerator.Resume.DTO;

import com.example.pdfgenerator.Resume.ComponentDTO.*;

import java.util.List;

public record Resume(
        User user,
        Description description,
        List<Skills> skills,
        List<Exprience> expriences,
        List<Certification> certifications,
        List<Project> projects,
        List<Education> educations
) {
}
