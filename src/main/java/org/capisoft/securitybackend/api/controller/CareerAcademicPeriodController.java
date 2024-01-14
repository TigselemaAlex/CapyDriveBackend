package org.capisoft.securitybackend.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.capisoft.securitybackend.api.models.requests.AcademicPeriodRequest;
import org.capisoft.securitybackend.api.models.requests.CareerAcademicPeriodRequest;
import org.capisoft.securitybackend.common.CustomAPIResponse;
import org.capisoft.securitybackend.service.abstract_services.ICareerAcademicPeriodService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/career-academic-period")
@SecurityRequirement(name = "swagger")
public class CareerAcademicPeriodController {

    private final ICareerAcademicPeriodService careerAcademicPeriodService;

    public CareerAcademicPeriodController(ICareerAcademicPeriodService careerAcademicPeriodService) {
        this.careerAcademicPeriodService = careerAcademicPeriodService;
    }

    @PostMapping
    public ResponseEntity<CustomAPIResponse<?>> save(@Valid @RequestBody final CareerAcademicPeriodRequest request){
        return careerAcademicPeriodService.save(request);
    }

    @GetMapping
    public ResponseEntity<CustomAPIResponse<?>> getAll(){
        return careerAcademicPeriodService.findAll();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CustomAPIResponse<?>> findById(@PathVariable final Long id){
        return careerAcademicPeriodService.findById(id);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CustomAPIResponse<?>> update(@PathVariable final Long id, @Valid @RequestBody final CareerAcademicPeriodRequest request){
        return careerAcademicPeriodService.update(id, request);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<CustomAPIResponse<?>> delete(@PathVariable final Long id){
        return careerAcademicPeriodService.delete(id);
    }

}
