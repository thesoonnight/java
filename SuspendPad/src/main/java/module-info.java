module com.bit702.suspendpad {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.swing;
    requires tess4j;


    opens com.bit702.suspendpad to javafx.fxml;
    exports com.bit702.suspendpad;
}