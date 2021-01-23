/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.justjava;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    int Quantity=0;
    String Name , priceMessage;
    //boolean wc;

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whippedCreamCheckBox);
        boolean haswhippedCream = whippedCreamCheckBox .isChecked();
        CheckBox Chocolate = (CheckBox) findViewById(R.id.chocolate);
        boolean haschocolate = Chocolate .isChecked();
        EditText name = (EditText) findViewById(R.id.name);
        Name = name.getText().toString();

        int w=0,c=0;
       priceMessage="Hiii " + Name + "\n\nNo of Coffees: " + Quantity + "\n" ;
      if(haswhippedCream==true)
      {
          priceMessage += "With Vanilla Topping \n";
          w= Quantity*1;
      }
        if(haschocolate==true)
        {
            priceMessage += "With Chocolate Topping \n";
            c= Quantity*2;
        }
      priceMessage += "Total price is Rs: " + (Quantity*5 + w + c)+ "\n\nTHANK YOU !!" ;
      displayMessage(priceMessage);
    }

    public void Confirm (View view) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, "JUST JAVA order for" + Name);
        intent.putExtra(Intent.EXTRA_SUBJECT, "Orderd Coffee");
        intent.putExtra(Intent.EXTRA_TEXT , priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }


    //increment func
    public void increment(View View)
    {
        Quantity= Quantity+1;
        display(Quantity);

    }

    //decrement func
    public void decremnt(View View)
    {
        if (Quantity>1)
        {
            Quantity = Quantity-1;
            display(Quantity);
            
        }
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }
    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }
}