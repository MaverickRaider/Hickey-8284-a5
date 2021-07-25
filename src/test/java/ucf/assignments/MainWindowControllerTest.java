package ucf.assignments;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainWindowControllerTest {
    private App mainApp;
    public void setMainApp(App mainApp) {
        this.mainApp = mainApp;
    }

    @Test
    void handleAddItem() {
        Item tempItem = new Item();
        tempItem.setItemName("Test1");
        tempItem.setItemSerialNumber("1234567890");
        tempItem.setItemValue("$4.20");

        System.out.println(tempItem.getItemName());
        System.out.println(tempItem.getItemSerialNumber());
        System.out.println(tempItem.getItemValue());
    }

    @Test
    void editItemValueFormatter() {
        String str = "$4.20";
        String newStr = str.replaceAll("\\$", "");
        newStr = newStr.replaceAll("\\.", "");
        System.out.println(newStr);
    }
    @Test
    void handleEditItem() {
        Item tempItem = new Item();
        tempItem.setItemName("Test1");
        tempItem.setItemSerialNumber("1234567890");
        tempItem.setItemValue("$4.20");

        mainApp.showAddLayout(tempItem);
    }

    @Test
    void handleDeleteItem() {
        Item tempItem = new Item();
        tempItem.setItemName("Test1");
        tempItem.setItemSerialNumber("1234567890");
        tempItem.setItemValue("$4.20");

        System.out.println(tempItem.getItemName());
        System.out.println(tempItem.getItemSerialNumber());
        System.out.println(tempItem.getItemValue());

        tempItem.setItemName("");
        tempItem.setItemSerialNumber("");
        tempItem.setItemValue("");

        System.out.println(tempItem.getItemName());
        System.out.println(tempItem.getItemSerialNumber());
        System.out.println(tempItem.getItemValue());
    }
}