package com.example.storedotvoltedge;

import com.example.storedotvoltedge.di.AppModule;
import com.example.storedotvoltedge.models.ApiEntry;
import com.example.storedotvoltedge.ui.ApiEntryTable;
import com.example.storedotvoltedge.viewmodel.MainViewModel;
import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyListProperty;
import javafx.collections.ObservableList;
import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainApp extends Application {

    private Injector injector;
    private MainViewModel viewModel;

    @Override
    public void init() {
        injector = Guice.createInjector(new AppModule());
        viewModel = injector.getInstance(MainViewModel.class);
    }

    private Scene initUI(){
        // Create a button to load the data
        Button btnLoad = new Button("Load Data");
        btnLoad.setOnAction(event -> viewModel.loadData());

        // Create an instance of ApiEntryTable
        ApiEntryTable apiEntryTable = new ApiEntryTable();

        // Set the data to the ApiEntryTable
        apiEntryTable.setItems(viewModel.getDataProperty());

        // Create a VBox to hold the button and the ApiEntryTable
        VBox vBox = new VBox(btnLoad, apiEntryTable);

        // This line makes the table fill the available height
        VBox.setVgrow(apiEntryTable, Priority.ALWAYS);

        // Create a scene with the VBox as the root node
        return new Scene(vBox, 1200, 800);
    }

    @Override
    public void start(Stage primaryStage) {
        // Create the UI
        Scene scene = initUI();

        // Set the title and show the stage
        primaryStage.setTitle("StoreDot VoltEdge");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
