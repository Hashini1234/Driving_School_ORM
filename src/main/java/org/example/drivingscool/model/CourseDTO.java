package org.example.drivingscool.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class CourseDTO {
    private long courseId;
    private String name;
    private String duration;
    private String fee;


    public CourseDTO(String name, String duration, String fee) {
        this.name = name;
        this.duration = duration;
        this.fee = fee;
    }
}
