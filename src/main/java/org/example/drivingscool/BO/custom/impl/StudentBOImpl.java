package org.example.drivingscool.BO.custom.impl;

import org.example.drivingscool.BO.custom.StudentBO;
import org.example.drivingscool.BO.util.EntityDTOConverter;
import org.example.drivingscool.DAO.DAOFactory;
import org.example.drivingscool.DAO.custom.StudentDAO;
import org.example.drivingscool.entity.Student;
import org.example.drivingscool.model.StudentDTO;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class StudentBOImpl implements StudentBO {

    private final StudentDAO studentDAO =
            (StudentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.STUDENT);
    private final EntityDTOConverter converter = new EntityDTOConverter();


    @Override
    public List<StudentDTO> getAllStudent() throws SQLException {
        return List.of();
    }

    @Override
    public boolean save(StudentDTO dto) throws Exception {
        Optional<Student> optionalStudent = studentDAO.findById(dto.getStudentId());


        Optional<Student> customerByNicOptional = studentDAO.findStudentByNic(dto.getName());


        Student customer = converter.getStudent(dto);
//        customer.setEmail("hello");

        boolean save = studentDAO.save(customer);
        return save;
    }

    @Override
    public void update(StudentDTO dto) throws SQLException {

    }

    @Override
    public boolean delete(String id) throws Exception {
        return false;
    }

    @Override
    public String getNextId() throws SQLException {
        return "";
    }
}
