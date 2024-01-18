package org.capisoft.securitybackend.mappers;

import org.capisoft.securitybackend.api.models.requests.StudentRequestHelper;
import org.capisoft.securitybackend.api.models.responses.AcademicPeriodResponse;
import org.capisoft.securitybackend.api.models.responses.StudentResponse;
import org.capisoft.securitybackend.entities.Student;

import java.util.List;

public class StudentMapper {

    public static Student studentFromStudentRequest(StudentRequestHelper studentRequest) {
        return Student.builder()
                .names(studentRequest.getNames())
                .dni(studentRequest.getDni())
                .surnames(studentRequest.getSurnames())
                .phone(studentRequest.getPhone())
                .email(studentRequest.getEmail())
                .build();
    }

    public static StudentResponse studentResponseFromStudent(Student student) {

        List<AcademicPeriodResponse> responses = student.getCareerAcademicPeriods().stream().map(
                careerAcademicPeriod -> {
                    return AcademicPeriodResponse.builder()
                            .id(careerAcademicPeriod.getAcademicPeriod().getId())
                            .name(careerAcademicPeriod.getAcademicPeriod().getName())
                            .endDate(careerAcademicPeriod.getAcademicPeriod().getEndDate())
                            .startDate(careerAcademicPeriod.getAcademicPeriod().getStartDate())
                            .build();
                }
        ).toList();
        return StudentResponse.builder()
                .id(student.getId())
                .names(student.getNames())
                .dni(student.getDni())
                .surnames(student.getSurnames())
                .phone(student.getPhone())
                .email(student.getEmail())
                .academicPeriods(responses)
                .build();
    }
}
