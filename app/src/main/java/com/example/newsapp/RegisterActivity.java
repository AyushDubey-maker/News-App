package com.example.newsapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    EditText username,email,password,confirmPassword;
    Button register;
    FirebaseAuth rootNode;
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sinup);

        username = findViewById(R.id.reg_username);
        email = findViewById(R.id.reg_email);
        password = findViewById(R.id.reg_password);
        confirmPassword = findViewById(R.id.confirm_password);
        register = findViewById(R.id.register_Account_btn);

        rootNode = FirebaseAuth.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Regemail=email.getText().toString().trim();
                String Password=password.getText().toString().trim();
                String UserName=username.getText().toString().trim();
                String ConfirmPassword=confirmPassword.getText().toString().trim();
                if(UserName.isEmpty()){
                    Toast.makeText(RegisterActivity.this, "Please Enter Your FullName", Toast.LENGTH_SHORT).show();

                    return;

                }
                if(Regemail.isEmpty()){
                   Toast.makeText(RegisterActivity.this,"Please Enter Email",Toast.LENGTH_SHORT).show();

                    return;
                }
                else if(Password.length()<=6){
                    Toast.makeText(RegisterActivity.this, "Password Must Be Greater than 6 Characters", Toast.LENGTH_SHORT).show();
                }
                if(Password.isEmpty()){
                    Toast.makeText(RegisterActivity.this, "Please Enter Password", Toast.LENGTH_SHORT).show();

                    return;
                }
                if(ConfirmPassword.isEmpty()){
                    Toast.makeText(RegisterActivity.this, "Enter Confirm Password", Toast.LENGTH_SHORT).show();

                    return;
                }
                // This process will only set-up if password==confirmPassword---//
                if (Password.equals(ConfirmPassword)) {


                    rootNode.createUserWithEmailAndPassword(Regemail, Password)
                            .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    if (task.isSuccessful()) {
                                        Toast.makeText(RegisterActivity.this, "Data Saved", Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(RegisterActivity.this, MainActivity.class);
                                        startActivity(i);
                                    } else {
                                        Toast.makeText(RegisterActivity.this, "Authentication Failed..Password Weak",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
    }

}


