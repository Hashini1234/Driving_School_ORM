package org.example.drivingscool.BO.util;

import org.example.drivingscool.entity.Student;
import org.example.drivingscool.model.StudentDTO;

public class EntityDTOConverter {
    public StudentDTO getStudentDTO(Student student) {
        StudentDTO dto = new StudentDTO();
        dto.setStudentId(student.getStudentId());
        dto.setName(student.getName());
        dto.setAddress(student.getAddress());
        dto.setPhone(student.getPhone());
        dto.setRegistrationDate(student.getRegistrationDate());
        dto.setRegisterFee(student.getRegisterFee());
        dto.setEmail(student.getEmail());

        return dto;
    }

    public Student getStudent(StudentDTO dto) {
        Student student = new Student();
       student.setStudentId(dto.getStudentId());
       student.setName(dto.getName());
       student.setAddress(dto.getAddress());
       student.setPhone(dto.getPhone());
       student.setRegistrationDate(dto.getRegistrationDate());
       student.setRegisterFee(dto.getRegisterFee());
       student.setEmail(dto.getEmail());

       return student;
    }
}
