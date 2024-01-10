package org.capisoft.securitybackend.service.abstract_services;

import org.capisoft.securitybackend.api.models.requests.CampusRequest;
import org.capisoft.securitybackend.common.CustomAPIResponse;
import org.springframework.http.ResponseEntity;

public interface ICampusService {

    ResponseEntity<CustomAPIResponse<?>> save(CampusRequest request);

    ResponseEntity<CustomAPIResponse<?>> getAll();

    ResponseEntity<CustomAPIResponse<?>> update(Long id, CampusRequest request);

    ResponseEntity<CustomAPIResponse<?>> findByName(String name);

    ResponseEntity<CustomAPIResponse<?>> delete(Long id);

}
