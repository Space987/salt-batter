package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
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
    Button registerBtn, imageBtn, backBtn;
    TextView tmp;
    ImageView imageView;
    ImageButton showBtn;
    boolean exists = false;

    int SELECT_PICTURE = 200;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new DatabaseHelper(getApplicationContext());
        getSupportActionBar().setTitle("Salt&Batter");
        setContentView(R.layout.activity_register);
        userText = findViewById(R.id.editTextUsername);
        passText = findViewById(R.id.editTextPassword);
        rePassText = findViewById(R.id.editTextRePassword);
        registerBtn = findViewById(R.id.registerButton);
        imageView = findViewById(R.id.imageView);
        backBtn = findViewById(R.id.backButton);
        imageBtn = findViewById(R.id.buttonImage);
        showBtn = findViewById(R.id.showPassBtn);
        tmp = findViewById(R.id.textViewTemp);

        userText.setBackgroundResource(R.drawable.my_shape);
        passText.setBackgroundResource(R.drawable.my_shape);
        rePassText.setBackgroundResource(R.drawable.my_shape);

        InputFilter[] filtersMaxUser = new InputFilter[1];
        filtersMaxUser[0] = new InputFilter.LengthFilter(17);
        userText.setFilters(filtersMaxUser);

        userText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 17) {
                    userText.setError("Max of 16 characters!");
                    userText.setBackgroundResource(R.drawable.my_shape_error);
                }

                if (s.length() < 17) {
                    userText.setBackgroundResource(R.drawable.my_shape);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        InputFilter[] filtersMaxPass = new InputFilter[1];
        filtersMaxPass[0] = new InputFilter.LengthFilter(17);
        passText.setFilters(filtersMaxPass);

        passText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 17) {
                    passText.setError("Max of 16 characters!");
                    passText.setBackgroundResource(R.drawable.my_shape_error);
                }

                if (s.length() < 6) {
                    passText.setError("Minimum of 6 characters!");
                    passText.setBackgroundResource(R.drawable.my_shape_error);
                }

                if (s.length() >= 6 && s.length() < 17) {
                    InputFilter[] filtersMaxPass = new InputFilter[1];
                    filtersMaxPass[0] = new InputFilter.LengthFilter(16);
                    passText.setFilters(filtersMaxPass);
                    passText.setBackgroundResource(R.drawable.my_shape);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        InputFilter[] filtersMaxRePass = new InputFilter[1];
        filtersMaxRePass[0] = new InputFilter.LengthFilter(16);
        rePassText.setFilters(filtersMaxRePass);

        rePassText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 17) {
                    rePassText.setError("Max of 16 characters!");
                    rePassText.setBackgroundResource(R.drawable.my_shape_error);
                }

                if (s.length() < 6) {
                    rePassText.setError("Minimum of 6 characters!");
                    rePassText.setBackgroundResource(R.drawable.my_shape_error);
                }

                if (s.length() >= 6 && s.length() < 17) {
                    InputFilter[] filtersMaxPass = new InputFilter[1];
                    filtersMaxPass[0] = new InputFilter.LengthFilter(16);
                    rePassText.setFilters(filtersMaxPass);
                    rePassText.setBackgroundResource(R.drawable.my_shape);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!userText.getText().toString().matches("")) {
                    userText.setBackgroundResource(R.drawable.my_shape);
                    if (!passText.getText().toString().matches("")) {
                        passText.setBackgroundResource(R.drawable.my_shape);
                        if (!db.getData().isEmpty()) {
                            for (User user : db.getData()) {
                                if (userText.getText().toString().equals(user.getUsername())) {
                                    exists = true;
                                    break;
                                }
                            }
                        }
                        if (!exists) {
                            if (passText.getText().toString().equals(rePassText.getText().toString())) {
                                db = new DatabaseHelper(getApplicationContext());
                                imageView.buildDrawingCache();
                                Bitmap bmap = imageView.getDrawingCache();
                                byte[] imgData = getBitmapAsByteArray(bmap);
                                userText.setBackgroundResource(R.drawable.my_shape_correct);
                                passText.setBackgroundResource(R.drawable.my_shape_correct);
                                rePassText.setBackgroundResource(R.drawable.my_shape_correct);
                                db.addData(userText.getText().toString(), passText.getText().toString(), imgData);
                                Intent i = new Intent(getApplicationContext(), Login.class);

                                startActivity(i);
                            } else {
                                passText.setError("Your re-typed Password does not match!");
                                passText.setBackgroundResource(R.drawable.my_shape_error);

                                rePassText.setError("Your re-typed Password does not match!");
                                rePassText.setBackgroundResource(R.drawable.my_shape_error);
                            }
                        } else {
                            userText.setError("Username already exists!");
                            userText.setBackgroundResource(R.drawable.my_shape_error);

                            if (!passText.getText().toString().equals(rePassText.getText().toString())) {
                                passText.setError("Your re-typed Password does not match!");
                                passText.setBackgroundResource(R.drawable.my_shape_error);

                                rePassText.setError("Your re-typed Password does not match!");
                                rePassText.setBackgroundResource(R.drawable.my_shape_error);
                            }

                        }
                    }
                    else{
                        passText.setError("You cannot leave password input blank!");
                        passText.setBackgroundResource(R.drawable.my_shape_error);
                    }
                }
                else{
                    userText.setError("You cannot leave username input blank!");
                    userText.setBackgroundResource(R.drawable.my_shape_error);

                    if(passText.getText().toString().matches("")){
                        passText.setError("You cannot leave password input blank!");
                        passText.setBackgroundResource(R.drawable.my_shape_error);
                    }
                    else{
                        passText.setBackgroundResource(R.drawable.my_shape);
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

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iback = new Intent(getApplicationContext(), Login.class);
                startActivity(iback);
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