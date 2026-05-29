package com.igovern.program.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.nio.file.*;
import java.util.List;
import java.util.UUID;

@Service
public class FileStorageService {

    private final Path uploadPath;

    private static final List<String> ALLOWED_TYPES = List.of(
            "application/pdf",
            "image/png",
            "image/jpeg"
    );

    public FileStorageService(
            @Value("${file.upload-dir}") String uploadDir) throws Exception {

        this.uploadPath = Paths.get(uploadDir)
                .toAbsolutePath()
                .normalize();

        Files.createDirectories(this.uploadPath);
    }

    public String storeFile(MultipartFile file) throws Exception {

        if (file.isEmpty()) {
            throw new RuntimeException("File is empty");
        }

        if (!ALLOWED_TYPES.contains(file.getContentType())) {
            throw new RuntimeException(
                    "Only PDF, PNG and JPG files are allowed");
        }

        String originalFileName =
                file.getOriginalFilename();

        String extension = "";

        if (originalFileName != null
                && originalFileName.contains(".")) {

            extension = originalFileName.substring(
                    originalFileName.lastIndexOf("."));
        }

        String storedFileName =
                UUID.randomUUID() + extension;

        Path targetLocation =
                uploadPath.resolve(storedFileName);

        Files.copy(
                file.getInputStream(),
                targetLocation,
                StandardCopyOption.REPLACE_EXISTING
        );

        return storedFileName;
    }

    public Resource loadFile(String fileName)
            throws MalformedURLException {

        Path filePath = uploadPath.resolve(fileName)
                .normalize();

        Resource resource =
                new UrlResource(filePath.toUri());

        if (!resource.exists()
                || !resource.isReadable()) {

            throw new RuntimeException("File not found");
        }

        return resource;
    }
}