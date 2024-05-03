package ibf2024.assessment.paf.batch4.models;

import java.util.HashMap;
import java.util.Random;

public class Order {

    private final String orderId;
    private int breweryId;
    private HashMap<Integer, Integer> orderCount;
    private int quantity;

    public Order() {
        this.orderId = null;    //TODO
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public static Order create(String json) {
        

        return null;
    }

    public String getOrderId() {
        return orderId;
    }

    public int getBreweryId() {
        return breweryId;
    }

    public void setBreweryId(int breweryId) {
        this.breweryId = breweryId;
    }

    public HashMap<Integer, Integer> getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(HashMap<Integer, Integer> orderCount) {
        this.orderCount = orderCount;
    }

    
    
}
