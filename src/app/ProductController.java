package app;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import BLL.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ProductController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Product> tvProducts;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnHome;

    @FXML
    void btn(MouseEvent event) {

    }

    @FXML
    void btnAddAction(ActionEvent event) {

    }

    @FXML
    void btnClearAction(ActionEvent event) {

    }

    @FXML
    void btnHomeAction(ActionEvent event){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../views/main.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);

        //gets the stage  -- gets the window
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }

    @FXML
    void initialize() {

    }
}
