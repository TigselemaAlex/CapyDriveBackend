package org.capisoft.securitybackend.service.services;

import org.capisoft.securitybackend.api.models.requests.FacultyRequest;
import org.capisoft.securitybackend.api.models.responses.FacultyResponse;
import org.capisoft.securitybackend.common.CustomAPIResponse;
import org.capisoft.securitybackend.common.CustomResponseBuilder;
import org.capisoft.securitybackend.entities.Campus;
import org.capisoft.securitybackend.entities.Faculty;
import org.capisoft.securitybackend.mappers.CampusMapper;
import org.capisoft.securitybackend.mappers.FacultyMapper;
import org.capisoft.securitybackend.repositories.CampusRepository;
import org.capisoft.securitybackend.repositories.FacultyRepository;
import org.capisoft.securitybackend.service.abstract_services.IFacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FacultyServiceImpl implements IFacultyService {

    private final FacultyRepository facultyRepository;
    private final CampusRepository campusRepository;
    private final CustomResponseBuilder responseBuilder;

    @Autowired
    public FacultyServiceImpl(FacultyRepository facultyRepository, CampusRepository campusRepository, CustomResponseBuilder responseBuilder) {
        this.facultyRepository = facultyRepository;
        this.campusRepository = campusRepository;
        this.responseBuilder = responseBuilder;
    }

    @Override
    public ResponseEntity<CustomAPIResponse<?>> save(FacultyRequest request) {
        Faculty faculty = FacultyMapper.facultyFromFacultyRequest(request);
        Campus campus = campusRepository.findById(request.getCampus()).orElseThrow(()-> new RuntimeException("Campus no encontrado."));
        campus.addFaculties(faculty);
        faculty.setCampus(campus);
        FacultyResponse facultyResponse = FacultyMapper.facultyResponseFromFaculty(facultyRepository.save(faculty));
        return responseBuilder.buildResponse(HttpStatus.CREATED, "Facultad creada exitosamente!", facultyResponse);
    }

    @Override
    public ResponseEntity<CustomAPIResponse<?>> getFaculties() {
        List<Faculty> facultyList = facultyRepository.findAll();
        List<FacultyResponse> facultyResponseList = facultyList.stream().map(faculty -> {
            Campus campus = campusRepository.findById(faculty.getCampus().getId()).orElseThrow(()-> new RuntimeException("No se encontró el campus."));
            return FacultyMapper.facultyResponseCampusFromFaculty(faculty, CampusMapper.campusResponseFromCampus(campus));
        }).toList();
        return responseBuilder.buildResponse(HttpStatus.OK, "Lista de Facultades", facultyResponseList);
    }

    @Override
    public ResponseEntity<CustomAPIResponse<?>> findAllByCampus(Long id) {
        List<Faculty> facultyList = facultyRepository.findAllByCampus_Id(id);
        List<FacultyResponse> facultyResponseList = facultyList.stream().map(FacultyMapper::facultyResponseFromFaculty).toList();
        return responseBuilder.buildResponse(HttpStatus.OK, "Lista de Facultades por Campus.", facultyResponseList);
    }

    @Override
    public ResponseEntity<CustomAPIResponse<?>> update(Long id, FacultyRequest request) {
        Faculty facultyToEdit = facultyRepository.findById(id).orElseThrow(()-> new RuntimeException("Facultad no encontrada"));
        facultyToEdit.setName(request.getName());
        FacultyResponse facultyResponse = FacultyMapper.facultyResponseFromFaculty(facultyRepository.save(facultyToEdit));
        return responseBuilder.buildResponse(HttpStatus.OK, "Facultad actualizada exitosamente!", facultyResponse);
    }

    @Override
    public ResponseEntity<CustomAPIResponse<?>> findByName(String name) {
        Faculty faculty = facultyRepository.findByName(name).orElseThrow(()-> new RuntimeException("No hay resultados para: " + name + "."));
        FacultyResponse facultyResponse = FacultyMapper.facultyResponseFromFaculty(faculty);
        return responseBuilder.buildResponse(HttpStatus.OK, "Facultad encontrada exitosamente!", facultyResponse);
    }

    @Override
    public ResponseEntity<CustomAPIResponse<?>> delete(Long id) {
        Faculty faculty = facultyRepository.findById(id).orElseThrow(()-> new RuntimeException("Facultad no encontrada"));
        facultyRepository.delete(faculty);
        return responseBuilder.buildResponse(HttpStatus.OK, "Facultad eliminada exitosamente!");
    }
}
