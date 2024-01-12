package org.capisoft.securitybackend.service.services;

import jakarta.transaction.Transactional;
import org.capisoft.securitybackend.api.models.requests.UserRequest;
import org.capisoft.securitybackend.api.models.responses.UserResponse;
import org.capisoft.securitybackend.common.CustomAPIResponse;
import org.capisoft.securitybackend.common.CustomResponseBuilder;
import org.capisoft.securitybackend.entities.Role;
import org.capisoft.securitybackend.entities.User;
import org.capisoft.securitybackend.mappers.RoleMapper;
import org.capisoft.securitybackend.mappers.UserMapper;
import org.capisoft.securitybackend.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final CustomResponseBuilder responseBuilder;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository, CustomResponseBuilder responseBuilder, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.responseBuilder = responseBuilder;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public ResponseEntity<CustomAPIResponse<?>> save(UserRequest userRequest) {
        User user = UserMapper.userFromUserRequest(userRequest);
        user.setPassword(bCryptPasswordEncoder.encode(userRequest.getPassword()));
        userRepository.save(user);
        return responseBuilder.buildResponse(HttpStatus.CREATED, "Usuario creado exitosamente!");
    }

    public ResponseEntity<CustomAPIResponse<?>> findAll() {
        List<UserResponse> response = userRepository.findAll().stream().map(UserMapper::userResponseFromUser).toList();

        return responseBuilder.buildResponse(HttpStatus.OK, "Lista de usuarios.", response);
    }

    public ResponseEntity<CustomAPIResponse<?>> findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado."));
        return responseBuilder.buildResponse(HttpStatus.OK, "Usuario encontrado.", UserMapper.userResponseFromUser(user));
    }

    public ResponseEntity<CustomAPIResponse<?>> update(Long id, UserRequest userRequest) {
        List<Role> roles = userRequest.getRoles().stream().map(RoleMapper::roleFromRoleResponse).toList();
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado."));
        user.setNames(userRequest.getNames());
        user.setSurnames(userRequest.getSurnames());
        user.setPhone(userRequest.getPhone());
        user.setDni(userRequest.getDni());
        user.setEmail(userRequest.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(userRequest.getPassword()));
        user.setRoles(new HashSet<>(roles));
        userRepository.save(user);
        return responseBuilder.buildResponse(HttpStatus.OK, "Usuario actualizado exitosamente!");
    }

    public ResponseEntity<CustomAPIResponse<?>> delete(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado."));
        userRepository.delete(user);
        return responseBuilder.buildResponse(HttpStatus.OK, "Usuario eliminado exitosamente!");
    }

}
