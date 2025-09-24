package org.example.drivingscool.BO.custom.impl;

import org.example.drivingscool.BO.custom.LessonBO;
import org.example.drivingscool.DAO.DAOFactory;
import org.example.drivingscool.DAO.custom.CourseDAO;
import org.example.drivingscool.DAO.custom.InstructorDAO;
import org.example.drivingscool.DAO.custom.LessonDAO;
import org.example.drivingscool.DAO.custom.StudentDAO;
import org.example.drivingscool.entity.Course;
import org.example.drivingscool.entity.Instructor;
import org.example.drivingscool.entity.Lesson;
import org.example.drivingscool.entity.Student;
import org.example.drivingscool.model.LessonDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LessonBOImpl implements LessonBO {

    private final LessonDAO lessonDAO = (LessonDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.LESSON);
    private final InstructorDAO instructorDAO = (InstructorDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.INSTRUCTOR);
    private final CourseDAO courseDAO = (CourseDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.COURSE);
    private final StudentDAO studentDAO = (StudentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.STUDENT);


    @Override
    public boolean saveLesson(LessonDTO dto) throws Exception {
        Instructor instructor = instructorDAO.findById(dto.getInstructorID());
        Course  course = courseDAO.findById(dto.getCourseID());
        Student student = studentDAO.findById(dto.getStudentID());
        Lesson lesson = new Lesson(
                dto.getDate(),
                dto.getTime(),
                dto.getStatus(),
                student,
                course,
                instructor
        );
        return lessonDAO.save(lesson);
    }

    @Override
    public boolean updateLesson(LessonDTO dto) throws Exception {
        Instructor instructor = instructorDAO.findById(dto.getInstructorID());
        Course  course = courseDAO.findById(dto.getCourseID());
        Student student = studentDAO.findById(dto.getStudentID());

        Lesson lesson = new Lesson(
                dto.getLessonID(),
                dto.getDate(),
                dto.getTime(),
                dto.getStatus(),
                student,
                course,
                instructor
        );
        return lessonDAO.update(lesson);
    }

    @Override
    public boolean deleteLesson(String id) throws Exception {
        return lessonDAO.delete(id);
    }

    @Override
    public List<LessonDTO> findAll() throws Exception {
        return lessonDAO.getall().stream().map(lesson ->
                new LessonDTO(
                        lesson.getLessonID(),
                        lesson.getDate(),
                        lesson.getTime(),
                        lesson.getStatus(),
                        lesson.getStudent().getStudentId(),
                        lesson.getCourse().getCourseId(),
                        lesson.getInstructor().getInstructorId()
                )).collect(Collectors.toList());
    }

    @Override
    public List<String> getAllInstructorIds() throws Exception {
        List<Instructor> list = instructorDAO.getAll();
        List<String> idList = new ArrayList<>();
        for (Instructor i : list) {
            idList.add(String.valueOf(i.getInstructorId()));
        }
        return idList;
    }

    @Override
    public List<String> getAllCourseIds() throws Exception {
        List<Course> list = courseDAO.getAll();
        List<String> idList = new ArrayList<>();
        for (Course i : list) {
            idList.add(String.valueOf(i.getCourseId()));
        }
        return idList;
    }

    @Override
    public List<String> getAllStudentIds() throws Exception {
        List<Course> list = courseDAO.getAll();
        List<String> idList = new ArrayList<>();
        for (Course i : list) {
            idList.add(String.valueOf(i.getCourseId()));
        }
        return idList;
    }
}
