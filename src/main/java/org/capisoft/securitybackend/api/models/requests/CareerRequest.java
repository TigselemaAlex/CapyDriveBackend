package org.capisoft.securitybackend.api.models.requests;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CareerRequest {

    @NotNull(message = "El nombre de la carrera es obligatorio.")
    private String name;

    @NotNull(message = "El id de la facultad es obligatorio.")
    private Long faculty;

}
