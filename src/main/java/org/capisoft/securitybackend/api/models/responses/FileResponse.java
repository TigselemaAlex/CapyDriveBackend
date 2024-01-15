package org.capisoft.securitybackend.api.models.responses;

import lombok.*;
import org.capisoft.securitybackend.entities.Template;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FileResponse {
    private Long id;
    private String name;
    private String url;
    private String description;
    private Instant issueDate;
    private StudentResponse student;
    private TemplateResponse.FolderResponse folder;
}
