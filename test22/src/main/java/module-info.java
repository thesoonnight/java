module com.example.test22 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.base;
    requires javafx.swing;


    opens com.example.test22 to javafx.fxml;
    exports com.example.test22;
}