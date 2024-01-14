package org.capisoft.securitybackend.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.capisoft.securitybackend.api.models.requests.FacultyRequest;
import org.capisoft.securitybackend.common.CustomAPIResponse;
import org.capisoft.securitybackend.service.abstract_services.IFacultyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/faculty")
@SecurityRequirement(name = "swagger")
public class FacultyController {

    private final IFacultyService facultyService;

    public FacultyController(IFacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    public ResponseEntity<CustomAPIResponse<?>> save(@Valid @RequestBody final FacultyRequest request) {
        return facultyService.save(request);
    }

    @GetMapping
    public ResponseEntity<CustomAPIResponse<?>> getFaculties() {
        return facultyService.getFaculties();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CustomAPIResponse<?>> getAllByCampus(@PathVariable final Long id) {
        return facultyService.findAllByCampus(id);
    }

    @GetMapping(value = "/{name}")
    public ResponseEntity<CustomAPIResponse<?>> findByName(@PathVariable final String name) {
        return facultyService.findByName(name);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CustomAPIResponse<?>> update(@PathVariable final Long id, @RequestBody final FacultyRequest request) {
        return facultyService.update(id, request);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<CustomAPIResponse<?>> delete(@PathVariable final Long id) {
        return facultyService.delete(id);
    }

}
