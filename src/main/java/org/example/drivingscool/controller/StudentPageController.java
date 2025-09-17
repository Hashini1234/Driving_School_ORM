package org.example.drivingscool.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.drivingscool.BO.custom.StudentBO;
import org.example.drivingscool.BO.custom.impl.StudentBOImpl;
import org.example.drivingscool.model.StudentDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class StudentPageController {
    private final StudentBO studentBO = new StudentBOImpl();  // You'll need to import the implementation


    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPhone;

    @FXML
    private TableColumn<?, ?> colRegDate;

    @FXML
    private TableColumn<?, ?> colRegisterFee;

    @FXML
    private TableColumn<?, ?> colStudentId;

    @FXML
    private DatePicker dpRegistrationDate;

    @FXML
    private TableView<StudentDTO> tblStudent;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPhone;

    @FXML
    private TextField txtRegisterFee;

    @FXML
    private TextField txtStudentId;

    public void initialize() throws SQLException, ClassNotFoundException {
        setCellValueFactory();
        setNextId();
        loadtable();
    }

    private void loadtable() throws SQLException {
        ArrayList<StudentDTO> studentDTOS = (ArrayList<StudentDTO>) studentBO.getAllStudent();

        ObservableList<StudentDTO> data = FXCollections.observableArrayList();

        for (StudentDTO studentDTO : studentDTOS) {
            data.add(studentDTO);
        }

        tblStudent.setItems(data);

    }

    private void setNextId() {
        String nextId = null;
        try {
            nextId = studentBO.getNextId();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        txtStudentId.setText(nextId);


    }

    private void setCellValueFactory() {
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colRegisterFee.setCellValueFactory(new PropertyValueFactory<>("registerFee"));
        colRegDate.setCellValueFactory(new PropertyValueFactory<>("registrationDate"));


    }

    @FXML
    void handleClear(ActionEvent event) {

    }

    @FXML
    void handleDeletePayment(ActionEvent event) {
        String id = txtStudentId.getText();

        if (id == null || id.trim().isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please select a class to delete.", ButtonType.OK).show();
            return;
        }

        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Delete Confirmation");
        confirmAlert.setHeaderText(null);
        confirmAlert.setContentText("Are you sure you want to delete the class with ID: " + id + "?");

        Optional<ButtonType> result = confirmAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            boolean isDelete = false;
            try {
                isDelete = studentBO.delete(id);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            if (isDelete) {
                setNextId();
                try {
                    loadtable();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                new Alert(Alert.AlertType.INFORMATION, "Deleted Successfully").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Deleting Failed").show();
            }
        } else {
            new Alert(Alert.AlertType.INFORMATION, "Deletion Cancelled").show();
}


    }

    @FXML
    void handleSavePayment(ActionEvent event) throws Exception {
        int studentId = Integer.parseInt(txtStudentId.getText());
        String name = txtName.getText();
        String email = txtEmail.getText();
        String phone = txtPhone.getText();
        String address = txtAddress.getText();
        double registerFee = Double.parseDouble(txtRegisterFee.getText());
        String registrationDate = dpRegistrationDate.getValue().toString();


        StudentDTO studentDTO = new StudentDTO(
            studentId,
            name,
            email,
            phone,
            address,
            registerFee,
            registrationDate
        );

        boolean isSave = studentBO.save(studentDTO);

        if (isSave) {
            setNextId();
            try {
                loadtable();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            new Alert(Alert.AlertType.INFORMATION, "Saved Successfully").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Saving Failed").show();
        }



    }

    @FXML
    void handleUpdatePayment(ActionEvent event) {

    }

}
