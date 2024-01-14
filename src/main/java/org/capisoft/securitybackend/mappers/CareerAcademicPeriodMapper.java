package org.capisoft.securitybackend.mappers;

import org.capisoft.securitybackend.api.models.requests.CareerAcademicPeriodRequest;
import org.capisoft.securitybackend.api.models.responses.AcademicPeriodResponse;
import org.capisoft.securitybackend.api.models.responses.CareerAcademicPeriodResponse;
import org.capisoft.securitybackend.api.models.responses.CareerResponse;
import org.capisoft.securitybackend.entities.CareerAcademicPeriod;

public class CareerAcademicPeriodMapper {

    public static CareerAcademicPeriod careerAcademicPeriodFromCareerAcademicPeriodRequest(CareerAcademicPeriodRequest request){
        return CareerAcademicPeriod.builder()
                .build();
    }

    public static CareerAcademicPeriodResponse careerAcademicPeriodResponseFromCareerAcademicPeriod(CareerAcademicPeriod careerAcademicPeriod, CareerResponse careerResponse, AcademicPeriodResponse academicPeriodResponse){
        return CareerAcademicPeriodResponse.builder()
                .id(careerAcademicPeriod.getId())
                .career(careerResponse)
                .academicPeriod(academicPeriodResponse)
                .build();
    }

}
