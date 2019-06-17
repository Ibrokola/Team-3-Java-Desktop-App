package BLL;

import DLL.DBConnect;
import javafx.scene.control.Alert;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PackageDB {

    public static List<Package> getPackages()
    {
        List<Package> packages = null;

        try{
            //connection built
            Connection con = DBConnect.getConnection();

            //query
            String selectPackage = "SELECT PackageId,  PkgName, PkgStartDate, PkgEndDate, " +
                    "        PkgDesc, PkgBasePrice, PkgAgencyCommission " +
                    "FROM Packages " +
                    "ORDER BY PackageId";

            //makes a sql statement
            Statement query = con.createStatement();


            //execute statement
            ResultSet rs = query.executeQuery(selectPackage);


            //Packages ArrayList
            packages = new ArrayList<Package>();


            while(rs.next()){
                Package pkg = new Package(rs.getInt("PackageId"),
                        rs.getString("PkgName"),
                        rs.getDate("PkgStartDate"),
                        rs.getDate("PkgEndDate"),
                        rs.getString("PkgDesc"),
                        rs.getDouble("PkgBasePrice"),
                        rs.getDouble("PkgAgencyCommission"));
                packages.add(pkg);
            }
            con.close();

        }catch(Exception e) { e.printStackTrace(); }

        return packages;
    }


    //Update Packages
    public static void updatePackages(Package packages)
    {
        try{
            //connection built
            Connection con = DBConnect.getConnection();

            //query
            String updatePackages =
                 /*   " UPDATE Packages SET " +
                    "PkgName = @PkgName, " +
                    "PkgStartDate = @PkgStartDate, " +
                    "PkgEndDate = @PkgEndDate, " +
                    "PkgDesc = @PkgDesc, " +
                    "PkgBasePrice = @PkgBasePrice, " +
                    "PkgAgencyCommission = @PkgAgencyCommission " +
                    "WHERE PackageId = @PackageId";*/ //what is the difference with PackageId  =?

                    " UPDATE Packages SET " +
                            "PkgName = ?, " +
                            "PkgStartDate = ?, " +
                            "PkgEndDate = ?, " +
                            "PkgDesc = ?, " +
                            "PkgBasePrice = ?, " +
                            "PkgAgencyCommission = ? " +
                            "WHERE PackageId = ?";

            // Prepared Statement
            PreparedStatement stmt = con.prepareStatement(updatePackages);

            stmt.setInt(1,packages.getPackageId());
            stmt.setString(2,packages.getPkgName());
            stmt.setDate(3, (Date) packages.getPkgStartDate());
            stmt.setDate(4, (Date) packages.getPkgEndDate());
            stmt.setString(5,packages.getPkgDesc());
            stmt.setDouble(6,packages.getPkgBasePrice());
            stmt.setDouble(7,packages.getPkgAgencyCommission());

            //checks if the data was inserted
            int numRows = stmt.executeUpdate();
            if (numRows == 0)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR,
                        "Package update failed. Please try again or contact Tech Support.");
                alert.showAndWait();
            }
            stmt.close();
            con.close();

        }catch(Exception e) { e.printStackTrace(); }

    }


    //Insert Packages
    public static void addPackages (Package packages)
    {
        try{
            //connection built
            Connection con = DBConnect.getConnection();

            //query
            String updatePackages =
                    "INSERT INTO Packages values(?,?,?,?,?,?,?)";

            // Prepared Statement
            PreparedStatement stmt = con.prepareStatement(updatePackages);

            stmt.setInt(1,packages.getPackageId());
            stmt.setString(2,packages.getPkgName());
            stmt.setDate(3, (Date) packages.getPkgStartDate());
            stmt.setDate(4, (Date) packages.getPkgEndDate());
            stmt.setString(5,packages.getPkgDesc());
            stmt.setDouble(6,packages.getPkgBasePrice());
            stmt.setDouble(7,packages.getPkgAgencyCommission());

            //checks if the data was inserted
            int numRows = stmt.executeUpdate();
            if (numRows == 0)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR,
                        "Package update failed. Please try again or contact Tech Support.");
                alert.showAndWait();
            }
            stmt.close();
            con.close();

        }catch(Exception e) { e.printStackTrace(); }

    }

    //Deletes an Agent
    public static void deletePackage(Package packages)
    {
        try{
        //connection built
        Connection con = DBConnect.getConnection();

        // What's happening here ?????

        //query "delete all from Agents where AgentId=?";
        String updatePackages =
                "DELETE ALL FROM Packages where (?,?,?,?,?,?,?)";

        // Prepared Statement
        PreparedStatement stmt = con.prepareStatement(updatePackages);


        //checks if the data was inserted
        int numRows = stmt.executeUpdate();
        if (numRows == 0)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR,
                    "Package update failed. Please try again or contact Tech Support.");
            alert.showAndWait();
        }
        stmt.close();
        con.close();

    }catch(Exception e) { e.printStackTrace(); }


}













}
