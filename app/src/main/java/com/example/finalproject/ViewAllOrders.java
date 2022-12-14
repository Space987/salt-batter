package com.example.finalproject;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;


public class ViewAllOrders extends AppCompatActivity {

    ListView lv;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_view_all_orders);
        lv = findViewById(R.id.viewAllOrdersListView);
        db = new DatabaseHelper(getApplicationContext());
        viewOrdersListAdapter viewOrdersListAdapters = new viewOrdersListAdapter(getApplicationContext(), db.getOrderHistoryAll());
        lv.setAdapter(viewOrdersListAdapters);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflater is the class that converts xml to java object
        //this inflater converts the menu resource file to java object and deploy on main activity

            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menadmin, menu);


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

            case R.id.subItemAddOrder:

                Intent iAddOrderAdmin = new Intent(getApplicationContext(), AddItemAdmin.class);
                Admin adminOrder = (Admin) getIntent().getSerializableExtra("admin");
                iAddOrderAdmin.putExtra("admin", adminOrder);
                iAddOrderAdmin.putExtra("check", "admin");
                startActivity(iAddOrderAdmin);
                return true;

            case R.id.subItemEditMenu:

                Intent iEditMenu = new Intent(getApplicationContext(), EditItemAdmin.class);
                Admin adminEditMenu = (Admin) getIntent().getSerializableExtra("admin");
                iEditMenu.putExtra("admin", adminEditMenu);
                iEditMenu.putExtra("check", "admin");
                startActivity(iEditMenu);
                return true;

            case R.id.subItemViewOrders:

                return true;

            case R.id.subItemViewMessagesReceived:
                Intent iMessagesR = new Intent(getApplicationContext(), ViewMessagesAdmin.class);
                Admin adminMessagesR = (Admin) getIntent().getSerializableExtra("admin");
                iMessagesR.putExtra("admin", adminMessagesR);
                startActivity(iMessagesR);
                return true;

            case R.id.subItemAccountsAdmin:
                Intent iAllAccounts = new Intent(getApplicationContext(), AllAccounts.class);
                Admin adminAllAccounts = (Admin) getIntent().getSerializableExtra("admin");
                iAllAccounts.putExtra("admin", adminAllAccounts);
                iAllAccounts.putExtra("check", "admin");
                startActivity(iAllAccounts);
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