package org.capisoft.securitybackend.api.models.requests;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AcademicPeriodRequest {

    @NotNull(message = "El nombre del periodo académico es obligatorio.")
    private String name;
    @NotNull(message = "La fecha de inicio es obligatorio.")
    private LocalDate startDate;
    @NotNull(message = "La fecha de fin es obligatorio.")
    private LocalDate endDate;

}
