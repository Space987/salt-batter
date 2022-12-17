package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

public class EditItemAdmin extends AppCompatActivity {

    ListView lv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item_admin);
        lv = findViewById(R.id.listViewEditItem);

        DatabaseHelper db = new DatabaseHelper(getApplicationContext());

        EditItemListAdapter editItemListAdapter = new EditItemListAdapter(getApplicationContext(), db.getDataFood());
        lv.setAdapter(editItemListAdapter);
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

            case R.id.subItemEditMenu:



            case R.id.subItemViewOrders:
                Intent iViewOrders = new Intent(getApplicationContext(), ViewAllOrders.class);
                Admin adminViewOrders = (Admin) getIntent().getSerializableExtra("admin");
                iViewOrders.putExtra("admin", adminViewOrders);
                iViewOrders.putExtra("check", "admin");
                startActivity(iViewOrders);
                return true;


            case R.id.subItemAccountsAdmin:
                Intent iAllAccounts = new Intent(getApplicationContext(), AllAccounts.class);
                Admin adminAllAccounts = (Admin) getIntent().getSerializableExtra("admin");
                iAllAccounts.putExtra("admin", adminAllAccounts);
                iAllAccounts.putExtra("check", "admin");
                startActivity(iAllAccounts);
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }
}