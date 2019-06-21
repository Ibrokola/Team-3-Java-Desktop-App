package app;

import BLL.Administrator;
import BLL.AdministratorDB;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SettingsController {
    /*
     * Purpose: Controller for the settings page. Allows user to switch css/change password
     * Author: Brent Ward
     * Module: PROJ-207-OSD
     * Date: May 31, 2019
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

    @FXML private Button btnSave;
    @FXML private Button btnAvatar;

    //Other properties
    @FXML private Label lblUserName;
    @FXML private Label lblClock;
    private Stage tempStage;

    @FXML private PasswordField txtPassword;
    @FXML private PasswordField txtPasswordConfirm;

    @FXML private RadioButton rbLightMode;
    @FXML private RadioButton rbDarkMode;
    @FXML private ToggleGroup colorMode;

    private static String currentMode = "darkmode";
    private String imagePath = "";

    @FXML private AnchorPane mainWindow;
    @FXML private ImageView imgProfileDemo;
    @FXML private ImageView imgProfilePicture;
    @FXML private Label lblPreview;
    private static Image imageHolder; //used to pass the profile pic to other controllers



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
            //Changes the scene, fetches the stage
            Parent root = FXMLLoader.load(getClass().getResource("../views/settings.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow(); //grabs the stage
            stage.setScene(scene);
        }
        //log out
        if(event.getSource() == btnSignout){
            //Changes the scene, fetches the stage
            Parent root = FXMLLoader.load(getClass().getResource("../views/login.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow(); //grabs the stage
            stage.setScene(scene);
            tempStage = stage;
        }

        if(event.getSource() == btnSave){
            Administrator sessionUser  = LoginController.userLoggedIn();

            //checks for color mode changes
            if(rbLightMode.isSelected() || rbDarkMode.isSelected()){
                AdministratorDB.changeColorMode(sessionUser, currentMode);
                sessionUser.setColorMode(currentMode);
            }
            //checks for password changes
            if(!txtPassword.getText().equals("") && !txtPasswordConfirm.getText().equals("")) {
                if (txtPassword.getText().equals(txtPasswordConfirm.getText())) { ;
                    AdministratorDB.changePassword(sessionUser, txtPassword.getText());

                    txtPassword.clear();
                    txtPasswordConfirm.clear();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Passwords don't match. Please try again.");
                    alert.show();
                }
            }
            //checks for image changes
            if(imgProfileDemo.getImage() != null){
                imgProfilePicture.setImage(imgProfileDemo.getImage());
                AdministratorDB.changePicture(sessionUser, imagePath);
                sessionUser.setProfilePicture(imagePath);
            }

            //save alert
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Changes saved!");
            alert.show();
        }
        if(event.getSource() == btnAvatar){
            FileChooser chooser = new FileChooser();
            chooser.setTitle("Choose a picture");
            chooser.setInitialDirectory(new File(System.getProperty("user.home") + System.getProperty("file.separator") + "Pictures")); //redirects to the picture directory

            //locks user choice to image files
            FileChooser.ExtensionFilter fileExtensions = new FileChooser.ExtensionFilter("Image files", "*.png", "*jpg");
            chooser.getExtensionFilters().add(fileExtensions);

            //users image
            File profilePicture = chooser.showOpenDialog(tempStage);

            if(profilePicture != null){
                imagePath = profilePicture.toURI().toString();
                Image image = new Image(profilePicture.toURI().toString());
                imageHolder = new Image(profilePicture.toURI().toString());
                imgProfileDemo.setImage(image);
                lblPreview.setVisible(true);
            }
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
        lblPreview.setVisible(false);

        //assigns radio buttons to a group - will be used to listener
        rbLightMode.setToggleGroup(colorMode);
        rbDarkMode.setToggleGroup(colorMode);

        //loads users color setting and profile picture
        String mode = SettingsController.getColorMode();
        mainWindow.getStylesheets().clear();
        mainWindow.getStylesheets().add("css/" + mode + ".css");
        if(mode.equals("darkmode")){ rbDarkMode.setSelected(true); }
        if(mode.equals("lightmode")){ rbLightMode.setSelected(true); }
        if(SettingsController.getProfilePicture() != null){
            imgProfilePicture.setImage(SettingsController.getProfilePicture());
        }

        colorMode.selectedToggleProperty().addListener((ob, o, n) -> {
            if(rbLightMode.isSelected()){
                currentMode = "lightmode";
                mainWindow.getStylesheets().clear();
                mainWindow.getStylesheets().add("css/lightmode.css");
            }
            if((rbDarkMode.isSelected())){
                currentMode = "darkmode";
                mainWindow.getStylesheets().clear();
                mainWindow.getStylesheets().add("css/darkmode.css");
            }
        });
    }

    //used to set the color mode in other scenes
    public static String getColorMode(){
        Administrator user = LoginController.userLoggedIn();
        if(user.getColorMode() != null){
            currentMode = user.getColorMode();
        }
        return currentMode;
    }
    //used to set the profile picture in other scenes
    public static Image getProfilePicture() {
        Administrator user = LoginController.userLoggedIn();
        if(user.getProfilePicture() != null){
            String path = user.getProfilePicture();
            path.trim();
            File temp = new File(path);
            if(temp.isFile()){
                imageHolder = new Image(temp.toURI().toString());
            }
        }
        return imageHolder;
    }

}
