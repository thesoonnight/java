module com.bit702.mydemo {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.bit702.mydemo to javafx.fxml;
    exports com.bit702.mydemo;
}