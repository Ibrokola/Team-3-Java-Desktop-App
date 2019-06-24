package BLL;


import DLL.DBConnect;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
 * Purpose: code for supplier database functions
 * Author: Linda Wallace
 * Module: PROJ-207-OSD
 * Date: June 12, 2019
 * */

public class SupplierDB {

    public static List<Supplier> getSuppliers() {
        ArrayList<Supplier> suppliers = null;


        try{
            // instantiate DB connection
            Connection conn = DBConnect.getConnection();

            // instantiate query
            Statement query = conn.createStatement();

            // execute statement
            ResultSet rs = query.executeQuery("select * from suppliers");

            // assign products arrayList
            suppliers = new ArrayList<Supplier>();

            // retrieve result set and add to product arrays
            while(rs.next()){
                suppliers.add(new Supplier(
                        rs.getInt(1),
                        rs.getString(2)));
            }

            rs.close();
            conn.close();

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return suppliers;
    }

    public static void addSupplier(Supplier supplier){

        Connection conn = DBConnect.getConnection();

        try {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO suppliers VALUES (?, ?)");

            stmt.setInt(1, supplier.getSupplierId());
            stmt.setString(2, supplier.getSupName());

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated == 0)
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Error adding to the database", ButtonType.OK);
                alert.showAndWait();
            } else if (rowsUpdated == 1)
            {
                Alert alert3 = new Alert(Alert.AlertType.INFORMATION, "Supplier Added", ButtonType.OK);
                alert3.showAndWait();
            }

            conn.close();

        } catch (
                SQLException e) {
            e.printStackTrace();
        }
    }


    public static void updateSupplier(Supplier supplier){

    Connection conn = DBConnect.getConnection();

        try {
        PreparedStatement stmt = conn.prepareStatement("UPDATE suppliers SET SupName=? WHERE SupplierId=?");

        stmt.setString(1, supplier.getSupName());
        stmt.setInt(2, supplier.getSupplierId());

        int rowsUpdated = stmt.executeUpdate();
        if (rowsUpdated == 0)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Error updating to the database", ButtonType.OK);
            alert.showAndWait();
        } else if (rowsUpdated == 1)
        {
                Alert alert3 = new Alert(Alert.AlertType.INFORMATION, "Supplier Updated", ButtonType.OK);
                alert3.showAndWait();
        }

        conn.close();

    } catch (
    SQLException e) {
        e.printStackTrace();
    }

    }

    public static void deleteSupplier(Supplier supplier){
        String selection = supplier.getSupplierId() + ", " + supplier.getSupName();
        Alert deleteAlert = new Alert(Alert.AlertType.CONFIRMATION, "Delete Supplier Number " + selection + " ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        deleteAlert.showAndWait();

        if (deleteAlert.getResult() == ButtonType.YES) {


            Connection conn = DBConnect.getConnection();

            try {
                PreparedStatement stmt = conn.prepareStatement("DELETE FROM suppliers WHERE SupplierId=?");

                stmt.setInt(1, supplier.getSupplierId());

                int rowsUpdated = stmt.executeUpdate();
                if (rowsUpdated == 0) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Error deleting from the database", ButtonType.OK);
                    alert.showAndWait();
                } else if (rowsUpdated == 1) {
                    Alert alert3 = new Alert(Alert.AlertType.INFORMATION, "Supplier Deleted", ButtonType.OK);
                    alert3.showAndWait();
                }

                conn.close();

            } catch (
                    SQLException e) {
                e.printStackTrace();
            }
        }

    }

    // Retrieve a single supplier
    public static Supplier getSupplier(int id){
        Supplier supplier = null;

        try {
            //connection built
            Connection conn = DBConnect.getConnection();

            //makes a sql statement
            PreparedStatement stmt = conn.prepareStatement("select * from suppliers where SupplierId=?");
            stmt.setInt(1, id);

            //assigns and executes statement
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                supplier = new Supplier(rs.getInt("SupplierId"),
                        rs.getString("SupName"));
            }
            conn.close();

        }catch(Exception e){ e.printStackTrace(); }

        return supplier;
    }

    //gets the number of suppliers
    public static int numOfSuppliers(){
        int total = 0;

        try{
            //connection built
            Connection connect = DBConnect.getConnection();

            //query
            String selectQuery = "select count(*) from Suppliers";

            //makes a sql statement
            Statement stmt = connect.createStatement();

            //assigns & executes statement
            ResultSet rs = stmt.executeQuery(selectQuery);
            while(rs.next()){
                total = rs.getInt("count(*)");
            }
            connect.close();

        }catch(Exception e) { e.printStackTrace(); }

        return total;
    }

}
