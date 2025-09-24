package org.example.drivingscool.DAO.custom.impl;

import org.example.drivingscool.DAO.custom.InstructorDAO;
import org.example.drivingscool.config.FactoryConfigaration;
import org.example.drivingscool.entity.Instructor;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;

public class InstructorDAOImpl implements InstructorDAO {

    @Override
    public ArrayList<Instructor> getAll() {
        ArrayList<Instructor> instructors = new ArrayList<>();
        Transaction transaction = null;

        try (Session session = FactoryConfigaration.getInstance().getSession()) {
            transaction = session.beginTransaction();

            Query<Instructor> query = session.createQuery("FROM Instructor", Instructor.class);
            instructors = (ArrayList<Instructor>) query.list();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }

        return (instructors != null) ? instructors : new ArrayList<>();
    }

    @Override
    public boolean delete(String id) {
        Session session = FactoryConfigaration.getInstance().getSession();
        Transaction tx = session.beginTransaction();

        Instructor instructor = session.get(Instructor.class, Long.parseLong(id));
        if (instructor != null) {
            session.remove(instructor);
            tx.commit();
            session.close();
            return true;
        } else {
            tx.rollback();
            session.close();
            return false;
        }
    }

    @Override
    public boolean update(Instructor instructor) {
        Session session = FactoryConfigaration.getInstance().getSession();
        session.beginTransaction();
        session.update(instructor);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean save(Instructor instructor) {
        Session session = FactoryConfigaration.getInstance().getSession();
        session.beginTransaction();
        session.save(instructor);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    public Instructor findById(long id) throws Exception {
        try (Session session = FactoryConfigaration.getInstance().getSession().getSessionFactory().openSession()) {
            return session.get(Instructor.class, id);
        }
    }
}
