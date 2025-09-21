package org.example.drivingscool.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.example.drivingscool.BO.custom.UserBO;
import org.example.drivingscool.BO.custom.impl.UserBOImpl;
import org.example.drivingscool.model.UserDTO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class UserController {
    private final UserBO userBO = new UserBOImpl();
    public Button btnBackToDashboard;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colPassword;

    @FXML
    private TableColumn<?, ?> colRole;

    @FXML
    private TableColumn<?, ?> colUserId;

    @FXML
    private TableColumn<?, ?> colUserName;

    @FXML
    private TableView<UserDTO> tblUsers;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtRole;

    @FXML
    private TextField txtUserId;

    @FXML
    private TextField txtUserName;

    @FXML
    public void initialize() throws SQLException {
        setCellValueFactory();
        loadTable();
    }

    private void setCellValueFactory() {
        colUserId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        colUserName.setCellValueFactory(new PropertyValueFactory<>("userName"));
        colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        colRole.setCellValueFactory(new PropertyValueFactory<>("role"));
    }

    private void loadTable() throws SQLException {
        ArrayList<UserDTO> userList = (ArrayList<UserDTO>) userBO.getAllUsers();
        ObservableList<UserDTO> data = FXCollections.observableArrayList(userList);
        tblUsers.setItems(data);
    }

    @FXML
    void handleClear(ActionEvent event) {
        txtUserId.clear();
        txtUserName.clear();
        txtPassword.clear();
        txtRole.clear();

    }

    @FXML
    void handleDeleteUser(ActionEvent event) throws Exception {
        String id = txtUserId.getText();
        if (id == null || id.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please select a user to delete.").show();
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Delete user with ID: " + id + "?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = confirm.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.YES) {
            if (userBO.delete(id)) {
                loadTable();
                new Alert(Alert.AlertType.INFORMATION, "User Deleted Successfully!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "User Delete Failed!").show();
            }
        }

    }

    @FXML
    void handleSaveUser(ActionEvent event) throws Exception {
        UserDTO user = new UserDTO(
                txtUserName.getText(),
                txtPassword.getText(),
                txtRole.getText()
        );

        if (userBO.save(user)) {
            loadTable();
            new Alert(Alert.AlertType.INFORMATION, "User Saved Successfully!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "User Save Failed!").show();
        }

    }

    @FXML
    void handleUpdateUser(ActionEvent event) throws SQLException, ClassNotFoundException {
        UserDTO user = new UserDTO(
                Long.parseLong(txtUserId.getText()),
                txtUserName.getText(),
                txtPassword.getText(),
                txtRole.getText()
        );

        if (userBO.update(user)) {
            loadTable();
            new Alert(Alert.AlertType.INFORMATION, "User Updated Successfully!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "User Update Failed!").show();
        }

    }

    public void tableClick(MouseEvent mouseEvent) {
        UserDTO selected = tblUsers.getSelectionModel().getSelectedItem();
        if (selected != null) {
            txtUserId.setText(String.valueOf(selected.getUserId()));
            txtUserName.setText(selected.getUserName());
            txtPassword.setText(selected.getPassword());
            txtRole.setText(selected.getRole());
        }
    }

    @FXML
    private void handleBackToDashboard(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DashBoard.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}

