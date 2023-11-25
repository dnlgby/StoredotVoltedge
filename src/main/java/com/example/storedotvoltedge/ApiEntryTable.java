package com.example.storedotvoltedge;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ApiEntryTable extends TableView<ApiEntry> {

    public ApiEntryTable() {
        initializeTable();
    }

    private void initializeTable() {

        setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);


        // Create columns for each property
        TableColumn<ApiEntry, String> apiColumn = new TableColumn<>("API");
        apiColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAPI()));

        TableColumn<ApiEntry, String> descriptionColumn = new TableColumn<>("Description");
        descriptionColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescription()));

        TableColumn<ApiEntry, String> authColumn = new TableColumn<>("Auth");
        authColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAuth()));

        TableColumn<ApiEntry, Boolean> httpsColumn = new TableColumn<>("HTTPS");
        httpsColumn.setCellValueFactory(cellData -> new SimpleBooleanProperty(cellData.getValue().isHTTPS()));

        TableColumn<ApiEntry, String> corsColumn = new TableColumn<>("Cors");
        corsColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCors()));

        TableColumn<ApiEntry, String> linkColumn = new TableColumn<>("Link");
        linkColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLink()));

        TableColumn<ApiEntry, String> categoryColumn = new TableColumn<>("Category");
        categoryColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCategory()));

        // Add columns to the TableView
        getColumns().addAll(apiColumn, descriptionColumn, authColumn, httpsColumn, corsColumn, linkColumn, categoryColumn);
    }
}
