package com.example.rucafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * Class used to handle all user input and output within the Donut Order activity
 *
 * @author David Ha, Andrew McAvoy
 */
public class DonutOrder extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner donutFlavor;
    private Spinner donutQuantity;
    private TextView subtotal;
    private Button addCoffee;

    private Order order;

    final static double DONUT_PRICE = 1.39;

    /**
     * Code to be ran on create
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donut_order);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(R.string.donut_menu);
        Intent intent = getIntent();
        order = (Order) intent.getSerializableExtra("com.example.rucafe.MainActivity.ORDER");
        donutFlavor = findViewById(R.id.donutFlavor);
        donutQuantity = findViewById(R.id.donutQuantity);
        subtotal = findViewById(R.id.donutSubtotal);
        addCoffee = findViewById(R.id.addDonut);
        ArrayAdapter<CharSequence> flavors = ArrayAdapter.createFromResource(this, R.array.flavors, android.R.layout.simple_spinner_dropdown_item);
        donutFlavor.setAdapter(flavors);
        ArrayAdapter<CharSequence> quantity = ArrayAdapter.createFromResource(this, R.array.donut_quantity, android.R.layout.simple_spinner_dropdown_item);
        donutQuantity.setAdapter(quantity);
        donutFlavor.setSelection(0);
        donutQuantity.setSelection(0);
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        subtotal.setText(formatter.format(Integer.parseInt(donutQuantity.getSelectedItem().toString()) * DONUT_PRICE));
        donutQuantity.setOnItemSelectedListener(this);
    }

    /**
     * Adds donut(s) to the order
     *
     * @param view
     */
    public void addDonut(View view){
        Donut donut = new Donut(donutFlavor.getSelectedItem().toString(), Integer.parseInt(donutQuantity.getSelectedItem().toString()));
        donut.calculateItemPrice();
        order.add(donut);
        Toast toast = Toast.makeText(getApplicationContext(), R.string.donut_added, Toast.LENGTH_SHORT);
        toast.show();
    }

    /**
     * Changes the subtotal of the donuts whenever the quantity is changed
     *
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        subtotal.setText(formatter.format(Integer.parseInt(donutQuantity.getSelectedItem().toString()) * DONUT_PRICE));
    }

    /**
     * Handles the situation in case nothing is selected
     *
     * @param parent
     */
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //Note: item selection is set to the item in index 1 by default on creation
    }
}