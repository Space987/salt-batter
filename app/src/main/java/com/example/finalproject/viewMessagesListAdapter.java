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

public class viewMessagesListAdapter extends BaseAdapter {

    private Context mContext;
    private List<Messages> list;
    ImageView image;
    DatabaseHelper db;



    public viewMessagesListAdapter(Context mContext , List<Messages> data) {
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


        View view = View.inflate(mContext, R.layout.view_messages_child_user ,null );
        TextView message = view.findViewById(R.id.textViewMessage);
        TextView email = view.findViewById(R.id.textViewEmail);
        db = new DatabaseHelper(view.getContext());

        message.setText(list.get(position).getMessage());
        email.setText(list.get(position).getEmail());



        return view;
    }


}