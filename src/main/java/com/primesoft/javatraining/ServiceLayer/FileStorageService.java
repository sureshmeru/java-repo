package com.primesoft.javatraining.ServiceLayer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageService {

    private final Path uploadRoot;

    public FileStorageService(@Value("${file.upload-dir:uploads}") String uploadDir) {
        this.uploadRoot = Paths.get(uploadDir).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.uploadRoot);
        } catch (IOException e) {
            throw new RuntimeException("Could not create upload directory: " + this.uploadRoot, e);
        }
    }

    public String storeFile(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("File must not be empty");
        }

        String candidateName = file.getOriginalFilename();
        if (candidateName == null || candidateName.isBlank()) {
            candidateName = "file";
        }
        String originalName = StringUtils.cleanPath(candidateName);
        if (originalName.contains("..")) {
            throw new IllegalArgumentException("Invalid file name " + originalName);
        }

        String filename = System.currentTimeMillis() + "_" + originalName;
        Path targetPath = this.uploadRoot.resolve(filename);
        try {
            Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file " + filename, e);
        }
        return filename;
    }

    public Path getUploadRoot() {
        return uploadRoot;
    }
}


