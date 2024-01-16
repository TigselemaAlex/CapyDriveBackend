package org.capisoft.securitybackend.service.services;

import jakarta.transaction.Transactional;
import org.apache.coyote.Response;
import org.capisoft.securitybackend.api.models.responses.FileResponse;
import org.capisoft.securitybackend.common.CustomAPIResponse;
import org.capisoft.securitybackend.common.CustomResponseBuilder;
import org.capisoft.securitybackend.entities.File;
import org.capisoft.securitybackend.entities.Folder;
import org.capisoft.securitybackend.entities.Student;
import org.capisoft.securitybackend.mappers.FileMapper;
import org.capisoft.securitybackend.repositories.FileRepository;
import org.capisoft.securitybackend.repositories.FolderRepository;
import org.capisoft.securitybackend.repositories.StudentRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class FileService {

    private final FileRepository fileRepository;

    private final StudentRepository studentRepository;

    private final FolderRepository  folderRepository;
    private final String uploadDir = System.getProperty("user.dir");
    private final CustomResponseBuilder responseBuilder;


    public FileService(FileRepository fileRepository, StudentRepository studentRepository, FolderRepository folderRepository, CustomResponseBuilder responseBuilder) {
        this.fileRepository = fileRepository;
        this.studentRepository = studentRepository;
        this.folderRepository = folderRepository;
        this.responseBuilder = responseBuilder;
    }

    public ResponseEntity<CustomAPIResponse<?>> save( String description, Long folder, Long student, MultipartFile file){
        Folder folder_ = folderRepository.findById(folder).orElseThrow(() -> new RuntimeException("Folder no encontrado."));
        Student student_ = studentRepository.findById(student).orElseThrow(() -> new RuntimeException("Estudiante no encontrado."));
        File file_ = new File();
        file_.setDescription(description);
        file_.setFolder(folder_);
        file_.setStudent(student_);

        if (file.isEmpty()) {
            return responseBuilder.buildResponse(HttpStatus.BAD_REQUEST, "Archivo vacío");
        }

        try {
            // Obtén el nombre original del archivo
            String fileName = student_.getDni() + LocalDate.now().toString();

            // Construye la ruta completa del archivo dentro de la carpeta "uploads"
            String filePath = uploadDir + java.io.File.separator + fileName + ".pdf";

            // Guarda el archivo localmente
            file.transferTo(new java.io.File(filePath));
            file_.setName(fileName);
            file_.setUrl(filePath);
            fileRepository.save(file_);
            return responseBuilder.buildResponse(HttpStatus.CREATED, "Archivo creado exitosamente!");
        } catch (IOException e) {
            return responseBuilder.buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Error al guardar el archivo");
        }
    }

    public ResponseEntity<CustomAPIResponse<?>> getFilesByFolder(Long folderId, Long studentId){
        Folder folder = folderRepository.findById(folderId).orElseThrow(() -> new RuntimeException("Folder no encontrado."));
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("Estudiante no encontrado."));
        List<File> files = fileRepository.findAllByFolderAndStudent(folder, student);
        List<FileResponse> responses = files.stream().map(FileMapper::fileResponseFromFile).toList();
        return responseBuilder.buildResponse(HttpStatus.OK, "Archivos encontrados", responses);
    }

    public ResponseEntity<byte[]> getFile(Long fileId) {
        File file = fileRepository.findById(fileId).orElseThrow(() -> new RuntimeException("Archivo no encontrado."));
        Path path = java.nio.file.Paths.get(file.getUrl());
        byte[] data;
        try {
            data = Files.readAllBytes(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", file.getName());

        // Devuelve el contenido del archivo como respuesta
        return ResponseEntity.ok()
                .headers(headers)
                .body(data);
    }


}
