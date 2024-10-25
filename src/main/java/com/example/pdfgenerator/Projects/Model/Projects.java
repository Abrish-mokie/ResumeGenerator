package com.example.pdfgenerator.Projects.Model;


import com.example.pdfgenerator.User.Model.Candidate;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Setter
@Getter
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

    @Column(length = 10240)
    private String description;

    private String duration;

    @ElementCollection
    private List<String> projectObjectives;


}
