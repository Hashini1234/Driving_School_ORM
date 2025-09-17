package org.example.drivingscool.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class LessonController {

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colCourseId;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colInstructorId;

    @FXML
    private TableColumn<?, ?> colLessonId;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private TableColumn<?, ?> colStudentId;

    @FXML
    private TableColumn<?, ?> colTime;

    @FXML
    private TableView<?> tblPayments;

    @FXML
    private TextField txtCourseId;

    @FXML
    private TextField txtDate;

    @FXML
    private TextField txtInstructorId;

    @FXML
    private TextField txtLessonId;

    @FXML
    private TextField txtStatus;

    @FXML
    private TextField txtStudentId;

    @FXML
    private TextField txtTime;

    @FXML
    void handleClear(ActionEvent event) {

    }

    @FXML
    void handleDeletePayment(ActionEvent event) {

    }

    @FXML
    void handleSavePayment(ActionEvent event) {

    }

    @FXML
    void handleUpdatePayment(ActionEvent event) {

    }

}
