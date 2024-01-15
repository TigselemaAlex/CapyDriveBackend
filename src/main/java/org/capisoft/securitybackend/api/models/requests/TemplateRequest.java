package org.capisoft.securitybackend.api.models.requests;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TemplateRequest {

    private Long career;
    private Long academicPeriod;
    private String name;
}
