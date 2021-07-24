package ucf.assignments;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MainWindowController {
    @FXML
    private TableView<Item> itemsTableView;
    @FXML
    private TableColumn<Item, String> itemsSerialColumn;
    @FXML
    private TableColumn<Item, String> itemsNameColumn;
    @FXML
    private TableColumn<Item, String> itemsValueColumn;
    @FXML
    private TextField searchBox;


    private App mainApp;

    public MainWindowController() {
    }

    @FXML
    private void initialize() {
        itemsSerialColumn.setCellValueFactory(cellData -> cellData.getValue().itemSerialNumberProperty());
        itemsNameColumn.setCellValueFactory(cellData -> cellData.getValue().itemNameProperty());
        itemsValueColumn.setCellValueFactory(cellData -> cellData.getValue().itemValueProperty());
    }

    public void setMainApp(App mainApp) {
        this.mainApp = mainApp;
        itemsTableView.setItems(mainApp.getItemData());

        FilteredList<Item> filteredData = new FilteredList<>(mainApp.getItemData());
        itemsTableView.setItems(filteredData);

        searchBox.textProperty().addListener((observable, oldValue, newValue) ->
                filteredData.setPredicate(item -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }

                    String lowerCaseFilter = newValue.toLowerCase();
                    if (item.getItemName().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    else if (item.getItemSerialNumber().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    else {
                        return false;
                    }
                }));
        SortedList<Item> sortedList = new SortedList<>(filteredData);

        itemsTableView.setItems(sortedList);
    }

    @FXML
    public void addButtonClicked() {
        handleAddItem();
    }
    private void handleAddItem(){
        Item tempItem = new Item();
        boolean okClicked = mainApp.showAddLayout(tempItem);
        if (okClicked) {
            mainApp.getItemData().add(tempItem);
        }
    }
    @FXML
    public void editButtonClicked() {
        handleEditItem();
    }
    private void handleEditItem(){
        Item selectedItem = itemsTableView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            selectedItem.setItemValue(editItemValueFormatter(selectedItem.getItemValue()));
            boolean okClicked = mainApp.showAddLayout(selectedItem);
            if (okClicked) {
                mainApp.showAddLayout(selectedItem);
            }
        } else {
            errorReport();
        }
    }
    private String editItemValueFormatter (String str) {
        String newStr = str.replaceAll("\\$", "");
        newStr = newStr.replaceAll("\\.", "");
        return newStr;
    }

    @FXML
    private void deleteButtonClicked() {
        handleDeleteItem();
    }
    private void handleDeleteItem() {
        Item selectedItem = itemsTableView.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            mainApp.getItemData().remove(selectedItem);
        } else {
            errorReport();
        }
    }

    @FXML
    public void saveAsMenuClicked() {
        handleSaveAs();
    }
    private void handleSaveAs() {
        Stage saveStage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save As");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        if(mainApp.getItemData().isEmpty()) {
            saveErrorReport();
        }
        else {
            File file = fileChooser.showSaveDialog(saveStage);
            if(file != null) {
                saveFile(itemsTableView.getItems(), file);
            }
        }
    }

    public void saveFile(ObservableList<Item> itemList, File file) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for(Item item : itemList) {
                writer.write(item.toString());
                writer.newLine();
            }
            System.out.println(itemList);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            saveErrorReport();
        }
    }

    @FXML
    public void loadMenuClicked() {
        handleLoad();
    }
    private void handleLoad() {
        Stage openStage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        File file = fileChooser.showSaveDialog(openStage);
        openFile(file);
    }
    public void openFile(File file) {
        //itemsTableView.setItems();
    }

    @FXML
    public void closedMenuClicked() {
        handleClose();
    }
    private void handleClose() {
        System.exit(0);
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
        alert.showAndWait();
    }
    private void saveErrorReport() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initOwner(mainApp.getPrimaryStage());
        alert.setTitle("Save Error");
        alert.setHeaderText("There was an Issue Saving your File.");
        alert.setContentText("Please review the table and try again.");
        alert.showAndWait();
    }

}
