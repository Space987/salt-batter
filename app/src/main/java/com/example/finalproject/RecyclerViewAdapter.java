package com.example.finalproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder> {


    private List<User> mData;
    private LayoutInflater myInflater;
    DatabaseHelper db;

    public RecyclerViewAdapter(Context context, List<User> mData){
        this.myInflater = LayoutInflater.from(context);
        this.mData = mData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = myInflater.inflate(R.layout.recycler_child, parent, false);
        db = new DatabaseHelper(view.getContext());
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.usernameText.setText(mData.get(position).getUsername());
        holder.passwordText.setText(mData.get(position).getPassword());
        holder.costText.setText("$" + String.valueOf(mData.get(position).getTotal()));
        Bitmap bmp = BitmapFactory.decodeByteArray(db.getUserFoodImage(mData.get(position).getId()), 0,db.getUserFoodImage(mData.get(position).getId()).length);
        holder.imageView.setImageBitmap(bmp);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
