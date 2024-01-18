package org.capisoft.securitybackend.api.models.responses;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
public class CareerResponseDTO {

    private Long id;
    private String name;
    private FacultyResponse faculty;
    private List<AcademicPeriodResponse> academicPeriods;

}
