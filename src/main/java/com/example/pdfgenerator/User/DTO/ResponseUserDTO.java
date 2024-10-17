package com.example.pdfgenerator.User.DTO;

import com.example.pdfgenerator.Certification.DTO.ResponseCertificateDTO;
import com.example.pdfgenerator.Description.DTO.DescriptionDTOResponse;
import com.example.pdfgenerator.Education.DTO.ResponseEducationDTO;
import com.example.pdfgenerator.Experience.DTO.ResponseExprienceDTO;
import com.example.pdfgenerator.Projects.DTO.ResponseProjectDTO;
import com.example.pdfgenerator.Skills.DTO.ResponseSkillsDTO;

import java.util.List;

public record ResponseUserDTO(
        Long id,
        String name,
        String title,
        String email,
        String phoneNumber,
        String address,
        String linkedIn,
        String website,
        DescriptionDTOResponse descriptionDTOResponse,
        List<ResponseSkillsDTO> responseSkillsDTO,
        List<ResponseExprienceDTO> responseExprienceDTO,
        List<ResponseCertificateDTO> responseCertificateDTO,
        List<ResponseProjectDTO> responseProjectDTO,
        List<ResponseEducationDTO> responseEducationDTO
) {
}
