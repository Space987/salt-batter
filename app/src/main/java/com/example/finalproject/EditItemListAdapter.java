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


        name.setText(list.get(position).getName());
        description.setText(list.get(position).getDescription());
        cost.setText(String.valueOf(list.get(position).getCost()));
        quantity.setText(String.valueOf(list.get(position).getQuantity()));

        Bitmap bmp = BitmapFactory.decodeByteArray(list.get(position).getImageData(), 0, list.get(position).getImageData().length);
        imageView.setImageBitmap(bmp);


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

                if (name.getText().toString().matches("") ||cost.getText().toString().matches("") ||quantity.getText().toString().matches("") ||(description.getText().toString().matches(""))) {
                    Toast.makeText(view.getContext(), "Values cannot be empty", Toast.LENGTH_SHORT).show();
                }
                else {
                    db.updateFood(list.get(position).getId(), name.getText().toString(), description.getText().toString(), Integer.parseInt(cost.getText().toString()), Integer.parseInt(quantity.getText().toString()), list.get(position).getImageData());
                    description.setText(db.getDataFoodSpecific(list.get(position).getId()).getDescription());
                    name.setText(db.getDataFoodSpecific(list.get(position).getId()).getName());
                    cost.setText(String.valueOf(db.getDataFoodSpecific(list.get(position).getId()).getCost()));
                    quantity.setText(String.valueOf(db.getDataFoodSpecific(list.get(position).getId()).getQuantity()));
                    Toast.makeText(view.getContext(), db.getDataFoodSpecific(list.get(position).getId()).getName() + " Updated!", Toast.LENGTH_SHORT).show();
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



