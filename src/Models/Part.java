package Models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Kyle McIntyre
 */
public abstract class Part {
    
    protected IntegerProperty id;
    protected StringProperty name;
    protected DoubleProperty price;
    protected IntegerProperty stock;
    protected IntegerProperty min;
    protected IntegerProperty max;
    
    public Part() {
        this.id = new SimpleIntegerProperty();
        this.name = new SimpleStringProperty();
        this.price = new SimpleDoubleProperty();
        this.stock = new SimpleIntegerProperty();
        this.min = new SimpleIntegerProperty();
        this.max = new SimpleIntegerProperty();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public void setStock(int stock) {
        this.stock.set(stock);
    }

    public void setMin(int min) {
        this.min.set(min);
    }

    public void setMax(int max) {
        this.max.set(max);
    }

    public int getId() {
        return id.getValue();
    }

    public String getName() {
        return name.getValue();
    }

    public double getPrice() {
        return price.getValue();
    }

    public int getStock() {
        return stock.getValue();
    }

    public int getMin() {
        return min.getValue();
    }

    public int getMax() {
        return max.getValue();
    }
    
}
