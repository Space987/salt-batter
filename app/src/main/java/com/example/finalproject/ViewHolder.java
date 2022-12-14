package com.example.finalproject;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder {

    TextView usernameText, passwordText, costText;
    ImageView imageView;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        usernameText = itemView.findViewById(R.id.textViewUsernameAdmin);
        passwordText = itemView.findViewById(R.id.textViewPasswordAdmin);
        costText = itemView.findViewById(R.id.textViewTotalUser);
        imageView = itemView.findViewById(R.id.imageView2);
    }
}