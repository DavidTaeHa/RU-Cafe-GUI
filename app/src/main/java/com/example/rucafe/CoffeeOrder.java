package com.example.rucafe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.NumberFormat;

public class CoffeeOrder extends AppCompatActivity implements AdapterView.OnItemSelectedListener, CompoundButton.OnCheckedChangeListener {

    private Spinner size, coffeeQuantity;
    private CheckBox cream, syrup, milk, caramel, whipped;
    private TextView subtotal;

    private Order order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee_order);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(R.string.coffee_menu);
        order = MainActivity.getOrder();
        size = findViewById(R.id.coffee_size);
        cream = findViewById(R.id.cream);
        syrup = findViewById(R.id.syrup);
        milk = findViewById(R.id.milk);
        caramel = findViewById(R.id.whipped);
        whipped = findViewById(R.id.whipped);
        subtotal = findViewById(R.id.coffee_subtotal);
        ArrayAdapter<CharSequence> sizeList = ArrayAdapter.createFromResource(this, R.array.coffee_sizes, android.R.layout.simple_spinner_dropdown_item);
        size.setAdapter(sizeList);
        size.setSelection(0);
        ArrayAdapter<CharSequence> quantity = ArrayAdapter.createFromResource(this, R.array.quantity, android.R.layout.simple_spinner_dropdown_item);
        coffeeQuantity.setAdapter(quantity);
        coffeeQuantity.setSelection(0);
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        subtotal.setText(formatter.format(Integer.parseInt(coffeeQuantity.getSelectedItem().toString()) * Coffee.SHORT_PRICE));
        size.setOnItemSelectedListener(this);
        coffeeQuantity.setOnItemSelectedListener(this);
        cream.setOnCheckedChangeListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        switch(size.getSelectedItem().toString()){
            case "Short":
                subtotal.setText(formatter.format(Integer.parseInt(coffeeQuantity.getSelectedItem().toString()) * Coffee.SHORT_PRICE));
                break;
            case "Tall":
                subtotal.setText(formatter.format(Integer.parseInt(coffeeQuantity.getSelectedItem().toString()) * Coffee.TALL_PRICE));
                break;
            case "Grande":
                subtotal.setText(formatter.format(Integer.parseInt(coffeeQuantity.getSelectedItem().toString()) * Coffee.GRANDE_PRICE));
                break;
            case "Venti":
                subtotal.setText(formatter.format(Integer.parseInt(coffeeQuantity.getSelectedItem().toString()) * Coffee.VENTI_PRICE));
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //Note: item selection is set to the item in index 1 by default on creation
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        
    }
}