package BLL;

import DLL.DBConnect;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class ProductDB {

    public static List<Product> getProducts() {
        ArrayList<Product> products = null;


        try{
            // instantiate DB connection
            Connection conn = DBConnect.getConnection();

            // instantiate query
            Statement query = conn.createStatement();

            // execute statement
            ResultSet rs = query.executeQuery("select * from Products");

            // assign products arrayList
            products = new ArrayList<Product>();

            // retrieve result set and add to product arrays
            while(rs.next()){
                products.add(new Product(
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

        return products;
    }

    public static void addProduct(Product product){
        try{
            // instantiate DB connection
            Connection conn = DBConnect.getConnection();

            // call prepared statement
            PreparedStatement stmt = conn.prepareStatement("Insert into Products values(?, ?)");

            stmt.setString(1, null);
            stmt.setString(2, product.getProdName());

            // checks if the data was inserted

            int numInserts = stmt.executeUpdate();

            if (numInserts == 0)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR,
                        "Product not added. Try again or contact Tech Support.");
                alert.showAndWait();
            }
            stmt.close();
            conn.close();

        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void updateProduct(Product product){
        try{
            // instantiate DB connection
            Connection conn = DBConnect.getConnection();

            // call prepared statement
            PreparedStatement stmt = conn.prepareStatement(
                    "update Products set ProdName=? where ProductId=?");

            stmt.setString(1, product.getProdName());
            stmt.setInt(2, product.getProductId());

            //checks if the data was inserted
            int numInserts = stmt.executeUpdate();
            if (numInserts == 0)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR,
                        "Product update failed. Please try again or contact Tech Support.");
                alert.showAndWait();
            }
            stmt.close();
            conn.close();

        }catch(Exception e) { e.printStackTrace(); }
    }

    //Searches product in response to text input
    public static List<Product> searchProducts(String para) {
        List<Product> products = null;

        try {

            //Instantiate DB connection
            Connection conn = DBConnect.getConnection();

            //Prepare an sql statement
            PreparedStatement stmt = conn.prepareStatement(
                    "select * from Products where ProductId like ? or ProdName like ?");
            stmt.setString(1, '%' + para + '%');
            stmt.setString(2, '%' + para + '%');

            //Executes statement
            ResultSet rs = stmt.executeQuery();

            products = new ArrayList<>();
            //runs while reader has data
            while (rs.next()) {
                products.add(new Product(
                        rs.getInt(1),
                        rs.getString(2)));
            }
            rs.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return products;
    }

    // Deletes a Product (Not advisable, due to database inconsistencies)
    public static void deleteProduct(Product product){
        try
        {
            //connection built
            Connection conn = DBConnect.getConnection();

            PreparedStatement stmt = conn.prepareStatement("delete from Products where ProductId=?");
            stmt.setInt(1, product.getProductId());

            //checks if agent is deleted
            int numRows = stmt.executeUpdate();
            if(numRows == 0){
                Alert alert = new Alert(Alert.AlertType.ERROR, "Product failed to delete. Please try again or contact Tech Support.");
                alert.showAndWait();
            }

            conn.close();

        }catch(Exception e) { e.printStackTrace(); }

    }

    // Retrieve a single product
    public static Product getProduct(int id){
        Product product = null;

        try {
            //connection built
            Connection conn = DBConnect.getConnection();

            //makes a sql statement
            PreparedStatement stmt = conn.prepareStatement("select * from products where ProductId=?");
            stmt.setInt(1, id);

            //assigns and executes statement
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                product = new Product(rs.getInt("ProductId"),
                        rs.getString("ProdName"));
            }
            conn.close();

        }catch(Exception e){ e.printStackTrace(); }

        return product;
    }

}
