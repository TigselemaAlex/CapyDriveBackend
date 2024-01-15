package org.capisoft.securitybackend.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.capisoft.securitybackend.api.models.requests.LoginRequest;
import org.capisoft.securitybackend.api.models.requests.UserRequest;
import org.capisoft.securitybackend.common.CustomAPIResponse;
import org.capisoft.securitybackend.service.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@SecurityRequirement(name = "swagger")
public class UsersController {

    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<CustomAPIResponse<?>> save(@RequestBody UserRequest request) {
        return userService.save(request);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<CustomAPIResponse<?>> login(@RequestBody LoginRequest request) {
        return userService.login(request);
    }
    @GetMapping(value = "all/{id}")
    public ResponseEntity<CustomAPIResponse<?>> findAll(@PathVariable Long id) {
        return userService.findAll(id);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CustomAPIResponse<?>> findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CustomAPIResponse<?>> update(@PathVariable Long id, @RequestBody UserRequest request) {
        return userService.update(id, request);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<CustomAPIResponse<?>> delete(@PathVariable Long id) {
        return userService.delete(id);
    }
}
