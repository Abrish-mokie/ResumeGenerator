package com.example.pdfgenerator.Resume.Service.Mapper;

import com.example.pdfgenerator.Certification.DTO.ResponseCertificateDTO;
import com.example.pdfgenerator.Certification.Service.Mapper.CertificateMapper;
import com.example.pdfgenerator.Description.DTO.DescriptionDTOResponse;
import com.example.pdfgenerator.Description.Service.Mapper.DescriptionMappers;
import com.example.pdfgenerator.Education.DTO.ResponseEducationDTO;
import com.example.pdfgenerator.Education.Service.EducationService;
import com.example.pdfgenerator.Experience.DTO.ResponseExprienceDTO;
import com.example.pdfgenerator.Experience.Service.Mapper.ExprienceMapper;
import com.example.pdfgenerator.Projects.DTO.ResponseProjectDTO;
import com.example.pdfgenerator.Projects.Service.Mapper.ProjectMapper;
import com.example.pdfgenerator.Resume.ComponentDTO.*;
import com.example.pdfgenerator.Skills.DTO.ResponseSkillsDTO;
import com.example.pdfgenerator.Skills.Service.Mapper.SkillsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResumeMapper {


    public Description toDescription(DescriptionDTOResponse dto){
        return new Description(dto.Description());
    }

    public Skills toSkills(ResponseSkillsDTO dto){
        return new Skills(dto.title(),dto.skills());
    }

    public Exprience toExperience(ResponseExprienceDTO dto){
        return new Exprience(dto.title(),dto.subTitle(),dto.location(),dto.duration(),dto.responsibilities());
    }

    public Certification toCertificate(ResponseCertificateDTO dto){
        return new Certification(dto.description());
    }

    public Project toProject(ResponseProjectDTO dto){
        return new Project(dto.title(),dto.description(),dto.projectObjectives());
    }

    public Education toEducation(ResponseEducationDTO dto){
        return new Education(dto.title(),dto.description());
    }


}
