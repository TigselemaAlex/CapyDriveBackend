package org.capisoft.securitybackend.mappers;

import org.capisoft.securitybackend.api.models.requests.AcademicPeriodRequest;
import org.capisoft.securitybackend.api.models.responses.AcademicPeriodResponse;
import org.capisoft.securitybackend.entities.AcademicPeriod;

public class AcademicPeriodMapper {

    public static AcademicPeriod academicPeriodFromAcademicPeriodRequest(AcademicPeriodRequest request){
        return AcademicPeriod.builder()
                .name(request.getName())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .build();
    }

    public static AcademicPeriodResponse academicPeriodResponseFromAcademicPeriod(AcademicPeriod academicPeriod){
        return AcademicPeriodResponse.builder()
                .id(academicPeriod.getId())
                .name(academicPeriod.getName())
                .startDate(academicPeriod.getStartDate())
                .endDate(academicPeriod.getEndDate())
                .build();
    }

}
