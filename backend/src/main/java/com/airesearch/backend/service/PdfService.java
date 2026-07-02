package com.airesearch.backend.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.airesearch.backend.model.ResearchPaper;
import com.airesearch.backend.repository.ResearchPaperRepository;

@Service
public class PdfService {

    private static final String UPLOAD_DIR = "uploads";

    private final ResearchPaperRepository researchPaperRepository;

    public PdfService(ResearchPaperRepository researchPaperRepository) {
        this.researchPaperRepository = researchPaperRepository;
    }

    public String savePdf(MultipartFile file) throws IOException {

        Path uploadPath = Paths.get(UPLOAD_DIR);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        Path filePath = uploadPath.resolve(file.getOriginalFilename());

        // Save file (replace if it already exists)
        Files.copy(
                file.getInputStream(),
                filePath,
                StandardCopyOption.REPLACE_EXISTING
        );

        // Extract text
        String extractedText = extractText(filePath.toFile());

        // Save to MongoDB
        ResearchPaper paper = ResearchPaper.builder()
                .title(file.getOriginalFilename())
                .author("Unknown")
                .fileName(file.getOriginalFilename())
                .filePath(filePath.toString())
                .content(extractedText)
                .uploadedBy("Mona")
                .uploadDate(LocalDateTime.now())
                .build();

        researchPaperRepository.save(paper);

        System.out.println("========== PDF CONTENT ==========");
        System.out.println(extractedText);
        System.out.println("=================================");

        return filePath.toString();
    }

    public String extractText(File pdfFile) throws IOException {

        try (PDDocument document = Loader.loadPDF(pdfFile)) {

            PDFTextStripper stripper = new PDFTextStripper();

            return stripper.getText(document);
        }
    }
}