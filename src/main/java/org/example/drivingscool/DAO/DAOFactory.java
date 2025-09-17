package org.example.drivingscool.DAO;

import org.example.drivingscool.BO.SuperBO;
import org.example.drivingscool.BO.custom.BOFactory;
import org.example.drivingscool.BO.custom.impl.*;
import org.example.drivingscool.DAO.custom.impl.CourseDAOImpl;
import org.example.drivingscool.DAO.custom.impl.PaymentDAOImpl;
import org.example.drivingscool.DAO.custom.impl.StudentDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;
    private DAOFactory() {}
    public static DAOFactory getInstance() {
        return (daoFactory==null)?new DAOFactory():daoFactory;
    }
    public enum DAOTypes {
        STUDENT,
        COURSE,
        INSTRUCTOR,
        LESSON,
        PAYMENT    }
    public SuperDAO getDAO(DAOTypes daoType) {
        switch(daoType){
            case STUDENT:
                return new StudentDAOImpl();
            case COURSE:
                return new CourseDAOImpl();
            case INSTRUCTOR:
                return new InstructorDAOImpl();
            case LESSON:
                return new LessonDAOImpl();
            case PAYMENT:
                return new PaymentDAOImpl();
            default:
                return null;
 }
}
}