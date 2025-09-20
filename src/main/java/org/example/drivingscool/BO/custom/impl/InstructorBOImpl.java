package org.example.drivingscool.BO.custom.impl;

import org.example.drivingscool.BO.custom.InstructorBO;
import org.example.drivingscool.DAO.DAOFactory;
import org.example.drivingscool.DAO.custom.InstructorDAO;
import org.example.drivingscool.entity.Instructor;
import org.example.drivingscool.model.InstructorDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InstructorBOImpl implements InstructorBO {

    InstructorDAO instructorDAO =
            (InstructorDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.INSTRUCTOR);

    @Override
    public List<InstructorDTO> getAllInstructor() throws SQLException {
        ArrayList<Instructor> instructors = instructorDAO.getAll();

        ArrayList<InstructorDTO> instructorDTOS = new ArrayList<>();
        for (Instructor i : instructors) {
            instructorDTOS.add(new InstructorDTO(i.getInstructorId(), i.getName(), i.getEmail(), i.getPhone(), i.getAvailability()));
        }
        return instructorDTOS;
    }

    @Override
    public boolean save(InstructorDTO dto) throws Exception {
        return instructorDAO.save(new Instructor(
                dto.getInstructorId(), dto.getName(), dto.getEmail(), dto.getPhone(), dto.getAvailability()
        ));
    }

    @Override
    public boolean update(InstructorDTO dto) throws SQLException, ClassNotFoundException {
        return instructorDAO.update(new Instructor(
                dto.getInstructorId(), dto.getName(), dto.getEmail(), dto.getPhone(), dto.getAvailability()
        ));
    }

    @Override
    public boolean delete(String id) throws Exception {
        return instructorDAO.delete(id);
    }
}
