package org.example.drivingscool.BO.custom.impl;

import org.example.drivingscool.BO.custom.UserBO;
import org.example.drivingscool.DAO.DAOFactory;
import org.example.drivingscool.DAO.custom.UserDAO;
import org.example.drivingscool.entity.User;
import org.example.drivingscool.model.UserDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserBOImpl implements UserBO {

    UserDAO userDAO = (UserDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.USER);


    @Override
    public List<UserDTO> getAllUsers() throws SQLException {
        ArrayList<User> users = userDAO.getAll();
        ArrayList<UserDTO> userDTOs = new ArrayList<>();

        for (User u : users) {
            userDTOs.add(new UserDTO(u.getUserId(), u.getUserName(), u.getPassword(), u.getRole()));
        }
        return userDTOs;
    }

    @Override
    public boolean save(UserDTO dto) throws Exception {
        return userDAO.save(new User(dto.getUserId(), dto.getUserName(), dto.getPassword(), dto.getRole()));
    }

    @Override
    public boolean update(UserDTO dto) throws SQLException, ClassNotFoundException {
        return userDAO.update(new User(dto.getUserId(), dto.getUserName(), dto.getPassword(), dto.getRole()));
    }

    @Override
    public boolean delete(String id) throws Exception {
        return userDAO.delete(id);
    }
}
