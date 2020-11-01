package com.example.newsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    TextView new_userTrans;
    Button goHome;
    ImageView image;
   // TextView logoText,forgotPassword;
    EditText password,email;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;


    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
       firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
       if(firebaseUser !=null){
           Intent intent=new Intent(MainActivity.this,SelectPage.class);
           startActivity(intent);
           finish();
       }
        new_userTrans = findViewById(R.id.textView);

        //forgotPassword=findViewById(R.id.forgot);
        password =  findViewById(R.id.log_password);
        goHome = findViewById(R.id.login_account);
        firebaseAuth = FirebaseAuth.getInstance();
        email =  findViewById(R.id.log_email);
       // progressBar=findViewById(R.id.progress_bar);
     //   progressBar.setVisibility(View.GONE);
        new_userTrans.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {


                Intent i = new Intent(MainActivity.this, RegisterActivity.class);

                startActivity(i);
               // progressBar.setVisibility(View.GONE);
            }


        });
//        forgotPassword.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity2.this,Forgot_Password.class));
//            }
//        });

        goHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String val = email.getText().toString().trim();

                String val2 = password.getText().toString().trim();

                if (val.isEmpty()) {
                    email.setError("Field cannot be Empty");
                    return;
                }   else {
                    email.setError(null);

                    //return;
                }


                if (val2.isEmpty()) {
                    password.setError("Field cannot be Empty");
                    return;
                }  else {
                    password.setError(null);


                }
                //firebase Authentication proccess----//
                firebaseAuth.signInWithEmailAndPassword(val, val2)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                               // progressBar.setVisibility(View.VISIBLE);
                                if (task.isSuccessful()) {
                                    Intent i=new Intent(MainActivity.this,SelectPage.class);
                                    startActivity(i);
                                } else {
                                   // progressBar.setVisibility(View.GONE);
                                    Toast.makeText(MainActivity.this, "Invalid User Recheck!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

    }


}


