package org.example.drivingscool.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private long courseId;

    private String name;
    private String duration;
    private String fee;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Lesson> lessons = new java.util.ArrayList<>();

    public Course(long courseId, String name, String duration, String fee) {
        this.courseId = courseId;
        this.name = name;
        this.duration = duration;
        this.fee = fee;
    }
}
