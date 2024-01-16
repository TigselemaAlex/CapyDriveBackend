package org.capisoft.securitybackend.api.models.responses;

import lombok.*;
import org.capisoft.securitybackend.entities.Folder;

import java.time.Instant;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TemplateResponse {

    private Long id;
    private String name;
    private Instant createdAt;
    private List<FolderResponse> folders;
    private List<AcademicPeriodResponse> academicPeriods;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @Builder
    public static class FolderResponse{
        private Long id;
        private String name;
    }
}
