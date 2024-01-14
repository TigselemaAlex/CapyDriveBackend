package org.capisoft.securitybackend.api.models.responses;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
public class AcademicPeriodResponse {

    private Long id;

    private String name;

    private LocalDate startDate;

    private LocalDate endDate;

}
