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
        getSupportActionBar().setTitle("Salt&Batter");
        userText = findViewById(R.id.editTextUsernameAdmin);
        passText = findViewById(R.id.editTextPasswordAdmin);
        loginBtn = findViewById(R.id.loginButtonAdmin);
        userBtn = findViewById(R.id.buttonUser);
        showBtn = findViewById(R.id.showPassBtn2);
        hide = findViewById(R.id.textViewTemp3);

        userText.setBackgroundResource(R.drawable.my_shape_admin);
        passText.setBackgroundResource(R.drawable.my_shape_admin);
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

                if(!userText.getText().toString().matches("")) {
                    userText.setBackgroundResource(R.drawable.my_shape_admin);


                    if (!passText.getText().toString().matches("")) {
                        passText.setBackgroundResource(R.drawable.my_shape_admin);
                        if (db.getDataAdmin().getUsername().equals(userText.getText().toString()) && db.getDataAdmin().getPassword().equals(passText.getText().toString())) {
                            userText.setBackgroundResource(R.drawable.my_shape_correct_admin);
                            passText.setBackgroundResource(R.drawable.my_shape_correct_admin);
                            Intent i = new Intent(getApplicationContext(), MainActivity.class);
                            i.putExtra("admin", db.getDataAdmin());
                            i.putExtra("check", "admin");
                            startActivity(i);
                        } else {
                            userText.setError("Your Username/Password is not correct!");
                            userText.setBackgroundResource(R.drawable.my_shape_error_admin);
                            passText.setError("Your Username/Password is not correct!");
                            passText.setBackgroundResource(R.drawable.my_shape_error_admin);

                        }
                    }
                    else{
                        passText.setError("You cannot leave password input blank!");
                        passText.setBackgroundResource(R.drawable.my_shape_error_admin);
                    }
                }
                else{
                    userText.setError("You cannot leave username input blank!");
                    userText.setBackgroundResource(R.drawable.my_shape_error_admin);
                    if(passText.getText().toString().matches("")){
                        passText.setError("You cannot leave password input blank!");
                        passText.setBackgroundResource(R.drawable.my_shape_error_admin);
                    }
                    else{
                        passText.setBackgroundResource(R.drawable.my_shape_admin);
                    }
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