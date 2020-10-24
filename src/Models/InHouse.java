package Models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Kyle McIntyre
 */
public class InHouse extends Part {
    
    private IntegerProperty machineId;
    
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineID) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleDoubleProperty(price);
        this.stock = new SimpleIntegerProperty(stock);
        this.min = new SimpleIntegerProperty(min);
        this.max = new SimpleIntegerProperty(max);
        this.machineId = new SimpleIntegerProperty(machineID);
    }

    public InHouse() {
        this.id = new SimpleIntegerProperty(0);
        this.name = new SimpleStringProperty("");
        this.price = new SimpleDoubleProperty(0);
        this.stock = new SimpleIntegerProperty(0);
        this.min = new SimpleIntegerProperty(0);
        this.max = new SimpleIntegerProperty(0);
        this.machineId = new SimpleIntegerProperty(0);
    }

    public int getMachineId() {
        return machineId.get();
    }

    public void setMachineId(int machineId) {
        this.machineId.set(machineId);
    }
    
    public IntegerProperty machineIDProperty(){
        return machineId;
    }
}
