module com.example.foodiepal {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.foodiepal to javafx.fxml;
    exports com.example.foodiepal;
}