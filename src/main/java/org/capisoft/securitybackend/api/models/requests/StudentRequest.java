package org.capisoft.securitybackend.api.models.requests;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentRequest {
    private Long career;
    private Long academicPeriod;
    private List<StudentRequestHelper> students;
}
