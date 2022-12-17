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

public class AllAccounts extends AppCompatActivity {

    RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
        setContentView(R.layout.activity_all_accounts);


        RecyclerView recyclerView = findViewById(R.id.RecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerViewAdapter(this, db.getData());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menadmin, menu);

        DatabaseHelper db = new DatabaseHelper(getApplicationContext());


        getSupportActionBar().setTitle("admin:" + db.getDataAdmin().getUsername());

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {


            case R.id.subItemHome:
                Intent iHome = new Intent(getApplicationContext(), MainActivity.class);
                Admin adminHome = (Admin) getIntent().getSerializableExtra("admin");
                iHome.putExtra("admin", adminHome);
                iHome.putExtra("check", "admin");
                startActivity(iHome);


                return true;


            case R.id.subItemLogout:
                Intent iLogout = new Intent(getApplicationContext(), Login.class);
                startActivity(iLogout);
                return true;

            case R.id.subItemAddOrder:

                Intent iAddOrderAdmin = new Intent(getApplicationContext(), AddItemAdmin.class);
                Admin adminOrder = (Admin) getIntent().getSerializableExtra("admin");
                iAddOrderAdmin.putExtra("admin", adminOrder);
                iAddOrderAdmin.putExtra("check", "admin");
                startActivity(iAddOrderAdmin);
                return true;

            case R.id.subItemViewMessagesReceived:
                Intent iMessagesR = new Intent(getApplicationContext(), ViewMessagesAdmin.class);
                Admin adminMessagesR = (Admin) getIntent().getSerializableExtra("admin");
                iMessagesR.putExtra("admin", adminMessagesR);
                startActivity(iMessagesR);
                return true;

            case R.id.subItemViewOrders:
                Intent iViewOrders = new Intent(getApplicationContext(), ViewAllOrders.class);
                Admin adminViewOrders = (Admin) getIntent().getSerializableExtra("admin");
                iViewOrders.putExtra("admin", adminViewOrders);
                iViewOrders.putExtra("check", "admin");
                startActivity(iViewOrders);
                return true;

            case R.id.subItemEditMenu:

                Intent iEditMenu = new Intent(getApplicationContext(), EditItemAdmin.class);
                Admin adminEditMenu = (Admin) getIntent().getSerializableExtra("admin");
                iEditMenu.putExtra("admin", adminEditMenu);
                iEditMenu.putExtra("check", "admin");
                startActivity(iEditMenu);
                return true;


            case R.id.subItemAccountsAdmin:

                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }
}