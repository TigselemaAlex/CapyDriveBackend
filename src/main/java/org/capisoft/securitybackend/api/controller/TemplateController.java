package org.capisoft.securitybackend.api.controller;

import org.capisoft.securitybackend.api.models.requests.TemplateRequest;
import org.capisoft.securitybackend.common.CustomAPIResponse;
import org.capisoft.securitybackend.service.services.TemplateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("templates")
public class TemplateController {

    private final TemplateService templateService;

    public TemplateController(TemplateService templateService) {
        this.templateService = templateService;
    }

    @PostMapping
    public ResponseEntity<CustomAPIResponse<?>> save(@RequestBody TemplateRequest request){
        return templateService.save(request);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomAPIResponse<?>> findByCareer(@PathVariable Long id){
        return templateService.findByCareer(id);
    }

}
