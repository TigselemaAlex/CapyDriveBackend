package org.capisoft.securitybackend.api.models.requests;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class StudentRequestHelper {
    private String names;
    private String dni;
    private String surnames;
    private String phone;
    private String email;
}
