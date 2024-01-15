package org.capisoft.securitybackend.mappers;

import org.capisoft.securitybackend.api.models.requests.UserRequest;
import org.capisoft.securitybackend.api.models.responses.CareerResponse;
import org.capisoft.securitybackend.api.models.responses.RoleResponse;
import org.capisoft.securitybackend.api.models.responses.UserResponse;
import org.capisoft.securitybackend.entities.Career;
import org.capisoft.securitybackend.entities.Role;
import org.capisoft.securitybackend.entities.User;

import java.util.HashSet;
import java.util.List;

public class UserMapper {
    public static User userFromUserRequest(UserRequest request) {
        List<Role> roles = request.getRoles().stream().map(RoleMapper::roleFromRoleResponse).toList();
        List<Career> careers = request.getCareers().stream().map(CareerMapper::careerFromCareerResponse).toList();
        return User.builder()
                .names(request.getNames())
                .surnames(request.getSurnames())
                .phone(request.getPhone())
                .dni(request.getDni())
                .email(request.getEmail())
                .password(request.getPassword())
                .roles(new HashSet<>(roles))
                .careers(new HashSet<>(careers))
                .build();
    }

    public static UserResponse userResponseFromUser(User user) {
        List<RoleResponse> roles = user.getRoles().stream().map(RoleMapper::roleResponseFromRole).toList();
        List<CareerResponse> careers = user.getCareers().stream().map(
                career -> {
                    return CareerResponse.builder()
                            .id(career.getId())
                            .name(career.getName())
                            .faculty(FacultyMapper.facultyResponseFromFaculty(career.getFaculty()))
                            .build();
                }
        ).toList();
        return UserResponse.builder()
                .id(user.getId())
                .names(user.getNames())
                .surnames(user.getSurnames())
                .phone(user.getPhone())
                .dni(user.getDni())
                .email(user.getEmail())
                .roles(new HashSet<>(roles))
                .careers(new HashSet<>(careers))
                .build();
    }
}
