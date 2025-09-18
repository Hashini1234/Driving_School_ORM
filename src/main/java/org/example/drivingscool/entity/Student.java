package org.example.drivingscool.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "students")
public class Student {

    @Id
    @Column(name = "student_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long studentId;

    private String name;
    private String email;
    private String phone;
    private String address;

    @Column(name = "register_fee")
    private String registerFee;

    @Column(name = "registration_date")
    private String registrationDate;
}