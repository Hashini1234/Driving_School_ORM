package org.example.drivingscool.DAO;

import org.example.drivingscool.entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public interface CrudDao<T> extends SuperDAO {
    Optional<Student> findById(int studentId);
    boolean save(T t) throws SQLException;

}
