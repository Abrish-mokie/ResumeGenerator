package com.example.pdfgenerator.Resume.Service;

import com.example.pdfgenerator.Resume.ComponentDTO.*;
import com.example.pdfgenerator.Resume.DTO.Resume;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.*;
import com.lowagie.text.FontFactory;
import com.lowagie.text.ListItem;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.draw.LineSeparator;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Data
public class PdfGeneratorService {

    public PdfGeneratorService() {
        this.description = new Description("");
        this.experiences = new ArrayList<>();
        this.skills = new ArrayList<>();
        this.projects = new ArrayList<>();
        this.certifications = new ArrayList<>();
        this.education = new ArrayList<>();
    }

    public PdfGeneratorService(
            Description description,
            List<com.example.pdfgenerator.Resume.ComponentDTO.Skills> skills,
            List<Exprience> expriences,
            List<Certification> certifications,
            List<Project> projects,
            List<Education> educations
    ){
        this.description = description;
        this.skills = skills;
        this.experiences = expriences;
        this.certifications = certifications;
        this.projects = projects;
        this.education = educations;
    }

    public PdfGeneratorService(Resume resume){
        this.description = resume.description();
        this.skills = resume.skills();
        this.experiences = resume.expriences();
        this.certifications = resume.certifications();
        this.projects = resume.projects();
        this.education = resume.educations();
    }


    private final List<Exprience> experiences ;

    private final List<Skills> skills ;

    private final List<Project> projects ;

    private final List<Certification> certifications ;

    private final List<Education> education ;

    private final Description description;


    public void export(HttpServletResponse response) throws IOException {

        Document resume = new Document(PageSize.A4);
        PdfWriter.getInstance(resume,response.getOutputStream());

        resume.open();
        Font fontTitle = FontFactory.getFont(FontFactory.TIMES_BOLD,18);
        Font fontSubTitle = FontFactory.getFont(FontFactory.TIMES,12);
        Font fontSubTitleBold = FontFactory.getFont(FontFactory.TIMES_BOLD,16);
        Font paragraphFont = FontFactory.getFont(FontFactory.TIMES, 12);

        Paragraph paragraph = new Paragraph("Abraham Afewerki",fontTitle);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);
        paragraph.setSpacingBefore(30);

        Font fontAnchor = FontFactory.getFont(FontFactory.TIMES,12, new Color(0,0,255));


        Chunk email = new Chunk("Email: abrish.mokie@gmail.com",fontAnchor);
        email.setAnchor("mailto:abrish.mokie@gmail.com");
        Paragraph emailParagraph = new Paragraph(email);
        emailParagraph.setAlignment(Paragraph.ALIGN_CENTER);

        Chunk address = new Chunk("San Antonio, TX United States",fontSubTitle);
        Paragraph addressParagraph = new Paragraph(address);
        addressParagraph.setAlignment(Paragraph.ALIGN_CENTER);

        Chunk jobTitle = new Chunk("Software Engineer | Full Stack Developer",fontSubTitleBold);
        Paragraph jobTitleParagraph = new Paragraph(jobTitle);
        jobTitleParagraph.setAlignment(Paragraph.ALIGN_CENTER);
        jobTitleParagraph.setSpacingAfter(30);


        Chunk website = new Chunk("Personal Website",fontAnchor);
        website.setAnchor("https://abrishmokie.com");
        Paragraph websiteParagaraph = new Paragraph(website);
        websiteParagaraph.setAlignment(Paragraph.ALIGN_CENTER);

        LineSeparator divider = new LineSeparator();
        divider.setLineWidth(1);

        Paragraph spacerParagraphBefore = new Paragraph();
        spacerParagraphBefore.setSpacingBefore(30);

        Paragraph spacerParagraphAfter = new Paragraph();
        spacerParagraphAfter.setSpacingBefore(30);


        Paragraph intro = new Paragraph(this.description.Description(),paragraphFont);
        intro.setSpacingAfter(30);


