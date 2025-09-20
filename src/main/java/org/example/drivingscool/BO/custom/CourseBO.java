package org.example.drivingscool.BO.custom;

import org.example.drivingscool.BO.SuperBO;
import org.example.drivingscool.model.CourseDTO;

import java.sql.SQLException;
import java.util.List;

public interface CourseBO extends SuperBO {
    List<CourseDTO> getAllCourses() throws SQLException;

    boolean save(CourseDTO dto) throws Exception;

    boolean update(CourseDTO dto) throws SQLException;

    boolean delete(String id) throws Exception;


}
