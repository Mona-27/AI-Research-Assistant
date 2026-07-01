package com.airesearch.backend.service;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PdfService {

    private static final String UPLOAD_DIR = "uploads/";

    public String savePdf(MultipartFile file) throws IOException {

        File directory = new File(UPLOAD_DIR);

        if (!directory.exists()) {
            directory.mkdirs();
        }

        String filePath = UPLOAD_DIR + file.getOriginalFilename();

        file.transferTo(new File(filePath));

        return filePath;
    }

}