package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class Options extends AppCompatActivity {

    Switch musicSwitch, languageSwitch, themeSwitch;
    TextView titleText;
    public static MediaPlayer mediaPlayer = new MediaPlayer();
    ImageView image;

    public static boolean isChecked = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        musicSwitch = findViewById(R.id.switchMusic);
        languageSwitch = findViewById(R.id.switchLanguage);
        themeSwitch = findViewById(R.id.switchTheme);
        image = findViewById(R.id.imageViewShark2);
        titleText = findViewById(R.id.textViewTitleRegister);

        ConstraintLayout layout = (ConstraintLayout)findViewById(R.id.OptionsLayout);
        User user = (User) getIntent().getSerializableExtra("user");


            if (!mediaPlayer.isPlaying()) {
                mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.music);
            }

        musicSwitch.setChecked(isChecked);
        musicSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isChecked){
                    Toast.makeText(getApplicationContext(), "Music Stopped", Toast.LENGTH_SHORT).show();

                    mediaPlayer.pause();
                    isChecked = false;
                }
                else{

                    Toast.makeText(getApplicationContext(), "Music Started", Toast.LENGTH_SHORT).show();
                    isChecked = true;
                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.music);

                    mediaPlayer.start();

                }
            }
        });
        languageSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isChecked){
                    Toast.makeText(getApplicationContext(), "Switched to English", Toast.LENGTH_SHORT).show();
                    languageSwitch.setText("Change Language");
                    musicSwitch.setText("Music Player");
                    themeSwitch.setText("Change Themes");

                    isChecked = false;
                }
                else{

                    Toast.makeText(getApplicationContext(), "changer en français", Toast.LENGTH_SHORT).show();
                    isChecked = true;
                    languageSwitch.setText("Changer de Langue");
                    musicSwitch.setText("Lecteur de Musique");
                    themeSwitch.setText("Changer de Thème");

                }
            }
        });

        themeSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isChecked){
                    Toast.makeText(getApplicationContext(), "Theme Changed!", Toast.LENGTH_SHORT).show();
                    layout.setBackgroundResource(R.color.teal_700);

                    image.setImageResource(R.drawable.funny);
                   titleText.setTypeface(Typeface.SANS_SERIF, Typeface.NORMAL);
                    isChecked = false;
                }
                else{

                    Toast.makeText(getApplicationContext(), "Theme Changed!", Toast.LENGTH_SHORT).show();
                    layout.setBackgroundResource(R.color.purple_200);
                    image.setImageResource(R.drawable.anchor);

                    titleText.setTypeface(Typeface.SANS_SERIF, Typeface.ITALIC);
                    isChecked = true;

                }
            }
        });




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

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
                iHome.putExtra("music", isChecked);
                startActivity(iHome);
                return true;

            case R.id.subItemOrder:
                Intent iOrder = new Intent(getApplicationContext(), OrderNow.class);
                User userOrder = (User) getIntent().getSerializableExtra("user");
                iOrder.putExtra("user", userOrder);
                iOrder.putExtra("music", isChecked);
                startActivity(iOrder);
                return true;

            case R.id.orderHistory:
                Intent iOrderHistory = new Intent(getApplicationContext(), OrderHistory.class);
                User userOrderHistory = (User) getIntent().getSerializableExtra("user");
                iOrderHistory.putExtra("user", userOrderHistory);
                iOrderHistory.putExtra("music", isChecked);
                startActivity(iOrderHistory);
                return true;

            case R.id.subItemMenu:
                Intent iMenu = new Intent(getApplicationContext(), FoodMenu.class);
                User userMenu = (User) getIntent().getSerializableExtra("user");
                iMenu.putExtra("user", userMenu);
                iMenu.putExtra("music", isChecked);
                startActivity(iMenu);
                return true;

            case R.id.subItemLocations:

                Intent iLocation = new Intent(getApplicationContext(), Location.class);
                User userLocation = (User) getIntent().getSerializableExtra("user");
                iLocation.putExtra("user", userLocation);
                iLocation.putExtra("music", isChecked);
                startActivity(iLocation);
                return true;

            case R.id.subItemContact:
                Intent iContact = new Intent(getApplicationContext(), Contact.class);
                User userContact = (User) getIntent().getSerializableExtra("user");
                iContact.putExtra("user", userContact);
                iContact.putExtra("music", isChecked);
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
                iAccount.putExtra("music", isChecked);

                startActivity(iAccount);
                return true;

            case R.id.subItemOptions:

                return true;

            case R.id.subItemLogout:
                Intent iLogout = new Intent(getApplicationContext(), Login.class);
                startActivity(iLogout);

                mediaPlayer.pause();
                return true;


            default:
                return super.onOptionsItemSelected(item);

        }
    }
}