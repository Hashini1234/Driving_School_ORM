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
import javafx.stage.Stage;
import org.example.drivingscool.BO.custom.BOFactory;
import org.example.drivingscool.BO.custom.PaymentBO;
import org.example.drivingscool.model.PaymentDTO;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

public class PaymentManageController implements Initializable {
    private final PaymentBO paymentBO = (PaymentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PAYMENT);

    public ComboBox <String>cmbMethod;
    @FXML
    private Button btnBackToDashboard;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private ComboBox<String> cmbCourseID;

    @FXML
    private ComboBox<String> cmbStudentID;

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
    private DatePicker dpDate;

    @FXML
    private TableView<PaymentDTO> tblPayments;

    @FXML
    private TextField txtAmount;

    @FXML
    private TextField txtMethod;

    @FXML
    private TextField txtPaymentId;

    @FXML
    void handleBackToDashboard(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/DashBoard.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();

    }

    @FXML
    void handleClear(ActionEvent event) {
        clearFields();

    }

    @FXML
    void handleDeletePayment(ActionEvent event) {
        try {
            String id = txtPaymentId.getText();
            if (id.isEmpty()) {
                showError("Please select a payment to delete!"); // Warn if nothing selected
                return;
            }

            // Delete payment safely using BO
            if (paymentBO.deletePayment(id)) {
                showInfo("Payment deleted successfully!");
                loadAllPayments();
                clearFields();
            } else {
                showError("Payment not found or cannot be deleted!");
            }
        } catch (Exception e) {
            showError("Error deleting payment: " + e.getMessage()); // Show detailed error
        }

    }

    @FXML
    void handleSavePayment(ActionEvent event) {
        try {
            PaymentDTO dto = new PaymentDTO(
                    Date.valueOf(dpDate.getValue()),
                    cmbMethod.getSelectionModel().getSelectedItem(),
                    txtAmount.getText(),
                    Long.parseLong(cmbStudentID.getSelectionModel().getSelectedItem()),
                    Long.parseLong(cmbCourseID.getSelectionModel().getSelectedItem())
            );
            if (paymentBO.savePayment(dto)) {
                showInfo("Payment added successfully!");
                loadAllPayments();
                clearFields();
            }
        } catch (Exception e) {
            showError("Error saving payment: " + e.getMessage());
        }

    }

    private void showError(String s) {
        new Alert(Alert.AlertType.ERROR, s).show();

    }

    private void clearFields() {
        txtPaymentId.clear();
        dpDate.setValue(null);
        txtAmount.clear();
        cmbMethod.getSelectionModel().clearSelection();
        cmbStudentID.getSelectionModel().clearSelection();
        cmbCourseID.getSelectionModel().clearSelection();

    }

    private void loadAllPayments() {
        try {
            List<PaymentDTO> all = paymentBO.findAll();
            ObservableList<PaymentDTO> list = FXCollections.observableArrayList();
            for (PaymentDTO dto : all) {
                list.add(new PaymentDTO(
                        dto.getPaymentId(),
                        dto.getDate(),
                        dto.getMethod(),
                        dto.getAmount(),
                        dto.getStudentID(),
                        dto.getCourseID()
                ));
            }
            tblPayments.setItems(list);
        } catch (Exception e) {
            showError("Error loading payments: " + e.getMessage());
        }

    }

    private void showInfo(String s) {
        new Alert(Alert.AlertType.INFORMATION, s).show();

    }

    @FXML
    void handleUpdatePayment(ActionEvent event) {
        try {
            long id = Long.parseLong(txtPaymentId.getText());
            PaymentDTO dto = new PaymentDTO(
                    id,
                    Date.valueOf(dpDate.getValue()),
                    cmbMethod.getSelectionModel().getSelectedItem(),
                    txtAmount.getText(),
                    Long.parseLong(cmbStudentID.getSelectionModel().getSelectedItem()),
                    Long.parseLong(cmbCourseID.getSelectionModel().getSelectedItem())
            );
            if (paymentBO.updatePayment(dto)) {
                showInfo("Payment updated successfully!");
                loadAllPayments();
                clearFields();
            }
        } catch (Exception e) {
            showError("Error updating payment: " + e.getMessage());
        }

    }

    public void clickOnAction(MouseEvent mouseEvent) {
        PaymentDTO selected = (PaymentDTO) tblPayments.getSelectionModel().getSelectedItem();
        if (selected != null) {
            txtPaymentId.setText(String.valueOf(selected.getPaymentId()));
            dpDate.setValue(selected.getDate().toLocalDate());
            txtAmount.setText(selected.getAmount());
            cmbMethod.getSelectionModel().select(selected.getMethod());
            cmbStudentID.getSelectionModel().select(String.valueOf(selected.getStudentID()));
            cmbCourseID.getSelectionModel().select(String.valueOf(selected.getCourseID()));
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colPaymentId.setCellValueFactory(new PropertyValueFactory<>("paymentId"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colMethod.setCellValueFactory(new PropertyValueFactory<>("method"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colStudentId.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        colCourseId.setCellValueFactory(new PropertyValueFactory<>("courseID"));

        loadAllPayments();

        cmbMethod.setItems(FXCollections.observableArrayList("Cash", "Card").sorted());
        loadStudentIds();
        loadCourseIds();
    }

    private void loadCourseIds() {
        try {
            List<String> ids = paymentBO.getAllCourseIds();
            cmbCourseID.setItems(FXCollections.observableArrayList(ids));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadStudentIds() {
        try {
            List<String> ids = paymentBO.getAllStudentIds();
            cmbStudentID.setItems(FXCollections.observableArrayList(ids));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    }

