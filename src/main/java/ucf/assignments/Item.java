package ucf.assignments;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.math.BigDecimal;

public class Item {
    private final StringProperty itemName;
    private final StringProperty itemSerialNumber;
    private final StringProperty itemValue;

    public Item() {
        this(null, null, null);
    }

    public Item (String itemName, String itemSerialNumber, String itemValue) {
        this.itemName = new SimpleStringProperty(itemName);
        this.itemSerialNumber = new SimpleStringProperty(itemSerialNumber);
        this.itemValue = new SimpleStringProperty(itemValue);
    }

    public void setItemName(String itemName) {
        this.itemName.set(itemName);
    }

    public String getItemName() {
        return itemName.get();
    }

    public StringProperty itemNameProperty() {
        return itemName;
    }

    public void setItemSerialNumber(String itemSerialNumber) {
        this.itemName.set(itemSerialNumber);
    }

    public String getItemSerialNumber() {
        return itemSerialNumber.get();
    }

    public StringProperty itemSerialNumberProperty() {
        return itemSerialNumber;
    }

    public void setItemValue(String itemValue) {
        this.itemValue.set(itemValue);
    }

    public String getItemValue() {
        return itemValue.get();
    }

    public StringProperty itemValueProperty() {
        return itemValue;
    }
}
