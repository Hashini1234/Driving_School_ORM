package org.example.drivingscool.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import org.example.drivingscool.BO.custom.CourseBO;
import org.example.drivingscool.BO.custom.impl.CourseBOImpl;
import org.example.drivingscool.model.CourseDTO;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class CourseManagementController implements Initializable {

    private final CourseBO courseBO = new CourseBOImpl();
    public HBox imagehbox;
    public TextField txtCourseId;
    public TextField txtCourseName;
    public TextField txtDuration;
    public TextField txtFees;
    public Button btnAdd;
    public Button btnUpdate;
    public TableView tblCourses;
    public TableColumn colCourseId;
    public TableColumn colCourseName;
    public TableColumn colDuration;
    public TableColumn colFees;
    public Button btnBackToDashboard;


    private void setCellValueFactory() {
        colCourseId.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        colCourseName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colFees.setCellValueFactory(new PropertyValueFactory<>("fee"));
    }

    private void loadTable() throws SQLException {
        ArrayList<CourseDTO> courseList = (ArrayList<CourseDTO>) courseBO.getAllCourses();
        ObservableList<CourseDTO> data = FXCollections.observableArrayList(courseList);
        tblCourses.setItems(data);
    }

    @FXML
    void handleSaveCourse(ActionEvent event) throws SQLException {
        try {
            CourseDTO course = new CourseDTO(
                    txtCourseName.getText(),
                    txtDuration.getText(),
                    txtFees.getText()
            );

            if (courseBO.save(course)) {
                loadTable();
                new Alert(Alert.AlertType.INFORMATION, "Course Saved Successfully!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Course Saving Failed!").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleUpdateCourse(ActionEvent event) throws SQLException {
        try {
            CourseDTO course = new CourseDTO(
                    Long.parseLong(txtCourseId.getText()),
                    txtCourseName.getText(),
                    txtDuration.getText(),
                    txtFees.getText()
            );

            if (courseBO.update(course)) {
                loadTable();
                new Alert(Alert.AlertType.INFORMATION, "Course Updated Successfully!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Course Update Failed!").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleDeleteCourse(ActionEvent event) throws Exception {
        String id = txtCourseId.getText();
        if (id == null || id.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please select a course to delete.").show();
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Delete course with ID: " + id + "?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = confirm.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.YES) {
            if (courseBO.delete(id)) {
                loadTable();
                new Alert(Alert.AlertType.INFORMATION, "Course Deleted Successfully!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Course Deleting Failed!").show();
            }
        }
    }

    @FXML
    void handleClear(ActionEvent event) {
        txtCourseId.clear();
        txtCourseName.clear();
        txtDuration.clear();
        txtFees.clear();
    }


    @SneakyThrows
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setCellValueFactory();
        loadTable();

    }

    public void clickOnAction(MouseEvent mouseEvent) {
        CourseDTO selected = (CourseDTO) tblCourses.getSelectionModel().getSelectedItem();
        if (selected != null) {
            txtCourseId.setText(String.valueOf(selected.getCourseId()));
            txtCourseName.setText(selected.getName());
            txtDuration.setText(selected.getDuration());
            txtFees.setText(selected.getFee());
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







