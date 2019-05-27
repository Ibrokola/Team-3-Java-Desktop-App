package BLL;


import DLL.DBConnect;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SupplierDB {

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


}
