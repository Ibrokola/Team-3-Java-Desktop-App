package BLL;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/*
 * Purpose: Supplier class
 * Author: Linda Wallace
 * Module: PROJ-207-OSD
 * Date June 12, 2019
 * */

public class Supplier {
    // supplier properties (table columns)
    private SimpleIntegerProperty supplierId;
    private SimpleStringProperty supName;

    // constuctor
    public Supplier(Integer supplierId,
                 String supName) {
        this.supplierId = new SimpleIntegerProperty(supplierId);
        this.supName = new SimpleStringProperty(supName);
    }

    // getters and setters.
    public int getSupplierId() {
        return supplierId.get();
    }

    // getter for tableview
    public SimpleIntegerProperty supplierIdProperty() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId.set(supplierId);
    }

    public String getSupName() {
        return supName.get();
    }

    public SimpleStringProperty supNameProperty() {
        return supName;
    }

    public void setSupName(String supName) {
        this.supName.set(supName);
    }

    @Override
    public String toString() {
        return getSupName();
    }
}
