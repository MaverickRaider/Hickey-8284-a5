/*
 *  UCF COP3330 Summer 2021 Assignment 5 Solution
 *  Copyright 2021 Michael Hickey
 */

package ucf.assignments;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Item {
    private final StringProperty itemSerialNumber;
    private final StringProperty itemName;
    private final StringProperty itemValue;

    // Constructor
    public Item() {
        this(null, null, null);
    }

    public Item (String itemName, String itemSerialNumber, String itemValue) {
        this.itemSerialNumber = new SimpleStringProperty(itemSerialNumber);
        this.itemName = new SimpleStringProperty(itemName);
        this.itemValue = new SimpleStringProperty(itemValue);
    }

    // Getters and Setters
    public void setItemSerialNumber(String itemSerialNumber) {
        this.itemSerialNumber.set(itemSerialNumber);
    }

    public String getItemSerialNumber() {
        return itemSerialNumber.get();
    }

    public StringProperty itemSerialNumberProperty() {
        return itemSerialNumber;
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

    public void setItemValue(String itemValue) {
        this.itemValue.set(itemValue);
    }

    public String getItemValue() {
        return itemValue.get();
    }

    public StringProperty itemValueProperty() {
        return itemValue;
    }

    // toString Method is used in handleSaveAs() for TSV saving
    @Override
    public String toString() {
        return (this.getItemSerialNumber() + "\t" +
                this.getItemName() + "\t" +
                this.getItemValue());
    }
}
