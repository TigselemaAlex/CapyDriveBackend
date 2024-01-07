package org.capisoft.securitybackend.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.capisoft.securitybackend.api.models.requests.CampusRequest;
import org.capisoft.securitybackend.common.CustomAPIResponse;
import org.capisoft.securitybackend.service.abstract_services.ICampusService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/campus")
@SecurityRequirement(name = "swagger")
public class CampusController {

    private final ICampusService campusService;

    public CampusController(ICampusService campusService) {
        this.campusService = campusService;
    }

    @PostMapping
    public ResponseEntity<CustomAPIResponse<?>> save(@Valid @RequestBody final CampusRequest request){
        return campusService.save(request);
    }

    @GetMapping
    public ResponseEntity<CustomAPIResponse<?>> getAll(){
        return campusService.getAll();
    }

    @GetMapping(value = "/{name}")
    public ResponseEntity<CustomAPIResponse<?>> findByName(@PathVariable final String name){
        return campusService.findByName(name);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CustomAPIResponse<?>> update(@PathVariable final Long id, @RequestBody final CampusRequest request){
        return campusService.update(id, request);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<CustomAPIResponse<?>> delete(@PathVariable final Long id){
        return campusService.delete(id);
    }
}
