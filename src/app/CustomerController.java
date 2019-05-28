package app;

import BLL.Customer;
import BLL.CustomerDB;
import BLL.Validation;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class CustomerController {
    /*
     * Purpose: Controller for the Customer.fxml file, that handles operations within the window.
     * Author: Brent Ward
     * Module: PROJ-207-OSD
     * Date May 15, 2019
     * */

    //buttons
    @FXML private Button btnBack;
    @FXML private TextField txtSearch;
    @FXML private Button btnDelete;
    @FXML private Button btnEdit;
    @FXML private Button btnSave;

    //text fields
    @FXML private TextField txtCustId;
    @FXML private TextField txtFirstName;
    @FXML private TextField txtLastName;
    @FXML private TextField txtAddress;
    @FXML private TextField txtCity;
    @FXML private TextField txtProv;
    @FXML private TextField txtPostal;
    @FXML private TextField txtCountry;
    @FXML private TextField txtHomePhone;
    @FXML private TextField txtBusPhone;
    @FXML private TextField txtEmail;
    @FXML private TextField txtAgentId;

    //table components
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


    /****       Button Actions        ****/

    //Returns the user to the main menu window
    @FXML void btnBackAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../views/dashboard.fxml"));
        Scene scene = new Scene(root);

        //gets the stage  -- gets the window
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }
    //deletes a customer with the selected text fields
    @FXML void btnDeleteAction(ActionEvent event) {
        if(Validation.isProvided(txtCustId, "Customer Id") && Validation.isInteger(Integer.parseInt(txtCustId.getText()), "Customer Id")
            && Validation.isProvided(txtFirstName, "First name") && Validation.isProvided(txtLastName, "Last name") &&
            Validation.isProvided(txtAddress, "Address") && Validation.isProvided(txtCity, "City") &&
            Validation.isProvided(txtProv,"Province") && Validation.isProvided(txtPostal, "Postal") &&
            Validation.isProvided(txtCountry, "Country") && Validation.isProvided(txtHomePhone, "Home phone") &&
            Validation.isProvided(txtBusPhone, "Business phone") && Validation.isProvided(txtEmail, "Email") &&
            Validation.isProvided(txtAgentId, "Agent Id") && Validation.isInteger(Integer.parseInt(txtAgentId.getText()), "Agent Id")){

            //creates a customer with the text fields
            Customer customer = new Customer(Integer.parseInt(txtCustId.getText()), txtFirstName.getText(), txtLastName.getText(),
                    txtAddress.getText(), txtCity.getText(), txtProv.getText(),txtPostal.getText(),txtCountry.getText(),
                    txtHomePhone.getText(), txtBusPhone.getText(),txtEmail.getText(), Integer.parseInt(txtAgentId.getText()));

            //deletes the customer from the database
            CustomerDB.deleteCustomer(customer);
        }
    }
    //allows an agent to edit a customers data based off the selected text fields
    @FXML void btnEditAction(ActionEvent event) {
        txtFirstName.setEditable(true);
        txtLastName.setEditable(true);
        txtAddress.setEditable(true);
        txtCity.setEditable(true);
        txtProv.setEditable(true);
        txtPostal.setEditable(true);
        txtCountry.setEditable(true);
        txtHomePhone.setEditable(true);
        txtBusPhone.setEditable(true);
        txtEmail.setEditable(true);
        txtAgentId.setEditable(true);

        btnSave.setDisable(false);
    }
    //updates a customer based off the selected text fields
    @FXML void btnSaveAction(ActionEvent event) {
        if(Validation.isProvided(txtCustId, "Customer Id") && Validation.isInteger(Integer.parseInt(txtCustId.getText()), "Customer Id")
                && Validation.isProvided(txtFirstName, "First name") && Validation.isProvided(txtLastName, "Last name") &&
                Validation.isProvided(txtAddress, "Address") && Validation.isProvided(txtCity, "City") &&
                Validation.isProvided(txtProv,"Province") && Validation.isProvided(txtPostal, "Postal") &&
                Validation.isProvided(txtCountry, "Country") && Validation.isProvided(txtHomePhone, "Home phone") &&
                Validation.isProvided(txtBusPhone, "Business phone") && Validation.isProvided(txtEmail, "Email") &&
                Validation.isProvided(txtAgentId, "Agent Id") && Validation.isInteger(Integer.parseInt(txtAgentId.getText()), "Agent Id")){

            //creates a customer with the text fields
            Customer customer = new Customer(Integer.parseInt(txtCustId.getText()), txtFirstName.getText(), txtLastName.getText(),
                    txtAddress.getText(), txtCity.getText(), txtProv.getText(),txtPostal.getText(),txtCountry.getText(),
                    txtHomePhone.getText(), txtBusPhone.getText(),txtEmail.getText(), Integer.parseInt(txtAgentId.getText()));

            //deletes the customer from the database
            CustomerDB.updateCustomer(customer);
        }
    }


    //runs on start up
    @FXML void initialize() {
        //locks UI on load
        btnEdit.setDisable(true);
        btnDelete.setDisable(true);
        btnSave.setDisable(true);

        //sets up table columns
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

        //Adds the data to the table
        ObservableList<Customer> customers = FXCollections.observableArrayList(CustomerDB.getCustomers());
        tableCustomers.setItems(customers);


        /****           Listeners            ****/

        //Changes the table based off text in search bar
        txtSearch.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                                String oldValue, String newValue) {

                ObservableList<Customer> customers = FXCollections.observableArrayList(CustomerDB.searchCustomers(txtSearch.getText()));
                tableCustomers.setItems(customers);
            }
        });

        //fills text boxes with table row clicked
        tableCustomers.setOnMouseClicked((MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                int index = tableCustomers.getSelectionModel().getSelectedIndex();
                Customer customer = tableCustomers.getItems().get(index);

                //fills text fields with selected Customers data
                txtCustId.setText(Integer.toString(customer.getId()));
                txtFirstName.setText(customer.getFirstName());
                txtLastName.setText(customer.getLastName());
                txtAddress.setText(customer.getAddress());
                txtCity.setText(customer.getCity());
                txtProv.setText(customer.getProv());
                txtPostal.setText(customer.getPostal());
                txtCountry.setText(customer.getCountry());
                txtHomePhone.setText(customer.getHomePhone());
                txtBusPhone.setText(customer.getBusPhone());
                txtEmail.setText(customer.getEmail());
                txtAgentId.setText(Integer.toString(customer.getAgent()));

                //enables choice buttons, sets textboxes read only till choice made.
                btnEdit.setDisable(false);
                btnDelete.setDisable(false);
                txtFirstName.setEditable(false);
                txtLastName.setEditable(false);
                txtAddress.setEditable(false);
                txtCity.setEditable(false);
                txtProv.setEditable(false);
                txtPostal.setEditable(false);
                txtCountry.setEditable(false);
                txtHomePhone.setEditable(false);
                txtBusPhone.setEditable(false);
                txtEmail.setEditable(false);
                txtAgentId.setEditable(false);
            }

        });


    }

}
