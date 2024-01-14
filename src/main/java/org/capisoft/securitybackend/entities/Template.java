package org.capisoft.securitybackend.entities;

import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Template {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @CreationTimestamp
    private Instant createdAt;

    @ManyToMany(mappedBy = "templates")
    private Set<CareerAcademicPeriod> careerAcademicPeriods;

    @OneToMany(mappedBy = "template")
    private Set<Folder> folders;
}
