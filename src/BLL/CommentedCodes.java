package BLL;

public class CommentedCodes {

    //    private int prodId;

    // Package Products Suppliers Tab ----START----!!!
    //----------------------------------------------//
    // Package Products Suppliers List Pane /!START!/
//    @FXML
//    private Pane panePckSupList;
//
//    // Table attributes
//    @FXML
//    private TableView<PackageProductSupplier> tvPckProd;
//    @FXML
//    private TableColumn<PackageProductSupplier, String> clProdPck;
//    @FXML
//    private TableColumn<PackageProductSupplier, String> clProdPckSup;
//
//    // TextField attribute
//    @FXML
//    private TextField txtPckSupSearch;
//
//    // Buttons
//    @FXML
//    private Button btnAddPackageSuppliers;
//    @FXML
//    private Button btnUpdatePackageSuppliers;
//    // Package Products Suppliers List Pane /!END!/
//
//    // Package Products Suppliers Add Pane /!START!/
//
//    @FXML
//    private Pane panePckSupAdd;
//
//    // TextField attributes
//    @FXML
//    private TextField txtPckProdNameAdd;
//    @FXML
//    private TextField txtPckProdSupAdd;
//
//    // ComboBox attributes
//    @FXML
//    private ComboBox<Package> cbPckProdAdd;
//    @FXML
//    private ComboBox<ProductSupplier> cbPckProdSupAdd;
//
//    // Buttons
//    @FXML
//    private Button btnAddPckProdSup;
//    @FXML
//    private Button btnAddPckProdSupGoBack;
//
//    // Package Products Suppliers Add Pane /!END!/
//
//
//    // Package Products Suppliers Update Pane /!START!/
//
//    @FXML
//    private Pane panePckSupUpdate;
//
//    // TextField attributes
//    @FXML
//    private TextField txtPckProdUpdate;
//    @FXML
//    private TextField txtPckProdSupUpdate;
//
//    // ComboBox attributes
//    @FXML
//    private ComboBox<Package> cbPckProdUpdate;
//    @FXML
//    private ComboBox<ProductSupplier> cbPckProdSupUpdate;
//
//    // Buttons
//    @FXML
//    private Button btnPckProdSupUpdate;
//    @FXML
//    private Button btnPckProdSupUpdateGoBack;
//    // Package Products Suppliers Update Pane /!END!/
//
//    // Package Products Suppliers Delete Pane /!END!/
//    @FXML
//    private Pane panePckSupDelete;
    // Package Products Suppliers Delete Pane /!END!/
    //---------------------------------------------//
    // Package Products Suppliers Tab ----END----!!!

//    @FXML
//    private ComboBox<?> cbProdSupDelete;
//
//    @FXML
//    private Button btnProdSupDelete;
//
//    @FXML
//    private Button btnProdSupDeleteGoBack;
//
//    @FXML
//    void cbProdSupSelect(ActionEvent event) {
//
//    }


    /*** Package Products Suppliers Tab ***/
    /*** Pane switching buttons ***/
//        if(event.getSource() == btnAddPackageSuppliers){
//            loadPanePckProdAdd();
//        }
//        if(event.getSource() == btnUpdatePackageSuppliers){
//            loadPanePckProdUpdate();
//        }
//        if(event.getSource() == btnPckProdSupUpdateGoBack ||
//                event.getSource() == btnAddPckProdSupGoBack){
//            loadPanePckProdList();
//        }


    /*** PANE OPERATIONS ***/
    /*** Package Products Suppliers Tab Start ***/

    /*** Add Pane ***/
//        if(event.getSource() == btnAddPckProdSup){
//
//            if(
//                    Validation.isProvided(txtPckProdNameAdd, "package name") &&
//                            Validation.isProvided(txtPckProdSupAdd, "product supplier") &&
//                            Validation.hasSelection(cbPckProdAdd, "package id") &&
//                            Validation.hasSelection(cbPckProdSupAdd, "supplier id")
//            )
//            {
//                Package pck = cbPckProdAdd.getSelectionModel().getSelectedItem();
//                ProductSupplier sup = cbPckProdSupAdd.getSelectionModel().getSelectedItem();
//                PackageProductSupplier pckProdSup = new PackageProductSupplier(
//                        pck.getPackageId(),
//                        sup.getProductSupplierId());
//
//                PackageProductSupplierDB.addPackageProductSupplier(pckProdSup);
////                loadPanePckProdList();
//            }
//        }

    /*** Update Pane ***/
//        if(event.getSource() == btnPckProdSupUpdate){
//            if(Validation.isProvided(txtPckProdUpdate, "Package name") &&
//                    Validation.isProvided(txtPckProdSupUpdate, "Product supplier") &&
//                    Validation.hasSelection(cbPckProdUpdate, "product id") &&
//                    Validation.hasSelection(cbPckProdSupUpdate, "product id"))
//
//            {
//                Package pck = cbPckProdUpdate.getSelectionModel().getSelectedItem();
//                ProductSupplier prodSup = cbPckProdSupUpdate.getSelectionModel().getSelectedItem();
//
//                PackageProductSupplier pckProdSup = new PackageProductSupplier(
//                        pck.getPackageId(),
//                        pck.getPkgName(),
//                        prodSup.getProductSupplierId(),
//                        prodSup.getSupName());
//
//                PackageProductSupplierDB.updatePackageProductSupplier(pckProdSup);
////                loadPanePckProdList();
//            }
//        }

