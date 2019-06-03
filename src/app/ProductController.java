package app;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import BLL.*;
import BLL.Package;
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
    private ComboBox<Product> cbProdUpdate;

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
    private ComboBox<Product> cbProdDelete;

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
    private ComboBox<Product> cbProdAdd;
    @FXML
    private ComboBox<Supplier> cbProdSupAdd;
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
    private ComboBox<ProductSupplier> cbProSup;
    @FXML
    private ComboBox<Product> cbProdSUpdate;
    @FXML
    private ComboBox<Supplier> cbProdSupUpdate;

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
    private ComboBox<Package> cbPckProdAdd;
    @FXML
    private ComboBox<ProductSupplier> cbPckProdSupAdd;

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
    private ComboBox<Package> cbPckProdUpdate;
    @FXML
    private ComboBox<ProductSupplier> cbPckProdSupUpdate;

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

    private int prodId;


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
//            btnAdd.setDisable(true);
//            btnUpdate.setDisable(true);
        }
    }

//    @FXML
//    void btnRemoveAction(ActionEvent event) {
//        // Remove or deactivate the product from table view but don't delete
//    }

    @FXML
    void btnUpdateAction(ActionEvent event) {
        if (Validation.isProvided(txtProdName, "First name")) {

            // Integer.parseInt(txtProdId.getText())

            Product product = new Product(prodId, txtProdName.getText());

            ProductDB.updateProduct(product);
            txtProdName.setText("");

            // Refresh products list
            loadProducts();

//            btnUpdate.setDisable(true);
//            btnAdd.setDisable(true);
        }
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

        /*** Products Tab ***/
        /*** Pane switching buttons ***/
        if(event.getSource() == btnAddProducts){
            loadPaneProdAdd();
        }
        if(event.getSource() == btnUpdateProducts){
            loadPaneProdUpdate();
        }
        if(event.getSource() == btnDeleteProducts){
            loadPaneProdDelete();
        }

        if(event.getSource() == btnAddProductGoBack ||
                event.getSource() == btnProdUpdateGoBack ||
                event.getSource() == btnProdDeleteGoBack){
            loadPaneProdList();
        }


        /*** Products Suppliers Tab ***/
        /*** Pane switching buttons ***/
        if(event.getSource() == btnAddProductSuppliers){
            loadPaneProdSupAdd();
        }
        if(event.getSource() == btnUpdateProductSuppliers){
            loadPaneProdSupUpdate();
        }
        if(event.getSource() == btnUpdateProdSupGoBack ||
                event.getSource() == btnAddProdSupGoBack){
            loadPaneProdSupList();
        }

        /*** Package Products Suppliers Tab ***/
        /*** Pane switching buttons ***/
        if(event.getSource() == btnAddPackageSuppliers){
            loadPanePckProdAdd();
        }
        if(event.getSource() == btnUpdatePackageSuppliers){
            loadPanePckProdUpdate();
        }
        if(event.getSource() == btnPckProdSupUpdateGoBack ||
                event.getSource() == btnAddPckProdSupGoBack){
            loadPanePckProdList();
        }

        /*** PANE OPERATIONS ***/
        /*** Products Tab  Start ***/

        /*** Add Pane ***/
        if(event.getSource() == btnAddProduct){

            if(Validation.isProvided(txtProdName, "Product name"))
            {
                Product product = new Product(txtProdName.getText());
                ProductDB.addProduct(product);
                loadPaneProdList();
            }
        }

        /*** Update Pane ***/
        if(event.getSource() == btnProdUpdate){
            if(Validation.isProvided(txtProdUpdate, "Product name"))
            {
                Product prod = cbProdUpdate.getSelectionModel().getSelectedItem();
                Product product = new Product(prod.getProductId(), txtProdName.getText());
                ProductDB.updateProduct(product);
                loadPaneProdList();
            }
        }

        /*** Delete Pane ***/
        if(event.getSource() == btnProdDelete){
            if(Validation.isProvided(txtProdDelete, "Product name"))
            {
                Product prod = cbProdDelete.getSelectionModel().getSelectedItem();
                Product product = new Product(prod.getProductId(), txtProdDelete.getText());
                ProductDB.deleteProduct(product);
                loadPaneProdList();
            }
        }
        /*** Products Tab End ***/
        /*** PANE OPERATIONS ***/


        /*** PANE OPERATIONS ***/
        /*** Products Suppliers Tab Start ***/

        /*** Add Pane ***/
        if(event.getSource() == btnAddProdSup){

            if(
                    Validation.isProvided(txtProdNameAdd, "Product name") &&
                    Validation.isProvided(txtProdSupAdd, "Product supplier") &&
                            Validation.hasSelection(cbProdAdd, "product id") &&
                    Validation.hasSelection(cbProdSupAdd, "supplier id"))
            {
                Product prod = cbProdAdd.getSelectionModel().getSelectedItem();
                Supplier sup = cbProdSupAdd.getSelectionModel().getSelectedItem();
                ProductSupplier prodSup = new ProductSupplier(
                        prod.getProductId(),
                        sup.getSupplierId());

                ProductSupplierDB.addProductSupplier(prodSup);
                loadPaneProdSupList();
            }
        }



        /*** Update Pane ***/
        if(event.getSource() == btnUpdateProdSup){
            if(Validation.isProvided(txtProdNameUpdate, "Product name") &&
                    Validation.isProvided(txtProdSupUpdate, "Product supplier") &&
                    Validation.hasSelection(cbProdSUpdate, "product id") &&
                    Validation.hasSelection(cbProdSupUpdate, "supplier id"))

            {

                ProductSupplier proSup = cbProSup.getSelectionModel().getSelectedItem();
                Product prod = cbProdSUpdate.getSelectionModel().getSelectedItem();
                Supplier sup = cbProdSupUpdate.getSelectionModel().getSelectedItem();

                ProductSupplier prodSup = new ProductSupplier(
                        proSup.getProductSupplierId(),
                        prod.getProductId(),
                        prod.getProdName(),
                        sup.getSupplierId(),
                        sup.getSupName());

                ProductSupplierDB.updateProductSupplier(prodSup);
                loadPaneProdSupList();
            }
        }

        /*** Delete Pane ***/
        // if(event.getSource() == btnProdDelete){
        //   if(Validation.isProvided(txtProdDelete, "Product name"))
        //     {
        //       Product prod = cbProdDelete.getSelectionModel().getSelectedItem();
        //       Product product = new Product(prod.getProductId(), txtProdDelete.getText());
        //       ProductDB.deleteProduct(product);
        //       loadPaneProdList();
        //     }
        // }
        /*** Products Suppliers Tab End ***/
        /*** PANE OPERATIONS ***/


        /*** PANE OPERATIONS ***/
        /*** Package Products Suppliers Tab Start ***/

        /*** Add Pane ***/
        if(event.getSource() == btnAddPckProdSup){

            if(
                    Validation.isProvided(txtPckProdNameAdd, "package name") &&
                            Validation.isProvided(txtPckProdSupAdd, "product supplier") &&
                            Validation.hasSelection(cbPckProdAdd, "package id") &&
                            Validation.hasSelection(cbPckProdSupAdd, "supplier id")
            )
            {
                Package pck = cbPckProdAdd.getSelectionModel().getSelectedItem();
                ProductSupplier sup = cbPckProdSupAdd.getSelectionModel().getSelectedItem();
                PackageProductSupplier pckProdSup = new PackageProductSupplier(
                        pck.getPackageId(),
                        sup.getProductSupplierId());

                PackageProductSupplierDB.addPackageProductSupplier(pckProdSup);
                loadPanePckProdList();
            }
        }

        /*** Update Pane ***/
        if(event.getSource() == btnPckProdSupUpdate){
            if(Validation.isProvided(txtPckProdUpdate, "Package name") &&
                    Validation.isProvided(txtPckProdSupUpdate, "Product supplier") &&
                    Validation.hasSelection(cbPckProdUpdate, "product id") &&
                    Validation.hasSelection(cbPckProdSupUpdate, "product id"))

            {
                Package pck = cbPckProdUpdate.getSelectionModel().getSelectedItem();
                ProductSupplier prodSup = cbPckProdSupUpdate.getSelectionModel().getSelectedItem();

                PackageProductSupplier pckProdSup = new PackageProductSupplier(
                        pck.getPackageId(),
                        pck.getPkgName(),
                        prodSup.getProductSupplierId(),
                        prodSup.getSupName());

                PackageProductSupplierDB.updatePackageProductSupplier(pckProdSup);
                loadPanePckProdList();
            }
        }

        /*** Package Products Suppliers Tab End ***/
        /*** PANE OPERATIONS ***/

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


        //layout setup products tab
        paneProdAdd.setVisible(false);
        paneProdUpdate.setVisible(false);
        paneProdDelete.setVisible(false);
        paneProdList.setVisible(true);

        //layout setup products suppliers tab
        paneSupAdd.setVisible(false);
        paneSupUpdate.setVisible(false);
        paneSupDelete.setVisible(false);
        paneSupList.setVisible(true);

        //layout setup package products suppliers tab
        panePckSupAdd.setVisible(false);
        panePckSupUpdate.setVisible(false);
        panePckSupDelete.setVisible(false);
        panePckSupList.setVisible(true);


//        loadProducts();

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

//        tvProducts.setOnMouseClicked((MouseEvent event) -> {
//            if (event.getButton().equals(MouseButton.PRIMARY)) {
//                int index = tvProducts.getSelectionModel().getSelectedIndex();
//                Product product = tvProducts.getItems().get(index);
//
//                prodId = product.getProductId();
//                txtProdName.setText(product.getProdName());
//
////                btnUpdate.setDisable(false);
//            }
//
//        });


        txtSupSearch.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                ObservableList<ProductSupplier> productsSuppliers = FXCollections.observableArrayList(
                        ProductSupplierDB.searchProductsSuppliers(txtSupSearch.getText()));

                tvProdSup.setItems(productsSuppliers);
            }
        });

        txtPckSupSearch.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                ObservableList<PackageProductSupplier> pckProductsSuppliers = FXCollections.observableArrayList(
                        PackageProductSupplierDB.searchPckgProductsSuppliers(txtPckSupSearch.getText()));
                tvPckProd.setItems(pckProductsSuppliers);
            }
        });

