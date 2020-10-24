package Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.stage.Modality;

/**
 *
 * @author Kyle McIntyre
 */
public class Inventory {
    
    public static ObservableList<Part> allParts = FXCollections.observableArrayList();
    public static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    
    public static void addPart (Part newPart) {
        allParts.add(newPart);
    }
    
    public static void addProduct (Product newProduct) {
        allProducts.add(newProduct);
    }
    
    // returns matched part if user entered an int
    public static Part lookupPart (int partId) {
        try {
        for (int i = 0; i < allParts.size(); i++) {
                if (allParts.get(i).getId() == partId) {
                    return allParts.get(i);
                }
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setTitle("No parts found");
            alert.setHeaderText("No parts found matching part id");
            alert.showAndWait();
            return allParts.get(partId);
        }
        return allParts.get(partId);
    }
    
    // returns matched part if user entered a string
    public static ObservableList<Part> lookupPart (String partName) {
       ObservableList<Part> foundSearch = FXCollections.observableArrayList();
       
       if(partName.length() == 0) {
            foundSearch = allParts;
        } else {
            for (int i = 0; i < allParts.size(); i++) {
                if (allParts.get(i).getName().toLowerCase().contains(partName.toLowerCase())) {
                    foundSearch.add(allParts.get(i));
                }
            }
            return foundSearch;
        }
       return foundSearch;
    }
    
    // returns matched product if user entered an int
    public static Product lookupProduct (int productId) {
        for (int i = 0; i < allParts.size(); i++) {
            if (allProducts.get(i).getId() == productId) {
                return allProducts.get(i);
                }
            }
        return allProducts.get(productId);
    }

    // returns matched product if user entered a string
    public static ObservableList<Product> lookupProduct (String productName) {
        ObservableList<Product> foundSearch = FXCollections.observableArrayList();
       
       if(productName.length() == 0) {
            foundSearch = allProducts;
        } else {
            for (int i = 0; i < allProducts.size(); i++) {
                if (allProducts.get(i).getName().toLowerCase().contains(productName.toLowerCase())) {
                    foundSearch.add(allProducts.get(i));
                }
            }
            return foundSearch;
        }
       return foundSearch;
    }
    
    public static void updatePart (int index, Part newPart) {
        allParts.set(index, newPart);
    }
    
    public static void updateProduct (int index, Product newProduct) {
        allProducts.set(index, newProduct);
    }
    
    public static boolean deletePart (Part selectedPart) {
        return true;
    }
    
    public static boolean deleteProduct (Product selectedProduct) {
        return true;
    }
    
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }
    
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }
    
    
    
}
