package org.capisoft.securitybackend.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String names;
    private String dni;
    private String surnames;
    private String phone;
    private String email;

    @ManyToMany(mappedBy = "students")
    private Set<CareerAcademicPeriod> careerAcademicPeriods;

    @OneToMany(mappedBy = "student")
    private Set<File> files;
}
