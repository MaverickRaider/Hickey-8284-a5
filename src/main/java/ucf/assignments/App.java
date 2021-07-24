/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Michael Hickey
 */

package ucf.assignments;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    private Stage primaryStage;

    private final ObservableList<Item> itemData = FXCollections.observableArrayList();

    public App() {
    }

    public ObservableList<Item> getItemData() {
        return itemData;
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Inventory Manager");
        showApp();
    }

    public void showApp() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("App.fxml"));
            BorderPane appLayout = loader.load();

            Scene scene = new Scene(appLayout);
            primaryStage.setScene(scene);
            primaryStage.show();

            MainWindowController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean showAddLayout(Item item) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("AddItemWindow.fxml"));
            GridPane page = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Add Item");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the task into the controller
            AddItemWindowController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setItem(item);
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }



    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
