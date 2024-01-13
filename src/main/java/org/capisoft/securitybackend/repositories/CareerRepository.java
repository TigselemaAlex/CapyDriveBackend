package org.capisoft.securitybackend.repositories;

import org.capisoft.securitybackend.entities.Career;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CareerRepository extends JpaRepository<Career, Long> {

    Optional<Career> findByName(String name);
    List<Career> findAllByFaculty_Id(Long careerid);

}
