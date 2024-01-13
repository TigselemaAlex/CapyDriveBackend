package org.capisoft.securitybackend.service.abstract_services;

import org.capisoft.securitybackend.api.models.requests.FacultyRequest;
import org.capisoft.securitybackend.common.CustomAPIResponse;
import org.springframework.http.ResponseEntity;

public interface IFacultyService {

    ResponseEntity<CustomAPIResponse<?>> save (FacultyRequest request);

    ResponseEntity<CustomAPIResponse<?>> getFaculties();

    ResponseEntity<CustomAPIResponse<?>> findAllByCampus(Long id);

    ResponseEntity<CustomAPIResponse<?>> update(Long id, FacultyRequest request);

    ResponseEntity<CustomAPIResponse<?>> findByName(String name);

    ResponseEntity<CustomAPIResponse<?>> delete(Long id);

}
