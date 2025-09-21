package org.example.drivingscool.BO.custom;

import org.example.drivingscool.BO.SuperBO;
import org.example.drivingscool.model.UserDTO;

import java.sql.SQLException;
import java.util.List;

public interface UserBO extends SuperBO {
    List<UserDTO> getAllUsers() throws SQLException;

    boolean save(UserDTO dto) throws Exception;

    boolean update(UserDTO dto) throws SQLException, ClassNotFoundException;

    boolean delete(String id) throws Exception;
}
