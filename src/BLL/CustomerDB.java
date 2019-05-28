package BLL;

import DLL.DBConnect;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerDB {
    /*
     * Purpose: Communicates between the customer object, and the database to complete operations.
     * Author: Brent Ward
     * Module: PROJ-207-OSD
     * Date May 15, 2019
     * */

    //gets list of customers
    public static List<Customer> getCustomers(){
        List<Customer> customers = null;

        try{
            //connection built
            Connection connect = DBConnect.getConnection();

            //query
            String selectQuery = "select CustomerId, CustFirstName, CustLastName, CustAddress, CustCity, CustProv," +
                                    "CustPostal, CustCountry, CustHomePhone, CustBusPhone, CustEmail, AgentId from Customers";

            //makes a sql statement
            Statement query = connect.createStatement();

            //assigns & executes statement
            ResultSet rs = query.executeQuery(selectQuery);

            customers = new ArrayList<Customer>();
            //runs while reader has data
            while(rs.next()){
                Customer customer = new Customer(rs.getInt("CustomerId"),
                        rs.getString("CustFirstName"),
                        rs.getString("CustLastName"),
                        rs.getString("CustAddress"),
                        rs.getString("CustCity"),
                        rs.getString("CustProv"),
                        rs.getString("CustPostal"),
                        rs.getString("CustCountry"),
                        rs.getString("CustHomePhone"),
                        rs.getString("CustBusPhone"),
                        rs.getString("CustEmail"),
                        rs.getInt("AgentId"));
                customers.add(customer);
            }
            connect.close();

        }catch(Exception e) { e.printStackTrace(); }

        return customers;
    }

    //searchs a customer based off user input
    public static List<Customer> searchCustomers(String name){
        List<Customer> customers = null;

        try{
            //connection built
            Connection connect = DBConnect.getConnection();

            //query
            String selectQuery = "select CustomerId, CustFirstName, CustLastName, CustAddress, CustCity, CustProv," +
                        "CustPostal, CustCountry, CustHomePhone, CustBusPhone, CustEmail, AgentId from Customers " +
                        "where CustFirstName like ? or CustLastName like ?";

            //makes a sql statement
            PreparedStatement stmt = connect.prepareStatement(selectQuery);
            stmt.setString(1,'%' + name + '%');
            stmt.setString(2, '%' + name + '%');

            //assigns & executes statement
            ResultSet rs = stmt.executeQuery();

            customers = new ArrayList<Customer>();
            //runs while reader has data
            while(rs.next()){
                Customer customer = new Customer(rs.getInt("CustomerId"),
                        rs.getString("CustFirstName"),
                        rs.getString("CustLastName"),
                        rs.getString("CustAddress"),
                        rs.getString("CustCity"),
                        rs.getString("CustProv"),
                        rs.getString("CustPostal"),
                        rs.getString("CustCountry"),
                        rs.getString("CustHomePhone"),
                        rs.getString("CustBusPhone"),
                        rs.getString("CustEmail"),
                        rs.getInt("AgentId"));
                customers.add(customer);
            }
            connect.close();

        }catch(Exception e) { e.printStackTrace(); }

        return customers;
    }

    //updates a customer
    public static void updateCustomer(Customer customer){
        try{
            //connection built
            Connection connect = DBConnect.getConnection();

            //query
            String updateQuery = "update Customers set CustFirstName=?, CustLastName=?, CustAddress=?, CustCity=?, CustProv=?," +
                    "CustPostal=?, CustCountry=?, CustHomePhone=?, CustBusPhone=?, CustEmail=?, AgentId=? where CustomerId=?";

            PreparedStatement stmt = connect.prepareStatement(updateQuery);

            //sets parameters for ?
            stmt.setString(1, customer.getFirstName());
            stmt.setString(2, customer.getLastName());
            stmt.setString(3, customer.getAddress());
            stmt.setString(4, customer.getCity());
            stmt.setString(5, customer.getProv());
            stmt.setString(6, customer.getPostal());
            stmt.setString(7, customer.getCountry());
            stmt.setString(8, customer.getHomePhone());
            stmt.setString(9, customer.getBusPhone());
            stmt.setString(10, customer.getEmail());
            stmt.setInt(11, customer.getAgent());
            stmt.setInt(12, customer.getId());

            //checks if the data was inserted
            int numRows = stmt.executeUpdate();
            if (numRows == 0)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Customer failed to update. Contact Tech Support.");
                alert.showAndWait();
            }
            connect.close();

        }catch(Exception e) { e.printStackTrace(); }
    }

    //deletes a customer
    public static void deleteCustomer(Customer customer){
        try{
            //connection built
            Connection connect = DBConnect.getConnection();
            String deleteQuery = "delete all from Customers where CustomerId=?";

            PreparedStatement stmt = connect.prepareStatement(deleteQuery);
            stmt.setInt(1, customer.getId());

            //checks if agent is deleted
            int numRows = stmt.executeUpdate();
            if(numRows == 0){
                Alert alert = new Alert(Alert.AlertType.ERROR, "Customer failed to delete. Contact Tech Support.");
                alert.showAndWait();
            }
            connect.close();

        }catch(Exception e) { e.printStackTrace(); }
    }

}
