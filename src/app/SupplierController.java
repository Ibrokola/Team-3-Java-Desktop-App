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
import BLL.Validation;
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

    /*
     * Purpose: Controller for the Supplier view page.
     * Author: Linda Wallace
     * Module: PROJ-207-OSD
     * Date: June 12, 2019
     * */

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
    @FXML private Button btnAdd;
    @FXML private Button btnUpdate;
    @FXML private Button btnDelete;

    //panes
    @FXML private Pane paneAdd;
    @FXML private Pane paneUpdate;
    @FXML private Pane paneDelete;
    @FXML private Pane paneOverview;

    // buttons along the bottom
    @FXML private Button btnAddSupplier;
    @FXML private Button btnAddGoBack;
    @FXML private Button btnUpdateSupplier;
    @FXML private Button btnUpdateGoBack;
    @FXML private Button btnDeleteSupplier;
    @FXML private Button btnDeleteGoBack;


    //Other properties
    @FXML private Label lblUserName;
    @FXML private Label lblClock;
    @FXML private TextField txtSearch;
    @FXML private AnchorPane mainWindow;

    //---------------------------------------------------------------
    //Table view properties
    @FXML private TableView<Supplier> tvSupplierList;
    @FXML private TableColumn<Supplier, Integer> colSupplierId;
    @FXML private TableColumn<Supplier, String> colSupplierName;

    //---------------------------------------------------------------
    //Buttons & text fields
    @FXML private TextField txtSupplierId;
    @FXML private TextField txtSupplierName;
    @FXML private TextField txtAddSupplierId;
    @FXML private TextField txtAddSupplierName;
    @FXML private TextField txtUpdateSupplierId;
    @FXML private TextField txtUpdateSupplierName;
    @FXML private TextField txtDeleteSupplierId;
    @FXML private TextField txtDeleteSupplierName;


    //---------------------------------------------------------------
    //Widget Code -- yeah Brent!
    private void startClock() {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss a   -   dd/MM/YYYY");
            lblClock.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    //---------------------------------------------------------------
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
            refreshOverviewPane();

            //populate the tableview list of suppliers
            colSupplierId.setCellValueFactory(cellData -> cellData.getValue().supplierIdProperty().asObject());
            colSupplierName.setCellValueFactory(cellData -> cellData.getValue().supNameProperty());
    }

    //---------------------------------------------------------------
    //method to refresh supplier table, take user back to the overview pane and clear contents of input fields
    public void refreshOverviewPane() {
        loadSuppliers();
        paneOverview.toFront();
        paneAdd.setVisible(false);
        paneUpdate.setVisible(false);
        paneDelete.setVisible(false);
        paneOverview.setVisible(true);
        txtAddSupplierId.clear();
        txtAddSupplierName.clear();
        txtUpdateSupplierId.clear();
        txtUpdateSupplierName.clear();
        txtDeleteSupplierId.clear();
        txtDeleteSupplierName.clear();
    }

    //---------------------------------------------------------------
    //create tableview of suppliers
    public void loadSuppliers() {
        ObservableList<Supplier> supplierList = FXCollections.observableArrayList();
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
    //---------------------------------------------------------------
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
        if(event.getSource() == btnAdd){
            paneAdd.toFront();
            paneAdd.setVisible(true);
            paneUpdate.setVisible(false);
            paneDelete.setVisible(false);
            paneOverview.setVisible(false);
        }
        if(event.getSource() == btnUpdate){
            paneUpdate.toFront();
            paneAdd.setVisible(false);
            paneUpdate.setVisible(true);
            paneDelete.setVisible(false);
            paneOverview.setVisible(false);
        }
        if(event.getSource() == btnDelete){
            paneDelete.toFront();
            paneAdd.setVisible(false);
            paneUpdate.setVisible(false);
            paneDelete.setVisible(true);
            paneOverview.setVisible(false);
        }
        if(event.getSource() == btnDeleteGoBack ||
                event.getSource() == btnAddGoBack ||
                event.getSource() == btnUpdateGoBack){
            refreshOverviewPane();
        }

        // Add a supplier button
        if(event.getSource() == btnAddSupplier){
            if( Validation.isProvided(txtAddSupplierId,"supplier id") &&
                    ( Validation.isProvided(txtAddSupplierName,"supplier name"))){
                Supplier supplier = new Supplier(Integer.parseInt(txtAddSupplierId.getText()), txtAddSupplierName.getText());
                SupplierDB.addSupplier(supplier);
                refreshOverviewPane();
            }


        }

        // Update a supplier button
        if(event.getSource() == btnUpdateSupplier){
            Supplier supplier = new Supplier(Integer.parseInt(txtUpdateSupplierId.getText()),txtUpdateSupplierName.getText());
            SupplierDB.updateSupplier(supplier);
            refreshOverviewPane();
        }

       // Delete a supplier button
        if(event.getSource() == btnDeleteSupplier) {
            Supplier supplier = new Supplier(Integer.parseInt(txtDeleteSupplierId.getText()),txtDeleteSupplierName.getText());
            SupplierDB.deleteSupplier(supplier);
            refreshOverviewPane();
        }
    }
}
