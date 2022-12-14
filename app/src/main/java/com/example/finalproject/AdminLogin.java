package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class AdminLogin extends AppCompatActivity {

    EditText userText, passText;
    Button loginBtn, userBtn;
    ImageButton showBtn;
    TextView hide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        userText = findViewById(R.id.editTextUsernameAdmin);
        passText = findViewById(R.id.editTextPasswordAdmin);
        loginBtn = findViewById(R.id.loginButtonAdmin);
        userBtn = findViewById(R.id.buttonUser);
        showBtn = findViewById(R.id.showPassBtn2);
        hide = findViewById(R.id.textViewTemp3);


        showBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(hide.getText().toString().equals("OFF")){
                    passText.setTransformationMethod(new PasswordTransformationMethod());
                    hide.setText("ON");
                } else{
                    passText.setTransformationMethod(null);
                    hide.setText("OFF");
                }
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper db = new DatabaseHelper(getApplicationContext());

                        if (db.getDataAdmin().getUsername().equals(userText.getText().toString()) && db.getDataAdmin().getPassword().equals(passText.getText().toString())) {
                            Intent i = new Intent(getApplicationContext(), MainActivity.class);
                            i.putExtra("admin", db.getDataAdmin());
                            i.putExtra("check", "admin");
                            startActivity(i);
                        } else {
                            Toast.makeText(getApplicationContext(), "Incorrect username and/or password", Toast.LENGTH_SHORT).show();

                        }

            }
        });

        userBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Login.class);
                startActivity(i);
            }
        });
    }
}