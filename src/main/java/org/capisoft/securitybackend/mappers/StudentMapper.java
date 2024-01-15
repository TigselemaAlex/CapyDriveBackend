package org.capisoft.securitybackend.mappers;

import org.capisoft.securitybackend.api.models.requests.StudentRequestHelper;
import org.capisoft.securitybackend.api.models.responses.StudentResponse;
import org.capisoft.securitybackend.entities.Student;

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
        return StudentResponse.builder()
                .id(student.getId())
                .names(student.getNames())
                .dni(student.getDni())
                .surnames(student.getSurnames())
                .phone(student.getPhone())
                .email(student.getEmail())
                .build();
    }
}
