package org.capisoft.securitybackend.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AcademicPeriod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;

    @OneToMany(mappedBy = "academicPeriod")
    private Set<CareerAcademicPeriod> careerAcademicPeriods;


}
