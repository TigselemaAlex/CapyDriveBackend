package org.capisoft.securitybackend.repositories;

import org.capisoft.securitybackend.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
