package app;

import BLL.Administrator;
import BLL.AdministratorDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML private TextField txtUsername;
    @FXML private TextField txtPassword;

    @FXML private Button btnLogin;

    static Administrator tempAdmin;

    @FXML void btnLoginAction(ActionEvent event) throws IOException {
       tempAdmin = AdministratorDB.checkAdmin(txtUsername.getText(), txtPassword.getText());
        if(tempAdmin != null){
            Parent root = FXMLLoader.load(getClass().getResource("../views/main.fxml"));
            Scene scene = new Scene(root);

            //gets the stage  -- gets the window
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
        }
    }

    public static Administrator userLoggedIn(){
        return tempAdmin;
    }

}