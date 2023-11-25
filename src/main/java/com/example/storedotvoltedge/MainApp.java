package com.example.storedotvoltedge;

import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainApp extends Application {

    private Injector injector;

    @Override
    public void init() {
        injector = Guice.createInjector(new AppModule());
    }

    @Override
    public void start(Stage primaryStage) {
        MainViewModel viewModel = injector.getInstance(MainViewModel.class);
        Button btnLoad = new Button("Load Data");
        btnLoad.setOnAction(event -> viewModel.loadData());

        // Create an instance of ApiEntryTable
        ApiEntryTable apiEntryTable = new ApiEntryTable();

        // Set the data to the ApiEntryTable
        apiEntryTable.setItems(viewModel.getData());

        VBox vBox = new VBox(btnLoad, apiEntryTable);
        VBox.setVgrow(apiEntryTable, Priority.ALWAYS); // This line makes the table fill the available height

        Scene scene = new Scene(vBox, 1200, 800);
        primaryStage.setTitle("StoreDot VoltEdge");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
