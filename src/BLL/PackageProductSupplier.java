package BLL;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class PackageProductSupplier {
    private SimpleIntegerProperty packageId;
    private SimpleStringProperty pkgName;
    private SimpleIntegerProperty productSupplierId;
    private SimpleStringProperty supName;


    public PackageProductSupplier(int packageId, int productSupplierId)
    {
        this.packageId = new SimpleIntegerProperty(packageId);
        this.productSupplierId = new SimpleIntegerProperty(productSupplierId);
    }

    public PackageProductSupplier(int packageId, String pkgName, int productSupplierId, String supName) {
        this.packageId = new SimpleIntegerProperty(packageId);
        this.pkgName = new SimpleStringProperty(pkgName);
        this.productSupplierId = new SimpleIntegerProperty(productSupplierId);
        this.supName = new SimpleStringProperty(supName);
    }

    public int getPackageId() {
        return packageId.get();
    }

    public SimpleIntegerProperty packageIdProperty() {
        return packageId;
    }

    public void setPackageId(int packageId) {
        this.packageId.set(packageId);
    }

    public String getPkgName() {
        return pkgName.get();
    }

    public SimpleStringProperty pkgNameProperty() {
        return pkgName;
    }

    public void setPkgName(String pkgName) {
        this.pkgName.set(pkgName);
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

    public String getSupName() {
        return supName.get();
    }

    public SimpleStringProperty supNameProperty() {
        return supName;
    }

    public void setSupName(String supName) {
        this.supName.set(supName);
    }
}
