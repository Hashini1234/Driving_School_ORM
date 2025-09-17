//package org.example.drivingscool.config;
//
//import lk.ijse.supermarketfx.entity.Customer;
//import lk.ijse.supermarketfx.entity.Order;
//import lk.ijse.supermarketfx.entity.OrderDetail;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.cfg.Configuration;
//
//public class FactoryConfiguration {
//    private static FactoryConfiguration factoryConfiguration;
//    private  SessionFactory sessionFactory;
//
//    private FactoryConfiguration() {
//
//        Configuration configuration = new Configuration();
//        configuration.configure();
//
////        2 add entity classes
//        configuration.addAnnotatedClass(Customer.class);
//        configuration.addAnnotatedClass(Order.class);
//        configuration.addAnnotatedClass(OrderDetail.class);
//        configuration.addAnnotatedClass(OrderDetail.class);
//
////        3 create session factory
//        sessionFactory = configuration.buildSessionFactory();
//    }
//
//    public static FactoryConfiguration getInstance() {
//        return factoryConfiguration == null ?
//                factoryConfiguration = new FactoryConfiguration()
//                :
//                factoryConfiguration;
//    }
//    public Session getSessionFactory() {
//        Session session = sessionFactory.openSession();
//        return session;
//    }
//
//
//    public Session getSession() {
//        return sessionFactory.openSession();
//
//    }
//
//
////    session is not thread safe
////    session factory is thread safe
//}
