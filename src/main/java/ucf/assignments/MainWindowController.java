package ucf.assignments;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.math.BigDecimal;

public class MainWindowController {
    @FXML
    private TableView<Item> itemsTableView;

    private App mainApp;

    public MainWindowController() {
    }

    @FXML
    private void initialize() {
    }

    public void setMainApp(App mainApp) {
        this.mainApp = mainApp;
        itemsTableView.setItems(mainApp.getItemData());
    }

    @FXML
    public void addButtonClicked() {
        handleAddItem();
    }
    private void handleAddItem(){

    }

    @FXML
    private void deleteButtonClicked() {
        handleDeleteItem();
    }
    private void handleDeleteItem() {
        int selectedIndex = itemsTableView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            itemsTableView.getItems().remove(selectedIndex);
        } else {
            errorReport();
        }
    }

    @FXML
    public void saveAsMenuClicked() {
        handleSaveAs();
    }
    private void handleSaveAs() {
        /**
         * save to file
         */
    }

    @FXML
    public void loadMenuClicked() {
        handleLoad();
    }
    private void handleLoad() {
        /**
         * load list from file
         */
    }

    @FXML
    public void closedMenuClicked() {
        handleClose();
    }
    private void handleClose() {
        System.exit(0);
    }

    @FXML
    public void searchMenuClicked() {
        handleSearch();
    }
    private void handleSearch() {

    }

    @FXML
    public void aboutMenuClicked() {
        handleAbout();
    }
    private void handleAbout() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Inventory Manager");
        alert.setHeaderText("About");
        alert.setContentText("This is an Inventory Manager");
        alert.showAndWait();
    }

    private void errorReport() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initOwner(mainApp.getPrimaryStage());
        alert.setTitle("No Selection");
        alert.setHeaderText("No Item Selected");
        alert.setContentText("Please select an Item in the table.");

        // Waits for the user to close the error out
        alert.showAndWait();
    }

}
