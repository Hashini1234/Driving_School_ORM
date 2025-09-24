package org.example.drivingscool.BO.custom.impl;

import org.example.drivingscool.BO.custom.LessonBO;
import org.example.drivingscool.entity.Course;
import org.example.drivingscool.entity.Instructor;
import org.example.drivingscool.entity.Student;
import org.example.drivingscool.model.LessonDTO;

import java.sql.SQLException;
import java.util.List;

public class LessonBOImpl implements LessonBO {
    @Override
    public List<LessonDTO> getAllLessons() throws SQLException {
        return List.of();
    }

    @Override
    public boolean save(LessonDTO dto) throws Exception {
        return false;
    }

    @Override
    public boolean update(LessonDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws Exception {
        return false;
    }

    @Override
    public List<Student> getAllStudents() throws SQLException {
        return List.of();
    }

    @Override
    public List<Course> getAllCourses() throws SQLException {
        return List.of();
    }

    @Override
    public List<Instructor> getAllInstructors() throws SQLException {
        return List.of();
    }
}
