package org.example.drivingscool.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Getter
@Setter
@AllArgsConstructor

public class StudentDTO {
    private long studentId;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String registerFee;
    private String registrationDate;

    public StudentDTO(String name, String email, String phone, String address, String registerFee, String registrationDate) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.registerFee = registerFee;
        this.registrationDate = registrationDate;
    }
}