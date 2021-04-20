package com.example.rucafe;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;

/**
 * This class represents a StoreOrder container that will contain instances of various orders made
 * by the customers
 *
 * @author David Ha, Andrew McAvoy
 */
public class StoreOrders implements Customizable, Serializable {
    private ArrayList<Order> ordersList;

    /**
     * Constructor for store order class
     */
    public StoreOrders(){
        this.ordersList = new ArrayList<>();
    }

    /**
     * Getter method for order list
     *
     * @return order list
     */
    public ArrayList<Order> getStoreOrders() {
        return ordersList;
    }

    /**
     * Adds an order to a the list of orders
     *
     * @param obj order to be added to the list
     * @return true if successfully added; false if otherwise
     */
    @Override
    public boolean add(Object obj) {
        if(obj instanceof Order){
            ordersList.add((Order) obj);
        }
        return false;
    }

    /**
     * Removes instance of an order from the list
     *
     * @param obj order to be removed from the list
     * @return true if successfully removed; false if otherwise
     */
    @Override
    public boolean remove(Object obj) {
        if(obj instanceof Order){
            ordersList.remove(obj);
        }
        return false;
    }

}
