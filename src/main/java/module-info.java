module com.example.lab01_fx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.lab01_fx to javafx.fxml;
    exports com.example.lab01_fx;
}