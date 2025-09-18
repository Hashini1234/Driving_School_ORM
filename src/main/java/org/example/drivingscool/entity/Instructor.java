package org.example.drivingscool.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "instructor")   // maps to instructor table
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increment PK
    @Column(name = "instructor_id")
    private int instructorId;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(unique = true, nullable = false, length = 100)
    private String email;

    @Column(length = 15)
    private String phone;
}