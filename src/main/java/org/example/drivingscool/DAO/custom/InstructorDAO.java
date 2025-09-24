package org.example.drivingscool.DAO.custom;

import org.example.drivingscool.DAO.CrudDao;
import org.example.drivingscool.entity.Instructor;

import java.util.ArrayList;

public interface InstructorDAO extends CrudDao<Instructor> {

    ArrayList<Instructor> getAll();

    boolean delete(String id);

    boolean update(Instructor instructor);

    public Instructor findById(long id) throws Exception;


}
