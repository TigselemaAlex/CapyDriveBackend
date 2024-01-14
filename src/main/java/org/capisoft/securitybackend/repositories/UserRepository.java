package org.capisoft.securitybackend.repositories;

import org.capisoft.securitybackend.entities.Role;
import org.capisoft.securitybackend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByEmailAndPassword(String email, String password);

    List<User> findUsersByRolesName(String name);
}
