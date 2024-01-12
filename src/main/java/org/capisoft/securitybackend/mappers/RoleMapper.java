package org.capisoft.securitybackend.mappers;

import org.capisoft.securitybackend.api.models.responses.RoleResponse;
import org.capisoft.securitybackend.entities.Role;

public class RoleMapper {
    public static RoleResponse roleResponseFromRole(Role role) {
        return RoleResponse.builder()
                .id(role.getId())
                .name(role.getName())
                .build();
    }

    public static Role roleFromRoleResponse(RoleResponse roleResponse) {
        return Role.builder()
                .id(roleResponse.getId())
                .name(roleResponse.getName())
                .build();
    }
}
