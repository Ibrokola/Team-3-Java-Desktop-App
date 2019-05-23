package app;

import BLL.Agent;
import BLL.AgentDB;
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
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import jdk.jfr.ValueDescriptor;

import java.io.IOException;

public class AgentController {

    //buttons
    @FXML private Button btnBack;
    @FXML private Button btnAdd;
    @FXML private Button btnDelete;
    @FXML private Button btnEdit;
    @FXML private Button btnSave;

    //text fields
    @FXML private TextField txtAgentId;
    @FXML private TextField txtFirstName;
    @FXML private TextField txtMiddleInitial;
    @FXML private TextField txtLastName;
    @FXML private TextField txtPhone;
    @FXML private TextField txtEmail;
    @FXML private TextField txtPosition;
    @FXML private TextField txtAgency;
    @FXML private TextField txtSearch;


    //table
    @FXML private TableView<Agent> tableAgents;
    @FXML private TableColumn<Agent, Integer> colID;
    @FXML private TableColumn<Agent, String> colFirstName;
    @FXML private TableColumn<Agent, String> colMiddleInitial;
    @FXML private TableColumn<Agent, String> colLastName;
    @FXML private TableColumn<Agent, String> colPhone;
    @FXML private TableColumn<Agent, String> colEmail;
    @FXML private TableColumn<Agent, String> colPosition;
    @FXML private TableColumn<Agent, Integer> colAgency;


    /****       Button Actions        ****/

    //Returns the user to the main menu window
    @FXML void btnBackAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../views/main.fxml"));
        Scene scene = new Scene(root);

        //gets the stage  -- gets the window
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }
    //Adds an agent with the current text fields
    @FXML void btnAddAction(ActionEvent event) {
        if(Validation.isProvided(txtFirstName, "First name") && Validation.isProvided(txtLastName, "Last name") &&
                Validation.isProvided(txtPhone, "Phone") && Validation.isProvided(txtEmail, "Email") &&
                Validation.isProvided(txtPosition, "Position") &&
                Validation.isInteger(Integer.parseInt(txtAgency.getText()), "Agency id")){

            //Creates the agent object with the text fields
            Agent agent = new Agent(txtFirstName.getText(), txtFirstName.getText(), txtLastName.getText(), txtPhone.getText(),
                    txtEmail.getText(), txtPosition.getText(), Integer.parseInt(txtAgency.getText()));

            //adds the agent to the database
            AgentDB.addAgent(agent);
        }

    }
    //deletes an agent with the current text fields
    @FXML void btnDeleteAction(ActionEvent event) {
        if(Validation.isProvided(txtAgentId, "Agent id") && Validation.isProvided(txtFirstName, "First name")
                && Validation.isProvided(txtLastName, "Last name") && Validation.isProvided(txtPhone, "Phone") &&
                Validation.isProvided(txtEmail, "Email") && Validation.isProvided(txtPosition, "Position") &&
                Validation.isProvided(txtAgency, "Agency id")){

            //Creates the agent object with the text fields
            Agent agent = new Agent(Integer.parseInt(txtAgentId.getText()),txtFirstName.getText(), txtFirstName.getText(),
                    txtLastName.getText(), txtPhone.getText(), txtEmail.getText(), txtPosition.getText(),
                    Integer.parseInt(txtAgency.getText()));

            //deletes the agent from the database
            AgentDB.deleteAgent(agent);
        }
    }
    //Allows the user to edit the text fields once an agent is selected
    @FXML void btnEditAction(ActionEvent event) {
        //allows textboxes to be changed
        txtFirstName.setEditable(true);
        txtMiddleInitial.setEditable(true);
        txtLastName.setEditable(true);
        txtPhone.setEditable(true);
        txtEmail.setEditable(true);
        txtPosition.setEditable(true);
        txtAgency.setEditable(true);

        //enables save button
        btnSave.setDisable(false);
    }
    //updates an agent with the current text fields
    @FXML void btnSaveAction(ActionEvent event) {
        if(Validation.isProvided(txtAgentId, "Agent id") && Validation.isProvided(txtFirstName, "First name")
                && Validation.isProvided(txtLastName, "Last name") && Validation.isProvided(txtPhone, "Phone") &&
                Validation.isProvided(txtEmail, "Email") && Validation.isProvided(txtPosition, "Position") &&
                Validation.isProvided(txtAgency, "Agency id")) {

            //Creates the agent object with the text fields
            Agent agent = new Agent(Integer.parseInt(txtAgentId.getText()),txtFirstName.getText(), txtFirstName.getText(),
                    txtLastName.getText(), txtPhone.getText(), txtEmail.getText(), txtPosition.getText(),
                    Integer.parseInt(txtAgency.getText()));

            //updates the agent in the database
            AgentDB.updateAgent(agent);
        }
    }

    //runs on startup
    @FXML void initialize() {
         //locking UI on load
        txtAgentId.setDisable(true);
        btnEdit.setDisable(true);
        btnSave.setDisable(true);
        btnDelete.setDisable(true);

        //sets up table columns
        colID.setCellValueFactory(cellData -> cellData.getValue().getIDProperty().asObject());
        colFirstName.setCellValueFactory(cellData -> cellData.getValue().getFirstNameProperty());
        colMiddleInitial.setCellValueFactory(cellData -> cellData.getValue().getMiddleInitialProperty());
        colLastName.setCellValueFactory(cellData -> cellData.getValue().getLastNameProperty());
        colPhone.setCellValueFactory(cellData -> cellData.getValue().getPhoneProperty());
        colEmail.setCellValueFactory(cellData -> cellData.getValue().getEmailProperty());
        colPosition.setCellValueFactory(cellData -> cellData.getValue().getPositionProperty());
        colAgency.setCellValueFactory(cellData -> cellData.getValue().getAgencyProperty().asObject());
        //Adds the data to the table
        ObservableList<Agent> agents = FXCollections.observableArrayList(AgentDB.getAgents());
        tableAgents.setItems(agents);

        //Changes the table based off text in search bar
        txtSearch.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                                String oldValue, String newValue) {

                ObservableList<Agent> agents = FXCollections.observableArrayList(AgentDB.searchAgents(txtSearch.getText()));
                tableAgents.setItems(agents);
            }
        });

        //fills text boxes with table row clicked
        tableAgents.setOnMouseClicked((MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                int index = tableAgents.getSelectionModel().getSelectedIndex();
                Agent agent = tableAgents.getItems().get(index);

                txtAgentId.setText(Integer.toString(agent.getID()));
                txtFirstName.setText(agent.getFirstName());
                txtMiddleInitial.setText(agent.getMiddleInitial());
                txtLastName.setText(agent.getLastName());
                txtPhone.setText(agent.getPhone());
                txtEmail.setText(agent.getEmail());
                txtPosition.setText(agent.getPosition());
                txtAgency.setText(Integer.toString(agent.getAgency()));

                //enables choice buttons, sets textboxes read only till choice made.
                btnEdit.setDisable(false);
                btnDelete.setDisable(false);
                txtFirstName.setEditable(false);
                txtMiddleInitial.setEditable(false);
                txtLastName.setEditable(false);
                txtPhone.setEditable(false);
                txtEmail.setEditable(false);
                txtPosition.setEditable(false);
                txtAgency.setEditable(false);
            }

        });
    }

}
