package org.example.drivingscool.DAO.custom.impl;

import org.example.drivingscool.DAO.custom.StudentDAO;
import org.example.drivingscool.entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class StudentDAOImpl implements StudentDAO {

    @Override
    public Optional<Student> findStudentByNic(String nic) throws SQLException {
        return Optional.empty();
    }

    @Override
    public Optional<Student> findById(int studentId) {
        return Optional.empty();
    }

    @Override
    public boolean save(Student student) throws SQLException {
        return false;
    }
}
