package org.example.drivingscool.DAO.custom;

import org.example.drivingscool.DAO.CrudDao;
import org.example.drivingscool.entity.User;

import java.util.ArrayList;

public interface UserDAO extends CrudDao<User> {
    ArrayList<User> getAll();
    boolean delete(String id);
    boolean update(User user);
}
