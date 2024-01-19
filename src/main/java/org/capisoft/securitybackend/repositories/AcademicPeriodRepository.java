package org.capisoft.securitybackend.repositories;

import org.capisoft.securitybackend.entities.AcademicPeriod;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AcademicPeriodRepository extends JpaRepository<AcademicPeriod, Long> {

    List<AcademicPeriod> findAllByIdNotIn(List<Long> ids);
}
