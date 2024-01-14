package org.capisoft.securitybackend.repositories;

import org.capisoft.securitybackend.entities.CareerAcademicPeriod;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface CareerAcademicPeriodRepository extends JpaRepository<CareerAcademicPeriod, Long> {

    Optional<CareerAcademicPeriod> findCareerAcademicPeriodByAcademicPeriod_IdAndCareer_Id(Long academicPeriodId, Long careerId);
}
