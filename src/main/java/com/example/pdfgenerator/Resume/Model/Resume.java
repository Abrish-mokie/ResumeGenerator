package com.example.pdfgenerator.Resume.Model;

import com.example.pdfgenerator.Resume.ComponentDTO.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@NoArgsConstructor
public class Resume {

    private User user;
    private Description description;
    private List<Skills> skills;
    private List<Exprience> professionalExperiences;
    private List<Certification> certifications;
    private List<Project> projects;
    private List<Education> educations;
}
