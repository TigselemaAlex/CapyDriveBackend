package org.capisoft.securitybackend.api.models.responses;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@ToString
public class CareerResponse {

    private Long id;
    private String name;
    private FacultyResponse faculty;

}
