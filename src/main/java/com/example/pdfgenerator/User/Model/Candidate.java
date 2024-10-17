package com.example.pdfgenerator.User.Model;


import com.example.pdfgenerator.Certification.Model.Certificates;
import com.example.pdfgenerator.Description.Model.Description;
import com.example.pdfgenerator.Education.Model.Education;
import com.example.pdfgenerator.Experience.Model.Experiences;
import com.example.pdfgenerator.Projects.Model.Projects;
import com.example.pdfgenerator.Skills.Model.Skills;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Candidate {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Column(unique = true)
    private String name;

    private String title;

    private String email;

    private String phoneNumber;

    private String address;

    private String linkedIn;

    private String website;

    @OneToOne(
            mappedBy = "user"
    )
    @JsonManagedReference
    private Description description;

    @OneToMany(
            mappedBy = "user"
    )
    @JsonManagedReference
    private List<Skills> skills;

    @OneToMany(
            mappedBy = "user"
    )
    @JsonManagedReference
    private List<Experiences> experiences;

    @OneToMany(
            mappedBy = "user"
    )
    @JsonManagedReference
    private List<Certificates> certificates;

    @OneToMany(
            mappedBy = "user"
    )
    @JsonManagedReference
    private List<Projects> projects;

    @OneToMany(
            mappedBy = "user"
    )
    @JsonManagedReference
    private List<Education> educations;

}
