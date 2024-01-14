package org.capisoft.securitybackend.repositories;

import org.capisoft.securitybackend.entities.CareerAcademicPeriod;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CareerAcademicPeriodRepository extends JpaRepository<CareerAcademicPeriod, Long> {

    List<CareerAcademicPeriod> findAllByCareer_Id(Long id);

}
