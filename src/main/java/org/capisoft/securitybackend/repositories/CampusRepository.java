package org.capisoft.securitybackend.repositories;

import org.capisoft.securitybackend.entities.Campus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CampusRepository extends JpaRepository<Campus, Long> {

    Optional<Campus> findByName(String name);

}
