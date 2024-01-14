package org.capisoft.securitybackend.api.controller;

import org.capisoft.securitybackend.api.models.requests.StudentRequest;
import org.capisoft.securitybackend.api.models.requests.StudentRequestHelper;
import org.capisoft.securitybackend.common.CustomAPIResponse;
import org.capisoft.securitybackend.service.services.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<CustomAPIResponse<?>> saveAll(@RequestBody StudentRequest requests){
        return studentService.saveAll(requests);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CustomAPIResponse<?>> update (@PathVariable Long id, @RequestBody StudentRequestHelper studentRequestHelper){
        return studentService.update(studentRequestHelper, id);
    }
}
