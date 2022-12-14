package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.OptionalInt;

public class AddItemAdmin extends AppCompatActivity {

    DatabaseHelper db;
    EditText editTextName, editTextPrice, editTextQuantity, editTextDescription;
    Button addBtn, selectImageBtn;
    ImageView imageView;

    // constant to compare
    // the activity result code
    int SELECT_PICTURE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item_admin);
        editTextName = findViewById(R.id.editTextName);
        editTextDescription = findViewById(R.id.editTextDescription);
        editTextPrice = findViewById(R.id.editTextPrice);
        editTextQuantity = findViewById(R.id.editTextQuantity);
        addBtn = findViewById(R.id.addItemBtn);
        selectImageBtn = findViewById(R.id.buttonSelectImage);
        imageView = findViewById(R.id.imageViewSelectImage);
        db = new DatabaseHelper(getApplicationContext());



        selectImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageChooser();
                Toast.makeText(getApplicationContext(), "Image Selected!", Toast.LENGTH_SHORT).show();

            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextName.getText().toString().matches("") ||editTextDescription.getText().toString().matches("") ||editTextPrice.getText().toString().matches("") ||(editTextQuantity.getText().toString().matches(""))) {
                    Toast.makeText(getApplicationContext(), "Values cannot be empty", Toast.LENGTH_SHORT).show();
                }

                else {
                    imageView.buildDrawingCache();

                    Bitmap bmap = imageView.getDrawingCache();
                    byte[] imgData = getBitmapAsByteArray(bmap);
                    db.addFoodAdmin(editTextName.getText().toString(), editTextDescription.getText().toString(), Integer.parseInt(editTextPrice.getText().toString()), Integer.parseInt(editTextQuantity.getText().toString()), imgData);
                    Toast.makeText(getApplicationContext(), editTextName.getText().toString() + " Added!", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    i.putExtra("check", "admin");
                    i.putExtra("admin", db.getDataAdmin());
                    startActivity(i);
                }
            }
        });

    }


    // this function is triggered when
    // the Select Image Button is clicked
    void imageChooser() {

        // create an instance of the
        // intent of the type image
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        // pass the constant to compare it
        // with the returned requestCode
        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
    }

    // this function is triggered when user
    // selects the image from the imageChooser
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode == SELECT_PICTURE) {
                // Get the url of the image from data
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    // update the preview image in the layout
                    imageView.setImageURI(selectedImageUri);
                }
            }
        }
    }

    public static byte[] getBitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

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


            case R.id.subItemLogout:
                Intent iLogout = new Intent(getApplicationContext(), Login.class);
                startActivity(iLogout);
                return true;

            case R.id.subItemAddOrder:

                return true;

            case R.id.subItemViewMessagesReceived:
                Intent iMessagesR = new Intent(getApplicationContext(), ViewMessagesAdmin.class);
                Admin adminMessagesR = (Admin) getIntent().getSerializableExtra("admin");
                iMessagesR.putExtra("admin", adminMessagesR);
                startActivity(iMessagesR);
                return true;

            case R.id.subItemEditMenu:
                Intent iEditMenu = new Intent(getApplicationContext(), EditItemAdmin.class);
                Admin adminEditMenu = (Admin) getIntent().getSerializableExtra("admin");
                iEditMenu.putExtra("admin", adminEditMenu);
                iEditMenu.putExtra("check", "admin");
                startActivity(iEditMenu);
                return true;


            case R.id.subItemAccountsAdmin:
                Intent iAllAccounts = new Intent(getApplicationContext(), AllAccounts.class);
                Admin adminAllAccounts = (Admin) getIntent().getSerializableExtra("admin");
                iAllAccounts.putExtra("admin", adminAllAccounts);
                iAllAccounts.putExtra("check", "admin");
                startActivity(iAllAccounts);
                return true;

            case R.id.subItemViewOrders:
                Intent iViewOrders = new Intent(getApplicationContext(), ViewAllOrders.class);
                Admin adminViewOrders = (Admin) getIntent().getSerializableExtra("admin");
                iViewOrders.putExtra("admin", adminViewOrders);
                iViewOrders.putExtra("check", "admin");
                startActivity(iViewOrders);
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }
}