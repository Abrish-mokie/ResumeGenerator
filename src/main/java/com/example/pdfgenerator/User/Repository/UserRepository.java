package com.example.pdfgenerator.User.Repository;

import com.example.pdfgenerator.User.Model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Candidate,Long> {

    Optional<Candidate> findByName(String name);
}
