package app;

import BLL.Administrator;
import BLL.Agent;
import BLL.AgentDB;
import BLL.Validation;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AgentController {
    /*
     * Purpose: Controller for the Agent viewpage.
     * Author: Brent Ward - starting template found at k33ptoo.
     * Module: PROJ-207-OSD
     * Date: May 15, 2019
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
    @FXML private Button btnAddAgent;
    @FXML private Button btnAddGoBack;

    //panes
    @FXML private Pane paneAdd;
    @FXML private Pane paneUpdate;
    @FXML private Pane paneDelete;
    @FXML private Pane paneOverview;

    //Text fields
    @FXML private TextField txtSearch;
    @FXML private TextField txtAddFirstName;
    @FXML private TextField txtAddMiddleInitial;
    @FXML private TextField txtAddLastName;
    @FXML private TextField txtAddPhone;
    @FXML private TextField txtAddEmail;

    //Combo boxes
    @FXML private ComboBox<String> cbAddPosition;
    @FXML private ComboBox<String> cbAddAgency;

    //Other properties
    @FXML private Label lblUserName;
    @FXML private Label lblClock;



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
            loadAddPane();
        }
        if(event.getSource() == btnUpdate){
            paneUpdate.toFront();

            //layout setup
            paneAdd.setVisible(false);
            paneUpdate.setVisible(true);
            paneDelete.setVisible(false);
            paneOverview.setVisible(false);
        }
        if(event.getSource() == btnDelete){
            paneDelete.toFront();

            //layout setup
            paneAdd.setVisible(false);
            paneUpdate.setVisible(false);
            paneDelete.setVisible(true);
            paneOverview.setVisible(false);
        }

        /*** Add Pane ***/
        if(event.getSource() == btnAddAgent){
            if(Validation.isProvided(txtAddFirstName, "first name") && Validation.isProvided(txtAddMiddleInitial, "middle initial") &&
                    Validation.isProvided(txtAddLastName, "last name") && Validation.isProvided(txtAddPhone, "phone") &&
                    Validation.isProvided(txtAddEmail, "email") && Validation.hasSelection(cbAddPosition, "position") &&
                    Validation.hasSelection(cbAddAgency, "agency")){

                int agency = 0;

                //Assigns agency combobox value
                if(cbAddAgency.getSelectionModel().getSelectedItem() == "Calgary"){
                    agency = 1;
                }else if(cbAddAgency.getSelectionModel().getSelectedItem() == "Okotoks"){
                    agency = 2;
                }

                Agent agent = new Agent(txtAddFirstName.getText(), txtAddMiddleInitial.getText(), txtAddLastName.getText(), txtAddPhone.getText(),
                                            txtAddEmail.getText(), cbAddPosition.getSelectionModel().getSelectedItem(), agency);

                //Adds the agent to the database
                AgentDB.addAgent(agent);

                loadOverviewPane();
            }
        }
        if(event.getSource() == btnAddGoBack){
            loadOverviewPane();
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

        //layout setup
        paneAdd.setVisible(false);
        paneUpdate.setVisible(false);
        paneDelete.setVisible(false);
        paneOverview.setVisible(true);

        loadOverviewPane();


        /****           Listeners            ****/

        //Changes the table based off text in search bar
        txtSearch.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                                String oldValue, String newValue) {
                //ADD CODE HERE
            }
        });
    }

    /*** Pane loading ***/

    private void loadOverviewPane(){
        paneOverview.toFront();

        //returns to overview pane
        paneAdd.setVisible(false);
        paneUpdate.setVisible(false);
        paneDelete.setVisible(false);
        paneOverview.setVisible(true);
    }
    private void loadAddPane(){
        paneAdd.toFront();

        //layout setup
        paneAdd.setVisible(true);
        paneUpdate.setVisible(false);
        paneDelete.setVisible(false);
        paneOverview.setVisible(false);

        //sets up combo boxes
        cbAddPosition.getSelectionModel().clearSelection();
        cbAddPosition.getItems().removeAll(cbAddPosition.getItems());
        cbAddPosition.getItems().addAll("Junior Agent", "Intermediate Agent", "Senior Agent");

        cbAddAgency.getSelectionModel().clearSelection();
        cbAddAgency.getItems().removeAll(cbAddAgency.getItems());
        cbAddAgency.getItems().addAll("Calgary", "Okotoks");
    }

    private void loadUpdatePane(){

    }
    private void loadDeletePane(){

    }
}
