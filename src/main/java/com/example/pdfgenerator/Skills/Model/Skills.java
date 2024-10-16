package com.example.pdfgenerator.Skills.Model;

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
public class Skills {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    @ManyToOne
    @JoinColumn(
            name = "user_id"
    )
    @JsonBackReference
    private Candidate user;

    @ElementCollection
    private List<String> skills;

}
