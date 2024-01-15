package org.capisoft.securitybackend.api.models.responses;

import lombok.*;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TemplateResponse {

    private Long id;
    private String name;
    private Instant createdAt;
}
