package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class Register extends AppCompatActivity {

    DatabaseHelper db;
    EditText userText, passText, rePassText;
    Button registerBtn, imageBtn;
    TextView tmp;
    ImageView imageView;
    ImageButton showBtn;
    boolean exists = false;

    int SELECT_PICTURE = 200;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new DatabaseHelper(getApplicationContext());
        setContentView(R.layout.activity_register);
        userText = findViewById(R.id.editTextUsername);
        passText = findViewById(R.id.editTextPassword);
        rePassText = findViewById(R.id.editTextRePassword);
        registerBtn = findViewById(R.id.registerButton);
        imageView = findViewById(R.id.imageView);
        imageBtn = findViewById(R.id.buttonImage);
        showBtn = findViewById(R.id.showPassBtn);
        tmp = findViewById(R.id.textViewTemp);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (passText.getText().toString().matches("") || userText.getText().toString().matches("")) {
                    Toast.makeText(getApplicationContext(), "Values cannot be empty", Toast.LENGTH_SHORT).show();
                } else {

                    if(!db.getData().isEmpty()) {
                        for (User user : db.getData()) {
                            if (userText.getText().toString().equals(user.getUsername())) {
                                exists = true;
                                break;
                            }
                        }
                    }
                    if(!exists) {
                        if (passText.getText().toString().equals(rePassText.getText().toString())) {
                            db = new DatabaseHelper(getApplicationContext());
                            imageView.buildDrawingCache();
                            Bitmap bmap = imageView.getDrawingCache();
                            byte[] imgData = getBitmapAsByteArray(bmap);
                            db.addData(userText.getText().toString(), passText.getText().toString(), imgData);
                            Intent i = new Intent(getApplicationContext(), Login.class);

                            startActivity(i);
                        } else {
                            Toast.makeText(getApplicationContext(), "Passwords must match", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Username already exists!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        showBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tmp.getText().toString().equals("OFF")){
                    passText.setTransformationMethod(new PasswordTransformationMethod());
                    tmp.setText("ON");
                } else{
                    passText.setTransformationMethod(null);
                    tmp.setText("OFF");
                }
            }
        });



        imageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageChooser();
                Toast.makeText(getApplicationContext(), "Image Selected!", Toast.LENGTH_SHORT).show();
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
}