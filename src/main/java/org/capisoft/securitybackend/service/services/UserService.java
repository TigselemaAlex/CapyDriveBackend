package org.capisoft.securitybackend.service.services;

import jakarta.transaction.Transactional;
import org.capisoft.securitybackend.api.models.requests.LoginRequest;
import org.capisoft.securitybackend.api.models.requests.UserRequest;
import org.capisoft.securitybackend.api.models.responses.RoleResponse;
import org.capisoft.securitybackend.api.models.responses.UserResponse;
import org.capisoft.securitybackend.common.CustomAPIResponse;
import org.capisoft.securitybackend.common.CustomResponseBuilder;
import org.capisoft.securitybackend.entities.Career;
import org.capisoft.securitybackend.entities.Role;
import org.capisoft.securitybackend.entities.User;
import org.capisoft.securitybackend.mappers.CareerMapper;
import org.capisoft.securitybackend.mappers.RoleMapper;
import org.capisoft.securitybackend.mappers.UserMapper;
import org.capisoft.securitybackend.repositories.CareerRepository;
import org.capisoft.securitybackend.repositories.RoleRepository;
import org.capisoft.securitybackend.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final CustomResponseBuilder responseBuilder;
    private final CareerRepository careerRepository;

    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, CustomResponseBuilder responseBuilder, CareerRepository careerRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.responseBuilder = responseBuilder;
        this.careerRepository = careerRepository;
        this.roleRepository = roleRepository;
    }

    public ResponseEntity<CustomAPIResponse<?>> login(LoginRequest request) {
        User user = userRepository.findUserByEmailAndPassword(request.getEmail(), request.getPassword()).orElseThrow(() -> new RuntimeException("Usuario no encontrado."));
        return responseBuilder.buildResponse(HttpStatus.OK, "Usuario encontrado.", UserMapper.userResponseFromUser(user));
    }

    public ResponseEntity<CustomAPIResponse<?>> save(UserRequest userRequest) {
        User user = UserMapper.userFromUserRequest(userRequest);
        userRequest.getCareers().forEach(careerId -> {
            Career career = careerRepository.findById(careerId.getId()).orElseThrow(() -> new RuntimeException("Carrera no encontrada."));
            career.getUsers().add(user);
        });
        userRepository.save(user);
        return responseBuilder.buildResponse(HttpStatus.CREATED, "Usuario creado exitosamente!");
    }

    public ResponseEntity<CustomAPIResponse<?>> findAll(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado."));
        if (user.getRoles().stream().anyMatch(role -> role.getName().equals("SUPER-ADMIN"))) {
            List<UserResponse> response = userRepository.findAll().stream().map(UserMapper::userResponseFromUser).toList();
            return responseBuilder.buildResponse(HttpStatus.OK, "Lista de usuarios.", response);
        } if (user.getRoles().stream().anyMatch(role -> role.getName().equals("ADMIN"))) {
            List<UserResponse> response = userRepository.findUsersByRolesName("SECRETARY").stream().map(UserMapper::userResponseFromUser).toList();
            return responseBuilder.buildResponse(HttpStatus.OK, "Lista de usuarios.", response);
        } else{
            return responseBuilder.buildResponse(HttpStatus.UNAUTHORIZED, "No tienes permisos para realizar esta acción.");
        }
    }

    public ResponseEntity<CustomAPIResponse<?>> findRolesByUserId(Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado."));
        if (user.getRoles().stream().anyMatch(role -> role.getName().equals("SUPER-ADMIN"))) {
            List<RoleResponse> response = roleRepository.findAll().stream().map(RoleMapper::roleResponseFromRole).toList();
            return responseBuilder.buildResponse(HttpStatus.OK, "Lista de roles SUPER-ADMIN.", response);
        } else if (user.getRoles().stream().anyMatch(role -> role.getName().equals("ADMIN"))) {
            List<RoleResponse> response = roleRepository.findById(3L).stream().map(RoleMapper::roleResponseFromRole).toList();
            return responseBuilder.buildResponse(HttpStatus.OK, "Lista de roles ADMIN.", response);
        }
        return responseBuilder.buildResponse(HttpStatus.UNAUTHORIZED, "No tienes permisos para realizar esta acción.");
    }

    public ResponseEntity<CustomAPIResponse<?>> findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado."));
        return responseBuilder.buildResponse(HttpStatus.OK, "Usuario encontrado.", UserMapper.userResponseFromUser(user));
    }

    public ResponseEntity<CustomAPIResponse<?>> update(Long id, UserRequest userRequest) {
        List<Role> roles = userRequest.getRoles().stream().map(RoleMapper::roleFromRoleResponse).toList();
        List<Career> careers = userRequest.getCareers().stream().map(CareerMapper::careerFromCareerResponse).toList();
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado."));
        user.setNames(userRequest.getNames());
        user.setSurnames(userRequest.getSurnames());
        user.setPhone(userRequest.getPhone());
        user.setDni(userRequest.getDni());
        user.setEmail(userRequest.getEmail());
        user.setRoles(new HashSet<>(roles));
        user.setCareers(new HashSet<>(careers));
        userRepository.save(user);
        return responseBuilder.buildResponse(HttpStatus.OK, "Usuario actualizado exitosamente!");
    }

    public ResponseEntity<CustomAPIResponse<?>> delete(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado."));
        userRepository.delete(user);
        return responseBuilder.buildResponse(HttpStatus.OK, "Usuario eliminado exitosamente!");
    }

}