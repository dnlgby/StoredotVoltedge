module com.example.storedotvoltedge {
    requires javafx.controls;
    requires javafx.fxml;
    requires retrofit2;
    requires retrofit2.converter.gson;
    requires java.sql;
    requires com.google.guice; // Google Guice module

    exports com.example.storedotvoltedge to com.google.guice, javafx.graphics, com.google.gson;
    exports com.example.storedotvoltedge.repository to com.google.gson, com.google.guice, javafx.graphics;
    exports com.example.storedotvoltedge.models to com.google.gson, com.google.guice, javafx.graphics;
    exports com.example.storedotvoltedge.di to com.google.gson, com.google.guice, javafx.graphics;
    exports com.example.storedotvoltedge.ui to com.google.gson, com.google.guice, javafx.graphics;
    exports com.example.storedotvoltedge.api to com.google.gson, com.google.guice, javafx.graphics;
    exports com.example.storedotvoltedge.viewmodel to com.google.gson, com.google.guice, javafx.graphics;

}
