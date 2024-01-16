package org.capisoft.securitybackend.api.models.requests;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TemplateRequest {

    private Long career;
    private Long academicPeriod;
    private String name;
    private List<Folder> folders;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @Builder
    public static class Folder{
        private String name;
    }
}
