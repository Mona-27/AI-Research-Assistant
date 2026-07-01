package com.airesearch.backend.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.airesearch.backend.model.ResearchPaper;

@Repository
public interface ResearchPaperRepository extends MongoRepository<ResearchPaper, String> {

    List<ResearchPaper> findByUploadedBy(String uploadedBy);

}