package org.capisoft.securitybackend.mappers;

import org.capisoft.securitybackend.api.models.requests.FacultyRequest;
import org.capisoft.securitybackend.api.models.responses.CampusResponse;
import org.capisoft.securitybackend.api.models.responses.FacultyResponse;
import org.capisoft.securitybackend.entities.Faculty;

public class FacultyMapper {

    public static Faculty facultyFromFacultyRequest(FacultyRequest request){
        return Faculty.builder().name(request.getName()).build();
    }

    public static FacultyResponse facultyResponseFromFaculty(Faculty faculty){
        return FacultyResponse.builder()
                .id(faculty.getId())
                .name(faculty.getName())
                .build();
    }public static FacultyResponse facultyResponseCampusFromFaculty(Faculty faculty, CampusResponse campus){
        return FacultyResponse.builder()
                .id(faculty.getId())
                .name(faculty.getName())
                .campus(campus)
                .build();
    }

}
