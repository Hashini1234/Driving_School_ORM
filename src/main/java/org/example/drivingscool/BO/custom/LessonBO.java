package org.example.drivingscool.BO.custom;



import org.example.drivingscool.BO.SuperBO;
import org.example.drivingscool.model.LessonDTO;

import java.sql.SQLException;
import java.util.List;

public interface LessonBO extends SuperBO {
    boolean saveLesson(LessonDTO dto) throws Exception;

    boolean updateLesson(LessonDTO dto) throws Exception;

    boolean deleteLesson(String id) throws Exception;

    List<LessonDTO> findAll() throws Exception;

    List<String> getAllInstructorIds() throws Exception;
    List<String> getAllCourseIds() throws Exception;
    List<String> getAllStudentIds() throws Exception;

    List<LessonDTO> getAllLessons() throws SQLException, ClassNotFoundException;
}