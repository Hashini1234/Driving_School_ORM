package org.example.drivingscool.BO.custom;


import org.example.drivingscool.BO.SuperBO;
import org.example.drivingscool.BO.custom.impl.*;

public class BOFactory {

    private static BOFactory boFactory;
    private BOFactory(){

    }
    public static BOFactory getBoFactory(){
        return (boFactory == null) ? new BOFactory() : boFactory;
    }
    public enum BOTypes{
      PAYMENT,LESSON,STUDENT,INSTRUCTOR,COURSE,USER
    }
    public SuperBO getBO(BOTypes boType) {
        switch(boType){
            case COURSE:
                    return new CourseBOImpl();
                case INSTRUCTOR:
                    return new InstructorBOImpl();
                case LESSON:
                    return new LessonBOImpl();
                case PAYMENT:
                    return new PaymentBOImpl();
                case STUDENT:
                    return new StudentBOImpl();
                    case USER:
                        return new UserBOImpl();



            default:
                return null;
        }
    }
}