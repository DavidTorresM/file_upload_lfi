package com.corp.upload;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.annotation.PostConstruct;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileStorageService {
    @Value("${upload.path}")
    private String uploadPath;
    private Path rootLocation;

    @PostConstruct
    public void post() throws IOException {
        rootLocation = Paths.get(uploadPath);
        if (!Files.exists(rootLocation)) {
            Files.createDirectories(rootLocation);
        }
    }

    public String saveFile(MultipartFile file) throws IOException {
        Path destination = rootLocation.resolve(file.getOriginalFilename());
        Files.copy(file.getInputStream(), destination);
        return destination.toString();
    }
}
