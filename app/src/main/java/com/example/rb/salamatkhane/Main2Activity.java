package com.example.rb.salamatkhane;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main2Activity extends AppCompatActivity {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        Typeface tf = Typeface.createFromAsset(getAssets(), "IRANSansMobile(FaNum).ttf");
        Button b1=(Button)findViewById(R.id.button);
        b1.setTypeface(tf);
        Button b2=(Button)findViewById(R.id.button2);
        b2.setTypeface(tf);
        pref = getSharedPreferences("SalamatPref", MODE_PRIVATE);
        editor=pref.edit();
    }
    public void clickRegister(View v){
        Intent i=new Intent(Main2Activity.this,AdminRegisterActivity.class);
        startActivity(i);
    }
    public void clickLogin(View v){

        Intent i=new Intent(Main2Activity.this,LoginActivity.class);
        startActivity(i);
    }
}
