package org.example.drivingscool.DAO.custom;

import org.example.drivingscool.DAO.CrudDao;
import org.example.drivingscool.entity.Payment;

import java.util.List;

public interface PaymentDAO extends CrudDao<Payment> {
    boolean update(Payment entity) throws Exception;

    boolean delete(String id) throws Exception;

    List<Payment> findAll() throws Exception;
}
