package org.capisoft.securitybackend.repositories;

import org.capisoft.securitybackend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    
}
