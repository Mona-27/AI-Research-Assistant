package com.airesearch.backend.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.airesearch.backend.model.ResearchPaper;
import com.airesearch.backend.repository.ResearchPaperRepository;

@Service
public class ResearchPaperService {

    private final ResearchPaperRepository repository;

    public ResearchPaperService(ResearchPaperRepository repository) {
        this.repository = repository;
    }

    public ResearchPaper savePaper(ResearchPaper paper) {

        paper.setUploadDate(LocalDateTime.now());

        return repository.save(paper);
    }

    public List<ResearchPaper> getAllPapers() {
        return repository.findAll();
    }

    public List<ResearchPaper> getUserPapers(String email) {
        return repository.findByUploadedBy(email);
    }

}