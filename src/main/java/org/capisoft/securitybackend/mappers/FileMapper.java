package org.capisoft.securitybackend.mappers;

import org.capisoft.securitybackend.api.models.responses.FileResponse;
import org.capisoft.securitybackend.entities.File;

public class FileMapper {
    public static FileResponse fileResponseFromFile(File file){
        return FileResponse.builder()
                .id(file.getId())
                .name(file.getName())
                .url(file.getUrl())
                .description(file.getDescription())
                .issueDate(file.getIssueDate())
                .student(StudentMapper.studentResponseFromStudent(file.getStudent()))
                .folder(FolderMapper.folderResponseFromFolder(file.getFolder()))
                .build();
    }
}
