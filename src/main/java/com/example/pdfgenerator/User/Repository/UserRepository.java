package com.example.pdfgenerator.User.Repository;

import com.example.pdfgenerator.User.Model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Candidate,Long> {

    Optional<Candidate> findByName(String name);
}
