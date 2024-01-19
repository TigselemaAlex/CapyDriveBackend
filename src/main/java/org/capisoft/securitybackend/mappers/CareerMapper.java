package org.capisoft.securitybackend.mappers;

import org.capisoft.securitybackend.api.models.requests.CareerRequest;
import org.capisoft.securitybackend.api.models.responses.AcademicPeriodResponse;
import org.capisoft.securitybackend.api.models.responses.CareerResponse;
import org.capisoft.securitybackend.api.models.responses.CareerResponseDTO;
import org.capisoft.securitybackend.api.models.responses.FacultyResponse;
import org.capisoft.securitybackend.entities.Career;

import java.util.List;

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

    public static CareerResponse careerResponseFromCareer(Career career){
        List<AcademicPeriodResponse> responseList = career.getCareerAcademicPeriods().stream().map(
                careerAcademicPeriod -> {
                    return AcademicPeriodResponse.builder()
                            .id(careerAcademicPeriod.getAcademicPeriod().getId())
                            .name(careerAcademicPeriod.getAcademicPeriod().getName())
                            .endDate(careerAcademicPeriod.getAcademicPeriod().getEndDate())
                            .startDate(careerAcademicPeriod.getAcademicPeriod().getStartDate())
                            .build();
                }
        ).toList();
        return CareerResponse.builder()
                .id(career.getId())
                .name(career.getName())
                .academicPeriods(responseList)
                .build();
    }

    public static Career careerFromCareerResponse(CareerResponse careerResponse){

        return Career.builder()
                .id(careerResponse.getId())
                .name(careerResponse.getName())
                .build();
    }

    public static CareerResponseDTO careerResponseDTOFromCareer(Career career, List<AcademicPeriodResponse> responseList, FacultyResponse faculty){
        return CareerResponseDTO.builder()
                .id(career.getId())
                .name(career.getName())
                .academicPeriods(responseList)
                .faculty(faculty)
                .build();
    }

}
