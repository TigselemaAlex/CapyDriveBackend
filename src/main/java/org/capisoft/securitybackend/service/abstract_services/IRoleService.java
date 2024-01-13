package org.capisoft.securitybackend.service.abstract_services;

import org.capisoft.securitybackend.common.CustomAPIResponse;
import org.springframework.http.ResponseEntity;

public interface IRoleService {

    ResponseEntity<CustomAPIResponse<?>> getRoles();

}
