package com.example.rucafe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;


/**
 * Class used to handle all user input and output within the Cart Activity
 *
 * @author David Ha, Andrew McAvoy
 */
public class Cart extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private Order order;
    private StoreOrders storeOrders;
    private TextView subtotal, salesTax, total;
    private ListView cart;
    private ArrayAdapter<MenuItem> list;

    /**
     * Code to be ran on creation
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(R.string.cart_menu);
        order = MainActivity.getOrder();
        storeOrders = MainActivity.getStoreOrder();
        subtotal = findViewById(R.id.subtotal);
        salesTax = findViewById(R.id.sales_tax);
        total = findViewById(R.id.total);
        cart = findViewById(R.id.cart);
        cart.setOnItemClickListener(this);
        list = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, order.getItems());
        cart.setAdapter(list);
        order.calculateTax();
        order.calculateTotal();
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        subtotal.setText(formatter.format(order.getSubtotal()));
        salesTax.setText(formatter.format(order.getTaxTotal()));
        total.setText(formatter.format(order.getTotal()));
    }

    /**
     * Places order and adds order to the list of store orders
     *
     * @param view
     */
    public void placeStoreOrder(View view){
        if(order.getItems().isEmpty()){
            Toast toast = Toast.makeText(getApplicationContext(), R.string.empty_order, Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        storeOrders.add(order);
        MainActivity.createOrder();
        order = MainActivity.getOrder();
        list = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, order.getItems());
        cart.setAdapter(list);
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        subtotal.setText(formatter.format(order.getSubtotal()));
        salesTax.setText(formatter.format(order.getTaxTotal()));
        total.setText(formatter.format(order.getTotal()));
        list.notifyDataSetChanged();
        Toast toast = Toast.makeText(getApplicationContext(), R.string.order_placed, Toast.LENGTH_SHORT);
        toast.show();
    }

    /**
     * Allows deletion of an item from the cart when an item is selected
     *
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        AlertDialog.Builder warning = new AlertDialog.Builder(Cart.this);
        warning.setTitle(R.string.remove_cart);
        warning.setMessage(R.string.remove_cart_warning);
        MenuItem item = order.getItems().get(position);
        warning.setNegativeButton(R.string.no, null);
        warning.setPositiveButton(R.string.yes, (dialog, which) -> {
            order.remove(item);
            order.calculateTax();
            order.calculateTotal();
            NumberFormat formatter = NumberFormat.getCurrencyInstance();
            subtotal.setText(formatter.format(order.getSubtotal()));
            salesTax.setText(formatter.format(order.getTaxTotal()));
            total.setText(formatter.format(order.getTotal()));
            list.notifyDataSetChanged();
        });
        warning.show();
    }

}
