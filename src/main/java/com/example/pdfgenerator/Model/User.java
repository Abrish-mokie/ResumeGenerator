package com.example.pdfgenerator.Model;


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
public class User {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    private String name;

    private String title;

    @OneToOne(
            mappedBy = "user"
    )
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
