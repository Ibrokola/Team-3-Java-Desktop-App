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
    @FXML private Button btnUpdateAgent;
    @FXML private Button btnUpdateGoBack;

    @FXML private Button btnDeleteAgent;
    @FXML private Button btnDeleteGoBack;

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

    @FXML private TextField txtUpdateFirstName;
    @FXML private TextField txtUpdateMiddleInitial;
    @FXML private TextField txtUpdateLastName;
    @FXML private TextField txtUpdatePhone;
    @FXML private TextField txtUpdateEmail;

    //Combo boxes
    @FXML private ComboBox<String> cbAddPosition;
    @FXML private ComboBox<String> cbAddAgency;

    @FXML private ComboBox<Agent> cbUpdateAgent;
    @FXML private ComboBox<String> cbUpdatePosition;
    @FXML private ComboBox<String> cbUpdateAgency;

    @FXML private ComboBox<Agent> cbDeleteAgent;

    //Labels
    @FXML private Label lblUserName;
    @FXML private Label lblClock;

    @FXML private Label lblDeleteFirstName;
    @FXML private Label lblDeleteMiddleInitial;
    @FXML private Label lblDeleteLastName;
    @FXML private Label lblDeletePhone;
    @FXML private Label lblDeleteEmail;
    @FXML private Label lblDeletePosition;
    @FXML private Label lblDeleteAgency;

    //Table
    @FXML private TableView<Agent> tableAgents;
    @FXML private TableColumn<Agent, Integer> colID;
    @FXML private TableColumn<Agent, String> colFirstName;
    @FXML private TableColumn<Agent, String> colMiddleInitial;
    @FXML private TableColumn<Agent, String> colLastName;
    @FXML private TableColumn<Agent, String> colPhone;
    @FXML private TableColumn<Agent, String> colEmail;
    @FXML private TableColumn<Agent, String> colPosition;
    @FXML private TableColumn<Agent, Integer> colAgency;



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
            loadUpdatePane();
        }
        if(event.getSource() == btnDelete){
            loadDeletePane();
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

        /*** Update Pane ***/
        if(event.getSource() == btnUpdateAgent){
            if(Validation.isProvided(txtUpdateFirstName, "first name") && Validation.isProvided(txtUpdateLastName, "last name")
                    && Validation.isProvided(txtUpdatePhone, "phone") && Validation.isProvided(txtUpdateEmail, "email")
                    && Validation.hasSelection(cbUpdatePosition, "position") && Validation.hasSelection(cbUpdateAgency, "agency")){

                Agent tempAgent = cbUpdateAgent.getSelectionModel().getSelectedItem(); //used to grab the id

                //grabs the agency property from cb
                int agency = 0;
                if(cbUpdateAgency.getSelectionModel().getSelectedItem() == "Calgary"){
                    agency = 1;
                }else if(cbUpdateAgency.getSelectionModel().getSelectedItem() == "Okotoks"){
                    agency = 2;
                }

                Agent agent = new Agent(tempAgent.getID(), txtUpdateFirstName.getText(), txtUpdateMiddleInitial.getText(),
                        txtUpdateLastName.getText(), txtUpdatePhone.getText(), txtUpdateEmail.getText(),
                        cbUpdatePosition.getSelectionModel().getSelectedItem(), agency);

                //pushes it to the database
                AgentDB.updateAgent(agent);

                loadOverviewPane();
            }
        }

        /*** Delete Pane ***/
        if(event.getSource() == btnDeleteAgent){
            Agent agent = cbDeleteAgent.getSelectionModel().getSelectedItem();
            AgentDB.deleteAgent(agent);

            loadOverviewPane();
        }

        //Go back buttons
        if(event.getSource() == btnAddGoBack || event.getSource() == btnUpdateGoBack || event.getSource() == btnDeleteGoBack){
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


        //builds table
        colID.setCellValueFactory(cellData -> cellData.getValue().getIDProperty().asObject());
        colFirstName.setCellValueFactory(cellData -> cellData.getValue().getFirstNameProperty());
        colMiddleInitial.setCellValueFactory(cellData -> cellData.getValue().getMiddleInitialProperty());
        colLastName.setCellValueFactory(cellData -> cellData.getValue().getLastNameProperty());
        colPhone.setCellValueFactory(cellData -> cellData.getValue().getPhoneProperty());
        colEmail.setCellValueFactory(cellData -> cellData.getValue().getEmailProperty());
        colPosition.setCellValueFactory(cellData -> cellData.getValue().getPositionProperty());
        colAgency.setCellValueFactory(cellData -> cellData.getValue().getAgencyProperty().asObject());

        ObservableList<Agent> agents = FXCollections.observableArrayList(AgentDB.getAgents());
        tableAgents.setItems(agents);
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

        //clear text fields
        txtAddFirstName.clear();
        txtAddMiddleInitial.clear();
        txtAddLastName.clear();
        txtAddPhone.clear();
        txtAddEmail.clear();
    }

    private void loadUpdatePane(){
        paneUpdate.toFront();

        //layout setup
        paneAdd.setVisible(false);
        paneUpdate.setVisible(true);
        paneDelete.setVisible(false);
        paneOverview.setVisible(false);

        //combobox setup
        cbUpdateAgent.getItems().removeAll(cbDeleteAgent.getItems());
        ObservableList<Agent> agents = FXCollections.observableArrayList(AgentDB.getAgents());
        cbUpdateAgent.setItems(agents);

        cbUpdatePosition.getSelectionModel().clearSelection();
        cbUpdatePosition.getItems().removeAll(cbUpdatePosition.getItems());
        cbUpdatePosition.getItems().addAll("Junior Agent", "Intermediate Agent", "Senior Agent");

        cbUpdateAgency.getSelectionModel().clearSelection();
        cbUpdateAgency.getItems().removeAll(cbUpdateAgency.getItems());
        cbUpdateAgency.getItems().addAll("Calgary", "Okotoks");

        //clears text fields
        txtUpdateFirstName.clear();
        txtUpdateMiddleInitial.clear();
        txtUpdateLastName.clear();
        txtUpdatePhone.clear();
        txtUpdateEmail.clear();
    }
    private void loadDeletePane(){
        paneDelete.toFront();

        //layout setup
        paneAdd.setVisible(false);
        paneUpdate.setVisible(false);
        paneDelete.setVisible(true);
        paneOverview.setVisible(false);

        //combobox setup
        cbDeleteAgent.getItems().removeAll(cbDeleteAgent.getItems());
        ObservableList<Agent> agents = FXCollections.observableArrayList(AgentDB.getAgents());
        cbDeleteAgent.setItems(agents);

        //clear labels
        lblDeleteFirstName.setText("");
        lblDeleteMiddleInitial.setText("");
        lblDeleteLastName.setText("");
        lblDeletePhone.setText("");
        lblDeleteEmail.setText("");
        lblDeletePosition.setText("");
        lblDeleteAgency.setText("");
    }

    /****       Combo box Events       ****/


    @FXML void cbUpdateSelection(ActionEvent event){
        Agent tempAgent = cbUpdateAgent.getSelectionModel().getSelectedItem();

        txtUpdateFirstName.setText(tempAgent.getFirstName());
        txtUpdateMiddleInitial.setText(tempAgent.getMiddleInitial());
        txtUpdateLastName.setText(tempAgent.getLastName());
        txtUpdatePhone.setText(tempAgent.getPhone());
        txtUpdateEmail.setText(tempAgent.getEmail());
        cbUpdatePosition.setValue(tempAgent.getPosition());
        String agency = null;
        if(tempAgent.getAgency() == 1){
            agency = "Calgary";
        }else if(tempAgent.getAgency() == 2){
            agency = "Okotoks";
        }
        cbUpdateAgency.setValue(agency);
    }

    @FXML void cbDeleteSelection(ActionEvent event){
        Agent tempAgent = cbDeleteAgent.getSelectionModel().getSelectedItem();

        lblDeleteFirstName.setText(tempAgent.getFirstName());
        lblDeleteMiddleInitial.setText(tempAgent.getMiddleInitial());
        lblDeleteLastName.setText(tempAgent.getLastName());
        lblDeletePhone.setText(tempAgent.getPhone());
        lblDeleteEmail.setText(tempAgent.getEmail());
        lblDeletePosition.setText(tempAgent.getPosition());
        String agency = null;
        if(tempAgent.getAgency() == 1){
            agency = "Calgary";
        }else if(tempAgent.getAgency() == 2){
            agency = "Okotoks";
        }
        lblDeleteAgency.setText(agency);
    }
}
