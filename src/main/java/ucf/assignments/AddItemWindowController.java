package ucf.assignments;

import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.awt.*;

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
        item.setItemName(itemNameField.getText());
        item.setItemSerialNumber(serialNumberField.getText());
        item.setItemValue(itemValueField.getText());

        okClicked = true;
        dialogStage.close();
    }

    @FXML
    public void cancelClicked() {
        handleCancelClicked();
    }
    private void handleCancelClicked() {
        dialogStage.close();
    }
}