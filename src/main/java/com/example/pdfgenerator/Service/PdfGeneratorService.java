package com.example.pdfgenerator.Service;

import com.example.pdfgenerator.DTO.*;
import com.lowagie.text.*;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.ListItem;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.draw.LineSeparator;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Data
public class PdfGeneratorService {



//    public PdfGeneratorService(){
//        this.resume = new Document(PageSize.A4);
//    }

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
            List<Skills> skills,
            List<ProfessionalExprience> professionalExperiences,
            List<Certification> certifications,
            List<Projects> projects,
            List<Education> educations
    ){
        this.description = description;
        this.skills = skills;
        this.experiences = professionalExperiences;
        this.certifications = certifications;
        this.projects = projects;
        this.education = educations;
    }

//
//    private final List<ProfessionalExprience> experiences = new ArrayList<>();
//
//    private final List<Skills> skills = new ArrayList<>();
//
//    private final List<Projects> projects = new ArrayList<>();
//
//    private final List<Certification> certifications = new ArrayList<>();
//
//    private final List<Education> education = new ArrayList<>();




    private final List<ProfessionalExprience> experiences ;

    private final List<Skills> skills ;

    private final List<Projects> projects ;

    private final List<Certification> certifications ;

    private final List<Education> education ;

    private final Description description;


    public void export(HttpServletResponse response) throws IOException {


// Test data
//        this.experiences.add(new ProfessionalExprience("Software Developer | Zara Shiping Trade","Asmara,Eritrea | Feb 2020 - Aug 2022"
//                ,new String[]{"Migrated monolithic applications to microservices, improving system modularity and performance by 40%.",
//        "Built REST APIs and implemented Spring JPA for efficient database interactions."}));
//
//        this.experiences.add(new ProfessionalExprience("Software Developer2 | Zara Shiping Trade","Asmara,Eritrea2 | Feb 2020 - Aug 2022"
//                ,new String[]{"Migrated monolithic applications to microservices, improving system modularity and performance by 40%.",
//                "Built REST APIs and implemented Spring JPA for efficient database interactions."}));
//
//        this.experiences.add(new ProfessionalExprience("Software Developer2 | Zara Shiping Trade","Asmara,Eritrea2 | Feb 2020 - Aug 2022"
//                ,new String[]{"Migrated monolithic applications to microservices, improving system modularity and performance by 40%.",
//                "Built REST APIs and implemented Spring JPA for efficient database interactions."}));
//
//        this.skills.add(new Skills("Language",new String[]{"Java","Python","JavaScript","Dart"}));
//        this.skills.add(new Skills("Language",new String[]{"Java","Python","JavaScript","Dart"}));
//        this.skills.add(new Skills("Language",new String[]{"Java","Python","JavaScript","Dart"}));
//
//        this.projects.add(new Projects("Food tracking system in Fridges (02/2022 – 05/2022)","Creating and tracking every item a user puts and removes from fridge. The aim of the project was to reduce food wastage created by forgetting food items.",
//                new String[]{"Programming language used: Arduino, python, PHP, MySQL","Programming tools used: Arduino IDE, PyCharm",
//                        "Hardware used: ESP32, microphone (for voice recognition), raspberry pi camera (for QR code scanning), pn580 (for scanning NFC tags), NFC tags, and raspberry pi (as our server)"}));
//
//        this.projects.add(new Projects("Food tracking system in Fridges (02/2022 – 05/2022)","Creating and tracking every item a user puts and removes from fridge. The aim of the project was to reduce food wastage created by forgetting food items.",
//                new String[]{"Programming language used: Arduino, python, PHP, MySQL","Programming tools used: Arduino IDE, PyCharm",
//                        "Hardware used: ESP32, microphone (for voice recognition), raspberry pi camera (for QR code scanning), pn580 (for scanning NFC tags), NFC tags, and raspberry pi (as our server)"}));
//
//        this.projects.add(new Projects("Food tracking system in Fridges (02/2022 – 05/2022)","Creating and tracking every item a user puts and removes from fridge. The aim of the project was to reduce food wastage created by forgetting food items.",
//                new String[]{"Programming language used: Arduino, python, PHP, MySQL","Programming tools used: Arduino IDE, PyCharm",
//                        "Hardware used: ESP32, microphone (for voice recognition), raspberry pi camera (for QR code scanning), pn580 (for scanning NFC tags), NFC tags, and raspberry pi (as our server)"}));
//
//
//        this.certifications.add(new Certification(1L,"JavaScript aloghrithms"));
//        this.certifications.add(new Certification(2L,"JavaScript aloghrithms"));
//
//        this.education.add(new Education("Bachelor of Science in Information Technology","Zayed University, Abu Dhabi, UAE | 2022"));
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



//        Paragraph intro = new Paragraph("Motivated and hardworking IT graduate from Zayed University. I am proud to mention that I have\n" +
//                "received full scholarship for my bachelor's degree. During my studies, I consistently achieved high\n" +
//                "grades and developed a versatile skill set. Moreover, love to tinker and learn new technologies to\n" +
//                "further enhance my IT skills through practical experience.",paragraphFont);
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
        for (ProfessionalExprience expr: this.experiences){
            professionalExprienceGenerator(resume,fontSubTitle,expr,spacerParagraphBefore,spacerParagraphAfter);
        }
        resume.add(divider);
        resume.add(getSubTitleBold("Projects"));
        for(Projects projects: this.projects){
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

    private void professionalExprienceGenerator(Document doc,  Font normalFont, ProfessionalExprience professionalExprience, Paragraph topSpacer, Paragraph buttomSpacer){
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

    private void projectsGenerator(Document doc, Projects projects){
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