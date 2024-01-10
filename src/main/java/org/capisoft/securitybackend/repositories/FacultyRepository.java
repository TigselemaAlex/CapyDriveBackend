package org.capisoft.securitybackend.repositories;

import org.capisoft.securitybackend.entities.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    Optional<Faculty> findByName(String name);
    List<Faculty> findAllByCampus_Id(Long campusid);

}
