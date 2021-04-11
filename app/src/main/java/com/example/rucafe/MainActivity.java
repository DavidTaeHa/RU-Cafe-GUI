package com.example.rucafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

/**
 * Class used to handle all events within the main activity
 *
 * @author David Ha, Andrew McAvoy
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Opens the menu to order donuts
     *
     * @param view
     */
    public void openDonutMenu(View view) {
        Intent intent = new Intent(this, DonutOrder.class);
        startActivity(intent);
    }

    /**
     * Opens the menu to order coffee
     *
     * @param view
     */
    public void openCoffeeMenu(View view) {
        Intent intent = new Intent(this, CoffeeOrder.class);
        startActivity(intent);
    }

    /**
     * Opens the menu to view the current items in the cart
     *
     * @param view
     */
    public void openCart(View view) {
        Intent intent = new Intent(this, Cart.class);
        startActivity(intent);
    }

    /**
     * Opens the menu to view the list of store orders
     *
     * @param view
     */
    public void openOrders(View view) {
        Intent intent = new Intent(this, Database.class);
        startActivity(intent);
    }

}