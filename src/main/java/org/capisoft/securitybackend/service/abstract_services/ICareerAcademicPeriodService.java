package org.capisoft.securitybackend.service.abstract_services;

import org.capisoft.securitybackend.api.models.requests.CareerAcademicPeriodRequest;
import org.capisoft.securitybackend.common.CustomAPIResponse;
import org.springframework.http.ResponseEntity;

public interface ICareerAcademicPeriodService {

    ResponseEntity<CustomAPIResponse<?>> save (CareerAcademicPeriodRequest request);

    ResponseEntity<CustomAPIResponse<?>> findAll();

    ResponseEntity<CustomAPIResponse<?>> findAllByCareerId(Long id);

    ResponseEntity<CustomAPIResponse<?>> update(Long id, CareerAcademicPeriodRequest request);

    ResponseEntity<CustomAPIResponse<?>> findById(Long id);

    ResponseEntity<CustomAPIResponse<?>> delete(Long id);

}
