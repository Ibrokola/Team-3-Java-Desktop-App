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

public class MainController {

    //Buttons created
    @FXML private Button btnAgent;
    @FXML private Button btnCustomer;
    @FXML private Button btnPackage;
    @FXML private Button btnProduct;
    @FXML private Button btnSupplier;


    /******            SCENE SWAPS             ******/

    //Swaps to Agent scene when button is pressed
    @FXML void btnAgentAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../views/agent.fxml"));
        Scene scene = new Scene(root);

        //gets the stage  -- gets the window
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }

    //Swaps to Customer scene when button is pressed
    @FXML void btnCustomerAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../views/customer.fxml"));
        Scene scene = new Scene(root);

        //gets the stage  -- gets the window
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }

    //Swaps to Package scene when button is pressed
    @FXML void btnPackageAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../views/package.fxml"));
        Scene scene = new Scene(root);

        //gets the stage  -- gets the window
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }

    //Swaps to Product scene when button is pressed
    @FXML void btnProductAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../views/product.fxml"));
        Scene scene = new Scene(root);

        //gets the stage  -- gets the window
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }

    //Swaps to Supplier scene when button is pressed
    @FXML void btnSupplierAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../views/supplier.fxml"));
        Scene scene = new Scene(root);

        //gets the stage  -- gets the window
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }

}
