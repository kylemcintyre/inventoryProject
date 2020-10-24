package Views;

import Models.InHouse;
import Models.Inventory;
import static Models.Inventory.allProducts;
import Models.Outsourced;
import Models.Part;
import Models.Product;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Kyle McIntyre
 */
public class MainScreenController implements Initializable {

    private static Part partToModify;
    private static int partToModifyIndex;
    private static Product productToModify;
    private static int productToModifyIndex;
    
    public static int partToModifyIndex() {
        return partToModifyIndex;
    }
    
    public static int productToModifyIndex() {
        return productToModifyIndex;
    }
    
    @FXML
    private Button partsSearchButton;

    @FXML
    private Label label;

    @FXML
    private TextField partsTextField;

    @FXML
    private TableView<Part> partsTable;

    @FXML
    private TableColumn partIDCol, partNameCol, partInvCol, partPriceCol;

    @FXML
    private Button partsDelete;

    @FXML
    private Button partsModify;

    @FXML
    private Button partsAdd;

    @FXML
    private Button productsSearchButton;

    @FXML
    private TextField productsTextField;

    @FXML
    private TableView<Product> productsTable;

    @FXML
    private TableColumn productIDCol, productNameCol, productInvCol, productPriceCol;

    @FXML
    private Button productsDelete;

    @FXML
    private Button productsModify;

    @FXML
    private Button productsAdd;

    @FXML
    private Button exitButton;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        /* Test parts
        InHouse test1 = new InHouse(1, "Intel i7", 299.99, 3, 0, 10, 999);
        InHouse test2 = new InHouse(2, "RAM DDR4 3200", 199.99, 12, 0, 20, 999);
        Outsourced test3 = new Outsourced(3, "Motherboard", 149.99, 2, 0, 20, "Asus");
        Outsourced test4 = new Outsourced(4, "Nvidia RTX 3080", 1499.99, 3, 0, 10, "Nvidia");
        Product testProd1 = new Product();
        Product testProd2 = new Product();
        Inventory.addPart(test1);
        Inventory.addPart(test2);
        Inventory.addPart(test3);
        Inventory.addPart(test4); */
        
        // set parts to left parts table
        partIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        partsTable.setItems(Inventory.allParts);
        partsTable.refresh();
        
        // set products to right products table
        productsTable.setItems(Inventory.getAllProducts());
        productIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    @FXML
    void addPart(ActionEvent event) {
        // opens add part screen
        try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/AddPart.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));  
        stage.show();
    } catch(Exception e) {
        e.printStackTrace();
    }
    }

    @FXML
    void addProduct(ActionEvent event) {
        // opens add product screen
        try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/AddProduct.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));  
        stage.show();
    } catch(Exception e) {
        e.printStackTrace();
    }
    }

    @FXML
    void deletePart(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete");    
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you wish to delete this part?");
        ButtonType buttonTypeOne = new ButtonType("Yes");
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);
        alert.getDialogPane().lookupButton(buttonTypeCancel).setVisible(true);
        Optional<ButtonType> result = alert.showAndWait();
        
        if (result.get() == buttonTypeOne) {
        partsTable.getItems().removeAll(partsTable.getSelectionModel().getSelectedItem());
        }
    }

    @FXML
    void deleteProducts(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete");    
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you wish to delete this product?");
        ButtonType buttonTypeOne = new ButtonType("Yes");
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);
        alert.getDialogPane().lookupButton(buttonTypeCancel).setVisible(true);
        Optional<ButtonType> result = alert.showAndWait();
        
        if (result.get() == buttonTypeOne) {
        productsTable.getItems().removeAll(productsTable.getSelectionModel().getSelectedItem());
        }
    }

    @FXML
    void exitButton(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");    
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you wish to exit?");
        ButtonType buttonTypeOne = new ButtonType("Yes");
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);
        alert.getDialogPane().lookupButton(buttonTypeCancel).setVisible(true);
        Optional<ButtonType> result = alert.showAndWait();
        
        if (result.get() == buttonTypeOne) {        
        System.exit(0);
        }
    }

    @FXML
    void modifyPart(ActionEvent event) throws IOException {
        
        // opens modify part screen saving selected part
        // in the parts table and getting information to
        // the modify part screen
        try {
        partToModify = partsTable.getSelectionModel().getSelectedItem();
        partToModifyIndex = Inventory.getAllParts().indexOf(partToModify);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/ModifyPart.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));  
        stage.show();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("No parts found");
            alert.setHeaderText("Must select a part to modify");
            alert.showAndWait();
        }
        /*try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/ModifyPart.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));  
        stage.show(); 

    } catch(Exception e) {
        e.printStackTrace();
    }*/
    }

    @FXML
    void modifyProduct(ActionEvent event) throws IOException {
        
        // opens modify product screen saving selected part
        // in the parts table and getting information to
        // the modify product screen
        try {
        productToModify = productsTable.getSelectionModel().getSelectedItem();
        productToModifyIndex = Inventory.getAllProducts().indexOf(productToModify);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Views/ModifyProduct.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));  
        stage.show();
        } catch (IOException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("No products found");
            alert.setHeaderText("Must select a product to modify");
            alert.showAndWait();
        }
    }

    @FXML
    void searchPart(ActionEvent event) {
        String search = partsTextField.getText();
        // checks to see if what user entered into search
        // is an int or a string and then passes user
        // input to the appropriate search function in the
        // inventory class. Then sets the part table to 
        // display the found part
        try {
        if (numberValidation(search)) {
            Part searchPart = Inventory.lookupPart(Integer.parseInt(search));
            ObservableList<Part> searchFoundId = FXCollections.observableArrayList();
            searchFoundId.add(searchPart);
            partsTable.setItems(searchFoundId);
        }
        else if (!numberValidation(search)){
            ObservableList searchFound = Inventory.lookupPart(search);
            partsTable.setItems(searchFound);
                if (searchFound.isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.initModality(Modality.APPLICATION_MODAL);
                    alert.setTitle("No Parts found");
                    alert.setHeaderText("No parts found matching input"); 
                    alert.showAndWait();
                    partsTable.setItems(Inventory.allParts);
                }           
        }
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("No Parts found");
            alert.setHeaderText("No parts found matching part id");
            alert.showAndWait();
            partsTable.setItems(Inventory.allParts);
        }
        partsTextField.clear();
    }


    @FXML
    void searchProduct(ActionEvent event) {
        String search = productsTextField.getText();
        // checks to see if what user entered into search
        // is an int or a string and then passes user
        // input to the appropriate search function in the
        // inventory class. Then sets the product table to 
        // display the found part
        if (numberValidation(search)) {
            Product searchProduct = Inventory.lookupProduct(Integer.parseInt(search));
            ObservableList<Product> searchFoundId = FXCollections.observableArrayList();
            searchFoundId.add(searchProduct);
            productsTable.setItems(searchFoundId);
        }
        else if (!numberValidation(search)){
            ObservableList searchFound = Inventory.lookupProduct(search);
            productsTable.setItems(searchFound);
                if (searchFound.isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.initModality(Modality.APPLICATION_MODAL);
                    alert.setTitle("No products found");
                    alert.setHeaderText("No products found matching input"); 
                    alert.showAndWait();
                    productsTable.setItems(Inventory.allProducts);
                }           
        }
        productsTextField.clear();

    }
    
    // accepts input and returns true if input was an int
    // returns false for everything else
    boolean numberValidation(String number){
    try{
        Integer.parseInt(number);
        return true;
    }catch(NumberFormatException e){
        return false;
    }
}

}