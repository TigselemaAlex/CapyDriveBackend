package org.capisoft.securitybackend.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.capisoft.securitybackend.common.CustomAPIResponse;
import org.capisoft.securitybackend.service.abstract_services.IRoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/role")
@SecurityRequirement(name = "swagger")
public class RoleController {

    private final IRoleService roleService;

    public RoleController(IRoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<CustomAPIResponse<?>> getRoles() {
        return roleService.getRoles();
    }

}
