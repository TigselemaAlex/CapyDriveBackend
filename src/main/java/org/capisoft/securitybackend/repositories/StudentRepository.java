package org.capisoft.securitybackend.repositories;

import org.capisoft.securitybackend.entities.CareerAcademicPeriod;
import org.capisoft.securitybackend.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findStudentsByCareerAcademicPeriodsIn(Set<CareerAcademicPeriod> careerAcademicPeriods);
}
