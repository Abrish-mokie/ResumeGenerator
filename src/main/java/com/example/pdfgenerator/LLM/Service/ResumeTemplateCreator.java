package com.example.pdfgenerator.LLM.Service;


import com.example.pdfgenerator.Resume.ComponentDTO.*;
import com.example.pdfgenerator.Resume.DTO.Resume;
import com.example.pdfgenerator.Resume.Service.PDFCreation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class ResumeTemplateCreator {

    private final PDFCreation creation;

    @Value("classpath:/prompts/Resume_Template.st")
    private Resource resumeTemplate;

    public String resumeTemplate(){
        Resume resume = creation.buildResume();
        Map<String,Object> insertion = new HashMap<>();
        insertion.put("name",resume.user().name());
        insertion.put("email",resume.user().email());
        insertion.put("Address",resume.user().address());
        insertion.put("linkedIn",resume.user().linkedIn());
        insertion.put("website",resume.user().website());
        insertion.put("Title",resume.user().title());
        insertion.put("Description",resume.description().Description());
        insertion.put("skills",generateSkills(resume.skills()));
        insertion.put("proffesional_Exprience",generateProfessionalExprience(resume.expriences()));
        insertion.put("projects",generateProject(resume.projects()));
        insertion.put("certification",generateCertification(resume.certifications()));
        insertion.put("education",generateEducation(resume.educations()));

        PromptTemplate template = new PromptTemplate(resumeTemplate);

        String resumeGenerated = template.render(insertion);

        log.info(resumeGenerated);

        return resumeGenerated;
    }

    public String resumeTemplateFromResume(Resume resume){
        Map<String,Object> insertion = new HashMap<>();
        insertion.put("name",resume.user().name());
        insertion.put("email",resume.user().email());
        insertion.put("Address",resume.user().address());
        insertion.put("linkedIn",resume.user().linkedIn());
        insertion.put("website",resume.user().website());
        insertion.put("Title",resume.user().title());
        insertion.put("Description",resume.description().Description());
        insertion.put("skills",generateSkills(resume.skills()));
        insertion.put("proffesional_Exprience",generateProfessionalExprience(resume.expriences()));
        insertion.put("projects",generateProject(resume.projects()));
        insertion.put("certification",generateCertification(resume.certifications()));
        insertion.put("education",generateEducation(resume.educations()));

        PromptTemplate template = new PromptTemplate(resumeTemplate);

        String resumeGenerated = template.render(insertion);

        log.info(resumeGenerated);

        return resumeGenerated;
    }

    public String generateSkills(List<Skills> skills){
        StringBuilder skillSet = new StringBuilder();
        for(Skills skill: skills){
            skillSet.append("• ").append(skill.title()).append(": ").append(generateSkillsHelper(skill.skills())).append("\n");
        }

        return skillSet.toString();
    }

    public String generateSkillsHelper(List<String> skill){
        StringBuilder skills = new StringBuilder();
        for(String skillItem: skill){
            skills.append(skillItem).append(", ");
        }

        return skills.toString();
    }

    public String generateProfessionalExprience(List<Exprience> expriences){
        StringBuilder experience = new StringBuilder();
        for(Exprience exp: expriences){
            experience.append(exp.title()).append(":-\n").append(exp.location()).append("\n")
                    .append(exp.duration()).append("\n").append(exp.subTitle()).append("\n")
                    .append(bulletPointList(exp.responsibilities())).append("\n\n");
        }

        return experience.toString();
    }

    public String generateProject(List<Project> projects){
        StringBuilder project = new StringBuilder();
        for(Project prj: projects){
            project.append(prj.title()).append("\n").append(prj.duration()).append("\n").append(prj.description())
                    .append("\n").append(bulletPointList(prj.projectObjectives())).append("\n\n");
        }

        return project.toString();
    }

    public String generateCertification(List<Certification> certifications){
        StringBuilder certificatesBullet = new StringBuilder();
        for(Certification cert: certifications){
            certificatesBullet.append("• ").append(cert.description()).append("\n");
        }
        return certificatesBullet.toString();
    }

    public String generateEducation(List<Education> educations){
        StringBuilder education = new StringBuilder();
        for(Education edu: educations){
            education.append(edu.title()).append("\n").append(edu.description());
        }

        return education.toString();
    }

    public String bulletPointList(List<?> lists){
        StringBuilder bulletList = new StringBuilder();
        for(Object list: lists){
            bulletList.append("• ").append(list).append("\n");
        }

        return bulletList.toString();
    }

}
