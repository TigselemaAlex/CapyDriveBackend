package org.capisoft.securitybackend.api.models.responses;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class RoleResponse {
    private Long id;
    private String name;
}
