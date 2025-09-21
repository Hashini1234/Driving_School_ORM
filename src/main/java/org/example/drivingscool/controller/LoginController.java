package org.example.drivingscool.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.drivingscool.DAO.DAOFactory;
import org.example.drivingscool.DAO.custom.UserDAO;
import org.example.drivingscool.DAO.custom.impl.UserDAOImpl;
import org.example.drivingscool.entity.User;

public class LoginController {

    @FXML
    private Button btnLogin;

    @FXML
    private CheckBox chkRememberMe;

    @FXML
    private Label lblError;

    @FXML
    private Hyperlink linkForgotPassword;

    @FXML
    private VBox loginCard;

    @FXML
    private Hyperlink signUpLink;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUsername;

    UserDAO userDAO = (UserDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.USER);

    @FXML
    void loginOnAction(ActionEvent event) {
        String username = txtUsername.getText();
        String password = txtPassword.getText();

        try {
            User user = userDAO.search(username);
            if (user != null) {
                if (user.getPassword().equals(password)) {
                    new Alert(Alert.AlertType.INFORMATION, "Login Successfully").show();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DashBoard.fxml"));
                    Parent parent = loader.load();
                    Stage stage = (Stage) btnLogin.getScene().getWindow();
                    stage.setScene(new Scene(parent));
                    stage.centerOnScreen();
                    stage.show();
                }

            }else {
                new Alert(Alert.AlertType.ERROR, "Invalid username or password").show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void signUpOnAction(ActionEvent event) {

    }

}
