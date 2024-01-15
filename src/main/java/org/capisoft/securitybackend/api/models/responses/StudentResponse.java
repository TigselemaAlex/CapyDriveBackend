package org.capisoft.securitybackend.api.models.responses;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class StudentResponse {
    private Long id;
    private String names;
    private String dni;
    private String surnames;
    private String phone;
    private String email;
}
