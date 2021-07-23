package ucf.assignments;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

public class AddItemWindowController {

    @FXML
    private TextField itemNameField;
    @FXML
    private TextField serialNumberField;
    @FXML
    private TextField itemValueField;

    private Stage dialogStage;
    private Item item;
    private boolean okClicked = false;

    @FXML
    private void initialize() {
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setItem(Item item) {
        this.item = item;

        itemNameField.setText(item.getItemName());
        serialNumberField.setText(item.getItemSerialNumber());
        itemValueField.setText(item.getItemValue());
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    public void okClicked() {
        handleOkClicked();
    }
    private void handleOkClicked() {
        if (isInputValid()) {
            item.setItemName(itemNameField.getText());
            item.setItemSerialNumber(serialNumberField.getText());
            item.setItemValue(itemValueField.getText());

            okClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    public void cancelClicked() {
        handleCancelClicked();
    }
    private void handleCancelClicked() {
        dialogStage.close();
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (itemNameField.getText() == null || itemNameField.getText().length() == 0) {
            errorMessage += "Error: Must have a Title!\n";
        }
        if (serialNumberField.getText() == null || serialNumberField.getText().length() == 0) {
            errorMessage += "Error: Must have a Serial Number!\n";
        }
        if (serialNumberField.getText().length() < 10 || serialNumberField.getText().length() > 10) {
            errorMessage += "Error: Serial Number needs 10 characters!\n";
        }
        if (itemValueField.getText() == null || itemValueField.getText().length() == 0) {
            errorMessage += "Error: Must have Value!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}