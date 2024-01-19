package org.capisoft.securitybackend.service.services;

import org.capisoft.securitybackend.api.models.requests.AcademicPeriodRequest;
import org.capisoft.securitybackend.api.models.responses.AcademicPeriodResponse;
import org.capisoft.securitybackend.common.CustomAPIResponse;
import org.capisoft.securitybackend.common.CustomResponseBuilder;
import org.capisoft.securitybackend.entities.AcademicPeriod;
import org.capisoft.securitybackend.entities.Career;
import org.capisoft.securitybackend.entities.Student;
import org.capisoft.securitybackend.mappers.AcademicPeriodMapper;
import org.capisoft.securitybackend.repositories.AcademicPeriodRepository;
import org.capisoft.securitybackend.repositories.CareerRepository;
import org.capisoft.securitybackend.repositories.StudentRepository;
import org.capisoft.securitybackend.service.abstract_services.IAcademicPeriodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AcademicPeriodServiceImpl implements IAcademicPeriodService {

    private final AcademicPeriodRepository academicPeriodRepository;

    private final CustomResponseBuilder responseBuilder;
    private final CareerRepository careerRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public AcademicPeriodServiceImpl(AcademicPeriodRepository academicPeriodRepository, CustomResponseBuilder responseBuilder, CareerRepository careerRepository, StudentRepository studentRepository) {
        this.academicPeriodRepository = academicPeriodRepository;
        this.responseBuilder = responseBuilder;
        this.careerRepository = careerRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public ResponseEntity<CustomAPIResponse<?>> save(AcademicPeriodRequest request) {
        AcademicPeriod academicPeriod = AcademicPeriodMapper.academicPeriodFromAcademicPeriodRequest(request);
        AcademicPeriodResponse academicPeriodResponse = AcademicPeriodMapper.academicPeriodResponseFromAcademicPeriod(academicPeriodRepository.save(academicPeriod));
        return responseBuilder.buildResponse(HttpStatus.CREATED, "Periodo académico creado exitosamente.", academicPeriodResponse);
    }

    @Override
    public ResponseEntity<CustomAPIResponse<?>> getAll() {
        List<AcademicPeriod> academicPeriodList = academicPeriodRepository.findAll();
        List<AcademicPeriodResponse> academicPeriodResponseList = academicPeriodList.stream().map(AcademicPeriodMapper::academicPeriodResponseFromAcademicPeriod).toList();
        return responseBuilder.buildResponse(HttpStatus.OK, "Lista de periodos académicos.", academicPeriodResponseList);
    }

    @Override
    public ResponseEntity<CustomAPIResponse<?>> update(Long id, AcademicPeriodRequest request) {
        AcademicPeriod academicPeriodToEdit = academicPeriodRepository.findById(id).orElseThrow(()-> new RuntimeException("Periodo académico no encontrado."));
        academicPeriodToEdit.setName(request.getName());
        academicPeriodToEdit.setStartDate(request.getStartDate());
        academicPeriodToEdit.setEndDate(request.getEndDate());
        AcademicPeriodResponse academicPeriodResponse = AcademicPeriodMapper.academicPeriodResponseFromAcademicPeriod(academicPeriodRepository.save(academicPeriodToEdit));
        return responseBuilder.buildResponse(HttpStatus.OK, "Periodo académico actualizado exitosamente.", academicPeriodResponse);
    }

    @Override
    public ResponseEntity<CustomAPIResponse<?>> findById(Long id) {
        AcademicPeriod academicPeriod = academicPeriodRepository.findById(id).orElseThrow(()-> new RuntimeException("Periodo académico no encontrado."));
        AcademicPeriodResponse academicPeriodResponse = AcademicPeriodMapper.academicPeriodResponseFromAcademicPeriod(academicPeriod);
        return responseBuilder.buildResponse(HttpStatus.OK, "Periodo académico encontrado.", academicPeriodResponse);
    }

    @Override
    public ResponseEntity<CustomAPIResponse<?>> delete(Long id) {
        AcademicPeriod academicPeriod = academicPeriodRepository.findById(id).orElseThrow(()-> new RuntimeException("Periodo académico no encontrado."));
        academicPeriodRepository.delete(academicPeriod);
        return responseBuilder.buildResponse(HttpStatus.OK, "Periodo académico eliminado exitosamente.");
    }

    @Override
    public ResponseEntity<CustomAPIResponse<?>> getAllByCareer(Long id) {
        Career career = careerRepository.findById(id).orElseThrow(()-> new RuntimeException("Carrera no encontrada."));
        List<AcademicPeriod> academicPeriodList =
                academicPeriodRepository.findAllByIdNotIn(career.getCareerAcademicPeriods().stream().map(
                        careerAcademicPeriod -> careerAcademicPeriod.getAcademicPeriod().getId()
                ).toList());
        List<AcademicPeriodResponse> academicPeriodResponseList = academicPeriodList.stream().map(AcademicPeriodMapper::academicPeriodResponseFromAcademicPeriod).toList();
        return responseBuilder.buildResponse(HttpStatus.OK, "Lista de periodos académicos.", academicPeriodResponseList);
    }

    @Override
    public ResponseEntity<CustomAPIResponse<?>> getAllByStudent(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(()-> new RuntimeException("Estudiante no encontrado."));
        List<AcademicPeriod> academicPeriodList =
                academicPeriodRepository.findAllByIdNotIn(student.getCareerAcademicPeriods().stream().map(
                        careerAcademicPeriod -> careerAcademicPeriod.getAcademicPeriod().getId()
                ).toList());
        List<AcademicPeriodResponse> academicPeriodResponseList = academicPeriodList.stream().map(AcademicPeriodMapper::academicPeriodResponseFromAcademicPeriod).toList();
        return responseBuilder.buildResponse(HttpStatus.OK, "Lista de periodos académicos.", academicPeriodResponseList);
    }

}
