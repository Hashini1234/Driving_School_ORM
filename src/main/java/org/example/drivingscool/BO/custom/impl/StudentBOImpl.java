package org.example.drivingscool.BO.custom.impl;

import org.example.drivingscool.BO.custom.StudentBO;

import org.example.drivingscool.DAO.DAOFactory;
import org.example.drivingscool.DAO.custom.StudentDAO;
import org.example.drivingscool.entity.Student;
import org.example.drivingscool.model.StudentDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentBOImpl implements StudentBO {

   StudentDAO studentDAO =
            (StudentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.STUDENT);



    @Override
    public List<StudentDTO> getAllStudent() throws SQLException {
        ArrayList<Student> students = studentDAO.getall();

        ArrayList<StudentDTO> studentDTOS = new ArrayList<>();
        for (Student s : students) {
            studentDTOS.add(new StudentDTO(s.getStudentId(),s.getName(),s.getEmail(),s.getPhone(),s.getAddress(),s.getRegisterFee(),s.getRegistrationDate()));
        }
        return studentDTOS;
    }




    @Override
    public boolean save(StudentDTO s) throws Exception {
        boolean saved = studentDAO.save(new Student(
                s.getStudentId(), s.getName(), s.getEmail(), s.getPhone(),
                s.getAddress(), s.getRegisterFee(), s.getRegistrationDate()
        ));
        return saved;
    }

    @Override
    public boolean update(StudentDTO s) throws SQLException, ClassNotFoundException {
        boolean updated = studentDAO.update(new Student(
                s.getStudentId(), s.getName(), s.getEmail(), s.getPhone(),
                s.getAddress(), s.getRegisterFee(), s.getRegistrationDate()
        ));
        return updated;
    }





    @Override
    public boolean delete(String id) throws Exception {
        return studentDAO.delete(id);

    }


}
