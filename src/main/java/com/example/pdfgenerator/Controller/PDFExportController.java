package com.example.pdfgenerator.Controller;

import com.example.pdfgenerator.Experience.DTO.RequestExprienceDTO;
import com.example.pdfgenerator.Experience.Model.Experiences;
import com.example.pdfgenerator.Experience.Repository.ExperiencesRepository;
import com.example.pdfgenerator.Service.DataManipulation;
import com.example.pdfgenerator.Service.LLMService;
import com.example.pdfgenerator.Service.PdfGeneratorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Testing", description = "Used for general testing")
public class PDFExportController {

    private final PdfGeneratorService pdfGeneratorService;
    private final LLMService llmService;
    private final DataManipulation manipulation;
    private final ExperiencesRepository exprience;

    @GetMapping("/pdf/generate")
    public void GeneratePDF(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDateTime = dateFormat.format(new Date());

        String headerKey = "content-Disposition";
        String headerValue = "attachment; filename=resume_testing.pdf";
        response.setHeader(headerKey,headerValue);
        pdfGeneratorService.export(response);
    }


    @GetMapping("/testing")
    public void testing(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDateTime = dateFormat.format(new Date());

        String headerKey = "content-Disposition";
        String headerValue = "attachment; filename=resume_testing.pdf";
        response.setHeader(headerKey,headerValue);
        llmService.testing(response);
    }

    @GetMapping("/expriences/get")
    public ResponseEntity<List<Experiences>> getExpriences(){
        return ResponseEntity.ok(exprience.findAll());
    }

    @PostMapping("/expriences/post")
    public ResponseEntity postExprience(RequestExprienceDTO exprience){
        manipulation.postExprience(exprience);
        return ResponseEntity.status(201).build();
    }

}
