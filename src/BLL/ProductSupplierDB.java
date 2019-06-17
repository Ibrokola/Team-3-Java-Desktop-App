package BLL;

import DLL.DBConnect;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductSupplierDB {

    /*
     * Purpose: Handles the product supplier connection to database
     * Author: Ibraheem Kolawole
     * Module: PROJ-207-OSD
     * Date May 24, 2019
     * */

    public static List<ProductSupplier> getProductSuppliers() {
        ArrayList<ProductSupplier> productSuppliers = null;


        try{
            // instantiate DB connection
            Connection conn = DBConnect.getConnection();

            // instantiate query
            Statement query = conn.createStatement();

            // query statement
            String q = "select ProductSupplierId, ProductId, prodName, SupplierId,supName from products_suppliers " +
                    "left join products using (ProductId) " +
                    "right join suppliers using (SupplierId) ";
            // on products_suppliers.ProductId=products.ProductId
            // on products_suppliers.SupplierId=suppliers.SupplierId
            // execute statement
            ResultSet rs = query.executeQuery(q);

            // assign products arrayList
            productSuppliers = new ArrayList<>();

            // retrieve result set and add to product arrays
            while(rs.next()){
                productSuppliers.add(new ProductSupplier(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5))
                );
            }

            rs.close();
            conn.close();

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return productSuppliers;
    }

    public static void addProductSupplier(ProductSupplier prodSupplier){
        try{
            // instantiate DB connection
            Connection conn = DBConnect.getConnection();

            // call prepared statement
            PreparedStatement stmt = conn.prepareStatement(
                    "Insert into products_suppliers values(?, ?, ?)");

            stmt.setString(1, null);
            stmt.setInt(2, prodSupplier.getProductId());
            stmt.setInt(3, prodSupplier.getSupplierId());

            // checks if the data was inserted

            int numInserts = stmt.executeUpdate();

            if (numInserts == 0)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR,
                        "Products Supplier not added. Please try again or contact Tech Support.");
                alert.showAndWait();
            }
            stmt.close();
            conn.close();

        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void updateProductSupplier(ProductSupplier prodSupplier){
        try{
            // instantiate DB connection
            Connection conn = DBConnect.getConnection();

            // call prepared statement
            PreparedStatement stmt = conn.prepareStatement(
                    "update products_suppliers set ProductId=?, SupplierId=? where ProductSupplierId=?");

            stmt.setInt(1, prodSupplier.getProductId());
            stmt.setInt(2, prodSupplier.getSupplierId());
            stmt.setInt(3, prodSupplier.getProductSupplierId());

            //checks if the data was inserted
            int numInserts = stmt.executeUpdate();
            if (numInserts == 0)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR,
                        "Product supplier update failed. Please try again or contact Tech Support.");
                alert.showAndWait();
            }
            stmt.close();
            conn.close();

        }catch(Exception e) { e.printStackTrace(); }
    }

    //Searches product suppliers in response to text input
    public static List<ProductSupplier> searchProductsSuppliers(String para) {
        List<ProductSupplier> productSuppliers = null;

        try {

            //Instantiate DB connection
            Connection conn = DBConnect.getConnection();

            String q = "select ProductSupplierId, ProductId, prodName, SupplierId, supName from products_suppliers " +
                    "left join products using (ProductId) " +
                    "right join suppliers using (SupplierId) " +
                    "where ProductSupplierId like ? or " +
                    "ProductId like ? or " +
                    "prodName like ? or " +
                    "SupplierId like ? or " +
                    "supName like ?";

            //Prepare an sql statement
            PreparedStatement stmt = conn.prepareStatement(q);
            stmt.setString(1, '%' + para + '%');
            stmt.setString(2, '%' + para + '%');
            stmt.setString(3, '%' + para + '%');
            stmt.setString(4, '%' + para + '%');
            stmt.setString(5, '%' + para + '%');

            //Executes statement
            ResultSet rs = stmt.executeQuery();

            productSuppliers = new ArrayList<>();

            //runs while reader has data
            while (rs.next()) {
                productSuppliers.add(new ProductSupplier(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5)));
            }
            rs.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return productSuppliers;
    }
}

