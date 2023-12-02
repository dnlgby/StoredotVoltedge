package com.example.storedotvoltedge;

import com.example.storedotvoltedge.di.AppModule;
import com.example.storedotvoltedge.models.Action;
import com.example.storedotvoltedge.models.ApiEntry;
import com.example.storedotvoltedge.ui.ApiEntryTable;
import com.example.storedotvoltedge.viewmodel.MainViewModel;
import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainApp extends Application {

    private Injector mInjector;
    private MainViewModel mViewModel;
    private ListView<String> mListView;
    private ProgressIndicator progressIndicator;

    @Override
    public void init() {
        mInjector = Guice.createInjector(new AppModule());
        mViewModel = mInjector.getInstance(MainViewModel.class);
    }

    private MenuBar buildMenu() {
        // Create a menu bar
        MenuBar menuBar = new MenuBar();

        // Create menus
        Menu fileMenu = new Menu("File");
        Menu helpMenu = new Menu("Help");

        // Create menu items
        MenuItem exitMenuItem = new MenuItem("Exit");
        MenuItem aboutMenuItem = new MenuItem("About");

        // Add event handlers to the menu items
        exitMenuItem.setOnAction(actionEvent -> Platform.exit());
        aboutMenuItem.setOnAction(actionEvent -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("About");
            alert.setHeaderText("StoreDot Voltedge");
            alert.setContentText("Initial Version of StoreDot Voltedge Application");
            Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
            alertStage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
            alert.showAndWait();
        });

        // Add menu items to the menus
        fileMenu.getItems().add(exitMenuItem);
        helpMenu.getItems().add(aboutMenuItem);

        // Add menus to the menu bar
        menuBar.getMenus().addAll(fileMenu, helpMenu);

        return menuBar;
    }

    private Scene initUI() {
        BorderPane root = new BorderPane();

        // Create the menu bar and set it to the top of the BorderPane
        MenuBar menuBar = buildMenu();
        root.setTop(menuBar);

        // Initialize the HBox for the main content
        HBox hBox = new HBox(10);

        // Create the ApiEntryTable and ListView
        ApiEntryTable apiEntryTable = new ApiEntryTable();
        apiEntryTable.setPlaceholder(new Label("No content"));
        // Initialize the ProgressIndicator
        progressIndicator = new ProgressIndicator();
        progressIndicator.setVisible(false); // Initially hidden

        // Overlay the ProgressIndicator on the ApiEntryTable or place it appropriately
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(apiEntryTable, progressIndicator);
        HBox.setHgrow(stackPane, Priority.ALWAYS);

        ObservableList<String> a = FXCollections.observableArrayList();
        a.addAll("Cells", "Slurry", "Foil", "Material", "Tests");
        mListView = new ListView<>();
        mListView.setItems(a);
        mListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        mListView.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            switch (newValue) {
                case "Cells":
                    //apiEntryTable.setItems(mViewModel.getCellsDataProperty());
                    break;
                case "Slurry":
                    //apiEntryTable.setItems(mViewModel.getSlurryDataProperty());
                    break;
                case "Foil":
                    //apiEntryTable.setItems(mViewModel.getFoilDataProperty());
                    break;
                case "Material":
                    //apiEntryTable.setItems(mViewModel.getMaterialDataProperty());
                    break;
                case "Tests":
                    apiEntryTable.setPlaceholder(new Label(""));
                    progressIndicator.setVisible(true); // Show the progress indicator
                    mViewModel.loadApiEntryData();
                    break;
            }
            System.out.println("Selected item: " + newValue);
        });

        // Set the data to the ApiEntryTable
        mViewModel.actionProperty().addListener((observableValue, oldValue, newValue) -> {
            Platform.runLater(() -> {
                if (newValue.getStatus() == Action.ACTION_SUCCESS) {
                    List<ApiEntry> apiEntries = (List<ApiEntry>) newValue.getActionResult();
                    apiEntryTable.setItems(FXCollections.observableArrayList(apiEntries));
                    progressIndicator.setVisible(false); // Hide the progress indicator
                    apiEntryTable.setPlaceholder(new Label("No content")); // Sets an empty label as placeholder

                } else if (newValue.getStatus() == Action.ACTION_FAILURE) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Error loading data from the API");
                    alert.setContentText(newValue.getErrorMessage());
                    Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
                    alertStage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
                    alert.showAndWait();
                }
                progressIndicator.setVisible(false); // Hide the progress indicator
            });
        });

        // Set growth and margin for the table and list view
        HBox.setHgrow(apiEntryTable, Priority.ALWAYS);
        HBox.setMargin(apiEntryTable, new Insets(10)); // 20px margin on all sides
        HBox.setMargin(mListView, new Insets(10));    // 20px margin on all sides

        // Add the ListView and the stack pane (containing ApiEntryTable and ProgressIndicator) to the HBox
        hBox.getChildren().addAll(mListView, stackPane);

        // Set the HBox as the center of the BorderPane
        root.setCenter(hBox);

        // Create and return the scene
        return new Scene(root, 1800, 1200);
    }

    @Override
    public void start(Stage primaryStage) {
        // Create the splash stage
        Stage splashStage = new Stage(StageStyle.UNDECORATED);
        ImageView splashImage = new ImageView(new Image(getClass().getResourceAsStream("splash.png")));

        Scene splashScene = new Scene(new HBox(splashImage), 1000, 1000); // Adjust the size as needed
        splashStage.setScene(splashScene);
        splashStage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));

        // Show the splash screen and then close it after 1 second
        Timer splashTimer = new Timer();
        splashTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    splashStage.close();
                    // Initialize and show the main application stage
                    Scene scene = initUI();
                    primaryStage.setTitle("StoreDot Voltedge");
                    primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
                    primaryStage.setScene(scene);
                    primaryStage.show();
                });
            }
        }, 1500); // 1500 milliseconds for 1.5 seconds

        splashStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}