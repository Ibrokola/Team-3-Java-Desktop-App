package app;

import BLL.*;
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

public class CustomerController {
    /*
     * Purpose: Controller for the customer view page.
     * Author: Brent Ward
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
    @FXML private Button btnUpdate;
    @FXML private Button btnDelete;

    @FXML private Button btnUpdateCustomer;
    @FXML private Button btnUpdateGoBack;

    @FXML private Button btnDeleteCustomer;
    @FXML private Button btnDeleteGoBack;

    //panes
    @FXML private Pane paneUpdate;
    @FXML private Pane paneDelete;
    @FXML private Pane paneOverview;

    //Text fields
    @FXML private TextField txtSearch;

    @FXML private TextField txtUpdateFirstName;
    @FXML private TextField txtUpdateLastName;
    @FXML private TextField txtUpdateAddress;
    @FXML private TextField txtUpdateCity;
    @FXML private TextField txtUpdateProv;
    @FXML private TextField txtUpdatePostal;
    @FXML private TextField txtUpdateCountry;
    @FXML private TextField txtUpdateHomePhone;
    @FXML private TextField txtUpdateBusPhone;
    @FXML private TextField txtUpdateEmail;

    //Table
    @FXML private TableView<Customer> tableCustomers;
    @FXML private TableColumn<Customer, Integer> colID;
    @FXML private TableColumn<Customer, String> colFirstName;
    @FXML private TableColumn<Customer, String> colLastName;
    @FXML private TableColumn<Customer, String> colAddress;
    @FXML private TableColumn<Customer, String> colCity;
    @FXML private TableColumn<Customer, String> colProv;
    @FXML private TableColumn<Customer, String> colPostal;
    @FXML private TableColumn<Customer, String> colCountry;
    @FXML private TableColumn<Customer, String> colHomePhone;
    @FXML private TableColumn<Customer, String> colBusPhone;
    @FXML private TableColumn<Customer, String> colEmail;
    @FXML private TableColumn<Customer, Integer> colAgentId;

    //Combo Boxes
    @FXML private ComboBox<Customer> cbUpdateCustomer;
    @FXML private ComboBox<Agent> cbAgentId;

    @FXML private ComboBox<Customer> cbDeleteCustomer;

    //Labels
    @FXML private Label lblUserName;
    @FXML private Label lblClock;

    @FXML private Label lblDeleteCustomerId;
    @FXML private Label lblDeleteFirstName;
    @FXML private Label lblDeleteLastName;
    @FXML private Label lblDeleteAddress;
    @FXML private Label lblDeleteCity;
    @FXML private Label lblDeleteProv;
    @FXML private Label lblDeletePostal;
    @FXML private Label lblDeleteCountry;
    @FXML private Label lblDeleteHomePhone;
    @FXML private Label lblDeleteBusPhone;
    @FXML private Label lblDeleteEmail;
    @FXML private Label lblDeleteAgent;



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
        if(event.getSource() == btnUpdate){
            loadUpdate();
        }
        if(event.getSource() == btnDelete){
            loadDelete();
        }
        if(event.getSource() == btnUpdateGoBack || event.getSource() == btnDeleteGoBack){
            loadOverview();
        }

        /*** Pane operations ***/
        if(event.getSource() == btnUpdateCustomer){
            if(Validation.isProvided(txtUpdateFirstName, "first name") && Validation.isProvided(txtUpdateLastName, "last name")
            && Validation.isProvided(txtUpdateAddress, "address") && Validation.isProvided(txtUpdateCity, "city") &&
            Validation.isProvided(txtUpdateProv, "province") && Validation.isProvided(txtUpdatePostal, "postal") &&
            Validation.isProvided(txtUpdateCountry, "country") && Validation.isProvided(txtUpdateBusPhone, "business phone") &&
            Validation.isProvided(txtUpdateEmail, "email") && Validation.hasSelection(cbAgentId, "agent id")){

                Customer tempCustomer = cbUpdateCustomer.getSelectionModel().getSelectedItem();//stores old agent
                Agent tempAgent = cbAgentId.getSelectionModel().getSelectedItem();

                Customer customer = new Customer(tempCustomer.getId(), txtUpdateFirstName.getText(), txtUpdateLastName.getText(),
                        txtUpdateAddress.getText(), txtUpdateCity.getText(), txtUpdateProv.getText(), txtUpdatePostal.getText(),
                        txtUpdateCountry.getText(), txtUpdateHomePhone.getText(), txtUpdateBusPhone.getText(), txtUpdateEmail.getText(),
                        tempAgent.getID());

                //pushes changes to database
                CustomerDB.updateCustomer(customer);

                loadOverview();
            }
        }
        if(event.getSource() == btnDeleteCustomer){
            Customer customer = cbDeleteCustomer.getSelectionModel().getSelectedItem();

            //pushes to the database
            CustomerDB.deleteCustomer(customer);

            loadOverview();
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

        loadOverview();

        /****           Listeners            ****/

        //Changes the table based off text in search bar
        txtSearch.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                                String oldValue, String newValue) {
                ObservableList<Customer> agents = FXCollections.observableArrayList(CustomerDB.searchCustomers(txtSearch.getText()));
                tableCustomers.setItems(agents);
            }
        });
    }

    /***        Load Panes       ***/
    public void loadUpdate() {
        //layout
        paneUpdate.toFront();

        paneUpdate.setVisible(true);
        paneDelete.setVisible(false);
        paneOverview.setVisible(false);

        //combo box setup
        cbUpdateCustomer.getSelectionModel().clearSelection();
        cbUpdateCustomer.getItems().removeAll();
        ObservableList<Customer> customers = FXCollections.observableArrayList(CustomerDB.getCustomers());
        cbUpdateCustomer.setItems(customers);

        cbAgentId.getSelectionModel().clearSelection();
        cbAgentId.getItems().removeAll();
        ObservableList<Agent> agents = FXCollections.observableArrayList(AgentDB.getAgents());
        cbAgentId.setItems(agents);
    }
    public void loadDelete(){
        //layout
        paneDelete.toFront();

        paneUpdate.setVisible(false);
        paneDelete.setVisible(true);
        paneOverview.setVisible(false);

        //combo box setup
        cbDeleteCustomer.getSelectionModel().clearSelection();
        cbDeleteCustomer.getItems().removeAll();
        ObservableList<Customer> customers = FXCollections.observableArrayList(CustomerDB.getCustomers());
        cbDeleteCustomer.setItems(customers);
    }
    public void loadOverview() {
        //layout
        paneOverview.toFront();

        paneUpdate.setVisible(false);
        paneDelete.setVisible(false);
        paneOverview.setVisible(true);

        //builds the table
        colID.setCellValueFactory(cellData -> cellData.getValue().getIdProperty().asObject());
        colFirstName.setCellValueFactory(cellData -> cellData.getValue().getFirstNameProperty());
        colLastName.setCellValueFactory(cellData -> cellData.getValue().getLastNameProperty());
        colAddress.setCellValueFactory(cellData -> cellData.getValue().getAddressProperty());
        colCity.setCellValueFactory(cellData -> cellData.getValue().getCityProperty());
        colProv.setCellValueFactory(cellData -> cellData.getValue().getProvProperty());
        colPostal.setCellValueFactory(cellData -> cellData.getValue().getPostalProperty());
        colCountry.setCellValueFactory(cellData -> cellData.getValue().getCountryProperty());
        colHomePhone.setCellValueFactory(cellData -> cellData.getValue().getHomePhoneProperty());
        colBusPhone.setCellValueFactory(cellData -> cellData.getValue().getBusPhoneProperty());
        colEmail.setCellValueFactory(cellData -> cellData.getValue().getEmailProperty());
        colAgentId.setCellValueFactory(cellData -> cellData.getValue().getAgentProperty().asObject());

        ObservableList<Customer> customers = FXCollections.observableArrayList(CustomerDB.getCustomers());
        tableCustomers.setItems(customers);
    }

    /*** Combo Box Events ***/
    @FXML void cbUpdateSelection(ActionEvent event){
        Customer tempCustomer = cbUpdateCustomer.getSelectionModel().getSelectedItem();

        txtUpdateFirstName.setText(tempCustomer.getFirstName());
        txtUpdateLastName.setText(tempCustomer.getFirstName());
        txtUpdateAddress.setText(tempCustomer.getAddress());
        txtUpdateCity.setText(tempCustomer.getCity());
        txtUpdateProv.setText(tempCustomer.getProv());
        txtUpdatePostal.setText(tempCustomer.getPostal());
        txtUpdateCountry.setText(tempCustomer.getCountry());
        txtUpdateHomePhone.setText(tempCustomer.getHomePhone());
        txtUpdateBusPhone.setText(tempCustomer.getBusPhone());
        txtUpdateEmail.setText(tempCustomer.getEmail());
        cbAgentId.setValue(AgentDB.grabAgent(tempCustomer.getAgent()));//grabs the agents id to display name.
    }

    @FXML void cbDeleteSelection(ActionEvent event){
        Customer tempCustomer = cbDeleteCustomer.getSelectionModel().getSelectedItem();

        lblDeleteCustomerId.setText(Integer.toString(tempCustomer.getId()));
        lblDeleteFirstName.setText(tempCustomer.getFirstName());
        lblDeleteLastName.setText(tempCustomer.getLastName());
        lblDeleteAddress.setText(tempCustomer.getAddress());
        lblDeleteCity.setText(tempCustomer.getCity());
        lblDeleteProv.setText(tempCustomer.getProv());
        lblDeletePostal.setText(tempCustomer.getPostal());
        lblDeleteCountry.setText(tempCustomer.getCountry());
        lblDeleteHomePhone.setText(tempCustomer.getHomePhone());
        lblDeleteBusPhone.setText(tempCustomer.getBusPhone());
        lblDeleteEmail.setText(tempCustomer.getEmail());
        Agent tempAgent = AgentDB.grabAgent(tempCustomer.getAgent());
        lblDeleteAgent.setText(tempAgent.toString());
    }

}
