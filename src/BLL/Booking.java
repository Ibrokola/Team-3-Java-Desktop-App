package BLL;

import javafx.beans.property.*;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Booking {
    /*
     * Purpose: Store booking data for a customer
     * Author: Brent Ward
     * Module: PROJ-207-OSD
     * Date: June 24 2019
     * */

    //properties
    private int bookingId;
    private int customerId;
    private SimpleStringProperty bookingNo;
    private SimpleObjectProperty<Date> bookingDate;
    private SimpleIntegerProperty travelerCount;
    private SimpleObjectProperty<Date> startDate;
    private SimpleObjectProperty<Date> endDate;
    private SimpleStringProperty description;
    private SimpleStringProperty destination;
    private SimpleDoubleProperty basePrice;
    private SimpleStringProperty region;

    //constructor
    public Booking() { }

    public Booking(int bookingId, int customerId, String bookingNo, Date bookingDate, int travelerCount, Date startDate,
                   Date endDate, String description, String destination, double basePrice, String region) {
        this.bookingId = bookingId;
        this.customerId = customerId;
        this.bookingNo = new SimpleStringProperty(bookingNo);
        this.bookingDate = new SimpleObjectProperty<>(bookingDate);
        this.travelerCount = new SimpleIntegerProperty(travelerCount);
        this.startDate = new SimpleObjectProperty<>(startDate);
        this.endDate =  new SimpleObjectProperty<>(endDate);
        this.description = new SimpleStringProperty(description);
        this.destination = new SimpleStringProperty(destination);
        this.basePrice = new SimpleDoubleProperty(basePrice);
        this.region = new SimpleStringProperty(region);
    }

    //getters
    public int getBookingId() { return bookingId; }
    public int getCustomerId() { return customerId; }
    public String getBookingNo() { return bookingNo.get(); }
    public Date getBookingDate() { return bookingDate.get(); }
    public int getTravelerCount() { return travelerCount.get(); }
    public Date getStartDate() { return startDate.get(); }
    public Date getEndDate() { return endDate.get(); }
    public String getDescription() { return description.get(); }
    public String getDestination() { return destination.get(); }
    public double getBasePrice() { return basePrice.get(); }
    public String getRegion() { return region.get(); }

    //property getters
    public SimpleStringProperty getBookingNoProperty() { return bookingNo; }
    public SimpleObjectProperty<Date> getBookingDateProperty() { return bookingDate; }
    public SimpleIntegerProperty getTravelerCountProperty() { return travelerCount; }
    public SimpleObjectProperty<Date> getTripStartProperty() { return startDate; }
    public SimpleObjectProperty<Date> getTripEndProperty() { return endDate; }
    public SimpleStringProperty getDescriptionProperty() { return description; }
    public SimpleStringProperty getDestinationProperty() { return destination; }
    public SimpleDoubleProperty getBasePriceProperty() { return basePrice; }
    public SimpleStringProperty getRegionProperty() { return region; }
}
