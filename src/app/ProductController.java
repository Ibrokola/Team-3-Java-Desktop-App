package app;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import BLL.*;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;


public class ProductController {

    // Resource attributes
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;

    @FXML
    private AnchorPane mainWindow;

    // Sidebar controls ----START----!!!
    @FXML
    private ImageView imgProfilePicture;
    @FXML
    private Label lblUserName;
    @FXML
    private Button btnDashboard;
    @FXML
    private Button btnAgents;
    @FXML
    private Button btnCustomers;
    @FXML
    private Button btnPackages;
    @FXML
    private Button btnProducts;
    @FXML
    private Button btnSuppliers;
    @FXML
    private Button btnSettings;
    @FXML
    private Button btnSignout;
    @FXML
    private Label lblClock;
    // Sidebar controls ----END----!!!

    // Products Tab ----START----!!!
    //-----------------------------//
    // Products List Pane /!START!/
    @FXML
    private Pane paneProdList;
    // Buttons
    @FXML
    private Button btnAddProducts;
    @FXML
    private Button btnUpdateProducts;
    @FXML
    private Button btnDeleteProducts;

    // Table attributes
    @FXML
    private TableView<Product> tvProducts;
    @FXML
    private TableColumn<Product, Integer> clProdId;
    @FXML
    private TableColumn<Product, String> clProdName;

    // TextField attributes
    @FXML
    private TextField txtProdSearch;
    // Products List Pane /!END!/

    // Products Add Pane /!START!/
    @FXML
    private Pane paneProdAdd;

    // Buttons
    @FXML
    private Button btnAddProduct;
    @FXML
    private Button btnAddProductGoBack;

    // TextField attributes
    @FXML
    private TextField txtProdName;
    // Products Add Pane /!END!/

    // Products Update Pane /!START!/
    @FXML
    private Pane paneProdUpdate;

    // ComboBox attribute
    @FXML
    private ComboBox<?> cbProdUpdate;

    // TextField attribute
    @FXML
    private TextField txtProdUpdate;

    // Buttons
    @FXML
    private Button btnProdUpdate;
    @FXML
    private Button btnProdUpdateGoBack;
    // Products Update Pane /!END!/

    // Products Delete Pane /!START!/
    @FXML
    private Pane paneProdDelete;

    // ComboBox attribute
    @FXML
    private ComboBox<?> cbProdDelete;

    // TextField attribute
    @FXML
    private TextField txtProdDelete;

    // Buttons
    @FXML
    private Button btnProdDelete;
    @FXML
    private Button btnProdDeleteGoBack;
    // Products Delete Pane /!END!/
    //------------------------------//
    // Products Tab ----END----!!!

    // Products Suppliers Tab ----START----!!!
    //------------------------------//
    // Products Suppliers List Pane /!START!/

    @FXML
    private Pane paneSupList;

    // TextField attribute
    @FXML
    private TextField txtSupSearch;

    // Buttons
    @FXML
    private Button btnAddProductSuppliers;
    @FXML
    private Button btnUpdateProductSuppliers;
    @FXML
    private Button btnDeleteProductSuppliers;

    // Table attributes
    @FXML
    private TableView<ProductSupplier> tvProdSup;
    @FXML
    private TableColumn<ProductSupplier, Integer> clProdSupId;
    @FXML
    private TableColumn<ProductSupplier, String> clProdSupProd;
    @FXML
    private TableColumn<ProductSupplier, String> clProdSupSup;
    // Products Suppliers List Pane /!END!/

    // Products Suppliers Add Pane /!START!/
    @FXML
    private Pane paneSupAdd;

    // TextField attributes
    @FXML
    private TextField txtProdNameAdd;
    @FXML
    private TextField txtProdSupAdd;

    // Buttons
    @FXML
    private Button btnAddProdSup;
    @FXML
    private Button btnAddProdSupGoBack;

    // ComboBox attributes
    @FXML
    private ComboBox<?> cbProdAdd;
    @FXML
    private ComboBox<?> cbProdSupAdd;
    // Products Suppliers Add Pane /!END!/

    // Products Suppliers Update Pane /!START!/

    @FXML
    private Pane paneSupUpdate;

    // TextField attributes
    @FXML
    private TextField txtProdSupUpdate;
    @FXML
    private TextField txtProdNameUpdate;

    // Buttons
    @FXML
    private Button btnUpdateProdSupGoBack;
    @FXML
    private Button btnUpdateProdSup;

    // ComboBox attributes
    @FXML
    private ComboBox<?> cbProdSUpdate;
    @FXML
    private ComboBox<?> cbProdSupUpdate;

