package org.capisoft.securitybackend.service.services;

import org.capisoft.securitybackend.api.models.responses.RoleResponse;
import org.capisoft.securitybackend.common.CustomAPIResponse;
import org.capisoft.securitybackend.common.CustomResponseBuilder;
import org.capisoft.securitybackend.entities.Role;
import org.capisoft.securitybackend.mappers.RoleMapper;
import org.capisoft.securitybackend.repositories.RoleRepository;
import org.capisoft.securitybackend.service.abstract_services.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements IRoleService {

    private final RoleRepository roleRepository;

    private final CustomResponseBuilder responseBuilder;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, CustomResponseBuilder responseBuilder) {
        this.roleRepository = roleRepository;
        this.responseBuilder = responseBuilder;
    }

    @Override
    public ResponseEntity<CustomAPIResponse<?>> getRoles() {
        List<Role> roleList = roleRepository.findAll();
        List<RoleResponse> roleResponseList = roleList.stream().map(RoleMapper::roleResponseFromRole).toList();
        return responseBuilder.buildResponse(HttpStatus.OK, "Lista de Roles.", roleResponseList);
    }
}
