package org.capisoft.securitybackend.service.services;

import org.capisoft.securitybackend.api.models.requests.CampusRequest;
import org.capisoft.securitybackend.api.models.responses.CampusResponse;
import org.capisoft.securitybackend.common.CustomAPIResponse;
import org.capisoft.securitybackend.common.CustomResponseBuilder;
import org.capisoft.securitybackend.entities.Campus;
import org.capisoft.securitybackend.mappers.CampusMapper;
import org.capisoft.securitybackend.repositories.CampusRepository;
import org.capisoft.securitybackend.service.abstract_services.ICampusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CampusServiceImpl implements ICampusService {

    private final CampusRepository campusRepository;
    private final CustomResponseBuilder responseBuilder;
    @Autowired
    public CampusServiceImpl(CampusRepository campusRepository, CustomResponseBuilder responseBuilder) {
        this.campusRepository = campusRepository;
        this.responseBuilder = responseBuilder;
    }

    @Override
    public ResponseEntity<CustomAPIResponse<?>> save(CampusRequest request) {
        Campus campus = CampusMapper.campusFromCampusRequest(request);
        CampusResponse campusResponse = CampusMapper.campusResponseFromCampus(campusRepository.save(campus));
        return responseBuilder.buildResponse(HttpStatus.CREATED, "Campus creado exitosamente!", campusResponse);
    }

    @Override
    public ResponseEntity<CustomAPIResponse<?>> getAll() {
        List<Campus> campusList =  campusRepository.findAll();
        List<CampusResponse> campusResponseList = campusList.stream().map(CampusMapper::campusResponseFromCampus).toList();
        return responseBuilder.buildResponse(HttpStatus.OK, "Lista de Campus", campusResponseList);
    }

    @Override
    public ResponseEntity<CustomAPIResponse<?>> update(Long id, CampusRequest request) {
        Campus campusToEdit = campusRepository.findById(id).orElseThrow(()-> new RuntimeException("Campus no encontrado!"));
        campusToEdit.setName(request.getName());
        CampusResponse campusResponse = CampusMapper.campusResponseFromCampus(campusRepository.save(campusToEdit));
        return responseBuilder.buildResponse(HttpStatus.OK, "Campus actualizado exitosamente!", campusResponse);
    }

    @Override
    public ResponseEntity<CustomAPIResponse<?>> findByName(String name) {
        Campus campus = campusRepository.findByName(name).orElseThrow(()-> new RuntimeException("No hay resultados para: " + name + "."));
        CampusResponse campusResponse = CampusMapper.campusResponseFromCampus(campus);
        return responseBuilder.buildResponse(HttpStatus.OK, "Campus encontrado exitosamente!", campusResponse);
    }

    @Override
    public ResponseEntity<CustomAPIResponse<?>> delete(Long id) {
        Campus campus = campusRepository.findById(id).orElseThrow(()-> new RuntimeException("Campus no encontrado!"));
        campusRepository.delete(campus);
        return responseBuilder.buildResponse(HttpStatus.OK, "Campus eliminado exitosamente!");
    }
}
