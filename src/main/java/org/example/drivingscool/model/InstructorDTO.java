package org.example.drivingscool.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class InstructorDTO {
    private long instructorId;
    private String name;
    private String email;
    private String phone;
    private String availability;

    public InstructorDTO(String name, String email, String phone, String availability) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.availability = availability;
    }
    public InstructorDTO(long instructorID, String instructorName) {
        this.instructorId = instructorID;
        this.name = instructorName;
    }
}
