package org.example.drivingscool.DAO.custom;

import org.example.drivingscool.DAO.CrudDao;
import org.example.drivingscool.entity.Lesson;
import org.example.drivingscool.entity.Student;

import java.util.ArrayList;

public interface LessonDAO extends CrudDao<Lesson> {

    ArrayList<Lesson> getall();


    boolean delete(String id);

    boolean update(Lesson lesson);

}
