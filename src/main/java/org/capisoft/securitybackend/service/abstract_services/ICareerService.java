package org.capisoft.securitybackend.service.abstract_services;

import org.capisoft.securitybackend.api.models.requests.CareerRequest;
import org.capisoft.securitybackend.common.CustomAPIResponse;
import org.springframework.http.ResponseEntity;

public interface ICareerService {

    ResponseEntity<CustomAPIResponse<?>> save (CareerRequest request);

    ResponseEntity<CustomAPIResponse<?>> findAllByFaculty(Long id);

    ResponseEntity<CustomAPIResponse<?>> update(Long id, CareerRequest request);

    ResponseEntity<CustomAPIResponse<?>> findByName(String name);

    ResponseEntity<CustomAPIResponse<?>> delete(Long id);

}
