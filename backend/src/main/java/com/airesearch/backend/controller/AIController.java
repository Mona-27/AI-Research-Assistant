package com.airesearch.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.airesearch.backend.model.ResearchPaper;
import com.airesearch.backend.repository.ResearchPaperRepository;
import com.airesearch.backend.service.GeminiService;

@RestController
@RequestMapping("/api/ai")
public class AIController {

    private final GeminiService geminiService;
    private final ResearchPaperRepository researchPaperRepository;

    public AIController(GeminiService geminiService,
                        ResearchPaperRepository researchPaperRepository) {
        this.geminiService = geminiService;
        this.researchPaperRepository = researchPaperRepository;
    }

    @GetMapping("/summarize/{id}")
    public String summarize(@PathVariable String id) {

        ResearchPaper paper = researchPaperRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paper not found"));

        return geminiService.summarize(paper.getContent());
    }
}