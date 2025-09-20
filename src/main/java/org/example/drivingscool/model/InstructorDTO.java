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

    public InstructorDTO(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }
}
