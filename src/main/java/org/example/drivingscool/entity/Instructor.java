package org.example.drivingscool.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "instructors")
public class Instructor {

    @Id
    @Column(name = "instructor_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long instructorId;

    private String name;
    private String email;
    private String phone;
    private String availability;
}
