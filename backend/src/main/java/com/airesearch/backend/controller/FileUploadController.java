package com.airesearch.backend.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.airesearch.backend.service.PdfService;

@RestController
@RequestMapping("/api/files")
public class FileUploadController {

    private final PdfService pdfService;

    public FileUploadController(PdfService pdfService) {
        this.pdfService = pdfService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file)
            throws IOException {

        String path = pdfService.savePdf(file);

        return ResponseEntity.ok("Uploaded Successfully: " + path);
    }
    @GetMapping("/test")
public String test() {
    return "File Controller Working";
}

}