    /*** Package Products Suppliers Tab End ***/
    /*** PANE OPERATIONS ***/



    //        loadPanePckProdList();

    //layout setup package products suppliers tab
//        panePckSupAdd.setVisible(false);
//        panePckSupUpdate.setVisible(false);
//        panePckSupDelete.setVisible(false);
//        panePckSupList.setVisible(true);


//        txtPckSupSearch.textProperty().addListener(new ChangeListener<String>() {
//            @Override
//            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
//                ObservableList<PackageProductSupplier> pckProductsSuppliers = FXCollections.observableArrayList(
//                        PackageProductSupplierDB.searchPckgProductsSuppliers(txtPckSupSearch.getText()));
//                tvPckProd.setItems(pckProductsSuppliers);
//            }
//        });

    /*** Package Products Suppliers Tab START ***/
//    private void loadPanePckProdList() {
//        panePckSupList.toFront();
//
//        //layout
//        panePckSupAdd.setVisible(false);
//        panePckSupUpdate.setVisible(false);
//        panePckSupDelete.setVisible(false);
//        panePckSupList.setVisible(true);
//
//        // Prepare table columns products suppliers
//        clProdPck.setCellValueFactory(cellData -> cellData.getValue().pkgNameProperty());
//        clProdPckSup.setCellValueFactory(cellData -> cellData.getValue().supNameProperty());
//
//        ObservableList<PackageProductSupplier> pckProdSuppliers = FXCollections.observableArrayList(
//                PackageProductSupplierDB.getPckgProductSuppliers());
//        tvPckProd.setItems(pckProdSuppliers);
//    }

//    private void loadPanePckProdAdd() {
//        panePckSupAdd.toFront();
//
//        // layout
//        panePckSupAdd.setVisible(true);
//        panePckSupUpdate.setVisible(false);
//        panePckSupDelete.setVisible(false);
//        panePckSupList.setVisible(false);
//
//        // combo box
//        cbPckProdAdd.getSelectionModel().clearSelection();
//        cbPckProdAdd.getItems().removeAll();
//        ObservableList<Package> pckg = FXCollections.observableArrayList(
//                PackageDB.getPackages());
//        cbPckProdAdd.setItems(pckg);
//
//        cbPckProdSupAdd.getSelectionModel().clearSelection();
//        cbPckProdSupAdd.getItems().removeAll();
//        ObservableList<ProductSupplier> prodSups = FXCollections.observableArrayList(
//                ProductSupplierDB.getProductSuppliers());
//        cbPckProdSupAdd.setItems(prodSups);
//
//        txtPckProdNameAdd.clear();
//        txtPckProdSupAdd.clear();
//    }

//    private void loadPanePckProdUpdate() {
//        panePckSupUpdate.toFront();
//
//        // layout
//        panePckSupAdd.setVisible(false);
//        panePckSupUpdate.setVisible(true);
//        panePckSupDelete.setVisible(false);
//        panePckSupList.setVisible(false);
//
//        cbPckProdUpdate.getSelectionModel().clearSelection();
//        cbPckProdUpdate.getItems().removeAll();
//        ObservableList<Package> pckgs = FXCollections.observableArrayList(PackageDB.getPackages());
//        cbPckProdUpdate.setItems(pckgs);
//
//        cbPckProdSupUpdate.getSelectionModel().clearSelection();
//        cbPckProdSupUpdate.getItems().removeAll();
//        ObservableList<ProductSupplier> prodSuppliers = FXCollections.observableArrayList(
//                ProductSupplierDB.getProductSuppliers());
//        cbPckProdSupUpdate.setItems(prodSuppliers);
//
//        txtPckProdUpdate.clear();
//        txtPckProdSupUpdate.clear();
//    }
    /*** Package Products Suppliers Tab START ***/

//    private void loadPanePckProdDelete() {}


    /**** Package Products Suppliers TAB START ****/
//    @FXML
//    void cbPckProdAddSelect(ActionEvent event) {
//        Package prodPck = cbPckProdAdd.getSelectionModel().getSelectedItem();
//        txtPckProdNameAdd.setText(prodPck.getPkgName());
//    }
//
//    @FXML
//    void cbPckProdSupAddSelect(ActionEvent event) {
//        ProductSupplier prodSup = cbPckProdSupAdd.getSelectionModel().getSelectedItem();
//        txtPckProdSupAdd.setText(prodSup.getSupName());
//    }
//
//    @FXML
//    void cbPckProdSupUpdateSelect(ActionEvent event) {
//        ProductSupplier prodSup = cbPckProdSupUpdate.getSelectionModel().getSelectedItem();
//        txtPckProdSupUpdate.setText(prodSup.getSupName());
//
//    }
//
//    @FXML
//    void cbPckProdUpdateSelect(ActionEvent event) {
//        Package prodPck = cbPckProdUpdate.getSelectionModel().getSelectedItem();
//        txtPckProdUpdate.setText(prodPck.getPkgName());
//    }
    /**** Package Products Suppliers TAB END ****/
}
