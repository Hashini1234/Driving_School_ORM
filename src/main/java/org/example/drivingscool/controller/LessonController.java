package org.example.drivingscool.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.example.drivingscool.BO.custom.BOFactory;
import org.example.drivingscool.BO.custom.InstructorBO;
import org.example.drivingscool.BO.custom.LessonBO;
import org.example.drivingscool.model.LessonDTO;

import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

public class LessonController implements Initializable {
    private final LessonBO lessonBO = (LessonBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.LESSON);
    private final InstructorBO instructorBO = (InstructorBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.INSTRUCTOR);
    public DatePicker dpDate;
    @FXML
    private Button btnBackToDashboard;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private ComboBox<String> cbStudentId;

    @FXML
    private ComboBox<String> cmCourseId;

    @FXML
    private ComboBox<String> cmInstructorId;

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
    private TableView<LessonDTO> tblLesson;

    @FXML
    private TextField txtDate;

    @FXML
    private TextField txtLessonId;

    @FXML
    private TextField txtStatus;

    @FXML
    private TextField txtTime;

    @FXML
    void handleBackToDashboard(ActionEvent event) {


    }

    @FXML
    void handleClear(ActionEvent event) {
        clearFields();

    }

    @FXML
    void handleDeletePayment(ActionEvent event) {
        try {
            if (lessonBO.deleteLesson(txtLessonId.getText())) {
                showInfo("Lesson deleted successfully!");
                loadAllLessons();
                clearFields();
            }
        } catch (Exception e) {
            showError("Error deleting Lesson: " + e.getMessage());
        }

    }

    @FXML
    void handleSavePayment(ActionEvent event) {
        try {
            LessonDTO dto = new LessonDTO(
                    Date.valueOf(dpDate.getValue()),
                    txtTime.getText(),
                    txtStatus.getText(),
                    Long.parseLong( cbStudentId.getSelectionModel().getSelectedItem()),
                    Long.parseLong( cmCourseId.getSelectionModel().getSelectedItem()),
                    Long.parseLong( cmInstructorId.getSelectionModel().getSelectedItem())
            );
            if (lessonBO.saveLesson(dto)) {
                showInfo("Lesson added successfully!");
                loadAllLessons();
                clearFields();
            }
        } catch (Exception e) {
            showError("Error saving Lesson: " + e.getMessage());
        }

    }

    private void showError(String s) {
        new Alert(Alert.AlertType.ERROR, s).show();
    }

    private void clearFields() {
        txtLessonId.clear();
        dpDate.setValue(null);
        txtTime.clear();
        txtStatus.clear();
        cbStudentId.getSelectionModel().clearSelection();
        cmCourseId.getSelectionModel().clearSelection();
        cmInstructorId.getSelectionModel().clearSelection();
        
    }

    private void loadAllLessons() {
        try {
            List<LessonDTO> all = lessonBO.findAll();
            ObservableList<LessonDTO> list = FXCollections.observableArrayList();
            for (LessonDTO dto : all) {
                list.add(new LessonDTO(
                        dto.getLessonID(),
                        dto.getDate(),
                        dto.getTime(),
                        dto.getStatus(),
                        dto.getStudentID(),
                        dto.getCourseID(),
                        dto.getInstructorID()
                ));
            }
            tblLesson.setItems(list);
        } catch (Exception e) {
            showError("Error loading Lessons: " + e.getMessage());
        }
        
    }

    private void showInfo(String s) {
        new Alert(Alert.AlertType.INFORMATION, s).show();
        
    }

    @FXML
    void handleUpdateLesson(ActionEvent event) {

        try {
            long id = Long.parseLong(txtLessonId.getText());
            LessonDTO dto = new LessonDTO(
                    id,
                    Date.valueOf(dpDate.getValue()),
                    txtTime.getText(),
                    txtStatus.getText(),
                    Long.parseLong( cbStudentId.getSelectionModel().getSelectedItem()),
                    Long.parseLong( cmCourseId.getSelectionModel().getSelectedItem()),
                    Long.parseLong( cmInstructorId.getSelectionModel().getSelectedItem())
            );
            if (lessonBO.updateLesson(dto)) {
                showInfo("Lesson updated successfully!");
                loadAllLessons();
                clearFields();
            }
        } catch (Exception e) {
            showError("Error updating Lesson: " + e.getMessage());
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colLessonId.setCellValueFactory(new PropertyValueFactory<>("lessonID"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        colCourseId.setCellValueFactory(new PropertyValueFactory<>("courseID"));
        colInstructorId.setCellValueFactory(new PropertyValueFactory<>("instructorID"));

        loadAllLessons();
        loadCourseIds();
        loadInstructorIds();
        loadStudentIds();


    }

    public void ClickOnAction(MouseEvent mouseEvent) {
        LessonDTO selected = (LessonDTO) tblLesson.getSelectionModel().getSelectedItem();
        if (selected != null) {
            txtLessonId.setText(String.valueOf(selected.getLessonID()));
            dpDate.setValue(selected.getDate().toLocalDate());
            txtTime.setText(selected.getTime());
            txtStatus.setText(selected.getStatus());
            cbStudentId.getSelectionModel().select(String.valueOf(selected.getStudentID()));
            cmCourseId.getSelectionModel().select(String.valueOf(selected.getCourseID()));
            cmInstructorId.getSelectionModel().select(String.valueOf(selected.getInstructorID()));
        }
    }
    private void loadInstructorIds() {
        try {
            List<String> ids = lessonBO.getAllInstructorIds();
            ObservableList<String> list = FXCollections.observableArrayList(ids);
            cmInstructorId.setItems(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void loadCourseIds() {
        try {
            List<String> ids = lessonBO.getAllCourseIds();
            ObservableList<String> list = FXCollections.observableArrayList(ids);
            cmCourseId.setItems(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void loadStudentIds() {
        try {
            List<String> ids = lessonBO.getAllStudentIds();
            ObservableList<String> list = FXCollections.observableArrayList(ids);
            cbStudentId.setItems(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
