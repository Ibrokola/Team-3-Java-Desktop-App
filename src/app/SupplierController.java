package app;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import BLL.Supplier;
import DLL.DBConnect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class SupplierController {

    ObservableList<Supplier> supplierList = FXCollections.observableArrayList();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Supplier> tvSupplierList;

    @FXML
    private TextField tfSupplierIdAdd;

    @FXML
    private TextField tfSupplierNameAdd;

    @FXML
    private Button btnAddSupplier;

    @FXML
    private TextField tfSupplierIdUpdate;

    @FXML
    private TextField tfSupplierNameUpdate;

    @FXML
    private Button btnUpdateSupplier;

    @FXML
    private Button btnHome;

    @FXML
    void btnAddSupplierAction(ActionEvent event) {

    }

    @FXML
    void btnHomeAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateSupplierAction(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert tvSupplierList != null : "fx:id=\"tvSupplierList\" was not injected: check your FXML file 'supplier.fxml'.";
        assert tfSupplierIdAdd != null : "fx:id=\"tfSupplierIdAdd\" was not injected: check your FXML file 'supplier.fxml'.";
        assert tfSupplierNameAdd != null : "fx:id=\"tfSupplierNameAdd\" was not injected: check your FXML file 'supplier.fxml'.";
        assert btnAddSupplier != null : "fx:id=\"btnAddSupplier\" was not injected: check your FXML file 'supplier.fxml'.";
        assert tfSupplierIdUpdate != null : "fx:id=\"tfSupplierIdUpdate\" was not injected: check your FXML file 'supplier.fxml'.";
        assert tfSupplierNameUpdate != null : "fx:id=\"tfSupplierNameUpdate\" was not injected: check your FXML file 'supplier.fxml'.";
        assert btnUpdateSupplier != null : "fx:id=\"btnUpdateSupplier\" was not injected: check your FXML file 'supplier.fxml'.";
        assert btnHome != null : "fx:id=\"btnHome\" was not injected: check your FXML file 'supplier.fxml'.";

       // loadSuppliers();
    }



    public void loadSuppliers() {
        Connection conn = DBConnect.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from suppliers");
            while (rs.next())
            {
                supplierList.add(new Supplier(rs.getInt(1),
                        rs.getString(2)
                ));
            }
            conn.close();
            tvSupplierList.setItems(supplierList);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
