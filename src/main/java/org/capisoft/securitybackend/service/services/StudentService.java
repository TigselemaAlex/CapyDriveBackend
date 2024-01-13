package org.capisoft.securitybackend.service.services;

import jakarta.transaction.Transactional;
import org.capisoft.securitybackend.api.models.requests.StudentRequest;
import org.capisoft.securitybackend.common.CustomAPIResponse;
import org.capisoft.securitybackend.repositories.StudentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public ResponseEntity<CustomAPIResponse<?>> save(StudentRequest studentRequest){

        return null;
    }
}
