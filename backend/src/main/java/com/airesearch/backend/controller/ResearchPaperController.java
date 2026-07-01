package com.airesearch.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.airesearch.backend.model.ResearchPaper;
import com.airesearch.backend.service.ResearchPaperService;

@RestController
@RequestMapping("/api/research")
public class ResearchPaperController {

    private final ResearchPaperService service;

    public ResearchPaperController(ResearchPaperService service) {
        this.service = service;
    }

    @PostMapping("/save")
    public ResearchPaper save(@RequestBody ResearchPaper paper) {
        return service.savePaper(paper);
    }

    @GetMapping("/all")
    public List<ResearchPaper> getAll() {
        return service.getAllPapers();
    }

    @GetMapping("/user/{email}")
    public List<ResearchPaper> getUserPapers(@PathVariable String email) {
        return service.getUserPapers(email);
    }
}