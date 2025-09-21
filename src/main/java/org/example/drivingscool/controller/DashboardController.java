package org.example.drivingscool.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class DashboardController {

    @FXML private Label lblWelcome;

    // Set welcome message from login
    public void setWelcomeMessage(String username) {
        lblWelcome.setText("Welcome, " + username + "!");
    }

    @FXML
    void handleLogout(ActionEvent event) {
        // Close current dashboard and go back to login
        Stage stage = (Stage) lblWelcome.getScene().getWindow();
        stage.close();

        try {
            javafx.scene.Parent root = javafx.fxml.FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
            stage.setScene(new javafx.scene.Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
