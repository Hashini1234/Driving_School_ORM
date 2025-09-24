package org.example.drivingscool.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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

    @OneToMany(mappedBy = "instructor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Lesson> lessons = new java.util.ArrayList<>();

    public Instructor(long instructorId, String name, String email, String phone, String availability) {
        this.instructorId = instructorId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.availability = availability;
    }
}
