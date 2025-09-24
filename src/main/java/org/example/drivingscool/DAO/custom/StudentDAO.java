package org.example.drivingscool.DAO.custom;

import org.example.drivingscool.DAO.CrudDao;
import org.example.drivingscool.entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public interface StudentDAO extends CrudDao<Student> {

    ArrayList<Student> getall();


    boolean delete(String id);

    boolean update(Student student);
     Student findById(long id) throws Exception;


}
