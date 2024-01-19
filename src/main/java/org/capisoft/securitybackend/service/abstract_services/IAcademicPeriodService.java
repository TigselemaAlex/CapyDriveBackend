package org.capisoft.securitybackend.service.abstract_services;


import org.capisoft.securitybackend.api.models.requests.AcademicPeriodRequest;
import org.capisoft.securitybackend.common.CustomAPIResponse;

import org.springframework.http.ResponseEntity;

public interface IAcademicPeriodService {

    ResponseEntity<CustomAPIResponse<?>> save(AcademicPeriodRequest request);

    ResponseEntity<CustomAPIResponse<?>> getAll();

    ResponseEntity<CustomAPIResponse<?>> update(Long id, AcademicPeriodRequest request);

    ResponseEntity<CustomAPIResponse<?>> findById(Long id);

    ResponseEntity<CustomAPIResponse<?>> delete(Long id);
    public ResponseEntity<CustomAPIResponse<?>> getAllByCareer(Long id);

}
