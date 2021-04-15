package com.example.rucafe;

import java.util.ArrayList;

/**
 * This class represents an Order container that contains instances of MenuItems classes
 *
 * @author David Ha, Andrew McAvoy
 */
public class Order implements Customizable {
    private ArrayList<MenuItem> items;
    private double subtotal;
    private double taxTotal;
    private double total;
    private int orderNumber;

    private static int orderNumberTracker = 1;

    final static double TAX = 0.06625;

    /**
     * Constructor for the Order class
     */
    public Order() {
        this.items = new ArrayList<>();
        this.subtotal = 0;
        this.taxTotal = 0;
        this.total = 0;
        this.orderNumber = orderNumberTracker;
        orderNumberTracker++;
    }

    /**
     * Getter method for order number
     *
     * @return order number
     */
    public int getOrderNumber() {
        return orderNumber;
    }

    /**
     * Getter method for the subtotal of items
     *
     * @return subtotal
     */
    public double getSubtotal() {
        return subtotal;
    }

    /**
     * Getter method for the tax of items
     *
     * @return total tax
     */
    public double getTaxTotal() {
        return taxTotal;
    }

    /**
     * Getter method for the total
     *
     * @return total price
     */
    public double getTotal() {
        return total;
    }

    /**
     * Getter method for list of items
     *
     * @return list if items within the order
     */
    public ArrayList<MenuItem> getItems() {
        return items;
    }

    /**
     * Calculates the tax of the items
     */
    public void calculateTax() {
        taxTotal = subtotal * TAX;
    }

    /**
     * Calculates the total of the items
     */
    public void calculateTotal() {
        total = taxTotal + subtotal;
    }

    /**
     * Adds items from the menu to the order container and adds to subtotal
     *
     * @param obj menu item to be added to the order
     * @return
     */
    @Override
    public boolean add(Object obj) {
        if (obj instanceof MenuItem) {
            items.add((Coffee) obj);
            subtotal = subtotal + ((MenuItem) obj).getItemPrice();
            return true;
        }
        return false;
    }

    /**
     * Removes items from the order container
     *
     * @param obj item to be removed from order
     * @return
     */
    @Override
    public boolean remove(Object obj) {
        if (obj instanceof MenuItem) {
            items.remove(obj);
            subtotal = subtotal - ((MenuItem) obj).getItemPrice();
            return true;
        }
        return false;
    }

    /**
     * Represents each order in a String form based off of the order numbers
     *
     * @return
     */
    @Override
    public String toString() {
        return Integer.toString(orderNumber);
    }
}
