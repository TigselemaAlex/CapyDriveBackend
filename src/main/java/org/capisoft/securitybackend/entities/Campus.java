package org.capisoft.securitybackend.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Campus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "campus")
    private Set<Faculty> faculties = new HashSet<>();

    public void addFaculties(@NotNull Faculty faculty){
        faculties.add(faculty);
    }
}
