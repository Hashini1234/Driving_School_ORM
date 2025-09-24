package org.example.drivingscool.DAO.custom;

import org.example.drivingscool.DAO.CrudDao;
import org.example.drivingscool.entity.Course;

import java.util.ArrayList;

public interface CourseDAO extends CrudDao<Course> {
    ArrayList<Course> getAll();

    boolean delete(String id);

    boolean update(Course course);
    public Course findById(long id) throws Exception;


}
