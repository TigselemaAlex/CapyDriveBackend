package org.capisoft.securitybackend.api.models.requests;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FacultyRequest {

    @NotNull(message = "El nombre de la Facultad es obligatorio.")
    private String name;

    private Long campus;

}
