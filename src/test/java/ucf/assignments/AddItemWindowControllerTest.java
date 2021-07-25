package ucf.assignments;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddItemWindowControllerTest {

    @Test
    void setItem() {
        Item tempItem = new Item();
        tempItem.setItemName("Test1");
        tempItem.setItemSerialNumber("1234567890");
        tempItem.setItemValue("$4.20");

        System.out.println(tempItem.getItemName());
        System.out.println(tempItem.getItemSerialNumber());
        System.out.println(tempItem.getItemValue());
    }
    @Test
    void isInputValid() {
        String testName = "Test1";
        String testSerial = "1234567890";
        String testValue = "099";
        String errorMessage = "";

        if (testName == null || testName.length() < 2 || testName.length() > 256) {
            errorMessage += "Error: Must have a Title between 2 - 256 Characters!\n";
        }
        if (testSerial == null || testSerial.length() < 10 || testSerial.length() > 10) {
            errorMessage += "Error: Must have a 10 Character Serial Number!\n";
        }
        if (testValue == null || testValue.length() == 0) {
            errorMessage += "Error: Must have a Value!\n";
        }

        System.out.println(errorMessage);
    }
}