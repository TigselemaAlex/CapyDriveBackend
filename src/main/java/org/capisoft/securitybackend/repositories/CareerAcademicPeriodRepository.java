package org.capisoft.securitybackend.repositories;

import org.capisoft.securitybackend.entities.Career;
import org.capisoft.securitybackend.entities.CareerAcademicPeriod;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

public interface CareerAcademicPeriodRepository extends JpaRepository<CareerAcademicPeriod, Long> {

    List<CareerAcademicPeriod> findAllByCareer_Id(Long id);

    Optional<CareerAcademicPeriod> findCareerAcademicPeriodByAcademicPeriod_IdAndCareer_Id(Long academicPeriodId, Long careerId);
    List<CareerAcademicPeriod> findAllByCareer(Career career);



}
