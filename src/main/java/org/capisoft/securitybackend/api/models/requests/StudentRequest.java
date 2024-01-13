package org.capisoft.securitybackend.api.models.requests;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class StudentRequest {
    private String names;
    private String dni;
    private String surnames;
    private String phone;
    private String email;
    private Long career;
    private Long  academicPeriod;
}
