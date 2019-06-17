package BLL;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ProductSupplier {

    private SimpleIntegerProperty productSupplierId;
    private SimpleIntegerProperty productId;
    private SimpleStringProperty prodName;
    private SimpleIntegerProperty supplierId;
    private SimpleStringProperty supName;

    // private SimpleStringProperty productId;
    // private SimpleStringProperty supplierId;

    // For auto-increment
    public ProductSupplier(int productId, int supplierId) {
        this.productId = new SimpleIntegerProperty(productId);
        this.supplierId = new SimpleIntegerProperty(supplierId);
    }


    public ProductSupplier(int productSupplierId, int productId, String prodName, int supplierId, String supName) {
        this.productSupplierId = new SimpleIntegerProperty(productSupplierId);
        this.productId = new SimpleIntegerProperty(productId);
        this.prodName = new SimpleStringProperty(prodName);
        this.supplierId = new SimpleIntegerProperty(supplierId);
        this.supName = new SimpleStringProperty(supName);
    }

    public int getProductSupplierId() {
        return productSupplierId.get();
    }

    public SimpleIntegerProperty productSupplierIdProperty() {
        return productSupplierId;
    }

    public void setProductSupplierId(int productSupplierId) {
        this.productSupplierId.set(productSupplierId);
    }

    public int getProductId() {
        return productId.get();
    }

    public SimpleIntegerProperty productIdProperty() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId.set(productId);
    }

    public String getProdName() {
        return prodName.get();
    }

    public SimpleStringProperty prodNameProperty() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName.set(prodName);
    }

    public int getSupplierId() {
        return supplierId.get();
    }

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
        return getProdName() + ": " + getSupName();
    }
}
