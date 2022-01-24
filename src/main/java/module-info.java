module com.example.task8 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.desktop;


    opens com.example.task8 to javafx.fxml;
    exports com.example.task8;
}