package org.example.drivingscool.DAO.custom.impl;

import org.example.drivingscool.DAO.custom.InstructorDAO;
import org.example.drivingscool.entity.Instructor;
import org.example.drivingscool.entity.Student;

import java.sql.SQLException;
import java.util.Optional;

public class InstructorDAOImpl implements InstructorDAO {

    @Override
    public boolean save(Instructor instructor) throws SQLException {
        return false;
    }
}
