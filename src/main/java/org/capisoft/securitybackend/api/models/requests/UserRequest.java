package org.capisoft.securitybackend.api.models.requests;

import lombok.*;
import org.capisoft.securitybackend.api.models.responses.CareerResponse;
import org.capisoft.securitybackend.api.models.responses.RoleResponse;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequest {
    private String names;
    private String surnames;
    private String phone;
    private String dni;
    private String email;

    private String password;
    private Set<RoleResponse> roles;
    private Set<CareerResponse> careers;

}
