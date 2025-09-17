package org.example.drivingscool.DAO.custom;

import org.example.drivingscool.DAO.CrudDao;
import org.example.drivingscool.entity.Student;

import java.sql.SQLException;
import java.util.Optional;

public interface StudentDAO extends CrudDao<Student> {
    Optional<Student> findStudentByNic(String nic) throws SQLException;

}
