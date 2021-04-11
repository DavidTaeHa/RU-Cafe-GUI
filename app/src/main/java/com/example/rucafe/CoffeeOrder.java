package com.example.rucafe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class CoffeeOrder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee_order);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(R.string.coffee_menu);
    }
}