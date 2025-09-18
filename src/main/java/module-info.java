module org.example.drivingscool {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires static lombok;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires java.desktop;
requires java.naming;

    opens org.example.drivingscool.controller to javafx.fxml;
    opens org.example.drivingscool.view.tdm to javafx.base;
    opens org.example.drivingscool.entity to org.hibernate.orm.core;
    opens org.example.drivingscool.config to jakarta.persistence;
    opens org.example.drivingscool.model to javafx.base; // <-- give javafx.base access

    exports org.example.drivingscool;
    exports  org.example.drivingscool.controller;
}