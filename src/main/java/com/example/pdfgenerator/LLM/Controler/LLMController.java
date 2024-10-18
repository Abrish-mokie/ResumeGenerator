package com.example.pdfgenerator.LLM.Controler;

import com.example.pdfgenerator.LLM.DTO.ResponseLLMDTO;
import com.example.pdfgenerator.LLM.Service.LLMRepoServices;
import com.example.pdfgenerator.LLM.Service.LLMService;
import com.example.pdfgenerator.LLM.Service.ResumeTemplateCreator;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Testing", description = "Used for general testing")
public class LLMController {

    private final LLMService llmService;
    private final LLMRepoServices llmRepoServices;
    private final ResumeTemplateCreator creator;

    @PostMapping("/save")
    public ResponseEntity<Void> save(@RequestBody String prompt){
        llmRepoServices.save(prompt);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ResponseLLMDTO> get(@PathVariable Long id){
        return ResponseEntity.ok(llmRepoServices.getById(id));
    }

    @GetMapping("/get")
    public ResponseEntity<List<ResponseLLMDTO>> getAll(){
        return ResponseEntity.ok(llmRepoServices.getAll());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        llmRepoServices.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/testing")
    public void testing(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
//        String currentDateTime = dateFormat.format(new Date());

        String headerKey = "content-Disposition";
        String headerValue = "attachment; filename=resume_testing.pdf";
        response.setHeader(headerKey,headerValue);
        llmService.testing(response);
    }

    @GetMapping("/resumeTemplate")
    public ResponseEntity<String> stringLLMOutput(){
        return ResponseEntity.ok(creator.resumeTemplate());
    }

    @PostMapping("generate/{id}/{name}")
    public ResponseEntity<Void> resume(HttpServletResponse response, @RequestBody String jobDescription, @PathVariable Long id, @PathVariable String name) throws IOException {
        response.setContentType("application/pdf");
        String headerKey = "content-Disposition";
        String headerValue = "attachment; filename="+ name +".pdf";

        response.setHeader(headerKey,headerValue);
        llmService.generateResume(response,id,jobDescription);
        return ResponseEntity.ok().build();
    }


}
