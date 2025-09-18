package org.example.drivingscool.BO.custom;

import org.example.drivingscool.BO.SuperBO;
import org.example.drivingscool.model.StudentDTO;

import java.sql.SQLException;
import java.util.List;

public interface StudentBO  extends SuperBO {
    List<StudentDTO> getAllStudent() throws SQLException;

    boolean save(StudentDTO dto) throws  Exception;

    boolean update(StudentDTO dto) throws SQLException, ClassNotFoundException;

    boolean delete(String id) throws  Exception;

    String getNextId() throws SQLException;
}

