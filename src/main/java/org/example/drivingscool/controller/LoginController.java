package org.example.drivingscool.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.concurrent.Task;
import javafx.animation.FadeTransition;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class LoginController implements Initializable {

    @FXML
    private Button btnLogin;

    @FXML
    private Label lblError;

    @FXML
    private Hyperlink linkSignUp;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUsername;

    // Predefined credentials (In a real application, this would be handled by a database)
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "admin123";
    private static final String INSTRUCTOR_USERNAME = "instructor";
    private static final String INSTRUCTOR_PASSWORD = "inst123";
    private static final String STUDENT_USERNAME = "student";
    private static final String STUDENT_PASSWORD = "stud123";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Set up initial state
        lblError.setVisible(false);

        // Add enter key support for login
        setupEnterKeyLogin();

        // Add focus behavior for better UX
        setupFocusBehavior();

        // Setup contact admin link
        setupContactAdminLink();
    }

    @FXML
    void handleLogin(ActionEvent event) {
        String username = txtUsername.getText().trim();
        String password = txtPassword.getText();

        // Reset error state
        hideError();

        // Validate input
        if (username.isEmpty() || password.isEmpty()) {
            showError("Please enter both username and password");
            return;
        }

        // Disable login button to prevent multiple clicks
        btnLogin.setDisable(true);
        btnLogin.setText("Signing In...");

        // Simulate authentication process with a background task
        Task<Boolean> loginTask = new Task<Boolean>() {
            @Override
            protected Boolean call() throws Exception {
                // Simulate network delay
                Thread.sleep(1500);
                return authenticateUser(username, password);
            }

            @Override
            protected void succeeded() {
                Platform.runLater(() -> {
                    if (getValue()) {
                        // Successful login
                        showSuccessMessage("Login successful! Welcome to Drive Master.");

                        // Navigate to appropriate dashboard based on user type
                        CompletableFuture.delayedExecutor(1, TimeUnit.SECONDS)
                                .execute(() -> Platform.runLater(() -> navigateToDashboard(username)));
                    } else {
                        // Failed login
                        showError("Invalid username or password. Please try again.");
                        resetLoginButton();
                    }
                });
            }

            @Override
            protected void failed() {
                Platform.runLater(() -> {
                    showError("Login failed due to system error. Please try again.");
                    resetLoginButton();
                });
            }
        };

        // Run the login task in background
        Thread loginThread = new Thread(loginTask);
        loginThread.setDaemon(true);
        loginThread.start();
    }

    private boolean authenticateUser(String username, String password) {
        // Simple authentication logic (replace with database authentication in real app)
        return (ADMIN_USERNAME.equals(username) && ADMIN_PASSWORD.equals(password)) ||
                (INSTRUCTOR_USERNAME.equals(username) && INSTRUCTOR_PASSWORD.equals(password)) ||
                (STUDENT_USERNAME.equals(username) && STUDENT_PASSWORD.equals(password));
    }

    private void navigateToDashboard(String username) {
        try {
            String fxmlPath;
            String windowTitle;

            // Determine which dashboard to load based on user type
            switch (username) {
                case ADMIN_USERNAME:
                    fxmlPath = "/fxml/AdminDashboard.fxml";
                    windowTitle = "Drive Master - Admin Dashboard";
                    break;
                case INSTRUCTOR_USERNAME:
                    fxmlPath = "/fxml/InstructorDashboard.fxml";
                    windowTitle = "Drive Master - Instructor Dashboard";
                    break;
                case STUDENT_USERNAME:
                    fxmlPath = "/fxml/StudentDashboard.fxml";
                    windowTitle = "Drive Master - Student Dashboard";
                    break;
                default:
                    // Fallback to a general dashboard
                    fxmlPath = "/fxml/Dashboard.fxml";
                    windowTitle = "Drive Master - Dashboard";
                    break;
            }

            // Load the dashboard FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent dashboardRoot = loader.load();

            // Get current stage
            Stage currentStage = (Stage) btnLogin.getScene().getWindow();

            // Create new scene and set it to the stage
            Scene dashboardScene = new Scene(dashboardRoot);
            currentStage.setScene(dashboardScene);
            currentStage.setTitle(windowTitle);
            currentStage.centerOnScreen();

        } catch (IOException e) {
            // If dashboard FXML is not found, show a message
            showError("Dashboard not available. Please contact administrator.");
            resetLoginButton();
            e.printStackTrace();
        }
    }

    private void setupEnterKeyLogin() {
        // Allow Enter key to trigger login
        txtUsername.setOnKeyPressed(this::handleEnterKey);
        txtPassword.setOnKeyPressed(this::handleEnterKey);
    }

    private void handleEnterKey(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            handleLogin(new ActionEvent());
        }
    }

    private void setupFocusBehavior() {
        // Auto-focus on username field when scene is loaded
        Platform.runLater(() -> txtUsername.requestFocus());

        // Move focus to password field when username is filled
        txtUsername.setOnAction(e -> txtPassword.requestFocus());
    }

    private void setupContactAdminLink() {
        linkSignUp.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Contact Administrator");
            alert.setHeaderText("Need an Account?");
            alert.setContentText("To get access to Drive Master system:\n\n" +
                    "📧 Email: admin@drivemaster.com\n" +
                    "📱 Phone: +94 11 234 5678\n" +
                    "🕒 Office Hours: Mon-Fri 8:00 AM - 6:00 PM\n\n" +
                    "Demo Accounts:\n" +
                    "Admin: admin / admin123\n" +
                    "Instructor: instructor / inst123\n" +
                    "Student: student / stud123");
            alert.showAndWait();
        });
    }

    private void showError(String message) {
        lblError.setText(message);
        lblError.setVisible(true);

        // Add fade-in animation
        FadeTransition fadeIn = new FadeTransition(Duration.millis(300), lblError);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();
    }

    private void hideError() {
        lblError.setVisible(false);
    }

    private void showSuccessMessage(String message) {
        // Change error label to success styling temporarily
        lblError.setText(message);
        lblError.setStyle("-fx-text-fill: #27ae60; -fx-background-color: #d5f4e6; " +
                "-fx-background-radius: 8; -fx-padding: 10; -fx-font-size: 14px; -fx-font-weight: 600;");
        lblError.setVisible(true);

        // Add fade-in animation
        FadeTransition fadeIn = new FadeTransition(Duration.millis(300), lblError);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);
        fadeIn.play();
    }

    private void resetLoginButton() {
        btnLogin.setDisable(false);
        btnLogin.setText("Sign In");

        // Reset error label styling
        lblError.setStyle("-fx-text-fill: #e74c3c; -fx-background-color: #ffeaea; " +
                "-fx-background-radius: 8; -fx-padding: 10; -fx-font-size: 14px; -fx-font-weight: 600;");
    }

    // Method to clear form fields (can be called externally if needed)
    public void clearForm() {
        txtUsername.clear();
        txtPassword.clear();
        hideError();
        txtUsername.requestFocus();
    }

    // Getter methods for testing purposes
    public String getUsername() {
        return txtUsername.getText();
    }

    public String getPassword() {
        return txtPassword.getText();
    }
}