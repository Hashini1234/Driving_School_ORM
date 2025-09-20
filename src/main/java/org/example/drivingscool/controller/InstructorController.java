package org.example.drivingscool.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.example.drivingscool.BO.custom.InstructorBO;
import org.example.drivingscool.BO.custom.impl.InstructorBOImpl;
import org.example.drivingscool.model.InstructorDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;




public class InstructorController {
    private final InstructorBO instructorBO = new InstructorBOImpl();

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colAvailability;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colInstructorId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPhone;

    @FXML
    private TableView<InstructorDTO> tblInstructor;

    @FXML
    private TextField txtAvailability;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtInstructorId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPhone;


    public void initialize() throws SQLException {
        setCellValueFactory();
        loadTable();
    }

    private void setCellValueFactory() {
        colInstructorId.setCellValueFactory(new PropertyValueFactory<>("instructorId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colAvailability.setCellValueFactory(new PropertyValueFactory<>("availability"));
    }

    private void loadTable() throws SQLException {
        ArrayList<InstructorDTO> instructorList = (ArrayList<InstructorDTO>) instructorBO.getAllInstructor();
        ObservableList<InstructorDTO> data = FXCollections.observableArrayList(instructorList);
        tblInstructor.setItems(data);
    }

    public void handleClearInstructor(ActionEvent actionEvent) {
        txtInstructorId.clear();
        txtName.clear();
        txtEmail.clear();
        txtPhone.clear();
        txtAvailability.clear();
    }

    public void handleUpdateInstructor(ActionEvent actionEvent) {
        try {
            // Validate ID
            String idText = txtInstructorId.getText();
            if (idText == null || idText.isEmpty() || !idText.matches("\\d+")) {
                new Alert(Alert.AlertType.ERROR, "Invalid Instructor ID! Please select a valid instructor from the table.").show();
                return;
            }

            long id = Long.parseLong(idText);

            InstructorDTO instructor = new InstructorDTO(
                    id,
                    txtName.getText(),
                    txtEmail.getText(),
                    txtPhone.getText(),
                    txtAvailability.getText()
            );

            if (instructorBO.update(instructor)) {
                loadTable();
                new Alert(Alert.AlertType.INFORMATION, "Updated Successfully!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Update Failed!").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Something went wrong while updating!").show();
        }
    }





    public void handleDeleteInstructor(ActionEvent actionEvent) throws Exception {
        String id = txtInstructorId.getText();
        if (id == null || id.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please select an instructor to delete.").show();
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Delete instructor with ID: " + id + "?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = confirm.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.YES) {
            if (instructorBO.delete(id)) {
                loadTable();
                new Alert(Alert.AlertType.INFORMATION, "Deleted Successfully!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Deleting Failed!").show();
            }
        }
    }


    public void handleSaveInstructor(ActionEvent actionEvent) {
        try {
            InstructorDTO instructor = new InstructorDTO(
                    txtName.getText(),
                    txtEmail.getText(),
                    txtPhone.getText(),
                    txtAvailability.getText()
            );

            if (instructorBO.save(instructor)) {
                loadTable();
                new Alert(Alert.AlertType.INFORMATION, "Saved Successfully!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Saving Failed!").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void tableClickOnAction(MouseEvent mouseEvent) {
        InstructorDTO selected = tblInstructor.getSelectionModel().getSelectedItem();
        if (selected != null) {
            txtInstructorId.setText(String.valueOf(selected.getInstructorId()));
            txtName.setText(selected.getName());
            txtEmail.setText(selected.getEmail());
            txtPhone.setText(selected.getPhone());
            txtAvailability.setText(selected.getAvailability());
        }
    }

    }



