package app;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
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

    //////////////////////////////////////////////////////////
    //Table view to show all suppliers
    @FXML
    private TableView<Supplier> tvSupplierList;
    @FXML
    private TableColumn<Supplier, Integer> colSupplierId;
    @FXML
    private TableColumn<Supplier, String> colSupplierName;

    /////////////////////////////////////////////////////////////
    //button for Adding new Supplier
    private Button btnAddSupplier;


    ////////////////////////////////////////////////////////////
    //text fields and buttons for Updating Supplier Info
    @FXML
    private TextField tfSupplierId;
    @FXML
    private TextField tfSupplierName;
    @FXML
    private Button btnUpdateSupplier;
    @FXML
    private Button btnClearSupplier;

    //////////////////////////////////////////////////////////
    //button to return to main page
    @FXML
    private Button btnHomeSupplier;

    ////////////////////////////////////////////////////////////
    //button to select line(supplier) to update
    @FXML
    private Button btnSelectSupplier;

    //////////////////////////////////////////////////////////
    //add supplier to database
    @FXML
    void btnAddSupplierAction(ActionEvent event) {

    }


    /////////////////////////////////////////////////////////////////
    //update supplier in the database
    @FXML
    void btnUpdateSupplierAction(ActionEvent event) {

        Connection conn = DBConnect.getConnection();

        try {
            PreparedStatement stmt = conn.prepareStatement("UPDATE suppliers SET SupName=? WHERE SupplierId=?");
            stmt.setString(1, tfSupplierName.getText());
            stmt.setInt(2, Integer.parseInt(tfSupplierId.getText()));
            //stmt.setInt(2, tvSupplierList.getSelectionModel().getSelectedItem().getSupplierId());
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated == 0)
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Error updating the database", ButtonType.OK);
                alert.showAndWait();
            }
            conn.close();
            loadSuppliers();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    /////////////////////////////////////////////////////////////////////////
    //clear contents of text fields on the Update Supplier tab
    @FXML
    void btnClearSupplierAction(ActionEvent event) {
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
    //Uses the selection from the Tableview to and redirects to Update tab
    @FXML
    void btnSelectSupplierAction(ActionEvent event) throws IOException {
        Supplier s = tvSupplierList.getSelectionModel().getSelectedItem();
        tfSupplierId.setText(s.getSupplierId()+"");
        tfSupplierName.setText(s.getSupName()+"");
    }


    ////////////////////////////////////////////////////////////////////////
    @FXML
    void initialize() {


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
