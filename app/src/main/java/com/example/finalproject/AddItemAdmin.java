package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
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
    boolean checkLengthPrice = true;
    boolean checkLengthQty = true;

    boolean checkNumPrice = true;
    boolean checkNumQty = true;
    boolean nameCheck = false;

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

        editTextName.setBackgroundResource(R.drawable.my_shape_admin);
        editTextDescription.setBackgroundResource(R.drawable.my_shape_admin);
        editTextPrice.setBackgroundResource(R.drawable.my_shape_admin);
        editTextQuantity.setBackgroundResource(R.drawable.my_shape_admin);


        InputFilter[] filtersMaxUser = new InputFilter[1];
        filtersMaxUser[0] = new InputFilter.LengthFilter(5);
        editTextPrice.setFilters(filtersMaxUser);

        editTextPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 5) {
                    editTextPrice.setError("Max of 4 numbers!");
                    editTextPrice.setBackgroundResource(R.drawable.my_shape_error_admin);
                    checkLengthPrice = false;
                }

                for(int i = 0; i < s.length(); i++ ) {
                    if (!Character.isDigit(s.charAt(i))) {
                        editTextPrice.setError("Price must be a number!");
                        checkNumPrice = false;
                        editTextPrice.setBackgroundResource(R.drawable.my_shape_error_admin);
                    } else {
                        if (s.length() == 5) {
                            editTextPrice.setError("Max of 4 numbers!");
                            editTextPrice.setBackgroundResource(R.drawable.my_shape_error_admin);
                            checkLengthPrice = false;
                        }
                        else{
                            editTextPrice.setBackgroundResource(R.drawable.my_shape_admin);
                            checkLengthPrice = true;
                        }
                    }
                }

                if (s.length() < 5) {
                    for (int i = 0; i < s.length(); i++) {
                        if (!Character.isDigit(s.charAt(i))) {
                            checkNumPrice = false;
                            editTextPrice.setBackgroundResource(R.drawable.my_shape_error_admin);
                        } else {
                            editTextPrice.setBackgroundResource(R.drawable.my_shape_admin);
                            checkNumPrice = true;
                        }
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        InputFilter[] filtersMaxPass = new InputFilter[1];
        filtersMaxPass[0] = new InputFilter.LengthFilter(5);
        editTextQuantity.setFilters(filtersMaxPass);

        editTextQuantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 5) {
                    editTextQuantity.setError("Max of 4 numbers!");
                    checkLengthQty = false;
                    editTextQuantity.setBackgroundResource(R.drawable.my_shape_error_admin);


                }
                for (int i = 0; i < s.length(); i++) {
                    if (!Character.isDigit(s.charAt(i))) {
                        editTextQuantity.setError("Quantity must be a number!");
                        checkNumQty = false;
                        editTextQuantity.setBackgroundResource(R.drawable.my_shape_error_admin);
                    } else {

                        if (s.length() == 5) {
                            editTextQuantity.setError("Max of 4 numbers!");
                            editTextQuantity.setBackgroundResource(R.drawable.my_shape_error_admin);
                            checkLengthQty = false;
                        } else {
                            editTextQuantity.setBackgroundResource(R.drawable.my_shape_admin);
                            checkLengthQty = true;
                        }

                    }
                }

                if (s.length() < 5) {
                    for (int i = 0; i < s.length(); i++) {
                        if (!Character.isDigit(s.charAt(i))) {
                            checkNumQty = false;
                            editTextQuantity.setBackgroundResource(R.drawable.my_shape_error_admin);
                        } else {

                            checkNumQty = true;
                            editTextQuantity.setBackgroundResource(R.drawable.my_shape_admin);
                        }
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

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
                if(!editTextName.getText().toString().matches("")) {
                    editTextName.setBackgroundResource(R.drawable.my_shape_admin);


                    if (!editTextDescription.getText().toString().matches("")) {
                        editTextDescription.setBackgroundResource(R.drawable.my_shape_admin);

                        if (!editTextPrice.getText().toString().matches("")) {
                            editTextPrice.setBackgroundResource(R.drawable.my_shape_admin);

                            if (!editTextQuantity.getText().toString().matches("")) {
                                editTextQuantity.setBackgroundResource(R.drawable.my_shape_admin);

                                for(Food food : db.getDataFood()){
                                    if(editTextName.getText().toString().equals(food.getName())){
                                        nameCheck = true;
                                        break;
                                    }
                                    else{
                                        nameCheck = false;
                                    }
                                }
                                if(!nameCheck) {
                                    if (checkLengthPrice && checkLengthQty && checkNumQty && checkNumPrice) {
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

                                }else{
                                    editTextName.setError("Food name already exists!");
                                    editTextName.setBackgroundResource(R.drawable.my_shape_error_admin);
                                }
                            }
                            else{
                                editTextQuantity.setError("You cannot leave quantity input blank!");
                                editTextQuantity.setBackgroundResource(R.drawable.my_shape_error_admin);
                            }

                        }else{
                            editTextPrice.setError("You cannot leave price input blank!");
                            editTextPrice.setBackgroundResource(R.drawable.my_shape_error_admin);

                            if(editTextQuantity.getText().toString().matches("")){
                                editTextQuantity.setError("You cannot leave quantity input blank!");
                                editTextQuantity.setBackgroundResource(R.drawable.my_shape_error_admin);
                            }
                            else{
                                editTextQuantity.setBackgroundResource(R.drawable.my_shape_admin);
                            }
                        }
                    }else{
                        editTextDescription.setError("You cannot leave description input blank!");
                        editTextDescription.setBackgroundResource(R.drawable.my_shape_error_admin);

                        if(editTextPrice.getText().toString().matches("")){
                            editTextPrice.setError("You cannot leave price input blank!");
                            editTextPrice.setBackgroundResource(R.drawable.my_shape_error_admin);
                        }
                        else{
                            editTextPrice.setBackgroundResource(R.drawable.my_shape_admin);
                        }

                        if(editTextQuantity.getText().toString().matches("")){
                            editTextQuantity.setError("You cannot leave quantity input blank!");
                            editTextQuantity.setBackgroundResource(R.drawable.my_shape_error_admin);
                        }
                        else{
                            editTextQuantity.setBackgroundResource(R.drawable.my_shape_admin);
                        }
                    }
                }else{
                    editTextName.setError("You cannot leave name input blank!");
                    editTextName.setBackgroundResource(R.drawable.my_shape_error_admin);

                    if(editTextDescription.getText().toString().matches("")){
                        editTextDescription.setError("You cannot leave description input blank!");
                        editTextDescription.setBackgroundResource(R.drawable.my_shape_error_admin);
                    }


                    else{
                        editTextDescription.setBackgroundResource(R.drawable.my_shape_admin);
                    }

                    if(editTextPrice.getText().toString().matches("")){
                        editTextPrice.setError("You cannot leave price input blank!");
                        editTextPrice.setBackgroundResource(R.drawable.my_shape_error_admin);
                    }
                    else{
                        editTextPrice.setBackgroundResource(R.drawable.my_shape_admin);
                    }

                    if(editTextQuantity.getText().toString().matches("")){
                        editTextQuantity.setError("You cannot leave quantity input blank!");
                        editTextQuantity.setBackgroundResource(R.drawable.my_shape_error_admin);
                    }
                    else{
                        editTextQuantity.setBackgroundResource(R.drawable.my_shape_admin);
                    }
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