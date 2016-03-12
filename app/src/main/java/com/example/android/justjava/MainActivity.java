package com.example.android.justjava;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    int quantity = 0;

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        String temChantily;
        String temCocolate;

        EditText nameField = (EditText) findViewById(R.id.name_field);
        String nameClient = nameField.getText().toString();

        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWippedCream = whippedCreamCheckBox.isChecked();

        if (hasWippedCream) {
            temChantily = "SIM";
        } else {
            temChantily = "NÃO";
        }

        CheckBox whippedChocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_chocolate_checkbox);
        boolean hasWippedChocolate = whippedChocolateCheckBox.isChecked();

        if (hasWippedChocolate) {
            temCocolate = "SIM";
        } else {
            temCocolate = "NÃO";
        }

        //    Log.v("MainActivity", "tem chantily: " + hasWippedCream);
        int price = calculatePrice(hasWippedCream, hasWippedChocolate);

        String priceMessage = createOrderSummary(nameClient, price, temChantily, temCocolate);
        displayMessage(priceMessage);

    }

    /**
     * Calculates the price of the order.
     *
     * @param hasWippedCream     is whether or not the user wants whipped  cream topping
     * @param hasWippedChocolate is whether or not the user wants whipped  cream topping
     */
    private int calculatePrice(boolean addWippedCream, boolean addWippedChocolate) {
        int basePrice = 5;

        if (addWippedCream) {
            basePrice = basePrice + 1;
        }

        if (addWippedChocolate) {
            basePrice = basePrice + 2;
        }

        return (quantity * basePrice);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
        priceTextView.setText(message);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int quantity) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + quantity);
    }

    public void increment(View view) {
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    public void decrement(View view) {
        quantity = quantity - 1;
        displayQuantity(quantity);
    }

    private String createOrderSummary(String name, int price, String addWippedCream, String addWippedChocolate) {
        String priceMessage = "Nome: " + name;
        priceMessage += "\n\nAcrescenta chantily?....." + addWippedCream;
        priceMessage += "\nAcrescenta chocolate?.." + addWippedChocolate;
        priceMessage += "\n\nQuantidade      :    " + quantity;
        priceMessage += "\nValor Total      :   " + price;
        priceMessage += "\n\nOBRIGADO PELA PREFERENCIA";
        return priceMessage;
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.android.justjava/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.android.justjava/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}

