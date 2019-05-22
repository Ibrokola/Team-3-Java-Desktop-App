package app;

import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class SupplierController {

    ObservableList<Supplier> supplierList = FXCollections.observableArrayList();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    //Table view to show all suppliers
    @FXML
    private TableView<Supplier> tvSupplierList;
    @FXML
    private TableColumn<Supplier, Integer> colSupplierId;
    @FXML
    private TableColumn<Supplier, String> colSupplierName;

    //text fields and buttons for Adding new Supplier
    @FXML
    private TextField tfSupplierIdAdd;
    @FXML
    private TextField tfSupplierNameAdd;
    @FXML
    private Button btnAddSupplier;
    @FXML
    private Button btnClearAddSupplier;

    //text fields and buttons for Updating Supplier Info
    @FXML
    private TextField tfSupplierIdUpdate;
    @FXML
    private TextField tfSupplierNameUpdate;
    @FXML
    private Button btnUpdateSupplier;
    @FXML
    private Button btnClearUpdateSupplier;

    //button to return to main page
    @FXML
    private Button btnHomeSupplier;

    //add supplier to database
    @FXML
    void btnAddSupplierAction(ActionEvent event) {

    }

    //clear contents of text fields on the Add Supplier tab
    @FXML
    void btnClearAddSupplierAction(ActionEvent event) {
        tfSupplierIdAdd.clear();
        tfSupplierNameAdd.clear();
    }

    //update supplier in the database
    @FXML
    void btnUpdateSupplierAction(ActionEvent event) {

    }

    //clear contents of text fields on the Update Supplier tab
    @FXML
    void btnClearUpdateSupplierAction(ActionEvent event) {
        tfSupplierIdUpdate.clear();
        tfSupplierNameUpdate.clear();
    }

    // Brent's code
    // Takes the user back to the home page.
   @FXML void btnHomeAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../views/main.fxml"));
        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
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
        assert btnHomeSupplier != null : "fx:id=\"btnHome\" was not injected: check your FXML file 'supplier.fxml'.";

        //populate the tableview list of suppliers
        colSupplierId.setCellValueFactory(cellData -> cellData.getValue().supplierIdProperty().asObject());
        colSupplierName.setCellValueFactory(cellData -> cellData.getValue().supNameProperty());

       loadSuppliers();
    }

    //create tableview of suppliers
    public void loadSuppliers() {

        try {
            Connection conn = DBConnect.getConnection();
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
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "loadSuppliers did not work");
            alert.showAndWait();

            e.printStackTrace();
        }
    }
}
