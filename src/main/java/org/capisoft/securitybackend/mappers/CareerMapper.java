package org.capisoft.securitybackend.mappers;

import org.capisoft.securitybackend.api.models.requests.CareerRequest;
import org.capisoft.securitybackend.api.models.responses.CareerResponse;
import org.capisoft.securitybackend.api.models.responses.FacultyResponse;
import org.capisoft.securitybackend.entities.Career;

public class CareerMapper {

    public static Career careerFromCareerRequest(CareerRequest request){
        return Career.builder().name(request.getName()).build();
    }

    public static CareerResponse careerResponseFromCareer(Career career, FacultyResponse facultyResponse){
        return CareerResponse.builder()
                .id(career.getId())
                .name(career.getName())
                .faculty(facultyResponse)
                .build();
    }

    public static Career careerFromCareerResponse(CareerResponse careerResponse){
        return Career.builder()
                .id(careerResponse.getId())
                .name(careerResponse.getName())
                .build();
    }

}
