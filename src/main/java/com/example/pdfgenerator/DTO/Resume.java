package com.example.pdfgenerator.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@NoArgsConstructor
public class Resume {

    private Description description;
    private List<Skills> skills;
    private List<ProfessionalExprience> professionalExperiences;
    private List<Certification> certifications;
    private List<Projects> projects;
    private List<Education> educations;
}
