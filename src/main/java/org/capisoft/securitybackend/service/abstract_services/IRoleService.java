package org.capisoft.securitybackend.service.abstract_services;

import org.capisoft.securitybackend.api.models.requests.RoleRequest;
import org.capisoft.securitybackend.common.CustomAPIResponse;
import org.springframework.http.ResponseEntity;

public interface IRoleService {

    ResponseEntity<CustomAPIResponse<?>> save(RoleRequest request);

    ResponseEntity<CustomAPIResponse<?>> getRoles();

    ResponseEntity<CustomAPIResponse<?>> findById(Long id);

    ResponseEntity<CustomAPIResponse<?>> update(Long id, RoleRequest request);

    ResponseEntity<CustomAPIResponse<?>> delete(Long id);

}
