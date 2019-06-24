package BLL;

import DLL.DBConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookingDB {
    /*
     * Purpose: handles operations to the database for bookings
     * Author: Brent Ward
     * Module: PROJ-207-OSD
     * Date: June 24 2019
     * */

    //gets all the bookings for a specific customer
    public static List<Booking> getBookings(int customerId){
        List<Booking> bookings = null;

        try{
            //connection built
            Connection connect = DBConnect.getConnection();

            //query
            String selectQuery = "select b.BookingId, b.BookingDate, b.BookingNo, b.TravelerCount, b.CustomerId, bd.TripStart, " +
                    "bd.TripEnd, bd.Description, bd.Destination, bd.BasePrice, bd.RegionId from Bookings b inner join " +
                    "BookingDetails bd on b.BookingId = bd.BookingId where b.CustomerId = ?";

            //makes a sql statement
            PreparedStatement stmt = connect.prepareStatement(selectQuery);
            stmt.setInt(1, customerId);

            //assigns & executes statement
            ResultSet rs = stmt.executeQuery();

            bookings = new ArrayList<Booking>();
            //runs while reader has data
            while(rs.next()){
                Booking booking = new Booking(rs.getInt("BookingId"), rs.getInt("CustomerId"),
                        rs.getString("BookingNo"), rs.getDate("BookingDate"), rs.getInt("TravelerCount"),
                        rs.getDate("TripStart"), rs.getDate("TripEnd"), rs.getString("Description"),
                        rs.getString("Destination"), rs.getDouble("BasePrice"), rs.getString("RegionId"));
                bookings.add(booking);
            }
            connect.close();

        }catch(Exception e) { e.printStackTrace(); }

        return bookings;
    }

    //gets the name for the most sold package
    public static String topPackage(){
        String packageName = null;
        try {
            //connection built
            Connection connect = DBConnect.getConnection();

            //query
            String selectQuery = "SELECT p.PkgName, count(*) from Bookings b inner join Packages p on b.PackageId = p.PackageId limit 1";
            //makes a sql statement
            Statement query = connect.createStatement();

            //assigns & executes statement
            ResultSet rs = query.executeQuery(selectQuery);

            //runs while reader has data
            while (rs.next()){
                packageName = rs.getString("PkgName");
            }
            connect.close();

        }catch(Exception e){ e.printStackTrace(); }
        return packageName;
    }

    //returns the total of sales in the last 3 months
    public static int recentSales(){
        int total = 0;

        return total;
    }
}
