package org.example.drivingscool.BO.custom;

import org.example.drivingscool.BO.SuperBO;
import org.example.drivingscool.model.InstructorDTO;

import java.sql.SQLException;
import java.util.List;

public interface InstructorBO extends SuperBO {
    List<InstructorDTO> getAllInstructor() throws SQLException;

    boolean save(InstructorDTO dto) throws Exception;

    boolean update(InstructorDTO dto) throws SQLException, ClassNotFoundException;

    boolean delete(String id) throws Exception;
}
