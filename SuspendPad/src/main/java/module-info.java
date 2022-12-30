module com.bit702.suspendpad {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.swing;
    requires tess4j;
    requires com.google.gson;
    requires java.sql;

    opens com.bit702.suspendpad;
    exports com.bit702.suspendpad;
}