package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.function.BooleanSupplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import kotlin.text.Regex;

public class Contact extends AppCompatActivity {

    TextView userText;
    Button sendBtn;
    EditText emailText, messageText;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        emailText = findViewById(R.id.editTextEmail);
        messageText = findViewById(R.id.editTextMessage);
        sendBtn = findViewById(R.id.buttonSend);
        userText = findViewById(R.id.textViewName);
        db = new DatabaseHelper(getApplicationContext());

        User user = (User) getIntent().getSerializableExtra("user");
        userText.setText(user.getUsername());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("My Notification",
                    "My Notification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        sendBtn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick (View view){
                    addNotification();
                }

                private void addNotification () {
                    // call the addNotification builder class to build the notification messages
                    NotificationCompat.Builder builder =
                            new NotificationCompat.Builder(getApplicationContext(), "My Notification");
                    builder.setContentTitle("Salt and Batter");

                    Pattern myPattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
                    Matcher match = myPattern.matcher(emailText.getText().toString());
                    if(match.find()) {

                        builder.setContentText("Your message was sent!");
                        db.createMessage(user.getId(), messageText.getText().toString(), emailText.getText().toString());
                        Intent iHome = new Intent(getApplicationContext(), MainActivity.class);
                        User userHome = (User) getIntent().getSerializableExtra("user");
                        iHome.putExtra("user", userHome);
                        iHome.putExtra("check", "user");
                        startActivity(iHome);
                    }

                    else{
                        builder.setContentText("Your message was not sent! \n Please enter valid email!");
                    }
                    builder.setSmallIcon(R.drawable.funny);
                    builder.setAutoCancel(true);

                    Intent notificationIntent = new Intent(getApplicationContext(), Notification.class);
                    PendingIntent contentIntent = PendingIntent.getActivity(getApplicationContext(), 0,
                            notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                    builder.setContentIntent(contentIntent);

                    // Call the manager to add the message as Notification.
                    NotificationManager manager = (NotificationManager)
                            getSystemService(Context.NOTIFICATION_SERVICE);
                    manager.notify(0, builder.build());
                }

        });


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
                Intent iMenu = new Intent(getApplicationContext(), FoodMenu.class);
                User userMenu= (User) getIntent().getSerializableExtra("user");
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
                Options.mediaPlayer.pause();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }
}