package org.capisoft.securitybackend.repositories;

import org.capisoft.securitybackend.entities.CareerAcademicPeriod;
import org.capisoft.securitybackend.entities.Template;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface TemplateRepository extends JpaRepository<Template, Long> {

    List<Template> findAllByCareerAcademicPeriodsIn(Set<CareerAcademicPeriod> careerAcademicPeriods);

}
