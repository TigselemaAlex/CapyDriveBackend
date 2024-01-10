package org.capisoft.securitybackend.api.models.requests;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CampusRequest {

    @NotNull(message = "El nombre del campus es obligatorio.")
    private String name;

}
