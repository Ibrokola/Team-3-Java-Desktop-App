package app;

import BLL.Agent;
import BLL.AgentDB;
import BLL.Validation;
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


    //data holder
    @FXML private TableView<Agent> tableAgents;

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
        txtFirstName.setEditable(true);
        txtMiddleInitial.setEditable(true);
        txtLastName.setEditable(true);
        txtPhone.setEditable(true);
        txtEmail.setEditable(true);
        txtPosition.setEditable(true);
        txtAgency.setEditable(true);
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

    //updates the table based of search of "is like" for name
    @FXML void txtSearchAction(ActionEvent event) {

    }


    //runs on startup
    @FXML void initialize() {
        ObservableList<Agent> agents = FXCollections.observableArrayList(AgentDB.getAgents());
        tableAgents.setItems(agents);

        //locking UI on load
        txtAgentId.setDisable(true);
        btnEdit.setDisable(true);
        btnSave.setDisable(true);
        btnDelete.setDisable(true);
    }

}
