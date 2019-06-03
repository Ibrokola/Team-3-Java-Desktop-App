package BLL;

import javafx.beans.property.*;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Package {

    private SimpleIntegerProperty PackageId;
    private SimpleStringProperty PkgName;
    private ObjectProperty<Date> PkgStartDate;
    private ObjectProperty<Date> PkgEndDate;
    private SimpleStringProperty PkgDesc;
    private SimpleDoubleProperty PkgBasePrice;
    private SimpleDoubleProperty  PkgAgencyCommission;


    //Constructor
    public Package(int PackageId, String PkgName, Date PkgStartDate,
                   Date PkgEndDate, String PkgDesc, Double PkgBasePrice,
                   Double PkgAgencyCommission) {
        super();
        this.PackageId = new SimpleIntegerProperty (PackageId);
        this.PkgName = new SimpleStringProperty(PkgName);
        this.PkgStartDate = new SimpleObjectProperty<Date> (PkgStartDate);
        this.PkgEndDate = new SimpleObjectProperty<Date>(PkgEndDate);
        this.PkgDesc = new SimpleStringProperty (PkgDesc);
        this.PkgBasePrice = new SimpleDoubleProperty (PkgBasePrice);
        this.PkgAgencyCommission = new SimpleDoubleProperty (PkgAgencyCommission);
    }


    public int getPackageId() {
        return PackageId.get();
    }
    public SimpleIntegerProperty packageIdProperty() {
        return PackageId;
    }
    public void setPackageId(int packageId) {
        this.PackageId.set(packageId);
    }

    public String getPkgName() {
        return PkgName.get();
    }
    public SimpleStringProperty pkgNameProperty() {
        return PkgName;
    }
    public void setPkgName(String pkgName) {
        this.PkgName.set(pkgName);
    }

    public Date getPkgStartDate() {
        return PkgStartDate.get();
    }
    public ObjectProperty<Date> pkgStartDateProperty() {
        return PkgStartDate;
    }
    public void setPkgStartDate(Date pkgStartDate) {
        this.PkgStartDate.set(pkgStartDate);
    }

    public Date getPkgEndDate() {
        return PkgEndDate.get();
    }
    public ObjectProperty<Date> pkgEndDateProperty() {
        return PkgEndDate;
    }
    public void setPkgEndDate(Date pkgEndDate) {
        this.PkgEndDate.set(pkgEndDate);
    }

    public String getPkgDesc() {
        return PkgDesc.get();
    }
    public SimpleStringProperty pkgDescProperty() {
        return PkgDesc;
    }
    public void setPkgDesc(String pkgDesc) {
        this.PkgDesc.set(pkgDesc);
    }

    public double getPkgBasePrice() {
        return PkgBasePrice.get();
    }
    public SimpleDoubleProperty pkgBasePriceProperty() {
        return PkgBasePrice;
    }
    public void setPkgBasePrice(double pkgBasePrice) {
        this.PkgBasePrice.set(pkgBasePrice);
    }

    public double getPkgAgencyCommission() {
        return PkgAgencyCommission.get();
    }
    public SimpleDoubleProperty pkgAgencyCommissionProperty() {
        return PkgAgencyCommission;
    }
    public void setPkgAgencyCommission(double pkgAgencyCommission) {
        this.PkgAgencyCommission.set(pkgAgencyCommission);
    }
}
