package com.example.pdfgenerator.Resume.Controller;

import com.example.pdfgenerator.Resume.Service.PDFCreation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/resume")
@RequiredArgsConstructor
@Tag(name = "Resume", description = "For creating statically generated resume based on stored data")
public class ResumeController {

    private final PDFCreation creation;

    @GetMapping("/static")
    public ResponseEntity<Void> staticResume(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");

        String headerKey = "content-Disposition";
        String headerValue = "attachment; filename=resume_testing.pdf";
        response.setHeader(headerKey,headerValue);
        creation.staticResume(response);
        return ResponseEntity.ok().build();
    }
}
