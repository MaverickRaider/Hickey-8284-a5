package ucf.assignments;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.math.BigDecimal;

public class Item {
    private final StringProperty itemName;
    private final StringProperty itemSerialNumber;
    private final BigDecimal itemValue;

    public Item() {
        this(null, null, null);
    }

    public Item (String itemName, String itemSerialNumber, Double itemValue) {
        this.itemName = new SimpleStringProperty(itemName);
        this.itemSerialNumber = new SimpleStringProperty(itemSerialNumber);
        this.itemValue = new BigDecimal(itemValue);
    }

    public String getItemName() {
        return itemName.get();
    }

    public StringProperty itemNameProperty() {
        return itemName;
    }

    public String getItemSerialNumber() {
        return itemSerialNumber.get();
    }

    public StringProperty itemSerialNumberProperty() {
        return itemSerialNumber;
    }

    public BigDecimal getItemValue() {
        return itemValue;
    }
}
