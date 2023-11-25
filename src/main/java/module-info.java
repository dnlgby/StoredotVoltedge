module com.example.storedotvoltedge {
    requires javafx.controls;
    requires javafx.fxml;
    requires retrofit2;
    requires retrofit2.converter.gson;
    requires java.sql;
    requires com.google.guice; // Google Guice module

    exports com.example.storedotvoltedge to com.google.guice, javafx.graphics, com.google.gson;

}
