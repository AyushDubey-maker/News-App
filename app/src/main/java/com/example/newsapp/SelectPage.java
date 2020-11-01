package com.example.newsapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SelectPage extends AppCompatActivity {
    ImageView sports,techno,topHead;
    TextView txtSports,txtTech,txtHead;
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.select_page);
        txtSports=findViewById(R.id.txt_sports);
        txtTech=findViewById(R.id.txt_tech);
        txtHead=findViewById(R.id.txt_headlines);
        sports=findViewById(R.id.sports);
        techno=findViewById(R.id.tech);
        topHead=findViewById(R.id.headlines);

        txtSports.startAnimation(AnimationUtils.loadAnimation(SelectPage.this,R.anim.txt_anim));
        txtTech.startAnimation(AnimationUtils.loadAnimation(SelectPage.this,R.anim.txt_anim));
        txtHead.startAnimation(AnimationUtils.loadAnimation(SelectPage.this,R.anim.txt_anim));
//
//        sports.startAnimation(AnimationUtils.loadAnimation(SelectPage.this,R.anim.txt_anim));
//        techno.startAnimation(AnimationUtils.loadAnimation(SelectPage.this,R.anim.txt_anim));
//        topHead.startAnimation(AnimationUtils.loadAnimation(SelectPage.this,R.anim.txt_anim));


    }
    public void sports(View view){
        Intent intent=new Intent(this,SportsPage.class);
        startActivity(intent);
    }
    public void tech(View view){
        Intent intent=new Intent(this,TechnologyPage.class);
        startActivity(intent);
    }
    public void headLines(View view){
        Intent intent=new Intent(this,HomePage.class);
        startActivity(intent);
    }
}
