package com.example.rucafe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * Class used to handle all user input and output within the coffee activity
 *
 * @author David Ha, Andrew McAvoy
 */
public class CoffeeOrder extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner size, coffeeQuantity;
    private CheckBox cream, syrup, milk, caramel, whipped;
    private TextView subtotal;

    private Order order;
    private Coffee coffee;

    final static int NONE = 0;
    final static int INVALID = -1;

    /**
     * Code to be ran on creation
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee_order);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(R.string.coffee_menu);
        order = MainActivity.getOrder();
        size = findViewById(R.id.coffee_size);
        coffeeQuantity = findViewById(R.id.coffee_quantity);
        cream = findViewById(R.id.cream);
        syrup = findViewById(R.id.syrup);
        milk = findViewById(R.id.milk);
        caramel = findViewById(R.id.caramel);
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
        coffee = new Coffee(NONE, INVALID);
        coffee.setQuantity(Integer.parseInt(coffeeQuantity.getSelectedItem().toString()));
    }

    /**
     * Adds coffee to the cart
     *
     * @param view
     */
    public void addCoffee(View view){
        order.add(coffee);
        Coffee temp = coffee;
        coffee = new Coffee(Integer.parseInt(coffeeQuantity.getSelectedItem().toString()), temp.getSize());
        coffee.calculateItemPrice();
        cream.setSelected(false);
        syrup.setSelected(false);
        milk.setSelected(false);
        caramel.setSelected(false);
        whipped.setSelected(false);
        Toast toast = Toast.makeText(getApplicationContext(), R.string.coffee_added, Toast.LENGTH_SHORT);
        toast.show();
    }

    /**
     * Changes the subtotal of the coffee based off of quantity and size
     *
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        coffee.setQuantity(Integer.parseInt(coffeeQuantity.getSelectedItem().toString()));
        switch(size.getSelectedItem().toString()){
            case "Short":
                coffee.setSize(Coffee.SHORT);
                break;
            case "Tall":
                coffee.setSize(Coffee.TALL);
                break;
            case "Grande":
                coffee.setSize(Coffee.GRANDE);
                break;
            case "Venti":
                coffee.setSize(Coffee.VENTI);
                break;
        }
        coffee.calculateItemPrice();
        subtotal.setText(formatter.format(coffee.getItemPrice()));
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

    /**
     * Adds or removes addins to or from the coffee
     *
     * @param view
     */
    public void checked(View view) {
        if(cream.isChecked()){
            coffee.add(getString(R.string.cream));
        }
        else if(!cream.isChecked()){
            coffee.remove(getString(R.string.cream));
        }
        if(syrup.isChecked()){
            coffee.add(getString(R.string.syrup));
        }
        else if(!syrup.isChecked()){
            coffee.remove(getString(R.string.syrup));
        }
        if(milk.isChecked()){
            coffee.add(getString(R.string.milk));
        }
        else if(!milk.isChecked()){
            coffee.remove(getString(R.string.milk));
        }
        if(caramel.isChecked()){
            coffee.add(getString(R.string.caramel));
        }
        else if(!caramel.isChecked()){
            coffee.remove(getString(R.string.caramel));
        }
        if(whipped.isChecked()){
            coffee.add(getString(R.string.whipped));
        }
        else if(!whipped.isChecked()){
            coffee.remove(getString(R.string.whipped));
        }
        coffee.calculateItemPrice();
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        subtotal.setText(formatter.format(coffee.getItemPrice()));
    }

}