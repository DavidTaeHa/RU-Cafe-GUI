package com.example.rucafe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Database extends AppCompatActivity {

    ListView orderList;
    StoreOrders storeOrders;
    ArrayAdapter<Order> list;

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
    }
}