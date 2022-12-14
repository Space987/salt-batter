package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Login extends AppCompatActivity {


    EditText userText, passText;
    Button loginBtn, registerBtn, adminBtn;
    ImageButton showBtn;
    TextView temp;
    static boolean check = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userText = findViewById(R.id.editTextUsername);
        passText = findViewById(R.id.editTextPassword);
        loginBtn = findViewById(R.id.loginButton2);
        registerBtn = findViewById(R.id.loginRegisterBtn);
        adminBtn = findViewById(R.id.buttonAdmin);
        showBtn = findViewById(R.id.showTxt);
        temp = findViewById(R.id.textViewTemp2);

        showBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(temp.getText().toString().equals("OFF")){
                    passText.setTransformationMethod(new PasswordTransformationMethod());
                    temp.setText("ON");
                } else{
                    passText.setTransformationMethod(null);
                    temp.setText("OFF");
                }
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper db = new DatabaseHelper(getApplicationContext());
                if (!db.getData().isEmpty()) {

                    for (User user : db.getData()) {
                        if (user.getUsername().equals(userText.getText().toString()) && user.getPassword().equals(passText.getText().toString())) {
                            check = true;
                            Intent i = new Intent(getApplicationContext(), MainActivity.class);
                            i.putExtra("user", user);
                            i.putExtra("check", "user");
                            startActivity(i);
                            break;
                        }

                    }
                    if(!check){
                        Toast.makeText(getApplicationContext(), "Your Username/Password does not match!", Toast.LENGTH_SHORT).show();

                    }


                }
                else{
                    Toast.makeText(getApplicationContext(), "You must create an account first", Toast.LENGTH_SHORT).show();
                }
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Register.class);
                startActivity(i);

            }
        });

        adminBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), AdminLogin.class);
                startActivity(i);
            }
        });
    }
}