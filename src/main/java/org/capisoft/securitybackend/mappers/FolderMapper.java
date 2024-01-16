package org.capisoft.securitybackend.mappers;

import org.capisoft.securitybackend.api.models.requests.TemplateRequest;
import org.capisoft.securitybackend.api.models.responses.TemplateResponse;
import org.capisoft.securitybackend.entities.Folder;

public class FolderMapper {

    public static Folder folderFromFolderRequest(TemplateRequest.Folder folderRequest) {
        return Folder.builder()
                .name(folderRequest.getName())
                .build();
    }

    public static TemplateRequest.Folder folderRequestFromFolder(Folder folder) {
        return TemplateRequest.Folder.builder()
                .name(folder.getName())
                .build();
    }

    public static TemplateResponse.FolderResponse folderResponseFromFolder(Folder folder) {
        return TemplateResponse.FolderResponse.builder()
                .id(folder.getId())
                .name(folder.getName())
                .build();
    }
}
