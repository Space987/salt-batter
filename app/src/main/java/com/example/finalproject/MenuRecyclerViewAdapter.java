package com.example.finalproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MenuRecyclerViewAdapter extends RecyclerView.Adapter<MenuViewHolder> {


    private List<Food> mData;
    private LayoutInflater myInflater;
    DatabaseHelper db;

    public MenuRecyclerViewAdapter(Context context, List<Food> mData){
        this.myInflater = LayoutInflater.from(context);
        this.mData = mData;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = myInflater.inflate(R.layout.menu_recycler_view_child, parent, false);
        db = new DatabaseHelper(view.getContext());
        return new MenuViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {

        holder.nameText.setText(mData.get(position).getName());
        Bitmap bmp = BitmapFactory.decodeByteArray(db.getDataFoodImage(mData.get(position).getId()), 0, db.getDataFoodImage(mData.get(position).getId()).length);
        holder.imageView.setImageBitmap(bmp);
        holder.descriptionText.setText(mData.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
