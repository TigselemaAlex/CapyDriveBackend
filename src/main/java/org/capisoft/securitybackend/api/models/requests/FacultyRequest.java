package org.capisoft.securitybackend.api.models.requests;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.capisoft.securitybackend.entities.Campus;

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
