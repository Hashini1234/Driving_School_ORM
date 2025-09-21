package org.example.drivingscool.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class LoginController {

    @FXML private TextField txtUsername;
    @FXML private PasswordField txtPassword;
    @FXML private Button btnLogin;
    @FXML private Label lblError;


    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "admin123";

    @FXML
    void handleLogin(ActionEvent event) {
        String username = txtUsername.getText().trim();
        String password = txtPassword.getText();

        lblError.setVisible(false);

        if (username.isEmpty() || password.isEmpty()) {
            lblError.setText("Enter username & password!");
            lblError.setVisible(true);
            return;
        }

        if (ADMIN_USERNAME.equals(username) && ADMIN_PASSWORD.equals(password)) {
            openDashboard(username);
        } else {
            lblError.setText("Invalid credentials!");
            lblError.setVisible(true);
        }
    }

    private void openDashboard(String username) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Dashboard.fxml"));
            Parent root = loader.load();


            DashboardController controller = loader.getController();
            controller.setWelcomeMessage(username);

            Stage stage = (Stage) btnLogin.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Dashboard - " + username);
            stage.centerOnScreen();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
