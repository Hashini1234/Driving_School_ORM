package org.example.drivingscool.BO.custom;

import org.example.drivingscool.BO.SuperBO;
import org.example.drivingscool.model.LessonDTO;
import org.example.drivingscool.entity.Student;
import org.example.drivingscool.entity.Course;
import org.example.drivingscool.entity.Instructor;

import java.sql.SQLException;
import java.util.List;

public interface LessonBO extends SuperBO {
    List<LessonDTO> getAllLessons() throws SQLException;

    boolean save(LessonDTO dto) throws Exception;

    boolean update(LessonDTO dto) throws SQLException, ClassNotFoundException;

    boolean delete(String id) throws Exception;

    // Methods to get data for ComboBoxes
    List<Student> getAllStudents() throws SQLException;

    List<Course> getAllCourses() throws SQLException;

    List<Instructor> getAllInstructors() throws SQLException;
}