package org.capisoft.securitybackend.service.services;

import org.capisoft.securitybackend.api.models.requests.CareerRequest;
import org.capisoft.securitybackend.api.models.responses.CareerResponse;
import org.capisoft.securitybackend.common.CustomAPIResponse;
import org.capisoft.securitybackend.common.CustomResponseBuilder;
import org.capisoft.securitybackend.entities.Career;
import org.capisoft.securitybackend.entities.CareerAcademicPeriod;
import org.capisoft.securitybackend.entities.Faculty;
import org.capisoft.securitybackend.entities.User;
import org.capisoft.securitybackend.mappers.CareerMapper;
import org.capisoft.securitybackend.mappers.FacultyMapper;
import org.capisoft.securitybackend.repositories.CareerAcademicPeriodRepository;
import org.capisoft.securitybackend.repositories.CareerRepository;
import org.capisoft.securitybackend.repositories.FacultyRepository;
import org.capisoft.securitybackend.repositories.UserRepository;
import org.capisoft.securitybackend.service.abstract_services.ICareerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Service
@Transactional
public class CareerServiceImpl implements ICareerService {

    private final CareerRepository careerRepository;
    private final FacultyRepository facultyRepository;
    private final CareerAcademicPeriodRepository careerAcademicPeriodRepository;
    private final CustomResponseBuilder responseBuilder;

    @Autowired
    public CareerServiceImpl(CareerRepository careerRepository, FacultyRepository facultyRepository, UserRepository userRepository, CareerAcademicPeriodRepository careerAcademicPeriodRepository, CustomResponseBuilder responseBuilder) {
        this.careerRepository = careerRepository;
        this.facultyRepository = facultyRepository;
        this.careerAcademicPeriodRepository = careerAcademicPeriodRepository;
        this.responseBuilder = responseBuilder;
    }

    @Override
    public ResponseEntity<CustomAPIResponse<?>> save(CareerRequest request) {
        Career career = CareerMapper.careerFromCareerRequest(request);
        Faculty faculty = facultyRepository.findById(request.getFaculty()).orElseThrow(()-> new RuntimeException("Facultad no encontrada."));
        CareerAcademicPeriod careerAcademicPeriod = careerAcademicPeriodRepository.findById(request.getCareerAcademicPeriods()).orElseThrow(()-> new RuntimeException("Periodo Académico de la carrera no encontrado."));
        career.setFaculty(faculty);
        career.setCareerAcademicPeriods(new HashSet<>((Collection) careerAcademicPeriod));
        CareerResponse careerResponse = CareerMapper.careerResponseFromCareer(careerRepository.save(career), FacultyMapper.facultyResponseFromFaculty(faculty));
        return responseBuilder.buildResponse(HttpStatus.CREATED, "Carrera creada exitosamente.", careerResponse);
    }

    @Override
    public ResponseEntity<CustomAPIResponse<?>> findAllCareers() {
        List<Career> careerList = careerRepository.findAll();
        List<CareerResponse> careerResponseList = careerList.stream().map(career -> {
            return CareerMapper.careerResponseFromCareer(career, FacultyMapper.facultyResponseFromFaculty(career.getFaculty()));
        }).toList();
        return responseBuilder.buildResponse(HttpStatus.OK, "Lista de carreras.", careerResponseList);
    }

    @Override
    public ResponseEntity<CustomAPIResponse<?>> update(Long id, CareerRequest request) {
        Career careerToEdit = careerRepository.findById(id).orElseThrow(()-> new RuntimeException("Carrera no encontrada."));
        Faculty faculty = facultyRepository.findById(request.getFaculty()).orElseThrow(()-> new RuntimeException("Facultad no encontrada"));
        CareerAcademicPeriod careerAcademicPeriod = careerAcademicPeriodRepository.findById(request.getCareerAcademicPeriods()).orElseThrow(()-> new RuntimeException("Periodo Académico de la carrera no encontrado."));
        careerToEdit.setName(request.getName());
        careerToEdit.setFaculty(faculty);
        careerToEdit.setCareerAcademicPeriods(new HashSet<>((Collection) careerAcademicPeriod));
        CareerResponse careerResponse = CareerMapper.careerResponseFromCareer(careerRepository.save(careerToEdit), FacultyMapper.facultyResponseFromFaculty(faculty));
        return responseBuilder.buildResponse(HttpStatus.OK, "Carrera actualizada exitosamente.", careerResponse);
    }

    @Override
    public ResponseEntity<CustomAPIResponse<?>> findById(Long id) {
        Career career = careerRepository.findById(id).orElseThrow(()-> new RuntimeException("Carrera no encontrada."));
        CareerResponse careerResponse = CareerMapper.careerResponseFromCareer(career, FacultyMapper.facultyResponseFromFaculty(career.getFaculty()));
        return responseBuilder.buildResponse(HttpStatus.OK, "Carrera encontrada.", careerResponse);
    }

    @Override
    public ResponseEntity<CustomAPIResponse<?>> delete(Long id) {
        Career career = careerRepository.findById(id).orElseThrow(()-> new RuntimeException("Carrera no encontrada."));
        careerRepository.delete(career);
        return responseBuilder.buildResponse(HttpStatus.OK, "Carrera eliminada exitosamente");
    }
}
