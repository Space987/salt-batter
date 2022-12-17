package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.MenuItemCompat;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.style.ImageSpan;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    TextView titleText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        titleText = findViewById(R.id.textViewAdmin);

        if(getIntent().getStringExtra("check").equals("user")){
            User user = (User) getIntent().getSerializableExtra("user");
            titleText.setText("Welcome to the Home Page!");
        }
        if(getIntent().getStringExtra("check").equals("admin")){
            Admin admin = (Admin) getIntent().getSerializableExtra("admin");
            titleText.setText("Welcome to the Admin Home Page!");
        }

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflater is the class that converts xml to java object
        //this inflater converts the menu resource file to java object and deploy on main activity



        if(getIntent().getStringExtra("check").equals("user")){
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.men, menu);

            DatabaseHelper db = new DatabaseHelper(getApplicationContext());
            User user = (User) getIntent().getSerializableExtra("user");

            getSupportActionBar().setTitle("user: " +db.getDataSpecific(user.getId()).getUsername());

            Bitmap bmp = BitmapFactory.decodeByteArray(db.getUserFoodImage(user.getId()), 0, db.getUserFoodImage(user.getId()).length);
            Drawable draw = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bmp, 300, 300, true));

            getSupportActionBar().setHomeAsUpIndicator(draw);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        }
        if(getIntent().getStringExtra("check").equals("admin")){
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menadmin, menu);
            DatabaseHelper db = new DatabaseHelper(getApplicationContext());

            getSupportActionBar().setTitle("admin: " + db.getDataAdmin().getUsername());

            return true;
        }


        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {



            case R.id.subItemHome:

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

            case R.id.subItemViewMessages:
                Intent iMessages = new Intent(getApplicationContext(), viewMessagesUser.class);
                User userMessages = (User) getIntent().getSerializableExtra("user");
                iMessages.putExtra("user", userMessages);
                startActivity(iMessages);
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
                iOptions.putExtra("music", getIntent().getBooleanExtra("music", false));
                startActivity(iOptions);
                return true;

            case R.id.subItemLogout:
                Intent iLogout = new Intent(getApplicationContext(), Login.class);
                startActivity(iLogout);
                Options.mediaPlayer.pause();
                return true;

            /////////////ADMIN////////////////////////////////

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