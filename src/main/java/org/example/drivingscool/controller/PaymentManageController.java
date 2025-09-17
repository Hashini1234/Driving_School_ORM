package org.example.drivingscool.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class PaymentManageController {

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colAmount;

    @FXML
    private TableColumn<?, ?> colCourseId;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colMethod;

    @FXML
    private TableColumn<?, ?> colPaymentId;

    @FXML
    private TableColumn<?, ?> colStudentId;

    @FXML
    private TableView<?> tblPayments;

    @FXML
    private TextField txtAmount;

    @FXML
    private TextField txtCourseId;

    @FXML
    private TextField txtDate;

    @FXML
    private TextField txtMethod;

    @FXML
    private TextField txtPaymentId;

    @FXML
    private TextField txtStudentId;

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
