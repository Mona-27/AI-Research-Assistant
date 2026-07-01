package com.airesearch.backend.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "research_papers")
public class ResearchPaper {

    @Id
    private String id;

    private String title;

    private String author;

    private String fileName;

    private String filePath;

    private String content;

    private String uploadedBy;

    private LocalDateTime uploadDate;

}