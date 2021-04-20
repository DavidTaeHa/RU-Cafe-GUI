package com.example.rucafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageButton;

/**
 * Class used to handle all events within the main activity
 *
 * @author David Ha, Andrew McAvoy
 */
public class MainActivity extends AppCompatActivity {

    private static Order order;
    private static StoreOrders storeOrders;

    public static final String ORDER = "com.example.rucafe.MainActivity.ORDER";
    public static final String STORE_ORDER = "com.example.rucafe.MainActivity.STORE_ORDER";

    /**
     * Code to be ran when the program is first opened
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(order == null){
            order = new Order();
        }
        if(storeOrders == null){
            storeOrders = new StoreOrders();
        }
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

    /**
     * Getter method for the order
     *
     * @return
     */
    public static Order getOrder(){
        return order;
    }

    /**
     * Getter method for the store orders
     *
     * @return
     */
    public static StoreOrders getStoreOrder(){
        return storeOrders;
    }

    public static void createOrder() {
        MainActivity.order = new Order();
    }
}