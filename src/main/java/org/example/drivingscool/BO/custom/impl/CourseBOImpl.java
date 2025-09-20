package org.example.drivingscool.BO.custom.impl;

import org.example.drivingscool.BO.custom.CourseBO;
import org.example.drivingscool.DAO.DAOFactory;
import org.example.drivingscool.DAO.custom.CourseDAO;
import org.example.drivingscool.entity.Course;
import org.example.drivingscool.model.CourseDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseBOImpl implements CourseBO {

    CourseDAO courseDAO =
            (CourseDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.COURSE);

    @Override
    public List<CourseDTO> getAllCourses() throws SQLException {
        ArrayList<Course> courses = courseDAO.getAll();

        ArrayList<CourseDTO> courseDTOS = new ArrayList<>();
        for (Course c : courses) {
            courseDTOS.add(new CourseDTO(c.getCourseId(), c.getName(), c.getDuration(), c.getFee()));
       }
        return courseDTOS;
    }

    @Override
    public boolean save(CourseDTO c) throws Exception {
        return courseDAO.save(new Course(
                c.getCourseId(), c.getName(), c.getDuration(), c.getFee()
        ));
   }



    @Override
    public boolean update(CourseDTO c) throws SQLException {
        return courseDAO.update(new Course(
                c.getCourseId(), c.getName(), c.getDuration(), c.getFee()
        ));
   }


    @Override
    public boolean delete(String id) throws Exception {
        return courseDAO.delete(id);
  }
    }





