package com.example.pdfgenerator.Resume.Service;

import com.example.pdfgenerator.Certification.Service.CertificateService;
import com.example.pdfgenerator.Description.Service.DescriptionService;
import com.example.pdfgenerator.Education.Service.EducationService;
import com.example.pdfgenerator.Experience.Service.ExprienceService;
import com.example.pdfgenerator.Projects.Service.ProjectServices;
import com.example.pdfgenerator.Resume.ComponentDTO.*;
import com.example.pdfgenerator.Resume.DTO.Resume;
import com.example.pdfgenerator.Resume.Service.Mapper.ResumeMapper;
import com.example.pdfgenerator.Skills.Service.SkillsService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PDFCreation {

    private final ResumeMapper mapper;
    private final DescriptionService descriptionService;
    private final SkillsService skillsService;
    private final ExprienceService exprienceService;
    private final CertificateService certificateService;
    private final ProjectServices projectServices;
    private final EducationService educationService;


    public void staticResume(HttpServletResponse response) throws IOException {
        Resume resume = buildResume();
        PdfGeneratorService generator = new PdfGeneratorService(resume);
        generator.export(response);

    }

    public Resume buildResume(){

        List<Description> description = descriptionService.getAll().stream().map(mapper::toDescription).toList();
        List<Skills> skills = skillsService.getAll().stream().map(mapper::toSkills).toList();
        List<Exprience> exprience = exprienceService.getAll().stream().map(mapper::toExperience).toList();
        List<Certification> certifications = certificateService.getAll().stream().map(mapper::toCertificate).toList();
        List<Project> projects = projectServices.getAll().stream().map(mapper::toProject).toList();
        List<Education> educations = educationService.getAll().stream().map(mapper::toEducation).toList();

        return new Resume(
                description.getFirst(),
                skills,
                exprience,
                certifications,
                projects,
                educations
        );


    }


}
