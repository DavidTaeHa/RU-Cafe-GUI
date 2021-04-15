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

    /**
     * Exports and writes orders within the database to a text file
     *
     * @throws IOException
     */
    public void exportDatabase() throws IOException {
        File file = new File("export.txt");
        PrintWriter writer = new PrintWriter(new FileWriter(file));
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        for(Order order : ordersList){
            writer.println("Order " + order.getOrderNumber() + ":");
            for(MenuItem item : order.getItems()){
                writer.println(item);
            }
            writer.println("Total: " + formatter.format(order.getTotal()));
            writer.println("------------------------------------------");
        }
        writer.close();
    }
}