//        loadProductsSuppliers();

//        btnAdd.setDisable(true);
//        btnUpdate.setDisable(true);
//        btnRemove.setDisable(true);
    }

    private void loadProducts(){

        //Adds the data to the table
        ObservableList<Product> products = FXCollections.observableArrayList(
                ProductDB.getProducts());
        tvProducts.setItems(products);

    }

    private void loadProductsSuppliers(){
        ObservableList<ProductSupplier> productsSuppliers = FXCollections.observableArrayList(
                ProductSupplierDB.getProductSuppliers());
        tvProdSup.setItems(productsSuppliers);
    }

    /*** Load Panes ***/

    /*** Products Tab START ***/
    private void loadPaneProdList(){

        paneProdList.toFront();

        //layout
        paneProdAdd.setVisible(false);
        paneProdUpdate.setVisible(false);
        paneProdDelete.setVisible(false);
        paneProdList.setVisible(true);

        // Prepare table columns products
        clProdId.setCellValueFactory(cellData -> cellData.getValue().productIdProperty().asObject());
        clProdName.setCellValueFactory(cellData -> cellData.getValue().prodNameProperty());

        ObservableList<Product> products = FXCollections.observableArrayList(
                ProductDB.getProducts());
        tvProducts.setItems(products);

    }

    private void loadPaneProdAdd(){
        paneProdAdd.toFront();

        //layout
        paneProdAdd.setVisible(true);
        paneProdUpdate.setVisible(false);
        paneProdDelete.setVisible(false);
        paneProdList.setVisible(false);

        txtProdName.clear();
    }

    private void loadPaneProdUpdate(){
        paneProdUpdate.toFront();

        //layout
        paneProdAdd.setVisible(false);
        paneProdUpdate.setVisible(true);
        paneProdDelete.setVisible(false);
        paneProdList.setVisible(false);

        cbProdUpdate.getItems().removeAll();
        ObservableList<Product> products = FXCollections.observableArrayList(ProductDB.getProducts());
        cbProdUpdate.setItems(products);

        txtProdUpdate.clear();
    }

    private void loadPaneProdDelete(){
        paneProdDelete.toFront();

        //layout
        paneProdAdd.setVisible(false);
        paneProdUpdate.setVisible(false);
        paneProdDelete.setVisible(true);
        paneProdList.setVisible(false);

        cbProdDelete.getSelectionModel().clearSelection();
        cbProdDelete.getItems().removeAll();
        ObservableList<Product> products = FXCollections.observableArrayList(ProductDB.getProducts());
        cbProdDelete.setItems(products);

        txtProdUpdate.clear();
    }
    /*** Products Tab END ***/


    /*** Products Suppliers Tab START ***/
    private void loadPaneProdSupList(){
        paneSupList.toFront();

        //layout
        paneSupAdd.setVisible(false);
        paneSupUpdate.setVisible(false);
        paneSupDelete.setVisible(false);
        paneSupList.setVisible(true);

        // Prepare table columns products suppliers
        clProdSupId.setCellValueFactory(cellData -> cellData.getValue().productSupplierIdProperty().asObject());
        clProdSupProd.setCellValueFactory(cellData -> cellData.getValue().prodNameProperty());
        clProdSupSup.setCellValueFactory(cellData -> cellData.getValue().supNameProperty());

        ObservableList<ProductSupplier> productsSuppliers = FXCollections.observableArrayList(
                ProductSupplierDB.getProductSuppliers());
        tvProdSup.setItems(productsSuppliers);
    }

    private void loadPaneProdSupAdd(){

        paneSupAdd.toFront();

        // layout
        paneSupAdd.setVisible(true);
        paneSupUpdate.setVisible(false);
        paneSupDelete.setVisible(false);
        paneSupList.setVisible(false);

        // combo box
        cbProdAdd.getSelectionModel().clearSelection();
        cbProdAdd.getItems().removeAll();
        ObservableList<Product> products = FXCollections.observableArrayList(ProductDB.getProducts());
        cbProdAdd.setItems(products);

        cbProdSupAdd.getSelectionModel().clearSelection();
        cbProdSupAdd.getItems().removeAll();
        ObservableList<Supplier> suppliers = FXCollections.observableArrayList(SupplierDB.getSuppliers());
        cbProdSupAdd.setItems(suppliers);

        txtProdNameAdd.clear();
        txtProdSupAdd.clear();
    }

    private void loadPaneProdSupUpdate(){

        paneSupUpdate.toFront();

        // layout
        paneSupAdd.setVisible(false);
        paneSupUpdate.setVisible(true);
        paneSupDelete.setVisible(false);
        paneSupList.setVisible(false);

        // combo box setup
        cbProSup.getSelectionModel().clearSelection();
        cbProSup.getItems().removeAll();
        ObservableList<ProductSupplier> productsSuppliers = FXCollections.observableArrayList(
                ProductSupplierDB.getProductSuppliers());
        cbProSup.setItems(productsSuppliers);

        cbProdSUpdate.getSelectionModel().clearSelection();
        cbProdSUpdate.getItems().removeAll();
        ObservableList<Product> products = FXCollections.observableArrayList(ProductDB.getProducts());
        cbProdSUpdate.setItems(products);

        cbProdSupUpdate.getSelectionModel().clearSelection();
        cbProdSupUpdate.getItems().removeAll();
        ObservableList<Supplier> suppliers = FXCollections.observableArrayList(SupplierDB.getSuppliers());
        cbProdSupUpdate.setItems(suppliers);

        txtProdSupUpdate.clear();
        txtProdNameUpdate.clear();

    }
    /*** Products Suppliers Tab END ***/

    /*** Package Products Suppliers Tab START ***/
    private void loadPanePckProdList() {
        panePckSupList.toFront();

        //layout
        panePckSupAdd.setVisible(false);
        panePckSupUpdate.setVisible(false);
        panePckSupDelete.setVisible(false);
        panePckSupList.setVisible(true);

        // Prepare table columns products suppliers
        clProdPck.setCellValueFactory(cellData -> cellData.getValue().pkgNameProperty());
        clProdPckSup.setCellValueFactory(cellData -> cellData.getValue().supNameProperty());

        ObservableList<PackageProductSupplier> pckProdSuppliers = FXCollections.observableArrayList(
                PackageProductSupplierDB.getPckgProductSuppliers());
        tvPckProd.setItems(pckProdSuppliers);
    }

    private void loadPanePckProdAdd() {
        panePckSupAdd.toFront();

        // layout
        panePckSupAdd.setVisible(true);
        panePckSupUpdate.setVisible(false);
        panePckSupDelete.setVisible(false);
        panePckSupList.setVisible(false);

        // combo box
        cbPckProdAdd.getSelectionModel().clearSelection();
        cbPckProdAdd.getItems().removeAll();
        ObservableList<Package> pckg = FXCollections.observableArrayList(
                PackageDB.getPackages());
        cbPckProdAdd.setItems(pckg);

        cbPckProdSupAdd.getSelectionModel().clearSelection();
        cbPckProdSupAdd.getItems().removeAll();
        ObservableList<ProductSupplier> prodSups = FXCollections.observableArrayList(
                ProductSupplierDB.getProductSuppliers());
        cbPckProdSupAdd.setItems(prodSups);

        txtPckProdNameAdd.clear();
        txtPckProdSupAdd.clear();
    }

    private void loadPanePckProdUpdate() {
        panePckSupUpdate.toFront();

        // layout
        panePckSupAdd.setVisible(false);
        panePckSupUpdate.setVisible(true);
        panePckSupDelete.setVisible(false);
        panePckSupList.setVisible(false);

        cbPckProdUpdate.getSelectionModel().clearSelection();
        cbPckProdUpdate.getItems().removeAll();
        ObservableList<Package> pckgs = FXCollections.observableArrayList(PackageDB.getPackages());
        cbPckProdUpdate.setItems(pckgs);

        cbPckProdSupUpdate.getSelectionModel().clearSelection();
        cbPckProdSupUpdate.getItems().removeAll();
        ObservableList<ProductSupplier> prodSuppliers = FXCollections.observableArrayList(
                ProductSupplierDB.getProductSuppliers());
        cbPckProdSupUpdate.setItems(prodSuppliers);

        txtPckProdUpdate.clear();
        txtPckProdSupUpdate.clear();
    }
    /*** Package Products Suppliers Tab START ***/

    private void loadPanePckProdDelete() {}

    /**** Combo box Events ****/
    /**** Products TAB START ****/
    @FXML
    void cbProdUpdateSelect(ActionEvent event) {
        Product product = cbProdUpdate.getSelectionModel().getSelectedItem();
        txtProdUpdate.setText(product.getProdName());
    }

    @FXML
    void cbProdDeleteSelect(ActionEvent event) {
        Product product = cbProdUpdate.getSelectionModel().getSelectedItem();
        txtProdDelete.setText(product.getProdName());
    }
    /**** Products TAB END ****/

    /**** Products Suppliers TAB START ****/
    @FXML
    void cbProdAddSelect(ActionEvent event) {

        Product product = cbProdAdd.getSelectionModel().getSelectedItem();
        txtProdNameAdd.setText(product.getProdName());
    }

    @FXML
    void cbProdSupAddSelect(ActionEvent event) {
        Supplier supplier = cbProdSupAdd.getSelectionModel().getSelectedItem();
        txtProdSupAdd.setText(supplier.getSupName());
    }

    @FXML
    void cbProdSupUpdateSelect(ActionEvent event) {

        Supplier supplier = cbProdSupUpdate.getSelectionModel().getSelectedItem();
        txtProdSupUpdate.setText(supplier.getSupName());

    }

    @FXML
    void cbProdSUpdateSelect(ActionEvent event) {
        Product product = cbProdSUpdate.getSelectionModel().getSelectedItem();
        txtProdNameUpdate.setText(product.getProdName());
    }


    @FXML
    void cbProSupSelect(ActionEvent event) {

        ProductSupplier prodSup = cbProSup.getSelectionModel().getSelectedItem();

        txtProdNameUpdate.setText(prodSup.getProdName());
        txtProdSupUpdate.setText(prodSup.getSupName());
        cbProdSUpdate.setValue(ProductDB.getProduct(prodSup.getProductId()));
        cbProdSupUpdate.setValue(SupplierDB.getSupplier(prodSup.getSupplierId()));

    }
    /**** Products Suppliers TAB END ****/

    /**** Package Products Suppliers TAB START ****/
    @FXML
    void cbPckProdAddSelect(ActionEvent event) {
        Package prodPck = cbPckProdAdd.getSelectionModel().getSelectedItem();
        txtPckProdNameAdd.setText(prodPck.getPkgName());
    }

    @FXML
    void cbPckProdSupAddSelect(ActionEvent event) {
        ProductSupplier prodSup = cbPckProdSupAdd.getSelectionModel().getSelectedItem();
        txtPckProdSupAdd.setText(prodSup.getSupName());
    }

    @FXML
    void cbPckProdSupUpdateSelect(ActionEvent event) {
        ProductSupplier prodSup = cbPckProdSupUpdate.getSelectionModel().getSelectedItem();
        txtPckProdSupUpdate.setText(prodSup.getSupName());

    }

    @FXML
    void cbPckProdUpdateSelect(ActionEvent event) {
        Package prodPck = cbPckProdUpdate.getSelectionModel().getSelectedItem();
        txtPckProdUpdate.setText(prodPck.getPkgName());
    }
    /**** Package Products Suppliers TAB END ****/

}