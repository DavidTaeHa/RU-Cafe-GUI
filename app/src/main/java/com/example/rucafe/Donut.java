package com.example.rucafe;

import java.text.NumberFormat;

/**
 * This class extends the MenuItem class and contains common attributes and operations specific to an
 * instance of a donut
 *
 * @author David Ha, Andrew McAvoy
 */
public class Donut extends MenuItem {

    final static double DONUT_PRICE = 1.39;

    /**
     * Constructor for Donut class
     *
     * @param name name of the donut
     * @param quantity number of a donut
     */
    public Donut(String name, int quantity) {
        super(name, quantity);
    }

    /**
     * Calculates the price of a donut
     */
    @Override
    public void calculateItemPrice() {
        super.setItemPrice(DONUT_PRICE * getQuantity());
    }

    /**
     * Prints instance of this class in the following form
     * name(quantity) - donutType
     *
     * @return textual representation of an instance of a donut class
     */
    public String toString() {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        return super.getName() + " Donut (" + super.getQuantity() + ") " + formatter.format(super.getItemPrice());
    }
}
