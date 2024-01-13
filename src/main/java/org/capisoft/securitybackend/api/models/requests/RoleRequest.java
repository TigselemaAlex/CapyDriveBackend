package org.capisoft.securitybackend.api.models.requests;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RoleRequest {

    @NotNull(message = "El nombre del Rol es obligatorio.")
    private String name;

}
