package org.example.drivingscool.DAO.custom.impl;

import org.example.drivingscool.DAO.custom.UserDAO;
import org.example.drivingscool.config.FactoryConfigaration;
import org.example.drivingscool.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAOImpl implements UserDAO {

    @Override
    public ArrayList<User> getAll() {
        ArrayList<User> users = new ArrayList<>();
        Transaction transaction = null;

        try (Session session = FactoryConfigaration.getInstance().getSession()) {
            transaction = session.beginTransaction();
            Query<User> query = session.createQuery("FROM User", User.class);
            users = (ArrayList<User>) query.list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
        return (users != null) ? users : new ArrayList<>();
    }

    @Override
    public boolean delete(String id) {
        Session session = FactoryConfigaration.getInstance().getSession();
        Transaction tx = session.beginTransaction();

        User user = session.get(User.class, Long.parseLong(id));
        if (user != null) {
            session.remove(user);
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
    public boolean update(User user) {
        Session session = FactoryConfigaration.getInstance().getSession();
        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean save(User user) {
        Session session = FactoryConfigaration.getInstance().getSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
        return true;
    }


    @Override
    public User search(String name) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfigaration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        User user = session.createQuery("FROM User WHERE userName = :username", User.class)
                .setParameter("username", name)
                .uniqueResult();

        transaction.commit();
        session.close();
        return user;
    }
}
