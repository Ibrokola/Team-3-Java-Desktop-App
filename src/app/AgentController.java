package app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class AgentController {

    @FXML
    private Button btnBack;

    //Returns the user to the main menu window
    @FXML
    void btnBackAction(ActionEvent event) throws IOException {
        Parent mainParent = FXMLLoader.load(getClass().getResource("../views/main.fxml"));
        Scene mainScene = new Scene(mainParent);

        //gets the stage  -- gets the window
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(mainScene);
    }

}
