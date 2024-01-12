package org.capisoft.securitybackend.service.services;

import org.capisoft.securitybackend.api.models.requests.CareerRequest;
import org.capisoft.securitybackend.common.CustomAPIResponse;
import org.capisoft.securitybackend.common.CustomResponseBuilder;
import org.capisoft.securitybackend.entities.Career;
import org.capisoft.securitybackend.mappers.CareerMapper;
import org.capisoft.securitybackend.repositories.CareerRepository;
import org.capisoft.securitybackend.service.abstract_services.ICareerService;
import org.springframework.http.ResponseEntity;

public class CareerServiceImpl implements ICareerService {

    private final CareerRepository careerRepository;
    private final CustomResponseBuilder responseBuilder;

    public CareerServiceImpl(CareerRepository careerRepository, CustomResponseBuilder responseBuilder) {
        this.careerRepository = careerRepository;
        this.responseBuilder = responseBuilder;
    }

    @Override
    public ResponseEntity<CustomAPIResponse<?>> save(CareerRequest request) {
        Career career = CareerMapper.careerFromCareerRequest(request);

        return null;
    }

    @Override
    public ResponseEntity<CustomAPIResponse<?>> findAllByFaculty(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<CustomAPIResponse<?>> update(Long id, CareerRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<CustomAPIResponse<?>> findByName(String name) {
        return null;
    }

    @Override
    public ResponseEntity<CustomAPIResponse<?>> delete(Long id) {
        return null;
    }
}
