package app;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import BLL.*;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;



public class ProductController {

    // Resource attributes
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;

    // Table attributes
    @FXML
    private TableView<Product> tvProducts;
    @FXML
    private TableColumn<Product, Integer> clProdId;
    @FXML
    private TableColumn<Product, String> clProdName;
    @FXML
    private TableView<ProductSupplier> tvProdSup;
    @FXML
    private TableColumn<ProductSupplier, Integer> clProdSupId;
    @FXML
    private TableColumn<ProductSupplier, String> clProdSupProd;
    @FXML
    private TableColumn<ProductSupplier, String> clProdSupSup;

    // Textfield attributes
    @FXML
    private TextField txtProdSearch;
    @FXML
    private TextField txtProdName;
    @FXML
    private TextField txtSearchSup;

    // Button attributes
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnClear;
    @FXML
    private Button btnHome;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnRemove;

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


