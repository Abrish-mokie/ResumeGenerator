package com.example.pdfgenerator.Description.Model;


import com.example.pdfgenerator.User.Model.Candidate;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Description {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(
            name = "user_id"
    )
    @JsonBackReference
    private Candidate user;

    @Column(columnDefinition = "TEXT")
    private String description;

}
