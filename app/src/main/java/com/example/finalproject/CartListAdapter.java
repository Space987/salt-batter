package com.example.finalproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

public class CartListAdapter extends BaseAdapter {

    private Context mContext;
    private List<OrderFood> list;
    ImageView image;
    DatabaseHelper db;
    Button deletBtn;
    TextView myTextView;



    public CartListAdapter(Context mContext , List<OrderFood> data, TextView totalText) {

        this.mContext = mContext;
        this.list = data;
        this.myTextView = totalText;
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
        View view = View.inflate(mContext, R.layout.cart_list_child ,null );

        TextView name = view.findViewById(R.id.textViewNameOrder);
        TextView cost = view.findViewById(R.id.textViewPrice);
        TextView quantity = view.findViewById(R.id.textViewQuantity);
        image = view.findViewById(R.id.imageViewOrder);
        db = new DatabaseHelper(view.getContext());
        deletBtn = view.findViewById(R.id.removeFromCart);


        name.setText(list.get(position).getName());
        cost.setText((list.get(position).getCost() * db.getOrderSpecific(list.get(position).getProfile_id(), list.get(position).getFood_id()).getQuantity())+ "$");
        quantity.setText(String.valueOf(list.get(position).getQuantity()));

        Bitmap bmp = BitmapFactory.decodeByteArray(db.getDataFoodImage(list.get(position).getFood_id()), 0,db.getDataFoodImage(list.get(position).getFood_id()).length);
        image.setImageBitmap(bmp);


        deletBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, list.get(position).getName()+" removed!", Toast.LENGTH_SHORT).show();
                db.deleteOrder(list.get(position).getId());
                db.removeTotal(list.get(position).getProfile_id(), (db.getDataSpecific(list.get(position).getProfile_id()).getTotal() / list.get(position).getQuantity()));
            }
        });



        return view;
    }


}
