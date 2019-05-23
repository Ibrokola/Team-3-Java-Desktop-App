package BLL;


import DLL.DBConnect;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SupplierDB {

    public static void updateSupplier(Supplier supplier){

    Connection conn = DBConnect.getConnection();

        try {
        PreparedStatement stmt = conn.prepareStatement("UPDATE suppliers SET SupName=? WHERE SupplierId=?");

        stmt.setString(1, supplier.getSupName());
        stmt.setInt(2, supplier.getSupplierId());

        int rowsUpdated = stmt.executeUpdate();
        if (rowsUpdated == 0)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Error updating the database", ButtonType.OK);
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

}
