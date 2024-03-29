package org.capisoft.securitybackend.api.models.responses;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
public class CareerAcademicPeriodResponse {

    private Long id;

    private CareerResponse career;

    private AcademicPeriodResponse academicPeriod;

}
