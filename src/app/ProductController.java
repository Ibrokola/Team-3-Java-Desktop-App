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

    /*
     * Purpose: Controller for the Product and ProductsSuppliers view page.
     * Author: Ibraheem Kolawole
     * Module: PROJ-207-OSD
     * Date: May 20, 2019
     * */

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

    @FXML
    private TextField txtProdNameDelete;

    @FXML
    private Button btnDeleteProdSup;

    @FXML
    private ComboBox<ProductSupplier> cbProSupDelete;

    @FXML
    private ComboBox<Product> cbProdSDelete;

    @FXML
    private ComboBox<Supplier> cbProdSupDelete;

    @FXML
    private TextField txtProdSupDelete;

    @FXML
    private Button btnDeleteProdSupGoBack;

    // Products Suppliers Delete Pane /!END!/

    //------------------------------//
    // Products Suppliers Tab ----END----!!!


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
        if(event.getSource() == btnDeleteProductSuppliers){
            loadPaneProdSupDelete();
        }
        if(event.getSource() == btnUpdateProdSupGoBack ||
                event.getSource() == btnAddProdSupGoBack ||
                event.getSource() == btnDeleteProdSupGoBack){
            loadPaneProdSupList();
        }


        /*** PANE OPERATIONS ***/
        /*** Products Tab  Start ***/

        /*** Add Pane ***/
        if(event.getSource() == btnAddProduct){

            if(Validation.isProvided(txtProdName, "product name"))
            {
                Product product = new Product(txtProdName.getText());
                ProductDB.addProduct(product);
                loadPaneProdList();
            }
        }

        /*** Update Pane ***/
        if(event.getSource() == btnProdUpdate){
            if(Validation.isProvided(txtProdUpdate, "product name"))
            {
                Product prod = cbProdUpdate.getSelectionModel().getSelectedItem();
                Product product = new Product(prod.getProductId(), txtProdUpdate.getText());
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
                        sup.getSupplierId());

                ProductSupplierDB.updateProductSupplier(prodSup);
                loadPaneProdSupList();
            }
        }

        /*** Delete Pane ***/
        if(event.getSource() == btnDeleteProdSup){
           if(Validation.isProvided(txtProdNameDelete, "Product name") &&
                   Validation.isProvided(txtProdSupDelete, "Product supplier") &&
                   Validation.hasSelection(cbProdSDelete, "product") &&
                   Validation.hasSelection(cbProdSupDelete, "supplier"))
             {
               ProductSupplier proSup = cbProSupDelete.getSelectionModel().getSelectedItem();
               ProductSupplier prodSup = new ProductSupplier(proSup.getProductSupplierId(),
                       proSup.getProductId(), proSup.getSupplierId());
               ProductSupplierDB.deleteProductSupplier(prodSup);
               loadPaneProdSupList();
             }
         }
        /*** Products Suppliers Tab End ***/
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


        loadPaneProdList();

        loadPaneProdSupList();

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

        txtSupSearch.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                ObservableList<ProductSupplier> productsSuppliers = FXCollections.observableArrayList(
                        ProductSupplierDB.searchProductsSuppliers(txtSupSearch.getText()));

                tvProdSup.setItems(productsSuppliers);
            }
        });

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

        cbProdUpdate.getSelectionModel().clearSelection();
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

        txtProdDelete.clear();
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
        ObservableList<Product> products = FXCollections.observableArrayList(
                ProductDB.getProducts());
        cbProdSUpdate.setItems(products);

        cbProdSupUpdate.getSelectionModel().clearSelection();
        cbProdSupUpdate.getItems().removeAll();
        ObservableList<Supplier> suppliers = FXCollections.observableArrayList(
                SupplierDB.getSuppliers());
        cbProdSupUpdate.setItems(suppliers);

        // clear text fields
        txtProdNameUpdate.clear();
        txtProdSupUpdate.clear();
    }


    private void loadPaneProdSupDelete(){

        paneSupDelete.toFront();

        // layout
        paneSupAdd.setVisible(false);
        paneSupUpdate.setVisible(false);
        paneSupDelete.setVisible(true);
        paneSupList.setVisible(false);


        // combo box setup
        cbProSupDelete.getSelectionModel().clearSelection();
        cbProSupDelete.getItems().removeAll();
        ObservableList<ProductSupplier> productsSuppliers = FXCollections.observableArrayList(
                ProductSupplierDB.getProductSuppliers());
        cbProSupDelete.setItems(productsSuppliers);

        cbProdSDelete.getSelectionModel().clearSelection();
        cbProdSDelete.getItems().removeAll();
        ObservableList<Product> products = FXCollections.observableArrayList(
                ProductDB.getProducts());
        cbProdSDelete.setItems(products);

        cbProdSupDelete.getSelectionModel().clearSelection();
        cbProdSupDelete.getItems().removeAll();
        ObservableList<Supplier> suppliers = FXCollections.observableArrayList(
                SupplierDB.getSuppliers());
        cbProdSupDelete.setItems(suppliers);

        // clear text fields
        txtProdNameDelete.clear();
        txtProdSupDelete.clear();

    }
    /*** Products Suppliers Tab END ***/


    /**** Combo box Events ****/
    /**** Products TAB START ****/

    @FXML
    void cbProdUpdateSelect(ActionEvent event) {
        Product product = cbProdUpdate.getSelectionModel().getSelectedItem();

        if (product != null) {
            txtProdUpdate.setText(product.getProdName());
        }

    }

    @FXML
    void cbProdDeleteSelect(ActionEvent event) {
        Product product = cbProdDelete.getSelectionModel().getSelectedItem();

        if (product != null){
            txtProdDelete.setText(product.getProdName());
        }

    }
    /**** Products TAB END ****/


    /**** Products Suppliers TAB START ****/
    @FXML
    void cbProdAddSelect(ActionEvent event) {

        Product product = cbProdAdd.getSelectionModel().getSelectedItem();
        if (product != null){
            txtProdNameAdd.setText(product.getProdName());
        }
    }

    @FXML
    void cbProdSupAddSelect(ActionEvent event) {
        Supplier supplier = cbProdSupAdd.getSelectionModel().getSelectedItem();
        if (supplier != null){
            txtProdSupAdd.setText(supplier.getSupName());
        }
    }

    @FXML
    void cbProdSupUpdateSelect(ActionEvent event) {

        Supplier supplier = cbProdSupUpdate.getSelectionModel().getSelectedItem();

        if (supplier != null){
            txtProdSupUpdate.setText(supplier.getSupName());
        }

    }

    @FXML
    void cbProdSUpdateSelect(ActionEvent event) {
        Product product = cbProdSUpdate.getSelectionModel().getSelectedItem();

        if (product != null){
            txtProdNameUpdate.setText(product.getProdName());
        }

    }

    @FXML
    void cbProSupSelect(ActionEvent event) {

        ProductSupplier prodSup = cbProSup.getSelectionModel().getSelectedItem();

        if (prodSup != null){
            cbProdSUpdate.setValue(ProductDB.getProduct(prodSup.getProductId()));
            cbProdSupUpdate.setValue(SupplierDB.getSupplier(prodSup.getSupplierId()));

            txtProdNameUpdate.setText(prodSup.getProdName());
            txtProdSupUpdate.setText(prodSup.getSupName());
        }
    }

    @FXML
    void cbProSupDeleteSelect(ActionEvent event) {
        ProductSupplier prodSup = cbProSupDelete.getSelectionModel().getSelectedItem();

        if(prodSup != null){
            cbProdSDelete.setValue(ProductDB.getProduct(prodSup.getProductId()));
            cbProdSupDelete.setValue(SupplierDB.getSupplier(prodSup.getSupplierId()));

            txtProdNameDelete.setText(prodSup.getProdName());
            txtProdSupDelete.setText(prodSup.getSupName());
        }
    }

    @FXML
    void cbProdSDeleteSelect(ActionEvent event) {
        Product product = cbProdSDelete.getSelectionModel().getSelectedItem();

        if (product != null){
            txtProdNameDelete.setText(product.getProdName());
        }
    }

    @FXML
    void cbProdSupDeleteSelect(ActionEvent event) {
        Supplier supplier = cbProdSupDelete.getSelectionModel().getSelectedItem();

        if (supplier != null){
            txtProdSupDelete.setText(supplier.getSupName());
        }
    }
    /**** Products Suppliers TAB END ****/

}