package BLL;

import DLL.DBConnect;
import javafx.scene.control.Alert;

import java.security.AlgorithmConstraints;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class AdministratorDB {
    /*
     * Purpose: Handles the communications to the database for logging in
     * Author: Brent Ward
     * Module: PROJ-207-OSD
     * Date May 24, 2019
     * */

    public static Administrator checkAdmin(String username, String password){
        Administrator admin = null;

        try{
            //connection built
            Connection connect = DBConnect.getConnection();

            //query
            String selectQuery = "select adminFirstName, adminLastName from Administrators where " +
                                "adminUsername = ? and adminPassword = ?";

            //makes a sql statement
            PreparedStatement stmt = connect.prepareStatement(selectQuery);
            stmt.setString(1, username);
            stmt.setString(2, password);

            //assigns & executes statement
            ResultSet rs = stmt.executeQuery();

            //grabs admin
            if(rs.next()){
                admin = new Administrator(rs.getString("adminFirstName"), rs.getString("adminLastName"));
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid Username or Password.");
                alert.show();
            }

            connect.close();

        }catch(Exception e) { e.printStackTrace(); }

        return admin;
    }
}
