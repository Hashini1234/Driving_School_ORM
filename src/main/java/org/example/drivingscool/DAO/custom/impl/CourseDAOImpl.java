package org.example.drivingscool.DAO.custom.impl;

import org.example.drivingscool.DAO.custom.CourseDAO;
import org.example.drivingscool.config.FactoryConfigaration;
import org.example.drivingscool.entity.Course;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;

public class CourseDAOImpl implements CourseDAO {

    @Override
    public ArrayList<Course> getAll() {
        ArrayList<Course> courses = new ArrayList<>();
        Transaction transaction = null;

        try (Session session = FactoryConfigaration.getInstance().getSession()) {
            transaction = session.beginTransaction();

            Query<Course> query = session.createQuery("FROM Course", Course.class);
            courses = (ArrayList<Course>) query.list();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
        return (courses != null) ? courses : new ArrayList<>();
    }

    @Override
    public boolean save(Course course) throws SQLException {
        Session session = FactoryConfigaration.getInstance().getSession();
        session.beginTransaction();
        session.save(course);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Course course) {
        Session session = FactoryConfigaration.getInstance().getSession();
        session.beginTransaction();
        session.update(course);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(String id) {
        Session session = FactoryConfigaration.getInstance().getSession();
        Transaction tx = session.beginTransaction();

        Course course = session.get(Course.class, Long.parseLong(id));
        if (course != null) {
            session.remove(course);
            tx.commit();
            session.close();
            return true;
        } else {
            tx.rollback();
            session.close();
            return false;
        }
    }
}
