package org.capisoft.securitybackend.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.capisoft.securitybackend.api.models.requests.AcademicPeriodRequest;
import org.capisoft.securitybackend.common.CustomAPIResponse;
import org.capisoft.securitybackend.service.abstract_services.IAcademicPeriodService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/academic-period")
@SecurityRequirement(name = "swagger")
public class AcademicPeriodController {

    private final IAcademicPeriodService academicPeriodService;

    public AcademicPeriodController(IAcademicPeriodService academicPeriodService) {
        this.academicPeriodService = academicPeriodService;
    }

    @PostMapping
    public ResponseEntity<CustomAPIResponse<?>> save(@Valid @RequestBody final AcademicPeriodRequest request){
        return academicPeriodService.save(request);
    }

    @GetMapping
    public ResponseEntity<CustomAPIResponse<?>> getAll(){
        return academicPeriodService.getAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CustomAPIResponse<?>> findById(@PathVariable final Long id){
        return academicPeriodService.findById(id);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CustomAPIResponse<?>> update(@PathVariable final Long id, @Valid @RequestBody final AcademicPeriodRequest request){
        return academicPeriodService.update(id, request);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<CustomAPIResponse<?>> delete(@PathVariable final Long id){
        return academicPeriodService.delete(id);
    }

}