        resume.add(paragraph);
        resume.add(emailParagraph);
        resume.add(addressParagraph);
        resume.add(websiteParagaraph);
        resume.add(jobTitleParagraph);
        resume.add(divider);
        resume.add(getSubTitleBold("Introduction"));
        resume.add(intro);
        resume.add(divider);
        resume.add(getSubTitleBold("Skills"));
        for (Skills skill: this.skills) {
            skillsGenerator(resume,skill);
        }
        resume.add(spacerParagraphAfter);
        resume.add(divider);
        resume.add(getSubTitleBold("Professional Expreince: "));
        for (Exprience expr: this.experiences){
            professionalExprienceGenerator(resume,fontSubTitle,expr,spacerParagraphBefore,spacerParagraphAfter);
        }
        resume.add(divider);
        resume.add(getSubTitleBold("Projects"));
        for(Project projects: this.projects){
            projectsGenerator(resume,projects);
        }
        resume.add(spacerParagraphBefore);
        resume.add(divider);
        resume.add(getSubTitleBold("Certification"));
        setCertificationsGenerator(resume,certifications);
        resume.add(spacerParagraphBefore);
        resume.add(divider);
        resume.add(spacerParagraphAfter);
        educationGenerator(resume,education);
        resume.close();
    }

    private Paragraph listOfskills(java.util.List<String> list,String type){
        Font typeOfSkillFont = new Font(FontFactory.getFont(FontFactory.TIMES_BOLD,14));
        Font skills = new Font(FontFactory.getFont(FontFactory.TIMES,12));
        Chunk typeOfSkill = new Chunk(type,typeOfSkillFont);
        Paragraph listOfSkills = new Paragraph();
        listOfSkills.add("  ");
        listOfSkills.add(typeOfSkill);
        listOfSkills.add(": ");
        listOfSkills.add(new Chunk(list.getFirst(),skills));
        for(int i = 1; i < list.size(); i++){
            listOfSkills.add(", ");
            listOfSkills.add(new Chunk(list.get(i),skills));
        }
        return listOfSkills;
    }

    private static Paragraph getSubTitleBold(String subTitle){
        Font fontSubTitleBold = FontFactory.getFont(FontFactory.TIMES_BOLD,16);
        Paragraph subTitleParagraph = new Paragraph(subTitle, fontSubTitleBold);
        subTitleParagraph.setSpacingBefore(10);
        subTitleParagraph.setSpacingAfter(10);

        return subTitleParagraph;
    }

    private void professionalExprienceGenerator(Document doc, Font normalFont, Exprience professionalExprience, Paragraph topSpacer, Paragraph buttomSpacer){
        Font fontTitle = FontFactory.getFont(FontFactory.TIMES_BOLD,14);
        Paragraph title = new Paragraph(professionalExprience.title(),fontTitle);
        Paragraph subTitle = new Paragraph(professionalExprience.subTitle(),normalFont);
        subTitle.setSpacingAfter(8);
        doc.add(title);
        doc.add(subTitle);
        doc.add(professionalExprienceResponsibilitiesList(professionalExprience.responsibilities()));
        doc.add(buttomSpacer);

    }

    private com.lowagie.text.List professionalExprienceResponsibilitiesList(List<String> responsibilities){
        com.lowagie.text.List responsibilitiesList = new com.lowagie.text.List(com.lowagie.text.List.UNORDERED);
        responsibilitiesList.setListSymbol("\t•   ");
        responsibilitiesList.setIndentationLeft(20);
        for (String respon: responsibilities){
            responsibilitiesList.add(new ListItem(new Chunk(respon,new Font(FontFactory.getFont(FontFactory.TIMES, 12)))));
        }
        return responsibilitiesList;
    }

    private void skillsGenerator(Document doc, Skills skillSet){
            Paragraph skillListSpacer = new Paragraph();
            skillListSpacer.setSpacingAfter(8);
            com.lowagie.text.List language = new com.lowagie.text.List(com.lowagie.text.List.UNORDERED);
            language.setListSymbol("• ");
            language.add(new ListItem(listOfskills(skillSet.skills(), skillSet.title())));
            doc.add(language);
            doc.add(skillListSpacer);


    }

    private void projectsGenerator(Document doc, Project projects){
        Paragraph spacerParagraphBefore = new Paragraph();
        spacerParagraphBefore.setSpacingBefore(10);
        Font fontTitle = FontFactory.getFont(FontFactory.TIMES_BOLD,14);
        Font fontDescription = FontFactory.getFont(FontFactory.TIMES,12);
        Paragraph title = new Paragraph(projects.title(),fontTitle);
        Paragraph description = new Paragraph(projects.description(),fontDescription);
        description.setSpacingAfter(10);
        description.setSpacingBefore(10);
        description.setIndentationLeft(20);
        doc.add(spacerParagraphBefore);
        doc.add(title);
        doc.add(description);
        doc.add(professionalExprienceResponsibilitiesList(projects.projectObjectives()));
    }

    private void setCertificationsGenerator(Document doc, List<Certification> certifications){
        Font certsFont = new Font(FontFactory.getFont(FontFactory.TIMES,12));
        com.lowagie.text.List certList = new com.lowagie.text.List(com.lowagie.text.List.UNORDERED);
        certList.setIndentationLeft(20);
        certList.setListSymbol("• ");
        for(Certification cert: certifications){
            certList.add(new ListItem(new Paragraph(cert.description(),certsFont)));
        }
        doc.add(certList);
    }

    private void educationGenerator(Document doc, List<Education> eds){
        Font educationTitle = new Font(FontFactory.getFont(FontFactory.TIMES_BOLD, 16));
        Paragraph education = new Paragraph("Education",educationTitle);
        education.setSpacingAfter(10);
        Font edTitle = new Font(FontFactory.getFont(FontFactory.TIMES_BOLD, 12));
        Font edDescription = new Font(FontFactory.getFont(FontFactory.TIMES,12));
        doc.add(education);
        for(Education ed:eds) {
            Paragraph title = new Paragraph(ed.title(), edTitle);
            Paragraph description = new Paragraph(ed.description(), edDescription);
            doc.add(title);
            doc.add(description);
        }
    }

}
