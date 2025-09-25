package org.example.drivingscool.BO.custom;

import org.example.drivingscool.BO.SuperBO;
import org.example.drivingscool.model.PaymentDTO;

import java.util.List;

public interface PaymentBO extends SuperBO {
    boolean savePayment(PaymentDTO dto) throws Exception;

    boolean deletePayment(String id) throws Exception;

    boolean updatePayment(PaymentDTO dto) throws Exception;

    List<PaymentDTO> findAll() throws Exception;

    List<String> getAllStudentIds();

    List<String> getAllCourseIds();

    Object getAllPayment() throws Exception;
}
