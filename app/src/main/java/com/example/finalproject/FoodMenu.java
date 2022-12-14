package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FoodMenu extends AppCompatActivity {

    TextView userView;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_menu);
        userView = findViewById(R.id.textViewNameMenu);
        db = new DatabaseHelper(getApplicationContext());

        MenuRecyclerViewAdapter adapter;

        RecyclerView recyclerView = findViewById(R.id.menuRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        adapter = new MenuRecyclerViewAdapter(getApplicationContext(), db.getDataFood());
        recyclerView.setAdapter(adapter);

        User user = (User) getIntent().getSerializableExtra("user");

        userView.setText(db.getDataSpecific(user.getId()).getUsername());


    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.men, menu);

        return true;
    }

    // write a method to initiate an action when click the items on the menu bar


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
            case R.id.subItemViewMessages:
                Intent iMessages = new Intent(getApplicationContext(), viewMessagesUser.class);
                User userMessages = (User) getIntent().getSerializableExtra("user");
                iMessages.putExtra("user", userMessages);
                startActivity(iMessages);
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
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }
}