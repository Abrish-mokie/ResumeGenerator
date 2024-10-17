package com.example.pdfgenerator.Projects.Model;


import com.example.pdfgenerator.User.Model.Candidate;
import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class Projects {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(
            name = "user_id"
    )
    @JsonBackReference
    private Candidate user;

    private String title;

    private String description;

    private String duration;

    @ElementCollection
    private List<String> projectObjectives;
}
