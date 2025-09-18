package org.example.drivingscool.DAO.custom.impl;

import org.example.drivingscool.DAO.custom.CourseDAO;
import org.example.drivingscool.entity.Course;

import java.sql.SQLException;

public class CourseDAOImpl implements CourseDAO {
    @Override
    public boolean save(Course course) throws SQLException {
        return false;
    }
}
