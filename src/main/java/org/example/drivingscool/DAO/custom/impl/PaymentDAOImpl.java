package org.example.drivingscool.DAO.custom.impl;

import org.example.drivingscool.DAO.custom.PaymentDAO;
import org.example.drivingscool.entity.Payment;
import org.example.drivingscool.entity.Student;

import java.sql.SQLException;
import java.util.Optional;

public class PaymentDAOImpl implements PaymentDAO {

    @Override
    public boolean save(Payment payment) throws SQLException {
        return false;
    }
}
