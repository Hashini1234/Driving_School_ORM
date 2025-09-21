package org.example.drivingscool.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class UserDTO {
    private long userId;
    private String userName;
    private String password;
    private String role;

    public UserDTO(String userName, String password, String role) {
        this.userName = userName;
        this.password = password;
        this.role = role;
    }
}
