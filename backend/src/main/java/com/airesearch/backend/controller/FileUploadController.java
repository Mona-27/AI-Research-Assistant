package com.airesearch.backend.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

}