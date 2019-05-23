package app;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import BLL.Supplier;
import BLL.SupplierDB;
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

    //////////////////////////////////////////////////////////
    //Table view to show all suppliers
    @FXML
    private TableView<Supplier> tvSupplierList;
    @FXML
    private TableColumn<Supplier, Integer> colSupplierId;
    @FXML
    private TableColumn<Supplier, String> colSupplierName;

    /////////////////////////////////////////////////////////////
    //Buttons & text fields
    @FXML
    private TextField tfSupplierId;
    @FXML
    private TextField tfSupplierName;
    @FXML
    private Button btnAddSupplier;
    @FXML
    private Button btnDeleteSupplier;
    @FXML
    private Button btnClearSupplier;
    @FXML
    private Button btnEditSupplier;
    @FXML
    private Button btnSaveSupplier;

    @FXML
    private Button btnHomeSupplier;


    //////////////////////////////////////////////////////////
    //add supplier to database
    @FXML
    void btnAddSupplierAction(ActionEvent event) {

    }

    //////////////////////////////////////////////////////////
    //delete supplier from database
    @FXML
    void btnDeleteSupplierAction(ActionEvent event) {

    }


    /////////////////////////////////////////////////////////////////
    //update(Save edits) supplier in the database
    @FXML
    void btnSaveSupplierAction(ActionEvent event) {
        Supplier supplier = new Supplier(Integer.parseInt(tfSupplierId.getText()),tfSupplierName.getText());
        SupplierDB.updateSupplier(supplier);
        loadSuppliers();
        clearSupplierTextFields();

    }
    /////////////////////////////////////////////////////////////////////////
    //clear contents of text fields
    @FXML
    void btnClearSupplierAction(ActionEvent event) {
        clearSupplierTextFields();
    }

    void clearSupplierTextFields(){
        tfSupplierId.clear();
        tfSupplierName.clear();
    }

    /////////////////////////////////////////////////////////////////////
    // Brent's code
    // Takes the user back to the home page.
   @FXML
   void btnHomeAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../views/main.fxml"));
        Scene scene = new Scene(root);

        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }

    /////////////////////////////////////////////////////////////////////////
    //Uses the selection from the Tableview to populate the text fields
    @FXML
    void btnEditSupplierAction(ActionEvent event) throws IOException {
        Supplier s = tvSupplierList.getSelectionModel().getSelectedItem();
        if (s != null) {
            btnSaveSupplier.setDisable(false);
            btnDeleteSupplier.setDisable(false);
            tfSupplierId.setText(s.getSupplierId() + "");
            tfSupplierName.setText(s.getSupName() + "");
        } else {
            Alert alert2 = new Alert(Alert.AlertType.INFORMATION, "Please select supplier");
            alert2.showAndWait();
        }
    }


    ////////////////////////////////////////////////////////////////////////
    @FXML
    void initialize() {

        btnDeleteSupplier.setDisable(true);
        btnSaveSupplier.setDisable(true);

        //populate the tableview list of suppliers
        colSupplierId.setCellValueFactory(cellData -> cellData.getValue().supplierIdProperty().asObject());
        colSupplierName.setCellValueFactory(cellData -> cellData.getValue().supNameProperty());

       loadSuppliers();
    }

    //create tableview of suppliers
    public void loadSuppliers() {

        try {
            Connection conn = DBConnect.getConnection();
            supplierList.clear();
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
