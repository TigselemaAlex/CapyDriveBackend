package org.capisoft.securitybackend.service.services;

import jakarta.transaction.Transactional;
import org.capisoft.securitybackend.api.models.requests.StudentRequest;
import org.capisoft.securitybackend.api.models.requests.StudentRequestHelper;
import org.capisoft.securitybackend.common.CustomAPIResponse;
import org.capisoft.securitybackend.common.CustomResponseBuilder;
import org.capisoft.securitybackend.entities.Career;
import org.capisoft.securitybackend.entities.CareerAcademicPeriod;
import org.capisoft.securitybackend.entities.Student;
import org.capisoft.securitybackend.mappers.StudentMapper;
import org.capisoft.securitybackend.repositories.CareerAcademicPeriodRepository;
import org.capisoft.securitybackend.repositories.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class StudentService {

    private final StudentRepository studentRepository;
    private final CustomResponseBuilder responseBuilder;
    private final CareerAcademicPeriodRepository careerAcademicPeriodRepository;

    public StudentService(StudentRepository studentRepository, CustomResponseBuilder responseBuilder, CareerAcademicPeriodRepository careerAcademicPeriodRepository) {
        this.studentRepository = studentRepository;
        this.responseBuilder = responseBuilder;
        this.careerAcademicPeriodRepository = careerAcademicPeriodRepository;
    }

    public ResponseEntity<CustomAPIResponse<?>> saveAll(StudentRequest requests){
        CareerAcademicPeriod careerAcademicPeriod = careerAcademicPeriodRepository.findCareerAcademicPeriodByAcademicPeriod_IdAndCareer_Id(requests.getAcademicPeriod(), requests.getCareer()).orElseThrow(() -> new RuntimeException("Carrera no encontrada."));
        requests.getStudents().forEach(studentRequestHelper -> {
            Student student = StudentMapper.studentFromStudentRequest(studentRequestHelper);
            student.setCareerAcademicPeriods(new HashSet<>(List.of(careerAcademicPeriod)));
            careerAcademicPeriod.getStudents().add(student);
            studentRepository.save(student);
        });
        return responseBuilder.buildResponse(HttpStatus.CREATED, "Estudiantes creados exitosamente!");
    }

    public ResponseEntity<CustomAPIResponse<?>> update (StudentRequestHelper studentRequestHelper, Long id){
        Student student = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Estudiante no encontrado."));
        student.setNames(studentRequestHelper.getNames());
        student.setDni(studentRequestHelper.getDni());
        student.setSurnames(studentRequestHelper.getSurnames());
        student.setPhone(studentRequestHelper.getPhone());
        student.setEmail(studentRequestHelper.getEmail());
        studentRepository.save(student);
        return responseBuilder.buildResponse(HttpStatus.OK, "Estudiante actualizado exitosamente!");
    }

    public ResponseEntity<CustomAPIResponse<?>> delete (Long id){
        Student student = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Estudiante no encontrado."));
        studentRepository.delete(student);
        return responseBuilder.buildResponse(HttpStatus.OK, "Estudiante eliminado exitosamente!");
    }
}
