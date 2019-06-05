package app;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import BLL.Administrator;
import BLL.Supplier;
import BLL.SupplierDB;
import DLL.DBConnect;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SupplierController {

    ObservableList<Supplier> supplierList = FXCollections.observableArrayList();

    @FXML private ResourceBundle resources;
    @FXML private URL location;

    //buttons - left hand menu
    @FXML private Button btnDashboard;
    @FXML private Button btnAgents;
    @FXML private Button btnCustomers;
    @FXML private Button btnPackages;
    @FXML private Button btnProducts;
    @FXML private Button btnSuppliers;
    @FXML private Button btnSettings;
    @FXML private Button btnSignout;

    //buttons - top selections, overview pane - takes user to the appropriate pane
    @FXML private Button btnAddS;
    @FXML private Button btnUpdate;
    @FXML private Button btnDelete;

    //panes
    @FXML private Pane paneAddSupplier;
    @FXML private Pane paneUpdateSupplier;
    @FXML private Pane paneDeleteSupplier;
    @FXML private Pane paneOverviewSupplier;


    @FXML private Button btnAddSupplier;
    @FXML private Button btnAddGoBackSupplier;
    @FXML private Button btnUpdateSupplier;
    @FXML private Button btnUpdateGoBackSupplier;
    @FXML private Button btnDeleteSupplier;
    @FXML private Button btnDeleteGoBackSupplier;

    //Other properties
    @FXML private Label lblUserName;
    @FXML private Label lblClock;
    @FXML private TextField txtSearch;
    @FXML private AnchorPane mainWindow;

    //////////////////////////////////////////////////////////
    //Table view to show all suppliers
    @FXML private TableView<Supplier> tvSupplierList;
    @FXML private TableColumn<Supplier, Integer> colSupplierId;
    @FXML private TableColumn<Supplier, String> colSupplierName;

    /////////////////////////////////////////////////////////////
    //Buttons & text fields
    @FXML private TextField tfSupplierId;
    @FXML private TextField tfSupplierName;

   // @FXML private Button btnClearSupplier;
   // @FXML private Button btnEditSupplier;
    //@FXML private Button btnSaveSupplier;

   // @FXML private Button btnHomeSupplier;


    //////////////////////////////////////////////////////////
    //add supplier to database
    @FXML
    void btnAddSupplierAction(ActionEvent event) {
        Supplier supplier = new Supplier(Integer.parseInt(tfSupplierId.getText()),tfSupplierName.getText());
        SupplierDB.addSupplier(supplier);
        loadSuppliers();
        clearSupplierTextFields();

    }

    //////////////////////////////////////////////////////////
    //delete supplier from database
    @FXML
    void btnDeleteSupplierAction(ActionEvent event) {
        Supplier supplier = new Supplier(Integer.parseInt(tfSupplierId.getText()),tfSupplierName.getText());
        SupplierDB.deleteSupplier(supplier);
        loadSuppliers();
        clearSupplierTextFields();
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
        Parent root = FXMLLoader.load(getClass().getResource("../views/dashboard.fxml"));
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
           // btnSaveSupplier.setDisable(false);
            btnDeleteSupplier.setDisable(false);
            tfSupplierId.setText(s.getSupplierId() + "");
            tfSupplierName.setText(s.getSupName() + "");
        } else {
            Alert alert2 = new Alert(Alert.AlertType.INFORMATION, "Please select supplier");
            alert2.showAndWait();
        }
    }
    //Widget Code
    private void startClock() {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss a   -   dd/MM/YYYY");
            lblClock.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    ////////////////////////////////////////////////////////////////////////
    @FXML void initialize() {

            startClock(); //runs the clock
            //builds the welcome label
            Administrator user = LoginController.userLoggedIn();
            lblUserName.setText(user.getLastName() + ", " + user.getFirstName());
            lblUserName.setWrapText(true);

            //loads users color setting
            String mode = SettingsController.getColorMode();
            mainWindow.getStylesheets().clear();
            mainWindow.getStylesheets().add("css/" + mode + ".css");

            //layout setup
            paneAddSupplier.setVisible(false);
            paneUpdateSupplier.setVisible(false);
            paneDeleteSupplier.setVisible(false);
            paneOverviewSupplier.setVisible(true);

        btnDeleteSupplier.setDisable(true);
       // btnSaveSupplier.setDisable(true);

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

    //handles all button clicks
    @FXML void handleButtonClicks(ActionEvent event) throws IOException {
        //dashboard button
        if(event.getSource() == btnDashboard){
            //Changes the scene, fetches the stage
            Parent root = FXMLLoader.load(getClass().getResource("../views/dashboard.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow(); //grabs the stage
            stage.setScene(scene);
        }
        //agent button
        if(event.getSource() == btnAgents){
            //Changes the scene, fetches the stage
            Parent root = FXMLLoader.load(getClass().getResource("../views/agent.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow(); //grabs the stage
            stage.setScene(scene);
        }
        //customer button
        if(event.getSource() == btnCustomers){
            //Changes the scene, fetches the stage
            Parent root = FXMLLoader.load(getClass().getResource("../views/customer.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow(); //grabs the stage
            stage.setScene(scene);
        }
        //package button
        if(event.getSource() == btnPackages){
            //Changes the scene, fetches the stage
            Parent root = FXMLLoader.load(getClass().getResource("../views/package.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow(); //grabs the stage
            stage.setScene(scene);
        }
        //product button
        if(event.getSource() == btnProducts){
            //Changes the scene, fetches the stage
            Parent root = FXMLLoader.load(getClass().getResource("../views/product.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow(); //grabs the stage
            stage.setScene(scene);
        }
        //supplier button
        if(event.getSource() == btnSuppliers){
            //Changes the scene, fetches the stage
            Parent root = FXMLLoader.load(getClass().getResource("../views/supplier.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow(); //grabs the stage
            stage.setScene(scene);
        }
        //settings button
        if(event.getSource() == btnSettings){
            //Changes the scene, fetches the stage
            Parent root = FXMLLoader.load(getClass().getResource("../views/settings.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow(); //grabs the stage
            stage.setScene(scene);
        }
        //log out
        if(event.getSource() == btnSignout){
            //Changes the scene, fetches the stage
            Parent root = FXMLLoader.load(getClass().getResource("../views/login.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow(); //grabs the stage
            stage.setScene(scene);
        }

        /*** Pane switching buttons ***/
        if(event.getSource() == btnAddS){
            paneAddSupplier.toFront();
            paneAddSupplier.setVisible(true);
            paneUpdateSupplier.setVisible(false);
            paneDeleteSupplier.setVisible(false);
            paneOverviewSupplier.setVisible(false);
        }
        if(event.getSource() == btnUpdate){
            paneUpdateSupplier.toFront();
            paneAddSupplier.setVisible(false);
            paneUpdateSupplier.setVisible(true);
            paneDeleteSupplier.setVisible(false);
            paneOverviewSupplier.setVisible(false);
        }
        if(event.getSource() == btnDelete){
            paneDeleteSupplier.toFront();
            paneAddSupplier.setVisible(false);
            paneUpdateSupplier.setVisible(false);
            paneDeleteSupplier.setVisible(true);
            paneOverviewSupplier.setVisible(false);
        }


    }

}
