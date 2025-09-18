package org.example.drivingscool.DAO.custom.impl;

import org.example.drivingscool.DAO.custom.LessonDAO;
import org.example.drivingscool.entity.Lesson;

import java.sql.SQLException;

public class LessonDAOImpl implements LessonDAO {
    @Override
    public boolean save(Lesson lesson) throws SQLException {
        return false;
    }
}
