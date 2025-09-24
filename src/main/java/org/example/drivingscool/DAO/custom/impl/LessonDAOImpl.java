package org.example.drivingscool.DAO.custom.impl;

import org.example.drivingscool.DAO.custom.LessonDAO;
import org.example.drivingscool.config.FactoryConfigaration;
import org.example.drivingscool.entity.Lesson;
import org.example.drivingscool.entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LessonDAOImpl implements LessonDAO {
        private final FactoryConfigaration factoryConfiguration = FactoryConfigaration.getInstance();


    @Override
    public boolean save(Lesson lesson) throws SQLException {
        try (Session session = factoryConfiguration.getSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(lesson);
            tx.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ArrayList<Lesson> getall() {
        Session session = factoryConfiguration.getSession();
        List<Lesson> list = session.createQuery("FROM Lesson", Lesson.class).list();
        session.close();
        return (ArrayList<Lesson>) list;
    }

    @Override
    public boolean delete(String id) {
        Session session = factoryConfiguration.getSession();
        Transaction tx = session.beginTransaction();
        Lesson lesson = session.get(Lesson.class, Long.parseLong(id));
        if (lesson != null) {
            session.remove(lesson);
        }
        tx.commit();
        session.close();
        return lesson != null;
    }

    @Override
    public boolean update(Lesson lesson) {
        Session session = factoryConfiguration.getSession();
        Transaction tx = session.beginTransaction();
        session.merge(lesson);
        tx.commit();
        session.close();
        return true;
    }

}
