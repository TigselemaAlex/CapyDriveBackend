package org.capisoft.securitybackend.api.models.requests;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CareerRequest {

    @NotNull(message = "El nombre de la Carrera es obligatorio.")
    private String name;

    @NotNull(message = "El id de la facultad es obligatorio.")
    private Long faculty;

    @NotNull(message = "El id del usuario es obligatorio.")
    private Long user;

    @NotNull(message = "El id del período académico de la carrera es obligatorio.")
    private Long careerAcademicPeriods;

}