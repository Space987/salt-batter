package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Cart extends AppCompatActivity {

    Button confirmBtn, backBtn;
    TextView userText, costText;
    ListView lv;
    DatabaseHelper db;
    int currentTotal = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_page);

        backBtn = findViewById(R.id.backButton);
        db = new DatabaseHelper(this);
        lv = findViewById(R.id.cartListView);
        confirmBtn = findViewById(R.id.ConfirmOrderBtn);
        userText = findViewById(R.id.textViewName);
        costText = findViewById(R.id.textViewCostValue);

        User user = (User) getIntent().getSerializableExtra("user");

        userText.setText(db.getDataSpecific(user.getId()).getUsername());
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());

        CartListAdapter cartListAdapter = new CartListAdapter(Cart.this, db.getOrder(user.getId()), costText);
        lv.setAdapter(cartListAdapter);

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.addTotal(user.getId(), Integer.parseInt(costText.getText().toString()));


                    boolean checkIncrement = false;
                    boolean checkIncrementEmpty = false;
                    for(OrderFood order : db.getOrder((user.getId()))) {

                        if(db.getOrderHistory(user.getId()).isEmpty()){
                            checkIncrementEmpty = true;
                            db.createOrderHistory(user.getId(), order.getFood_id(), order.getName(), order.getCost(), order.getQuantity(), 1);
                            db.deleteOrder(order.getId());
                            Toast.makeText(getApplicationContext(), "Your Order was Placed!", Toast.LENGTH_SHORT).show();
                            Intent iHome = new Intent(getApplicationContext(), MainActivity.class);
                            User userHome = (User) getIntent().getSerializableExtra("user");
                            iHome.putExtra("user", userHome);
                            iHome.putExtra("check", "user");
                            startActivity(iHome);
                        }
                        else{
                            if(!checkIncrementEmpty && !checkIncrement) {

                                Log.e("myTag", String.valueOf(checkIncrement + String.valueOf(db.getOrderHistory(user.getId()).get(db.getOrderHistory(user.getId()).size() - 1).getOrder_group())));
                                db.createOrderHistory(user.getId(), order.getFood_id(), order.getName(), order.getCost(), order.getQuantity(), db.getOrderHistory(user.getId()).get(db.getOrderHistory(user.getId()).size() - 1).getOrder_group() + 1);
                                db.deleteOrder(order.getId());
                                Toast.makeText(getApplicationContext(), "Your Order was Placed!", Toast.LENGTH_SHORT).show();
                                checkIncrement = true;
                                Intent iHome = new Intent(getApplicationContext(), MainActivity.class);
                                User userHome = (User) getIntent().getSerializableExtra("user");
                                iHome.putExtra("user", userHome);
                                iHome.putExtra("check", "user");
                                startActivity(iHome);

                            }
                            else{
                                Log.e("myTag", String.valueOf(checkIncrement + String.valueOf(db.getOrderHistory(user.getId()).get(db.getOrderHistory(user.getId()).size() - 1).getOrder_group())));
                                db.createOrderHistory(user.getId(), order.getFood_id(), order.getName(), order.getCost(), order.getQuantity(), db.getOrderHistory(user.getId()).get(db.getOrderHistory(user.getId()).size() - 1).getOrder_group());
                                db.deleteOrder(order.getId());
                                Toast.makeText(getApplicationContext(), "Your Order was Placed!", Toast.LENGTH_SHORT).show();

                                Intent iHome = new Intent(getApplicationContext(), MainActivity.class);
                                User userHome = (User) getIntent().getSerializableExtra("user");
                                iHome.putExtra("user", userHome);
                                iHome.putExtra("check", "user");
                                startActivity(iHome);
                            }
                        }

                    }


            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iOrder = new Intent(getApplicationContext(), OrderNow.class);
                User userOrder = (User) getIntent().getSerializableExtra("user");
                iOrder.putExtra("user", userOrder);
                iOrder.putExtra("check", "user");
                iOrder.putExtra("music", getIntent().getBooleanExtra("music", false));
                startActivity(iOrder);
            }
        });


        for(OrderFood order : db.getOrder((user.getId()))){

                currentTotal =  currentTotal + (order.getCost() * order.getQuantity());
        }

                costText.setText(String.valueOf(currentTotal));


        userText.setText(user.getUsername());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.men, menu);

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.subItemHome:
                Intent iHome = new Intent(getApplicationContext(), MainActivity.class);
                User userHome = (User) getIntent().getSerializableExtra("user");
                iHome.putExtra("user", userHome);
                iHome.putExtra("check", "user");
                startActivity(iHome);
                return true;

            case R.id.subItemOrder:
                Intent iOrder = new Intent(getApplicationContext(), OrderNow.class);
                User userOrder = (User) getIntent().getSerializableExtra("user");
                iOrder.putExtra("user", userOrder);
                startActivity(iOrder);
                return true;

            case R.id.orderHistory:
                Intent iOrderHistory = new Intent(getApplicationContext(), OrderHistory.class);
                User userOrderHistory = (User) getIntent().getSerializableExtra("user");
                iOrderHistory.putExtra("user", userOrderHistory);
                startActivity(iOrderHistory);
                return true;

            case R.id.subItemMenu:
                Intent iMenu = new Intent(getApplicationContext(), FoodMenu.class);
                User userMenu = (User) getIntent().getSerializableExtra("user");
                iMenu.putExtra("user", userMenu);
                startActivity(iMenu);
                return true;

            case R.id.subItemLocations:

                Intent iLocation = new Intent(getApplicationContext(), Location.class);
                User userLocation = (User) getIntent().getSerializableExtra("user");
                iLocation.putExtra("user", userLocation);
                startActivity(iLocation);
                return true;

            case R.id.subItemContact:
                Intent iContact = new Intent(getApplicationContext(), Contact.class);
                User userContact = (User) getIntent().getSerializableExtra("user");
                iContact.putExtra("user", userContact);
                startActivity(iContact);
                return true;

            case R.id.subItemAccount:
                Intent iAccount = new Intent(getApplicationContext(), EditUser.class);
                User userAccount = (User) getIntent().getSerializableExtra("user");
                iAccount.putExtra("user", userAccount);
                startActivity(iAccount);
                return true;

            case R.id.subItemOptions:
                Intent iOptions = new Intent(getApplicationContext(), Options.class);
                User userOptions = (User) getIntent().getSerializableExtra("user");
                iOptions.putExtra("user", userOptions);
                startActivity(iOptions);

                return true;

            case R.id.subItemLogout:
                Intent iLogout = new Intent(getApplicationContext(), Login.class);
                startActivity(iLogout);
                Options.mediaPlayer.pause();
                return true;


            default:
                return super.onOptionsItemSelected(item);

        }
    }

}