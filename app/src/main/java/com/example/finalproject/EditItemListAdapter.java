package com.example.finalproject;

import static android.app.Activity.RESULT_OK;




import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class EditItemListAdapter extends BaseAdapter {

    private Context mContext;
    private List<Food> list;
    Button updateBtn, deleteBtn, changeBtn;
    ImageView imageView;
    DatabaseHelper db;
    boolean checkLengthPrice = true;
    boolean checkLengthQty = true;

    boolean checkNumPrice = true;
    boolean checkNumQty = true;
    boolean nameCheck = false;


    int SELECT_PICTURE = 200;

    public EditItemListAdapter(Context mContext , List<Food> data){
        this.mContext = mContext;
        this.list = data;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        View view = View.inflate(mContext, R.layout.activity_edit_item, null);
        EditText name = view.findViewById(R.id.editTextName);
        EditText description = view.findViewById(R.id.editTextDescription);
        EditText cost = view.findViewById(R.id.editTextPrice);
        EditText quantity = view.findViewById(R.id.editTextQuantity);
        updateBtn = view.findViewById(R.id.buttonUpdate);
        deleteBtn = view.findViewById(R.id.buttonDelete);
        imageView = view.findViewById(R.id.imageView);
        changeBtn = view.findViewById(R.id.buttonChangeImage);
        db = new DatabaseHelper(view.getContext());

        name.setBackgroundResource(R.drawable.my_shape_admin);
        description.setBackgroundResource(R.drawable.my_shape_admin);
        cost.setBackgroundResource(R.drawable.my_shape_admin);
        quantity.setBackgroundResource(R.drawable.my_shape_admin);

        name.setText(list.get(position).getName());
        description.setText(list.get(position).getDescription());
        cost.setText(String.valueOf(list.get(position).getCost()));
        quantity.setText(String.valueOf(list.get(position).getQuantity()));

        Bitmap bmp = BitmapFactory.decodeByteArray(list.get(position).getImageData(), 0, list.get(position).getImageData().length);
        imageView.setImageBitmap(bmp);

        InputFilter[] filtersMaxUser = new InputFilter[1];
        filtersMaxUser[0] = new InputFilter.LengthFilter(5);
        cost.setFilters(filtersMaxUser);

        cost.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 5) {
                    cost.setError("Max of 4 numbers!");
                    cost.setBackgroundResource(R.drawable.my_shape_error_admin);
                    checkLengthPrice = false;
                }

                for(int i = 0; i < s.length(); i++ ) {
                    if (!Character.isDigit(s.charAt(i))) {
                        cost.setError("Quantity must be a number!");
                        checkNumPrice = false;
                        cost.setBackgroundResource(R.drawable.my_shape_error_admin);
                    } else {
                        if (s.length() == 5) {
                            cost.setError("Max of 4 numbers!");
                            cost.setBackgroundResource(R.drawable.my_shape_error_admin);
                            checkLengthPrice = false;
                        }
                        else{
                            cost.setBackgroundResource(R.drawable.my_shape_admin);
                            checkLengthPrice = true;
                        }
                    }
                }

                if (s.length() < 5) {
                    for (int i = 0; i < s.length(); i++) {
                        if (!Character.isDigit(s.charAt(i))) {
                            checkNumPrice = false;
                            cost.setBackgroundResource(R.drawable.my_shape_error_admin);
                        } else {
                            cost.setBackgroundResource(R.drawable.my_shape_admin);
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
        quantity.setFilters(filtersMaxPass);

        quantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 5) {
                    quantity.setError("Max of 4 numbers!");
                    checkLengthQty = false;
                    quantity.setBackgroundResource(R.drawable.my_shape_error_admin);


                }
                for (int i = 0; i < s.length(); i++) {
                    if (!Character.isDigit(s.charAt(i))) {
                        quantity.setError("Quantity must be a number!");
                        checkNumQty = false;
                        quantity.setBackgroundResource(R.drawable.my_shape_error_admin);
                    } else {

                        if (s.length() == 5) {
                            quantity.setError("Max of 4 numbers!");
                            quantity.setBackgroundResource(R.drawable.my_shape_error_admin);
                            checkLengthQty = false;
                        } else {
                            quantity.setBackgroundResource(R.drawable.my_shape_admin);
                            checkLengthQty = true;
                        }

                    }
                }

                if (s.length() < 5) {
                    for (int i = 0; i < s.length(); i++) {
                        if (!Character.isDigit(s.charAt(i))) {
                            checkNumQty = false;
                            quantity.setBackgroundResource(R.drawable.my_shape_error_admin);
                        } else {

                            checkNumQty = true;
                            quantity.setBackgroundResource(R.drawable.my_shape_admin);
                        }
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        changeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //imageChooser();
                Toast.makeText(view.getContext(), "Image Selected!", Toast.LENGTH_SHORT).show();

            }
        });


        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                imageView.buildDrawingCache();
//                Bitmap bmap = imageView.getDrawingCache();
//                byte[] imgData = getBitmapAsByteArray(bmap);


                if(!name.getText().toString().matches("")) {
                    name.setBackgroundResource(R.drawable.my_shape_admin);


                    if (!description.getText().toString().matches("")) {
                        description.setBackgroundResource(R.drawable.my_shape_admin);

                        if (!cost.getText().toString().matches("")) {
                            cost.setBackgroundResource(R.drawable.my_shape_admin);

                            if (!quantity.getText().toString().matches("")) {
                                quantity.setBackgroundResource(R.drawable.my_shape_admin);

                                for (Food food : db.getDataFood()) {
                                    if (name.getText().toString().equals(food.getName())) {
                                        nameCheck = true;
                                        break;
                                    }
                                }
                                if (nameCheck && !db.getDataFoodSpecific(list.get(position).getId()).getName().equals(name.getText().toString())) {
                                    name.setError("Food name already exists!");
                                    name.setBackgroundResource(R.drawable.my_shape_error_admin);
                                    nameCheck = false;
                                } else {
                                    if (checkLengthPrice && checkLengthQty && checkNumQty && checkNumPrice) {
                                        db.updateFood(list.get(position).getId(), name.getText().toString(), description.getText().toString(), Integer.parseInt(cost.getText().toString()), Integer.parseInt(quantity.getText().toString()), list.get(position).getImageData());
                                        description.setText(db.getDataFoodSpecific(list.get(position).getId()).getDescription());
                                        name.setText(db.getDataFoodSpecific(list.get(position).getId()).getName());
                                        cost.setText(String.valueOf(db.getDataFoodSpecific(list.get(position).getId()).getCost()));
                                        quantity.setText(String.valueOf(db.getDataFoodSpecific(list.get(position).getId()).getQuantity()));
                                        Toast.makeText(view.getContext(), db.getDataFoodSpecific(list.get(position).getId()).getName() + " Updated!", Toast.LENGTH_SHORT).show();
                                        nameCheck = false;

                                    }
                                }

                            }
                            else{
                                quantity.setError("You cannot leave quantity input blank!");
                                quantity.setBackgroundResource(R.drawable.my_shape_error_admin);
                            }

                        }else{
                            cost.setError("You cannot leave price input blank!");
                            cost.setBackgroundResource(R.drawable.my_shape_error_admin);

                            if(quantity.getText().toString().matches("")){
                                quantity.setError("You cannot leave quantity input blank!");
                                quantity.setBackgroundResource(R.drawable.my_shape_error_admin);
                            }
                            else{
                                quantity.setBackgroundResource(R.drawable.my_shape_admin);
                            }
                        }
                    }else{
                        description.setError("You cannot leave description input blank!");
                        description.setBackgroundResource(R.drawable.my_shape_error_admin);

                        if(cost.getText().toString().matches("")){
                            cost.setError("You cannot leave price input blank!");
                            cost.setBackgroundResource(R.drawable.my_shape_error_admin);
                        }
                        else{
                            cost.setBackgroundResource(R.drawable.my_shape_admin);
                        }

                        if(quantity.getText().toString().matches("")){
                            quantity.setError("You cannot leave quantity input blank!");
                            quantity.setBackgroundResource(R.drawable.my_shape_error_admin);
                        }
                        else{
                            quantity.setBackgroundResource(R.drawable.my_shape_admin);
                        }
                    }
                }else{
                    name.setError("You cannot leave name input blank!");
                    name.setBackgroundResource(R.drawable.my_shape_error_admin);

                    if(description.getText().toString().matches("")){
                        description.setError("You cannot leave description input blank!");
                        description.setBackgroundResource(R.drawable.my_shape_error_admin);
                    }


                    else{
                        description.setBackgroundResource(R.drawable.my_shape_admin);
                    }

                    if(cost.getText().toString().matches("")){
                        cost.setError("You cannot leave price input blank!");
                        cost.setBackgroundResource(R.drawable.my_shape_error_admin);
                    }
                    else{
                        cost.setBackgroundResource(R.drawable.my_shape_admin);
                    }

                    if(quantity.getText().toString().matches("")){
                        quantity.setError("You cannot leave quantity input blank!");
                        quantity.setBackgroundResource(R.drawable.my_shape_error_admin);
                    }
                    else{
                        quantity.setBackgroundResource(R.drawable.my_shape_admin);
                    }
                }
            }

        });


        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                db.deleteFood(list.get(position).getId());
                Toast.makeText(view.getContext(), list.get(position).getName() + " Deleted!", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
        }


//    void imageChooser() {
//
//        // create an instance of the
//        // intent of the type image
//        Intent i = new Intent();
//        i.setType("image/*");
//        i.setAction(Intent.ACTION_GET_CONTENT);
//
//        // pass the constant to compare it
//        // with the returned requestCode
//        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
//    }
//
//    // this function is triggered when user
//    // selects the image from the imageChooser
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (resultCode == RESULT_OK) {
//
//            // compare the resultCode with the
//            // SELECT_PICTURE constant
//            if (requestCode == SELECT_PICTURE) {
//                // Get the url of the image from data
//                Uri selectedImageUri = data.getData();
//                if (null != selectedImageUri) {
//                    // update the preview image in the layout
//                    imageView.setImageURI(selectedImageUri);
//                }
//            }
//        }
//    }
//
//    public static byte[] getBitmapAsByteArray(Bitmap bitmap) {
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
//        return outputStream.toByteArray();
//
//    }

}



