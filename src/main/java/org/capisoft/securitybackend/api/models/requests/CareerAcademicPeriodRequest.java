package org.capisoft.securitybackend.api.models.requests;

import lombok.*;
import org.capisoft.securitybackend.entities.AcademicPeriod;
import org.capisoft.securitybackend.entities.Career;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CareerAcademicPeriodRequest {

    private Long career;

    private Long academicPeriod;

}
