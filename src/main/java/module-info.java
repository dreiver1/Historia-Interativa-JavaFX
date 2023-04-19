module com.example.historiainterativap2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;

    opens com.example.historiainterativap2 to javafx.fxml, com.google.gson;
    exports com.example.historiainterativap2;
}