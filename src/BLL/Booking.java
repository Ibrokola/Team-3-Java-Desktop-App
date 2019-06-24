package BLL;

import javafx.beans.property.SimpleStringProperty;

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
    private Date bookingDate;
    private int travelerCount;
    private Date startDate;
    private Date endDate;
    private SimpleStringProperty description;
    private SimpleStringProperty destination;
    private double basePrice;
    private SimpleStringProperty region;

    //constructor
    public Booking() { }

    public Booking(int bookingId, int customerId, String bookingNo, Date bookingDate, int travelerCount, Date startDate,
                   Date endDate, String description, String destination, double basePrice, String region) {
        this.bookingId = bookingId;
        this.customerId = customerId;
        this.bookingNo = new SimpleStringProperty(bookingNo);
        this.bookingDate = bookingDate;
        this.travelerCount = travelerCount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = new SimpleStringProperty(description);
        this.destination = new SimpleStringProperty(destination);
        this.basePrice = basePrice;
        this.region = new SimpleStringProperty(region);
    }

    //getters
    public int getBookingId() { return bookingId; }
    public int getCustomerId() { return customerId; }
    public String getBookingNo() { return bookingNo.get(); }
    public Date getBookingDate() { return bookingDate; }
    public int getTravelerCount() { return travelerCount; }
    public Date getStartDate() { return startDate; }
    public Date getEndDate() { return endDate; }
    public String getDescription() { return description.get(); }
    public String getDestination() { return destination.get(); }
    public double getBasePrice() { return basePrice; }
    public String getRegion() { return region.get(); }

    //property getters
    public SimpleStringProperty bookingNoProperty() { return bookingNo; }
    public SimpleStringProperty descriptionProperty() { return description; }
    public SimpleStringProperty destinationProperty() { return destination; }
    public SimpleStringProperty regionProperty() { return region; }
}
