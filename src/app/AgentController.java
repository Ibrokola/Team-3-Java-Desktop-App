package app;

import BLL.Agent;
import BLL.AgentDB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

public class AgentController {

    @FXML
    private Button btnBack;

    @FXML private TableView<Agent> tableAgents;

    //Returns the user to the main menu window
    @FXML void btnBackAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../views/main.fxml"));
        Scene scene = new Scene(root);

        //gets the stage  -- gets the window
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }
    //runs on startup
    @FXML void initialize() {
        ObservableList<Agent> agents = FXCollections.observableArrayList(AgentDB.getAgents());
        tableAgents.setItems(agents);
    }

    //creates the table columns for tableAgents
    private void buildTable(){

    }

}
