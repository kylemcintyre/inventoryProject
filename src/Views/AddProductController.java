package Views;

import Models.Inventory;
import Models.Part;
import Models.Product;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
public class AddProductController implements Initializable{
    
    // generates random int to be used as productId
    int id = (int )(Math.random() * 1000 + 1);
    private ObservableList productParts = FXCollections.observableArrayList();

    @FXML
    private Label label;

    @FXML
    private Button productAdd;

    @FXML
    private Button productsSearchButton;

    @FXML
    private TextField productsTextField;

    @FXML
    private Button productsDelete;

    @FXML
    private Button productsSave;

    @FXML
    private Button productCancel;

    @FXML
    private TextField idText;

    @FXML
    private TextField nameText;

    @FXML
    private TextField invText;

    @FXML
    private TextField priceText;

    @FXML
    private TextField minText;

    @FXML
    private TextField maxText;

    @FXML
    private TextField companyNameText;
    
    @FXML
    private TableView<Part> partsTable;

    @FXML
    private TableColumn partIDCol;
    
    @FXML
    private TableColumn partNameCol;
    
    @FXML
    private TableColumn partInvCol;
    
    @FXML
    private TableColumn partPriceCol;

    @FXML
    private Button partsDelete;

    @FXML
    private Button partsModify;

    @FXML
    private Button partsAdd;

    @FXML
    private TableView<Part> partsTableDelete;

    @FXML
    private TableColumn productIDCol;
    
    @FXML
    private TableColumn productNameCol;
        
    @FXML
    private TableColumn productInvCol;
            
    @FXML
    private TableColumn productPriceCol;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // sets data in the parts table on the top
        partIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        partsTable.setItems(Inventory.allParts);
        partsTable.refresh();
        
        // sets data in the part table on the bottom
        productIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
    
    // adds selected part from the top table to the bottom
    // table where it gets associated with product when saved
    @FXML
    void addPart(ActionEvent event) {
        Part part = partsTable.getSelectionModel().getSelectedItem();
        productParts.add(part);
        partsTableDelete.setItems(productParts);
    }

    // deletes selected part from the bottom table
    @FXML
    void delete(ActionEvent event) {
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
        Part part = partsTableDelete.getSelectionModel().getSelectedItem();
        productParts.remove(part);
        }
    }

    @FXML
    void exitButton(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancel");    
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you wish to cancel?");
        ButtonType buttonTypeOne = new ButtonType("Yes");
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);
        alert.getDialogPane().lookupButton(buttonTypeCancel).setVisible(true);
        Optional<ButtonType> result = alert.showAndWait();
        
        if (result.get() == buttonTypeOne) {
        Stage stage = (Stage) productCancel.getScene().getWindow();
        stage.close();
        }
    }

    @FXML
    void save(ActionEvent event) {
        String name = nameText.getText();
        int stock = Integer.parseInt(invText.getText());
        double price = Double.parseDouble(priceText.getText());
        int min = Integer.parseInt(minText.getText());
        int max = Integer.parseInt(maxText.getText());
        
        if (max < min) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("Inventory Conflict");
            alert.setHeaderText("Max must be more than min");
            alert.showAndWait();            
        }
        else {
            Product product = new Product();
            product.setId(id);
            product.setName(name);
            product.setPrice(price);
            product.setStock(stock);
            product.setMin(min);
            product.setMax(max);
            product.setAssociatedParts(productParts);
            Inventory.addProduct(product);

            Stage stage = (Stage) productCancel.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    void search(ActionEvent event) {
        String search = productsTextField.getText();
        
        // sends user input to correct search function
        // if input was an int or a string
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
        productsTextField.clear();
    }
    
    // returns true if user input was an int
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

