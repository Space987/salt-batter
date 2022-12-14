package com.example.finalproject;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MenuViewHolder extends RecyclerView.ViewHolder {

    TextView nameText, descriptionText;
    ImageView imageView;

    public MenuViewHolder(@NonNull View itemView) {
        super(itemView);
        nameText = itemView.findViewById(R.id.textViewNameMenu);
        descriptionText = itemView.findViewById(R.id.textViewDescription);
        imageView = itemView.findViewById(R.id.imageView);
    }
}