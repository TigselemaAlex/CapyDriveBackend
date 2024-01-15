package org.capisoft.securitybackend.api.models.requests;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CareerAcademicPeriodRequest {

    @NotNull(message = "La carrera es obligatorio.")
    private Long career;

    @NotNull(message = "El periodo academico es obligatorio.")
    private Long academicPeriod;

}
