package org.capisoft.securitybackend.api.controller;

import org.capisoft.securitybackend.common.CustomAPIResponse;
import org.capisoft.securitybackend.service.services.FileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/files")
public class FileController {

    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping
    public ResponseEntity<CustomAPIResponse<?>> save(
            @RequestParam String description,
            @RequestParam Long folder,
            @RequestParam Long student,
            @RequestParam MultipartFile file
    ){
        return fileService.save( description, folder, student, file);
    }

    @GetMapping(value = "/{folderId}/{studentId}")
    public ResponseEntity<CustomAPIResponse<?>> findByFolder(@PathVariable final Long folderId, @PathVariable final Long studentId){
        return fileService.getFilesByFolder(folderId, studentId);
    }

    @GetMapping(value = "/file/{fileId}")
    public ResponseEntity<byte[]> getFile(@PathVariable Long fileId){
        return fileService.getFile(fileId);
    }

    @DeleteMapping(value = "/{fileId}")
    public ResponseEntity<CustomAPIResponse<?>> deleteFile(@PathVariable Long fileId){
        return fileService.deleteFile(fileId);
    }

}
