package org.capisoft.securitybackend.service.services;

import org.capisoft.securitybackend.api.models.requests.CareerAcademicPeriodRequest;
import org.capisoft.securitybackend.api.models.responses.CareerAcademicPeriodResponse;
import org.capisoft.securitybackend.common.CustomAPIResponse;
import org.capisoft.securitybackend.common.CustomResponseBuilder;
import org.capisoft.securitybackend.entities.AcademicPeriod;
import org.capisoft.securitybackend.entities.Career;
import org.capisoft.securitybackend.entities.CareerAcademicPeriod;
import org.capisoft.securitybackend.mappers.AcademicPeriodMapper;
import org.capisoft.securitybackend.mappers.CareerAcademicPeriodMapper;
import org.capisoft.securitybackend.mappers.CareerMapper;
import org.capisoft.securitybackend.repositories.AcademicPeriodRepository;
import org.capisoft.securitybackend.repositories.CareerAcademicPeriodRepository;
import org.capisoft.securitybackend.repositories.CareerRepository;
import org.capisoft.securitybackend.service.abstract_services.ICareerAcademicPeriodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CareerAcademicPeriodServiceImpl implements ICareerAcademicPeriodService {

    private final CareerAcademicPeriodRepository careerAcademicPeriodRepository;

    private final CareerRepository careerRepository;

    private final AcademicPeriodRepository academicPeriodRepository;

    private final CustomResponseBuilder responseBuilder;

    @Autowired
    public CareerAcademicPeriodServiceImpl(CareerAcademicPeriodRepository careerAcademicPeriodRepository, CareerRepository careerRepository, AcademicPeriodRepository academicPeriodRepository, CustomResponseBuilder responseBuilder) {
        this.careerAcademicPeriodRepository = careerAcademicPeriodRepository;
        this.careerRepository = careerRepository;
        this.academicPeriodRepository = academicPeriodRepository;
        this.responseBuilder = responseBuilder;
    }


    @Override
    public ResponseEntity<CustomAPIResponse<?>> save(CareerAcademicPeriodRequest request) {
        CareerAcademicPeriod careerAcademicPeriod = CareerAcademicPeriodMapper.careerAcademicPeriodFromCareerAcademicPeriodRequest(request);
        Career career = careerRepository.findById(request.getCareer()).orElseThrow(()-> new RuntimeException("Carrera no encontrada."));
        AcademicPeriod academicPeriod = academicPeriodRepository.findById(request.getAcademicPeriod()).orElseThrow(()-> new RuntimeException("Periodo academico no encontrado."));
        careerAcademicPeriod.setCareer(career);
        careerAcademicPeriod.setAcademicPeriod(academicPeriod);
        CareerAcademicPeriodResponse careerAcademicPeriodResponse = CareerAcademicPeriodMapper.careerAcademicPeriodResponseFromCareerAcademicPeriod(careerAcademicPeriodRepository.save(careerAcademicPeriod), CareerMapper.careerResponseFromCareer(career), AcademicPeriodMapper.academicPeriodResponseFromAcademicPeriod(academicPeriod));
        return responseBuilder.buildResponse(HttpStatus.CREATED, "Período Académico de Carrera creado exitosamente.", careerAcademicPeriodResponse);
    }

    @Override
    public ResponseEntity<CustomAPIResponse<?>> findAll() {
        List<CareerAcademicPeriod> careerAcademicPeriodList = careerAcademicPeriodRepository.findAll();
        List<CareerAcademicPeriodResponse> careerAcademicPeriodResponseList = careerAcademicPeriodList
                .stream().map(careerAcademicPeriod -> {
                    Career career = careerAcademicPeriod.getCareer();
                    AcademicPeriod academicPeriod = careerAcademicPeriod.getAcademicPeriod();
                    return CareerAcademicPeriodMapper.careerAcademicPeriodResponseFromCareerAcademicPeriod(careerAcademicPeriod, CareerMapper.careerResponseFromCareer(career), AcademicPeriodMapper.academicPeriodResponseFromAcademicPeriod(academicPeriod));
                }).toList();
        return responseBuilder.buildResponse(HttpStatus.OK, "Lista de Periodos Académicos de Carreras.", careerAcademicPeriodResponseList);
    }

    @Override
    public ResponseEntity<CustomAPIResponse<?>> findAllByCareerId(Long id) {
        List<CareerAcademicPeriod> careerAcademicPeriodList = careerAcademicPeriodRepository.findAllByCareer_Id(id);
        List<CareerAcademicPeriodResponse> careerAcademicPeriodResponseList = careerAcademicPeriodList
                .stream().map(careerAcademicPeriod -> {
                    Career career = careerAcademicPeriod.getCareer();
                    AcademicPeriod academicPeriod = careerAcademicPeriod.getAcademicPeriod();
                    return CareerAcademicPeriodMapper.careerAcademicPeriodResponseFromCareerAcademicPeriod(careerAcademicPeriod, CareerMapper.careerResponseFromCareer(career), AcademicPeriodMapper.academicPeriodResponseFromAcademicPeriod(academicPeriod));
                }).toList();
        return responseBuilder.buildResponse(HttpStatus.OK, "Lista de Periodos Académicos por Carrera.", careerAcademicPeriodResponseList);
    }

    @Override
    public ResponseEntity<CustomAPIResponse<?>> update(Long id, CareerAcademicPeriodRequest request) {
        CareerAcademicPeriod careerAcademicPeriodToEdit = careerAcademicPeriodRepository.findById(id).orElseThrow(()-> new RuntimeException("Período Académico de Carrera no encontrada."));
        Career career = careerRepository.findById(request.getCareer()).orElseThrow(()-> new RuntimeException("Carrera no encontrada."));
        AcademicPeriod academicPeriod = academicPeriodRepository.findById(request.getAcademicPeriod()).orElseThrow(()-> new RuntimeException("Periodo academico no encontrado."));
        careerAcademicPeriodToEdit.setCareer(career);
        careerAcademicPeriodToEdit.setAcademicPeriod(academicPeriod);
        CareerAcademicPeriodResponse careerAcademicPeriodResponse = CareerAcademicPeriodMapper.careerAcademicPeriodResponseFromCareerAcademicPeriod(careerAcademicPeriodRepository.save(careerAcademicPeriodToEdit), CareerMapper.careerResponseFromCareer(career), AcademicPeriodMapper.academicPeriodResponseFromAcademicPeriod(academicPeriod));
        return responseBuilder.buildResponse(HttpStatus.OK, "Período Académico de Carrera actualizado exitosamente.", careerAcademicPeriodResponse);
    }

    @Override
    public ResponseEntity<CustomAPIResponse<?>> findById(Long id) {
        CareerAcademicPeriod careerAcademicPeriod = careerAcademicPeriodRepository.findById(id).orElseThrow(()-> new RuntimeException("Período Académico de Carrera no encontrada."));
        Career career = careerAcademicPeriod.getCareer();
        AcademicPeriod academicPeriod = careerAcademicPeriod.getAcademicPeriod();
        CareerAcademicPeriodResponse careerAcademicPeriodResponse = CareerAcademicPeriodMapper.careerAcademicPeriodResponseFromCareerAcademicPeriod(careerAcademicPeriod, CareerMapper.careerResponseFromCareer(career), AcademicPeriodMapper.academicPeriodResponseFromAcademicPeriod(academicPeriod));
        return responseBuilder.buildResponse(HttpStatus.OK, "Período Académico de Carrera encontrado.", careerAcademicPeriodResponse);
    }

    @Override
    public ResponseEntity<CustomAPIResponse<?>> delete(Long id) {
        CareerAcademicPeriod careerAcademicPeriod = careerAcademicPeriodRepository.findById(id).orElseThrow(()-> new RuntimeException("Período Académico de Carrera no encontrada."));
        careerAcademicPeriodRepository.delete(careerAcademicPeriod);
        return responseBuilder.buildResponse(HttpStatus.OK, "Período Académico de Carrera eliminado exitosamente.");
    }
}
