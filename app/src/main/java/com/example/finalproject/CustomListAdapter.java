package com.example.finalproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

import java.util.List;

public class CustomListAdapter extends BaseAdapter {

    private Context mContext;
    private List<Food> list;
    FloatingActionButton plusBtn;
    ImageView image;
    int myId;
    DatabaseHelper db;
    int newQuantity;
    int add = 0;
    boolean check = false;

    public CustomListAdapter(Context mContext , List<Food> data, int id){
        this.mContext = mContext;
        this.list = data;
        this.myId = id;
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


        View view = View.inflate(mContext, R.layout.list_child ,null );
        TextView name = view.findViewById(R.id.textViewNameOrder);
        TextView cost = view.findViewById(R.id.textViewPrice);
        TextView quantity = view.findViewById(R.id.textViewQuantityOrder);
        image = view.findViewById(R.id.imageViewOrder);
        db = new DatabaseHelper(view.getContext());


        plusBtn = view.findViewById(R.id.fabAdd);

        name.setText(list.get(position).getName());
        cost.setText(list.get(position).getCost() + "$");
        if(db.getDataFoodSpecific(list.get(position).getId()).getQuantity() == 0){
            quantity.setText("out of stock");
            quantity.setTextSize(16);
        }
        else {
            quantity.setText(String.valueOf(list.get(position).getQuantity()));
        }
        Bitmap bmp = BitmapFactory.decodeByteArray(list.get(position).getImageData(), 0,list.get(position).getImageData().length);
        image.setImageBitmap(bmp);



            plusBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(db.getDataFoodSpecific(list.get(position).getId()).getQuantity() != 0) {
                        newQuantity = db.getDataFood().get(position).getQuantity();
                        newQuantity = newQuantity - 1;
                        db.subtractQuantity(list.get(position).getId(), newQuantity);
                        quantity.setText(String.valueOf(db.getDataFood().get(position).getQuantity()));


                        for(OrderFood order : db.getOrder(myId)){

                            if(order.getFood_id() == list.get(position).getId()){
                                Toast.makeText(mContext, list.get(position).getName()+" Added to Cart", Toast.LENGTH_SHORT).show();
                                db.updateOrder(myId, list.get(position).getId(), (order.getQuantity() +1));
                                check = true;
                                break;
                            }

                        }

                        if(!check) {
                            if(db.getOrder(myId).isEmpty()) {
                                Toast.makeText(mContext, list.get(position).getName()+" Added to Cart", Toast.LENGTH_SHORT).show();
                                db.createOrder(myId, list.get(position).getId(), list.get(position).getName(), list.get(position).getCost(), 1);
                            }

                            else {
                                Toast.makeText(mContext, list.get(position).getName()+" Added to Cart", Toast.LENGTH_SHORT).show();
                                db.createOrder(myId, list.get(position).getId(), list.get(position).getName(), list.get(position).getCost(), 1);

                            }
                        }

                    }

                    else{
                        Toast.makeText(mContext, list.get(position).getName()+ " out of stock", Toast.LENGTH_SHORT).show();
                        quantity.setText("out of stock");
                        quantity.setTextSize(16);
                    }

                    check = false;
                }
            });



 return view;
    }


}
