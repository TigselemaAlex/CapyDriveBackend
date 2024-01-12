package org.capisoft.securitybackend.api.models.responses;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {

    private Long id;
    private String names;
    private String surnames;
    private String phone;
    private String dni;
    private String email;
    private Set<RoleResponse> roles;
    //private Set<Career> careers;
}
