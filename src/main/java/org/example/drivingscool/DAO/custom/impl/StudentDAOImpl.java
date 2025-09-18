package org.example.drivingscool.DAO.custom.impl;

import org.example.drivingscool.DAO.custom.StudentDAO;
import org.example.drivingscool.config.FactoryConfigaration;
import org.example.drivingscool.entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class StudentDAOImpl implements StudentDAO {


    @Override
    public ArrayList<Student> getall() {
        ArrayList<Student> students = new ArrayList<>();
        Transaction transaction = null;

        try (Session session = FactoryConfigaration.getInstance().getSession()) {
            transaction = session.beginTransaction();

            Query<Student> query = session.createQuery("FROM Student", Student.class);
            students = (ArrayList<Student>) query.list();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }

        return (students != null) ? students : new ArrayList<>(); // ensure non-null
    }


    @Override
    public String generateNewId() {
        Session session = FactoryConfigaration.getInstance().getSession();
        try {
            // Correct alias usage + ORDER BY
            Query<String> query = session.createQuery(
                    "SELECT c.id FROM Student c ORDER BY c.id DESC",
                    String.class
            );
            query.setMaxResults(1);

            String lastId = query.uniqueResult();

            // If no records yet, return first ID
            if (lastId == null) {
                return "S001";
            }

            // Otherwise, increment the numeric part of the ID
            int idNum = Integer.parseInt(lastId.replace("S", ""));
            idNum++;
            return String.format("S%03d", idNum);

        } finally {
            session.close();
        }

    }

    @Override
    public boolean delete(String id) {
        Session session = FactoryConfigaration.getInstance().getSession();
        Transaction tx = session.beginTransaction();

        // Load the entity first
        Student student = session.get(Student.class, id);
        if (student != null) {
            session.remove(student);
            tx.commit();
            session.close();
            return true;
        } else {
            tx.rollback();
            session.close();
            return false; // student not found
        }

    }

    @Override
    public boolean update(Student student) {
        Session session = FactoryConfigaration.getInstance().getSession();
        session.beginTransaction();
        session.update(student);
        session.getTransaction().commit();
        session.close();
        return true;

    }

    @Override
    public boolean save(Student student) throws SQLException {
        Session session = FactoryConfigaration.getInstance().getSession();
        session.beginTransaction();
        session.save(student);
        session.getTransaction().commit();
        session.close();
        return true;

    }
}
