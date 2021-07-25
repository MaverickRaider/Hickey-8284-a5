/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Michael Hickey
 */

package ucf.assignments;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;

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

    // initialize() automatically starts when MainWindowController is called
    @FXML
    private void initialize() {
        itemsSerialColumn.setCellValueFactory(cellData -> cellData.getValue().itemSerialNumberProperty());
        itemsNameColumn.setCellValueFactory(cellData -> cellData.getValue().itemNameProperty());
        itemsValueColumn.setCellValueFactory(cellData -> cellData.getValue().itemValueProperty());
    }

    // Fills the table with data on the main screen
    public void setMainApp(App mainApp) {
        this.mainApp = mainApp;
        itemsTableView.setItems(mainApp.getItemData());

        // Converts tablelist into a filtered list for the search function
        FilteredList<Item> filteredData = new FilteredList<>(mainApp.getItemData());
        itemsTableView.setItems(filteredData);

        // searchBox function, as the user types the table adjusts.
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
        // Sorts the filtered list to the table
        SortedList<Item> sortedList = new SortedList<>(filteredData);
        itemsTableView.setItems(sortedList);
    }

    // Adds item to the list
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

    // Grabs item to be edited
    @FXML
    public void editButtonClicked() {
        handleEditItem();
    }
    private void handleEditItem(){
        // Selects the item to be edited
        Item selectedItem = itemsTableView.getSelectionModel().getSelectedItem();
        // if there is no item selected, error message appears
        if (selectedItem != null) {
            selectedItem.setItemValue(editItemValueFormatter(selectedItem.getItemValue()));
            // showAddLayout will be filled with the data that was selected
            boolean okClicked = mainApp.showAddLayout(selectedItem);
            if (okClicked) {
                mainApp.showAddLayout(selectedItem);
            }
        } else {
            errorReport();
        }
    }
    // This method removes the "$" and "." from itemValue to be edited easier once data is populated
    private String editItemValueFormatter (String str) {
        String newStr = str.replaceAll("\\$", "");
        newStr = newStr.replaceAll("\\.", "");
        return newStr;
    }

    // Removes item from list and table
    @FXML
    private void deleteButtonClicked() {
        handleDeleteItem();
    }
    private void handleDeleteItem() {
        // Takes item to be deleted
        Item selectedItem = itemsTableView.getSelectionModel().getSelectedItem();
        // If nothing selected, send error to user
        if (selectedItem != null) {
            // Removes the item selected
            mainApp.getItemData().remove(selectedItem);
        } else {
            errorReport();
        }
    }

    // Clears table completely
    @FXML
    private void newButtonClicked() {
        handleNewButtonClicked();
    }
    private void handleNewButtonClicked() {
        mainApp.getItemData().clear();
    }

    // TSV Save Method
    @FXML
    public void saveAsMenuClicked() {
        handleSaveAs();
    }
    private void handleSaveAs() {
        // Opens save menu
        Stage saveStage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save As");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        // If table is empty, send error
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
    // This method handles saving the table to file.
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

    // Opens a txt file to be pulled into the table
    @FXML
    public void loadMenuClicked() {
        handleLoad();
    }
    private void handleLoad() {
        // Set open menu window
        Stage openStage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        File file = fileChooser.showOpenDialog(openStage);
        openFile(file);
    }
    // This method handles opening a file to read
    public void openFile(File file) {
        mainApp.getItemData().clear();
        try {
            BufferedReader in = new BufferedReader(new FileReader(file));
            String curLine = in.readLine();
            while (curLine != null) {
                Item tempItem = new Item();
                String[] splitted = curLine.split("\t");

                tempItem.setItemSerialNumber(splitted[0]);
                tempItem.setItemName(splitted[1]);
                tempItem.setItemValue(splitted[2]);

                mainApp.getItemData().add(tempItem);
                curLine = in.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // When Closed clicked, exits program.
    @FXML
    public void closedMenuClicked() {
        handleClose();
    }
    private void handleClose() {
        System.exit(0);
    }

    // About menu holes instructions on about the program.
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

    // Error report if nothing is selected when deleting or editing an item
    private void errorReport() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initOwner(mainApp.getPrimaryStage());
        alert.setTitle("No Selection");
        alert.setHeaderText("No Item Selected");
        alert.setContentText("Please select an Item in the table.");
        alert.showAndWait();
    }
    // Error report if saving fails.
    private void saveErrorReport() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.initOwner(mainApp.getPrimaryStage());
        alert.setTitle("Save Error");
        alert.setHeaderText("There was an Issue Saving your File.");
        alert.setContentText("Please review the table and try again.");
        alert.showAndWait();
    }
}
