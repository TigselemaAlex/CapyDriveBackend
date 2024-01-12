package org.capisoft.securitybackend.repositories;

import org.capisoft.securitybackend.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
