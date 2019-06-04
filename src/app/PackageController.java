package app;

import BLL.Administrator;
import BLL.Package;
import BLL.PackageDB;
import BLL.Validation;
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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class PackageController {
    /*
     * Purpose: To act as a base Controller for all other pages to branch from.
     * Author: Brent Ward - starting template found at k33ptoo.
     * Module: PROJ-207-OSD
     * Date: May 28, 2019
     * */

    //buttons
    @FXML private Button btnDashboard;
    @FXML private Button btnAgents;
    @FXML private Button btnCustomers;
    @FXML private Button btnPackages;
    @FXML private Button btnProducts;
    @FXML private Button btnSuppliers;
    @FXML private Button btnSettings;
    @FXML private Button btnSignout;
    @FXML private Button btnAdd;
    @FXML private Button btnUpdate;
    @FXML private Button btnDelete;
    //Button for adding input into "packages" table
    @FXML
    private Button btnAddPackage;


    //panes
    @FXML private Pane paneAdd;
    @FXML private Pane paneUpdate;
    @FXML private Pane paneOverview;

    //Other properties
    @FXML private Label lblUserName;
    @FXML private Label lblClock;
    @FXML private TextField txtSearch;

    //Table view for Packages data
    @FXML
    private TableView<Package> tblPackages;

    //Columns to display data from packages table
    @FXML private TableColumn<Package, Integer> colPkgId;
    @FXML private TableColumn<Package, String> colPkgName;
    @FXML private TableColumn<Package, Date> colPkgStrartDate;
    @FXML private TableColumn<Package, Date> colPkgEndDate;
    @FXML private TableColumn<Package, Double> colPkgBasePrice;
    @FXML private TableColumn<Package, String> colPkgDescription;

    //@FXML
    //private Pane paneAdd;

    //Form's fields to be filled in order to add a Package to "packages" table
    @FXML private TextField txtAddPkgName;
    @FXML private DatePicker dpPkgStartDate;
    @FXML private DatePicker dpPkgEndDate;
    @FXML private TextField txtPkgDescription;
    @FXML private TextField txtPkgBasePrice;



    //handles all button clocks
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
        }
        if(event.getSource() == btnUpdate){
            paneUpdate.toFront();
        }

        /*** Operational buttons ***/
        if(event.getSource() == btnDelete){ }

        /*** "Add package" Button ****/

        if(event.getSource() == btnAddPackage){
            if(Validation.isProvided(txtAddPkgName, "package name") &&
                    Validation.isProvided(dpPkgStartDate, "start date") &&
                    Validation.isProvided(dpPkgEndDate, "end date") &&
                    Validation.isProvided(txtPkgDescription, "description") &&
                    Validation.hasSelection(txtPkgBasePrice, "base price")){


                Package packages = new Package(txtAddPkgName.getText(),
                        dpPkgStartDate.getText(),
                        dpPkgEndDate.getText(),
                        txtPkgDescription.getText(),
                        txtPkgBasePrice.getText()
                );

                //Adds the agent to the database
                PackageDB.addPackages(packages);

                loadOverviewPane();
            }
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

    //Startup Sequence
    @FXML void initialize() {
        startClock(); //runs the clock
        //builds the welcome label
        Administrator user = LoginController.userLoggedIn();
        lblUserName.setText(user.getLastName() + ", " + user.getFirstName());
        lblUserName.setWrapText(true);
    }

    //Load Packages overview panel
    private void loadOverviewPane() {
        paneOverview.toFront();

        //returns to overview pane
        paneAdd.setVisible(false);
        paneUpdate.setVisible(false);
        //paneDelete.setVisible(false);
        paneOverview.setVisible(true);

        //Packages table
       // colPkgId.setCellValueFactory(cellData -> cellData.getValue().getPackageId().asObject());
        colPkgName.setCellValueFactory(cellData -> cellData.getValue().pkgNameProperty());
        colPkgStrartDate.setCellValueFactory(cellData -> cellData.getValue().pkgStartDateProperty());
        colPkgEndDate.setCellValueFactory(cellData -> cellData.getValue().pkgEndDateProperty());
        colPkgDescription.setCellValueFactory(cellData -> cellData.getValue().pkgDescProperty());
        //colPkgBasePrice.setCellValueFactory(cellData -> cellData.getValue().getPkgBasePrice().asObject);

        ObservableList<Package> packages = FXCollections.observableArrayList(PackageDB.getPackages());
                tblPackages.setItems(packages);

    }


    


    //button to go back to main panel
    @FXML
    private Button btnAddGoBack;


    /*** Methods for Buttons ****/
     //method for Add button




}
