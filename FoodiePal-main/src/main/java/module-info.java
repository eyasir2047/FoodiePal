module com.example.foodiepal {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens com.example.foodiepal to javafx.fxml;
    exports com.example.foodiepal;
}