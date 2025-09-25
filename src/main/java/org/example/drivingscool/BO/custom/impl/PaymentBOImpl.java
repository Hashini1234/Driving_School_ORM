package org.example.drivingscool.BO.custom.impl;

import org.example.drivingscool.BO.custom.PaymentBO;
import org.example.drivingscool.DAO.DAOFactory;
import org.example.drivingscool.DAO.custom.CourseDAO;
import org.example.drivingscool.DAO.custom.PaymentDAO;
import org.example.drivingscool.DAO.custom.StudentDAO;
import org.example.drivingscool.entity.Course;
import org.example.drivingscool.entity.Payment;
import org.example.drivingscool.entity.Student;
import org.example.drivingscool.model.PaymentDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PaymentBOImpl implements PaymentBO {
    private final PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PAYMENT);
    private final StudentDAO studentDAO = (StudentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.STUDENT);
    private final CourseDAO courseDAO = (CourseDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.COURSE);

    @Override
    public boolean savePayment(PaymentDTO dto) throws Exception {
        Student student = studentDAO.findById(dto.getStudentID());
        Course course = courseDAO.findById(dto.getCourseID());
        Payment payment = new Payment(dto.getDate(), dto.getMethod(), dto.getAmount(), student, course);
        System.out.println("new payment saving" +payment);
        return paymentDAO.save(payment);
    }

    @Override
    public boolean deletePayment(String id) throws Exception {
        return paymentDAO.delete(id);
    }

    @Override
    public boolean updatePayment(PaymentDTO dto) throws Exception {
        Student student = studentDAO.findById(dto.getStudentID());
        Course course = courseDAO.findById(dto.getCourseID());
        Payment payment = new Payment(dto.getPaymentId(), dto.getDate(), dto.getMethod(), dto.getAmount(), student, course);
        return paymentDAO.update(payment);
    }

    @Override
    public List<PaymentDTO> findAll() throws Exception {
        return paymentDAO.findAll().stream().map(payment ->
                new PaymentDTO(
                        payment.getPaymentId(),
                        payment.getDate(),
                        payment.getMethod(),
                        payment.getAmount(),
                        payment.getStudent().getStudentId(),
                        payment.getCourse().getCourseId()
                )).collect(Collectors.toList());
    }

    @Override
    public List<String> getAllStudentIds() {
        List<Student> list = studentDAO.getall();
        List<String> idList = new ArrayList<>();
        for (Student s : list) {
            idList.add(String.valueOf(s.getStudentId()));
        }
        return idList;
    }

    @Override
    public List<String> getAllCourseIds() {
        List<Course> list = courseDAO.getAll();
        List<String> idList = new ArrayList<>();
        for (Course c : list) {
            idList.add(String.valueOf(c.getCourseId()));
        }
        return idList;

    }
}
