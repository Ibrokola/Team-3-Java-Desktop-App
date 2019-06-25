package app;

import BLL.*;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class DashboardController {
    /*
     * Purpose: Controller for dashboard/home page for the desktop app.
     * Author: Brent Ward - starting template found at k33ptoo.
     * Module: PROJ-207-OSD
     * Date: May 15, 2019
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

    //labels
    @FXML private Label txtActiveSales;
    @FXML private Label txtTopPackage;
    @FXML private Label txtNumCustomers;
    @FXML private Label txtNumSuppliers;
    @FXML private Label txtNumAgents;

    //Other properties
    @FXML private Label lblUserName;
    @FXML private Label lblClock;
    @FXML private AnchorPane mainWindow;
    @FXML private ImageView imgProfilePicture;

    //charts
    @FXML private PieChart pcNumOfAgents;
    @FXML private PieChart pcAgentRoles;
    @FXML private BarChart<String, Number> bcTopSellers;
    @FXML NumberAxis bcYAxis;
    @FXML CategoryAxis bcXAxis;
    @FXML private LineChart<String, Number> chartStocks;


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

        //loads users color setting and profile picture
        String mode = SettingsController.getColorMode();
        mainWindow.getStylesheets().clear();
        mainWindow.getStylesheets().add("css/" + mode + ".css");
        if(SettingsController.getProfilePicture() != null){
            imgProfilePicture.setImage(SettingsController.getProfilePicture());
        }

        /***            CHART BUILDING            ***/

        //pie chart - agency
        ObservableList<PieChart.Data> pcNumOfAgentsData = FXCollections.observableArrayList(
                                                new PieChart.Data("Agency 1", AgentDB.AgentsPerAgency(1)),
                                                new PieChart.Data("Agency 2", AgentDB.AgentsPerAgency(2)));
        pcNumOfAgents.setData(pcNumOfAgentsData);

        //pie chart - agent roles
        ObservableList<PieChart.Data> pcAgentRolesData = FXCollections.observableArrayList(
                new PieChart.Data("Junior Agent", AgentDB.numAgentRoles("Junior Agent")),
                new PieChart.Data("Intermediate Agent", AgentDB.numAgentRoles("Intermediate Agent")),
                new PieChart.Data("Senior Agent", AgentDB.numAgentRoles("Senior Agent")));
        pcAgentRoles.setData(pcAgentRolesData);

        //bar chart - sellers
        List<AgentSales> sellers = AgentDB.getTopSellers();
        bcXAxis = new CategoryAxis();
        bcYAxis = new NumberAxis();
        bcTopSellers.setTitle("Top 3 Sellers");

        XYChart.Series<String, Number> dataSet1 = new XYChart.Series<>();
        dataSet1.setName(sellers.get(0).toString());
        dataSet1.getData().add(new XYChart.Data<>(sellers.get(0).toString(), sellers.get(0).getSales()));
        XYChart.Series<String, Number> dataSet2 = new XYChart.Series<>();
        dataSet1.setName(sellers.get(1).toString());
        dataSet1.getData().add(new XYChart.Data<>(sellers.get(1).toString(), sellers.get(1).getSales()));
        XYChart.Series<String, Number> dataSet3 = new XYChart.Series<>();
        dataSet1.setName(sellers.get(2).toString());
        dataSet1.getData().add(new XYChart.Data<>(sellers.get(2).toString(), sellers.get(2).getSales()));

        bcTopSellers.getData().addAll(dataSet1, dataSet2, dataSet3);
        bcTopSellers.setLegendVisible(false);

        //line chart - stocks
        XYChart.Series series = new XYChart.Series();

        series.getData().add(new XYChart.Data("Jan", 23));
        series.getData().add(new XYChart.Data("Feb", 14));
        series.getData().add(new XYChart.Data("Mar", 15));
        series.getData().add(new XYChart.Data("Apr", 24));
        series.getData().add(new XYChart.Data("May", 34));
        series.getData().add(new XYChart.Data("Jun", 36));
        //series.getData().add(new XYChart.Data("Jul", 22));
        //series.getData().add(new XYChart.Data("Aug", 45));
        //series.getData().add(new XYChart.Data("Sep", 43));
        //series.getData().add(new XYChart.Data("Oct", 17));
        //series.getData().add(new XYChart.Data("Nov", 29));
        //series.getData().add(new XYChart.Data("Dec", 25));

        chartStocks.getData().add(series);
        chartStocks.setLegendVisible(false);

        /***            DASHBOARD TEXT FIELDS            ***/
        txtActiveSales.setText(Integer.toString(BookingDB.recentSales()));
        String topPackage = BookingDB.topPackage();
        if(topPackage == null){ txtTopPackage.setText("N/A");} else { txtTopPackage.setText(topPackage); }
        txtNumCustomers.setText(Integer.toString(CustomerDB.numOfCustomers()));
        txtNumSuppliers.setText(Integer.toString(SupplierDB.numOfSuppliers()));
        txtNumAgents.setText(Integer.toString(AgentDB.numOfAgents()));
    }
}
