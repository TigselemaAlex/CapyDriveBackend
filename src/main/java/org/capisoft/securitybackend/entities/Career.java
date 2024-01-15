package org.capisoft.securitybackend.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Career {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Faculty faculty;
    @ManyToMany(mappedBy = "careers")
    private Set<User> users = new HashSet<>();

    @OneToMany(mappedBy = "career")
    private Set<CareerAcademicPeriod> careerAcademicPeriods;


}
