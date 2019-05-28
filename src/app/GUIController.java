package app;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import BLL.Administrator;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GUIController {
    /*
     * Purpose: Controller class for the base GUI, each other controll expands off of this.
     * Author: Brent Ward
     * Module:PROJ-207-OSD
     * Date May 28, 2019
     * */


    //Labels
    @FXML private Label lblClock;
    @FXML private Label lblWelcome;

    //Buttons
    @FXML private Button btnLogout;
    @FXML private Button btnDashboard;
    @FXML private Button btnAgents;
    @FXML private Button btnCustomers;
    @FXML private Button btnPackages;
    @FXML private Button btnProducts;
    @FXML private Button btnSuppliers;

    /****         BUTTON ACTIONS           ****/

    //Logout function
    @FXML void btnLogoutAction(ActionEvent event) throws IOException {
        //Changes the scene, fetches the stage
        Parent root = FXMLLoader.load(getClass().getResource("../views/login.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow(); //grabs the stage
        stage.setScene(scene);
    }

    //Returns to dashboard
    @FXML void btnDashboardAction(ActionEvent event) throws IOException {
        //Changes the scene, fetches the stage
        Parent root = FXMLLoader.load(getClass().getResource("../views/dashboard.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow(); //grabs the stage
        stage.setScene(scene);
    }
    //Navigates to Agent page
    @FXML void btnAgentsAction(ActionEvent event) throws IOException {
        //Changes the scene, fetches the stage
        Parent root = FXMLLoader.load(getClass().getResource("../views/agent.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow(); //grabs the stage
        stage.setScene(scene);
    }
    //Navigates to Customer page
    @FXML void btnCustomersAction(ActionEvent event) throws IOException {
        //Changes the scene, fetches the stage
        Parent root = FXMLLoader.load(getClass().getResource("../views/customer.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow(); //grabs the stage
        stage.setScene(scene);
    }
    //Navigates to Packages page
    @FXML void btnPackagesAction(ActionEvent event) throws IOException {
        //Changes the scene, fetches the stage
        Parent root = FXMLLoader.load(getClass().getResource("../views/package.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow(); //grabs the stage
        stage.setScene(scene);
    }
    //Navigates to Products page
    @FXML void btnProductsAction(ActionEvent event) throws IOException {
        //Changes the scene, fetches the stage
        Parent root = FXMLLoader.load(getClass().getResource("../views/product.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow(); //grabs the stage
        stage.setScene(scene);
    }
    //Navigates to Suppliers page
    @FXML void btnSuppliersAction(ActionEvent event) throws IOException {
        //Changes the scene, fetches the stage
        Parent root = FXMLLoader.load(getClass().getResource("../views/supplier.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow(); //grabs the stage
        stage.setScene(scene);
    }

    //Widget Code
    private void startClock() {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
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
        lblWelcome.setText(user.getLastName() + ", " + user.getFirstName());
        lblWelcome.setWrapText(true);
    }
}