    //ComboBox Actions

    // Products Suppliers Update Pane /!END!/


    // Products Suppliers Delete Pane /!START!/
    @FXML
    private Pane paneSupDelete;
    // Products Suppliers Delete Pane /!END!/

    //------------------------------//
    // Products Suppliers Tab ----END----!!!


    // Package Products Suppliers Tab ----START----!!!
    //----------------------------------------------//
    // Package Products Suppliers List Pane /!START!/
    @FXML
    private Pane panePckSupList;

    // Table attributes
    @FXML
    private TableView<PackageProductSupplier> tvPckProd;
    @FXML
    private TableColumn<PackageProductSupplier, String> clProdPck;
    @FXML
    private TableColumn<PackageProductSupplier, String> clProdPckSup;

    // TextField attribute
    @FXML
    private TextField txtPckSupSearch;

    // Buttons
    @FXML
    private Button btnAddPackageSuppliers;
    @FXML
    private Button btnUpdatePackageSuppliers;
    // Package Products Suppliers List Pane /!END!/

    // Package Products Suppliers Add Pane /!START!/

    @FXML
    private Pane panePckSupAdd;

    // TextField attributes
    @FXML
    private TextField txtPckProdNameAdd;
    @FXML
    private TextField txtPckProdSupAdd;

    // ComboBox attributes
    @FXML
    private ComboBox<?> cbPckProdAdd;
    @FXML
    private ComboBox<?> cbPckProdSupAdd;

    // Buttons
    @FXML
    private Button btnAddPckProdSup;
    @FXML
    private Button btnAddPckProdSupGoBack;

    // Package Products Suppliers Add Pane /!END!/


    // Package Products Suppliers Update Pane /!START!/

    @FXML
    private Pane panePckSupUpdate;

    // TextField attributes
    @FXML
    private TextField txtPckProdUpdate;
    @FXML
    private TextField txtPckProdSupUpdate;

    // ComboBox attributes
    @FXML
    private ComboBox<?> cbPckProdUpdate;
    @FXML
    private ComboBox<?> cbPckProdSupUpdate;

    // Buttons
    @FXML
    private Button btnPckProdSupUpdate;
    @FXML
    private Button btnPckProdSupUpdateGoBack;
    // Package Products Suppliers Update Pane /!END!/

    // Package Products Suppliers Delete Pane /!END!/
    @FXML
    private Pane panePckSupDelete;
    // Package Products Suppliers Delete Pane /!END!/
    //---------------------------------------------//
    // Package Products Suppliers Tab ----END----!!!


    // TextField attributes
    // @FXML
    // private TextField txtSearchSup;
    // @FXML
    // private TextField txtSearchPck;

    // Button attributes
    // @FXML
    // private Button btnAdd;
    // @FXML
    // private Button btnClear;

    // @FXML
    // private Button btnHome;

    // @FXML
    // private Button btnUpdate;
    // @FXML
    // private Button btnRemove;
    // @FXML
    // private Button btnSupAdd;
    //
    // @FXML
    // private Button btnSupUpdate;
    // @FXML
    // private Button btnSupClear;
    // @FXML
    // private Button btnPckClear;
    // @FXML
    // private Button btnPckAdd;
    // @FXML
    // private Button btnPckUpdate;

    private int prodId;


    // From SceneBuilder ------ START ------!!!!


    @FXML
    void cbPckProdAddSelect(ActionEvent event) {

    }

    @FXML
    void cbPckProdSupAddSelect(ActionEvent event) {

    }

    @FXML
    void cbPckProdSupUpdateSelect(ActionEvent event) {

    }

    @FXML
    void cbPckProdUpdateSelect(ActionEvent event) {

    }

    @FXML
    void cbProdAddSelect(ActionEvent event) {

    }

    @FXML
    void cbProdDeleteSelect(ActionEvent event) {

    }

    @FXML
    void cbProdSupAddSelect(ActionEvent event) {

    }

    @FXML
    void cbProdSupUpdateSelect(ActionEvent event) {

    }

    @FXML
    void cbProdSUpdateSelect(ActionEvent event) {

    }

    @FXML
    void cbProdUpdateSelect(ActionEvent event) {

    }

    // From SceneBuilder ------- END -------!!!!



    // Button methods
    // Add product button callback
    @FXML
    void btnAddAction(ActionEvent event) {
        if(Validation.isProvided(txtProdName, "Product name")){

            Product product = new Product(txtProdName.getText());

            ProductDB.addProduct(product);

            txtProdName.setText("");

            // Refresh products list
            loadProducts();
        }
    }

