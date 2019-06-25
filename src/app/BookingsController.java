package app;

import BLL.Administrator;
import BLL.Booking;
import BLL.BookingDB;
import BLL.Customer;
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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class BookingsController {
    /*
     * Purpose: Display Invoices for a selected Customer
     * Author: Brent Ward
     * Module: PROJ-207-OSD
     * Date: June 24 2019
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

    //panes
    @FXML private Pane paneOverview;

    //table
    @FXML private TableView<Booking> tableBookings;
    @FXML private TableColumn<Booking, String> tcBookingNo;
    @FXML private TableColumn<Booking, Date> tcBookingDate;
    @FXML private TableColumn<Booking, Integer> tcTravelerCount;
    @FXML private TableColumn<Booking, Date> tcTripStart;
    @FXML private TableColumn<Booking, Date> tcTripEnd;
    @FXML private TableColumn<Booking, String> tcDescription;
    @FXML private TableColumn<Booking, String> tcDestination;
    @FXML private TableColumn<Booking, Double> tcBasePrice;
    @FXML private TableColumn<Booking, String> tcRegion;

    //Other properties
    @FXML private Label lblUserName;
    @FXML private Label lblClock;
    @FXML private AnchorPane mainWindow;
    @FXML private ImageView imgProfilePicture;
    private Customer selectedCustomer;
    @FXML private Label txtSelectedCustomer;


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

        //loads users color setting
        String mode = SettingsController.getColorMode();
        mainWindow.getStylesheets().clear();
        mainWindow.getStylesheets().add("css/" + mode + ".css");
        if(SettingsController.getProfilePicture() != null){
            imgProfilePicture.setImage(SettingsController.getProfilePicture());
        }

        selectedCustomer = CustomerController.getSelectedCustomer();
        txtSelectedCustomer.setText(selectedCustomer.toString());

        loadTable();
    }

    private void loadTable(){
        ObservableList<Booking> bookings = FXCollections.observableArrayList(BookingDB.getBookings(selectedCustomer.getId()));

        //builds the table
        tcBookingNo.setCellValueFactory(cellData -> cellData.getValue().getBookingNoProperty());
        tcBookingDate.setCellValueFactory(cellData -> cellData.getValue().getBookingDateProperty());
        tcTravelerCount.setCellValueFactory(cellData -> cellData.getValue().getTravelerCountProperty().asObject());
        tcTripStart.setCellValueFactory(cellData -> cellData.getValue().getTripStartProperty());
        tcTripEnd.setCellValueFactory(cellData -> cellData.getValue().getTripEndProperty());
        tcDescription.setCellValueFactory(cellData -> cellData.getValue().getDescriptionProperty());
        tcDestination.setCellValueFactory(cellData -> cellData.getValue().getDestinationProperty());
        tcBasePrice.setCellValueFactory(cellData -> cellData.getValue().getBasePriceProperty().asObject());
        tcRegion.setCellValueFactory(cellData -> cellData.getValue().getRegionProperty());

        tableBookings.setItems(bookings);
    }

}
