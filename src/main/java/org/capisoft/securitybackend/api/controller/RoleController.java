package org.capisoft.securitybackend.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.capisoft.securitybackend.api.models.requests.RoleRequest;
import org.capisoft.securitybackend.common.CustomAPIResponse;
import org.capisoft.securitybackend.service.abstract_services.IRoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/role")
@SecurityRequirement(name = "swagger")
public class RoleController {

    private final IRoleService roleService;

    public RoleController(IRoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity<CustomAPIResponse<?>> save(@Valid @RequestBody final RoleRequest request){
        return roleService.save(request);
    }

    @GetMapping
    public ResponseEntity<CustomAPIResponse<?>> getRoles() {
        return roleService.getRoles();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CustomAPIResponse<?>> findById(@PathVariable final Long id) {
        return roleService.findById(id);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CustomAPIResponse<?>> update(@PathVariable final Long id, @RequestBody final RoleRequest request){
        return roleService.update(id, request);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<CustomAPIResponse<?>> update(@PathVariable final Long id){
        return roleService.delete(id);
    }

}
