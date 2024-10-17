package com.example.pdfgenerator.User.Service.Mapper;

import com.example.pdfgenerator.Certification.Service.Mapper.CertificateMapper;
import com.example.pdfgenerator.Description.Service.Mapper.DescriptionMappers;
import com.example.pdfgenerator.Education.Service.Mapper.EducationMapper;
import com.example.pdfgenerator.Experience.Service.Mapper.ExprienceMapper;
import com.example.pdfgenerator.Projects.Service.Mapper.ProjectMapper;
import com.example.pdfgenerator.Skills.Service.Mapper.SkillsMapper;
import com.example.pdfgenerator.User.DTO.RequestUserDTO;
import com.example.pdfgenerator.User.DTO.ResponseUserDTO;
import com.example.pdfgenerator.User.Model.Candidate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserMapper {

    private final DescriptionMappers descriptionMappers;
    private final SkillsMapper skillsMapper;
    private final ExprienceMapper exprienceMapper;
    private final CertificateMapper certificateMapper;
    private final ProjectMapper projectMapper;
    private final EducationMapper educationMapper;

    public Candidate toUser(RequestUserDTO dto){
        return Candidate.builder()
                .name(dto.name())
                .title(dto.title())
                .email(dto.email())
                .phoneNumber(dto.phoneNumber())
                .address(dto.address())
                .linkedIn(dto.linkedIn())
                .website(dto.website())
                .build();
    }

    public ResponseUserDTO fromUser(Candidate user){
        return new ResponseUserDTO(
                user.getId(),
                user.getName(),
                user.getTitle(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getAddress(),
                user.getLinkedIn(),
                user.getWebsite(),
                descriptionMappers.fromDescription(user.getDescription()),
                user.getSkills().stream().map(skillsMapper::fromSkills).toList(),
                user.getExperiences().stream().map(exprienceMapper::fromExprience).toList(),
                user.getCertificates().stream().map(certificateMapper::fromCertificate).toList(),
                user.getProjects().stream().map(projectMapper::fromProjects).toList(),
                user.getEducations().stream().map(educationMapper::fromEducation).toList()

        );
    }
}
