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

    public static List<Product> getAgents() {
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

            stmt.setInt(1, product.getProductId());
            stmt.setString(2, product.getProdName());

            // checks if the data was inserted
            int numRows = stmt.executeUpdate();
            if (numRows == 0)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Product not added. Try again or contact Tech Support.");
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
            int numRows = stmt.executeUpdate();
            if (numRows == 0)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR,
                        "Product update failed. Please try again or contact Tech Support.");
                alert.showAndWait();
            }
            stmt.close();
            conn.close();

        }catch(Exception e) { e.printStackTrace(); }
    }
}
