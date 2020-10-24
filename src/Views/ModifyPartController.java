package Views;

import Models.InHouse;
import Models.Inventory;
import static Models.Inventory.getAllParts;
import Models.Outsourced;
import Models.Part;
import static Views.MainScreenController.partToModifyIndex;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Kyle McIntyre
 */
public class ModifyPartController implements Initializable{
    
    int partIndex = partToModifyIndex();
    private int partId;

    @FXML
    private Label label;

    @FXML
    private RadioButton inhouseRadio;

    @FXML
    private ToggleGroup AddPart;

    @FXML
    private RadioButton outsourcedRadio;
    
    @FXML
    private Label companyNameLabel;

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
    private Button modifyPartSave;

    @FXML
    private Button modifyPartCancel;
    
    public void initialize(URL url, ResourceBundle rb) {
        // sets fields with the selected parts information
        Part part = Inventory.getAllParts().get(partIndex);
        partId = Inventory.getAllParts().get(partIndex).getId();
        idText.setText("" + partId);
        nameText.setText(part.getName());
        invText.setText(Integer.toString(part.getStock()));
        priceText.setText(Double.toString(part.getPrice()));
        minText.setText(Integer.toString(part.getMin()));
        maxText.setText(Integer.toString(part.getMax()));
        
        if (part instanceof InHouse) {
            companyNameLabel.setText("Machine ID");
            companyNameText.setText(Integer.toString(((InHouse) Inventory.getAllParts().get(partIndex)).getMachineId()));
            inhouseRadio.setSelected(true);
        }
        else {
            companyNameLabel.setText("Company Name");
            companyNameText.setText(((Outsourced) Inventory.getAllParts().get(partIndex)).getCompanyName());
            outsourcedRadio.setSelected(true);
        }
    }
    
    @FXML
    void cancel(ActionEvent event) {
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
        Stage stage = (Stage) modifyPartCancel.getScene().getWindow();
        stage.close();
        }
    }
    
    // sets label and prompt text depeneding on what radio
    // button is selected in add part screen
    @FXML
    void radioSelection(ActionEvent event) {
        if (AddPart.getSelectedToggle() == inhouseRadio) {
            companyNameLabel.setText("Machine ID");
            companyNameText.setPromptText("Machine ID");
        }
        else if (AddPart.getSelectedToggle() == outsourcedRadio){
            companyNameLabel.setText("Company Name");
            companyNameText.setPromptText("Company Name");
        }
    }

    // saves part using user input and adds part to the
    // observable list in the inventory class
    @FXML
    void save(ActionEvent event) {
        int id = Integer.parseInt(idText.getText());
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
            if (AddPart.getSelectedToggle() == inhouseRadio && max >= min) {
                int machineId = Integer.parseInt(companyNameText.getText());
                InHouse inHouse = new InHouse(id, name, price, stock, min, max, machineId);
                inHouse.setId(id);
                inHouse.setName(name);
                inHouse.setPrice(price);
                inHouse.setStock(stock);
                inHouse.setMin(min);
                inHouse.setMax(max);
                inHouse.setMachineId(machineId);
                Inventory.updatePart(partIndex, inHouse);

                Stage stage = (Stage) modifyPartCancel.getScene().getWindow();
                stage.close();
            }

            else if (AddPart.getSelectedToggle() == outsourcedRadio && max >= min) {
                String companyName = companyNameText.getText();
                Outsourced outsourced = new Outsourced();
                outsourced.setId(id);
                outsourced.setName(name);
                outsourced.setPrice(price);
                outsourced.setStock(stock);
                outsourced.setMin(min);
                outsourced.setMax(max);
                outsourced.setCompanyName(companyName);
                Inventory.updatePart(partIndex, outsourced);

                Stage stage = (Stage) modifyPartCancel.getScene().getWindow();
                stage.close();
            }
        }
}