    @FXML
    void btnClearAction(ActionEvent event) {
        if(!txtProdName.equals("") | !txtProdSearch.equals(""))
        {
            txtProdName.setText("");
            txtProdSearch.setText("");
            txtProdName.setFocusTraversable(false);
            btnAdd.setDisable(true);
            btnUpdate.setDisable(true);
        }
    }

    @FXML
    void btnRemoveAction(ActionEvent event) {
        // Remove or deactivate the product from table view but don't delete
    }

    @FXML
    void btnUpdateAction(ActionEvent event) {
        if (Validation.isProvided(txtProdName, "First name")) {

            // Integer.parseInt(txtProdId.getText())

            Product product = new Product(prodId, txtProdName.getText());

            ProductDB.updateProduct(product);
            txtProdName.setText("");

            // Refresh products list
            loadProducts();

            btnUpdate.setDisable(true);
            btnAdd.setDisable(true);
        }
    }



    @FXML
    void btnPckAddAction(ActionEvent event) {

    }

    @FXML
    void btnPckClearAction(ActionEvent event) {

    }

    @FXML
    void btnPckUpdateAction(ActionEvent event) {

    }


    @FXML
    void btnSupAddAction(ActionEvent event) {

    }

    @FXML
    void btnSupClearAction(ActionEvent event) {

    }

    @FXML
    void btnSupUpdateAction(ActionEvent event) {

    }

    @FXML
    void handleButtonClicks(ActionEvent event) throws IOException {

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

//    @FXML
//    void btnHomeAction(ActionEvent event){
//        Parent root = null;
//        try {
//            root = FXMLLoader.load(getClass().getResource("../views/dashboard.fxml"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Scene scene = new Scene(root);
//
//        //gets the stage  -- gets the window
//        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
//        stage.setScene(scene);
//    }

    //Widget Code
    private void startClock() {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss a   -   dd/MM/YYYY");
            lblClock.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    @FXML
    void initialize() {

        startClock();

        Administrator user = LoginController.userLoggedIn();
        lblUserName.setText(user.getLastName() + ", " + user.getFirstName());
        lblUserName.setWrapText(true);

        //loads users color setting
        String mode = SettingsController.getColorMode();
        mainWindow.getStylesheets().clear();
        mainWindow.getStylesheets().add("css/" + mode + ".css");
        if(SettingsController.getProfilePicture() != null){
            imgProfilePicture.setImage(SettingsController.getProfilePicture());
        }

        // Prepare table columns products
        clProdId.setCellValueFactory(cellData -> cellData.getValue().productIdProperty().asObject());
        clProdName.setCellValueFactory(cellData -> cellData.getValue().prodNameProperty());

        loadProducts();

        //Changes the table items based off text in search bar
        txtProdSearch.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                                String oldValue, String newValue) {

                ObservableList<Product> products = FXCollections.observableArrayList(
                        ProductDB.searchProducts(txtProdSearch.getText()));

                tvProducts.setItems(products);
            }
        });

        tvProducts.setOnMouseClicked((MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                int index = tvProducts.getSelectionModel().getSelectedIndex();
                Product product = tvProducts.getItems().get(index);

                prodId = product.getProductId();
                txtProdName.setText(product.getProdName());

                btnUpdate.setDisable(false);
            }

        });

        txtProdName.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
                btnAdd.setDisable(false);
            }
        });


        // Prepare table columns products suppliers
        clProdSupId.setCellValueFactory(cellData -> cellData.getValue().productSupplierIdProperty().asObject());
        clProdSupProd.setCellValueFactory(cellData -> cellData.getValue().prodNameProperty());
        clProdSupSup.setCellValueFactory(cellData -> cellData.getValue().supNameProperty());

        txtSearchSup.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                ObservableList<ProductSupplier> productsSuppliers = FXCollections.observableArrayList(
                        ProductSupplierDB.searchProductsSuppliers(txtSearchSup.getText()));

                tvProdSup.setItems(productsSuppliers);
            }
        });

        loadProductsSuppliers();

        btnAdd.setDisable(true);
        btnUpdate.setDisable(true);
        btnRemove.setDisable(true);
    }

    private void loadProducts(){

        //Adds the data to the table
        ObservableList<Product> products = FXCollections.observableArrayList(ProductDB.getProducts());
        tvProducts.setItems(products);

    }

    private void loadProductsSuppliers(){
        ObservableList<ProductSupplier> productsSuppliers = FXCollections.observableArrayList(
                ProductSupplierDB.getProductSuppliers());
        tvProdSup.setItems(productsSuppliers);
    }

}

