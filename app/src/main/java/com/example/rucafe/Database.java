package com.example.rucafe;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Class used to handle all user inputs and outputs within the database activity
 */
public class Database extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView orderList;
    StoreOrders storeOrders;
    ArrayAdapter<Order> list;

    /**
     * Code to be ran on creation
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(R.string.database_menu);
        orderList = findViewById(R.id.pastOrders);
        storeOrders = MainActivity.getStoreOrder();
        list = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, storeOrders.getStoreOrders());
        orderList.setAdapter(list);
        orderList.setOnItemClickListener(this);
    }

    /**
     * Allows cancellation of an order from the database when an order is selected
     *
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        AlertDialog.Builder warning = new AlertDialog.Builder(Database.this);
        warning.setTitle(R.string.cancel_order);
        warning.setMessage(R.string.cancel_order_warning);
        Order item = storeOrders.getStoreOrders().get(position);
        warning.setNegativeButton(R.string.no, null);
        warning.setPositiveButton(R.string.yes, (dialog, which) -> {
            storeOrders.remove(item);
            list.notifyDataSetChanged();
        });
        warning.show();
    }
}