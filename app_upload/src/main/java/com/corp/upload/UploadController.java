package com.corp.upload;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
public class UploadController {

    private final FileStorageService fileStorageService;

    public UploadController(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    @GetMapping("/")
    public String index(Model model) {
        return "index"; // Carga la vista index.html
    }

    @PostMapping("/upload")
    @ResponseBody
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            fileStorageService.saveFile(file);
            return ResponseEntity.ok("Archivo guardado: " + file.getOriginalFilename());
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Error subiendo el archivo: "+e.getMessage());
        }
    }
}
