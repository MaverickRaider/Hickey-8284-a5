package ucf.assignments;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

import java.math.BigDecimal;

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
        itemNameField.setPromptText("2 - 256 Characters");
        serialNumberField.setText(item.getItemSerialNumber());
        serialNumberField.setPromptText("XXXXXXXXXX");
        itemValueField.setText(item.getItemValue());
        itemValueField.setPromptText("In US Cents");
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
            item.setItemValue("$" + usCurrencyFormatter(itemValueField.getText()));

            okClicked = true;
            dialogStage.close();
        }
    }

    private BigDecimal usCurrencyFormatter (String str) {
        BigDecimal answer = new BigDecimal(str);
        BigDecimal divisor = new BigDecimal("100");
        answer = answer.divide(divisor);
        return answer;
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

        if (itemNameField.getText() == null || itemNameField.getText().length() < 2 || itemNameField.getText().length() > 256) {
            errorMessage += "Error: Must have a Title between 2 - 256 Characters!\n";
        }
        if (serialNumberField.getText() == null || serialNumberField.getText().length() > 10 || serialNumberField.getText().length() > 10) {
            errorMessage += "Error: Must have a 10 Character Serial Number!\n";
        }
        if (itemValueField.getText() == null || itemValueField.getText().length() == 0) {
            errorMessage += "Error: Must have a Value!\n";
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