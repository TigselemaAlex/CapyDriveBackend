package org.capisoft.securitybackend.mappers;

import org.capisoft.securitybackend.api.models.requests.CareerRequest;
import org.capisoft.securitybackend.api.models.responses.CareerResponse;
import org.capisoft.securitybackend.entities.Career;

public class CareerMapper {

    public static Career careerFromCareerRequest(CareerRequest request){
        return Career.builder().name(request.getName()).build();
    }

    public static CareerResponse facultyResponseFromFaculty(Career career){
        return CareerResponse.builder()
                .id(career.getId())
                .name(career.getName())
                .build();
    }

}
