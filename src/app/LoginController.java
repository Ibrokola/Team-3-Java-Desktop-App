package app;

import BLL.Administrator;
import BLL.AdministratorDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    /*
     * Purpose: Controller class for the login page.
     * Author: Brent Ward
     * Module: PROJ-207-OSD
     * Date: May 20, 2019
     * */

    @FXML private TextField txtUsername;
    @FXML private PasswordField txtPassword;
    @FXML private Button btnLogin;

    static Administrator tempAdmin;

    @FXML void btnLoginAction(ActionEvent event) throws IOException {
       tempAdmin = AdministratorDB.checkAdmin(txtUsername.getText(), txtPassword.getText());
        if(tempAdmin != null){
            Parent root = FXMLLoader.load(getClass().getResource("../views/dashboard.fxml"));
            Scene scene = new Scene(root);

            //gets the stage  -- gets the window
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
            stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
            stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
        }
    }

    //grabs logged in user
    public static Administrator userLoggedIn(){ return tempAdmin; }
}