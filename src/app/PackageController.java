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
     * Author: Brent Ward, Guido Amaya- starting template found at k33ptoo.
     * Module: PROJ-207-OSD
     * Date: May 28, 2019
     * */

    //buttons (right menu)
    @FXML private Button btnDashboard;
    @FXML private Button btnAgents;
    @FXML private Button btnCustomers;
    @FXML private Button btnPackages;
    @FXML private Button btnProducts;
    @FXML private Button btnSuppliers;
    @FXML private Button btnSettings;
    @FXML private Button btnSignout;

    //buttons for managing packages data (upper side overview pane)
    @FXML private Button btnAdd;
    @FXML private Button btnUpdate;
    @FXML private Button btnDelete;
    //Button for adding input into "packages" table

    // @FXML
    // private Button btnAddPackage;
    // @FXML
    // private Button btnAddGoBack;


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


    // Add package Form fields
    @FXML private TextField txtAddPkgName;
    @FXML private DatePicker dpPkgStartDate;
    @FXML private DatePicker dpPkgEndDate;
    @FXML private TextField txtPkgDescription;
    @FXML private TextField txtPkgBasePrice;
    //Button for adding input into "packages" table
    @FXML  private Button btnAddPackage;
    //button to go back to main panel
    @FXML private Button btnAddGoBack;

    //Update package form fields
    @FXML private ComboBox<Package> cbUpdatePackage;
    @FXML private TextField txtUpdatePkgName;
    @FXML private DatePicker dpUpdatePkgStartDate;
    @FXML private DatePicker dpUpdatePkgEndDate;
    @FXML private TextField txtUpdatePkgDescription;
    @FXML private TextField txtUpdatePkgBasePrice;
    //Button for updating package table
    @FXML private Button btnUpdatePackage;
    //button to go back to overview pane
    @FXML private Button btnUpdateGoBack;



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
//            paneAdd.toFront();
//            paneAdd.setVisible(true);
//            paneUpdate.setVisible(false);
//            //paneDelete.setVisible(false);
//            paneOverview.setVisible(false);
            // loadAddPane();



            loadAddPane();
        }
        if(event.getSource() == btnUpdate){
//            paneUpdate.toFront();
//            paneAdd.setVisible(false);
//            paneUpdate.setVisible(true);
//            //paneDelete.setVisible(false);
//            paneOverview.setVisible(false);
            loadUpdatePane();
        }

        /*** Operational buttons ***/
        if(event.getSource() == btnDelete){
            paneAdd.setVisible(false);
            paneUpdate.setVisible(false);
            //paneDelete.setVisible(false);
            paneOverview.setVisible(true);
        }


        /*** "Add" button on add package pane ****/

        if(event.getSource() == btnAddPackage){
            if(Validation.isProvided(txtAddPkgName, "package name") &&
                    // Validation.isProvided(dpPkgStartDate, "start date") &&
                    //Validation.isProvided(dpPkgEndDate, "end date") &&
                    // Validation.isProvided(txtPkgDescription, "description") &&
                    Validation.isProvided(txtPkgBasePrice, "base price")){


                Package packages = new Package(txtAddPkgName.getText(),
                        dpPkgStartDate.getValue(),
                        dpPkgEndDate.getValue(),
                        txtPkgDescription.getText(),
                        txtPkgBasePrice.getText()
                );

                //Adds package to the database
                PackageDB.addPackages(packages);

                loadOverviewPane();
            }
        }

        /****"update" button on update package pane***/
        /*
        if(Validation.isProvided(txtUpdatePkgName,"package name")&&
                Validation.isProvided(txtPkgDescription,"package description")&&
                //Validation.isProvided(dpUpdatePkgStartDate,"Start date")&&
                //Validation.isProvided(dpUpdatePkgEndDate,"end date")&&
                Validation.isProvided(txtUpdatePkgBasePrice, "base price"))
        {
           Package packages = new Package(
                   txtAddPkgName.getText(),
                   dpPkgStartDate.getValue(),
                   dpPkgEndDate.getValue(),
                   txtPkgDescription.getText(),
                   txtPkgBasePrice.getText()
           );

           //updates package to the database
            PackageDB.updatePackages(packages);

            loadOverviewPane();
        }

         */



        /*** go back buttons ****/
        if(event.getSource() == btnAddGoBack || event.getSource() == btnUpdateGoBack
               // || event.getSource() == btnDeleteGoBack
          )
        {
            loadOverviewPane();
        }







    }

    //"choose" combo box event on "update package" pane
    @FXML void cbUpdatePkgSelection(ActionEvent event) {
        Package tempPackage = cbUpdatePackage.getSelectionModel().getSelectedItem();

        txtUpdatePkgName.setText(tempPackage.getPkgName());
        txtUpdatePkgDescription.setText(tempPackage.getPkgDesc());
        //txtPkgBasePrice.setText(tempPackage.getPkgBasePrice());
        //dpUpdatePkgStartDate.setChronology(tempPackage.getPkgStartDate());
        //        dpUpdatePkgEndDate.setChronology(tempPackage.getPkgEndDate());
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

        paneAdd.setVisible(false);
        paneUpdate.setVisible(false);
        //paneDelete.setVisible(false);
        paneOverview.setVisible(true);

        loadOverviewPane();
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
        colPkgId.setCellValueFactory(cellData -> cellData.getValue().packageIdProperty().asObject());
        colPkgName.setCellValueFactory(cellData -> cellData.getValue().pkgNameProperty());
        colPkgStrartDate.setCellValueFactory(cellData -> cellData.getValue().pkgStartDateProperty());
        colPkgEndDate.setCellValueFactory(cellData -> cellData.getValue().pkgEndDateProperty());
        colPkgDescription.setCellValueFactory(cellData -> cellData.getValue().pkgDescProperty());
        colPkgBasePrice.setCellValueFactory(cellData -> cellData.getValue().pkgBasePriceProperty().asObject());

        ObservableList<Package> packages = FXCollections.observableArrayList(PackageDB.getPackages());
        tblPackages.setItems(packages);

    }

    //Loads "Add Packages" pane
    private void loadAddPane(){
        paneAdd.toFront();

        paneAdd.setVisible(true);
        paneUpdate.setVisible(false);
        //paneDelete.setVisible(false);
        paneOverview.setVisible(false);

    }


    // private void loadUpdatePane() {
    // }




    //Loads "Update Packages" pane
    private void loadUpdatePane(){
        paneUpdate.toFront();
        paneAdd.setVisible(false);
        paneUpdate.setVisible(true);
        //paneDelete.setVisible(false);
        paneOverview.setVisible(false);

        //combo box setup
        cbUpdatePackage.getSelectionModel().clearSelection();
        cbUpdatePackage.getItems().removeAll();
        ObservableList<Package> packages = FXCollections.observableArrayList(PackageDB.getPackages());
        cbUpdatePackage.setItems(packages);

    }








    /*** Methods for Buttons ****/
    //method for Add button




}
