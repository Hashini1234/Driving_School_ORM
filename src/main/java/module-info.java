module org.example.drivingscool {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.drivingscool to javafx.fxml;
    exports org.example.drivingscool;
}