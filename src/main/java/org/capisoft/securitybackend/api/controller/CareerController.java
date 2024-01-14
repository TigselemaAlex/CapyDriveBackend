package org.capisoft.securitybackend.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.capisoft.securitybackend.api.models.requests.CareerRequest;
import org.capisoft.securitybackend.common.CustomAPIResponse;
import org.capisoft.securitybackend.service.abstract_services.ICareerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/career")
@SecurityRequirement(name = "swagger")
public class CareerController {

    private final ICareerService careerService;

    public CareerController(ICareerService careerService) {
        this.careerService = careerService;
    }

    @PostMapping
    public ResponseEntity<CustomAPIResponse<?>> save (@Valid @RequestBody final CareerRequest request){
        return careerService.save(request);
    }

    @GetMapping
    public ResponseEntity<CustomAPIResponse<?>> findAllCareers(){
        return careerService.findAllCareers();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CustomAPIResponse<?>> update(@PathVariable final Long id, @Valid @RequestBody final CareerRequest request){
        return careerService.update(id, request);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CustomAPIResponse<?>> findById(@PathVariable final Long id){
        return careerService.findById(id);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<CustomAPIResponse<?>> delete(@PathVariable final Long id){
        return careerService.delete(id);
    }

}
