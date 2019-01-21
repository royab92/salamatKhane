package com.example.rb.salamatkhane;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EditInfoActivity extends AppCompatActivity {
    SharedPreferences pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_info);
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        final Typeface tf = Typeface.createFromAsset(getAssets(), "IRANSansMobile(FaNum).ttf");
        pref = getSharedPreferences("SalamatPref", MODE_PRIVATE);
        TextView t1 = (TextView) findViewById(R.id.textView);
        t1.setTypeface(tf);
        TextView t2 = (TextView) findViewById(R.id.textView2);
        t2.setTypeface(tf);
        EditText t3 = (EditText) findViewById(R.id.name);
        t3.setTypeface(tf);
        TextView t4 = (TextView) findViewById(R.id.textView3);
        t4.setTypeface(tf);
        EditText t6 = (EditText) findViewById(R.id.family);
        t6.setTypeface(tf);
        Button t9 = (Button) findViewById(R.id.button3);
        t9.setTypeface(tf);
        if(pref.contains("username")){
            t3.setText(pref.getString("username",""));
        }
        if(pref.contains("userfamily")){
            t6.setText(pref.getString("userfamily",""));
        }
    }
    public void clickSave(View v){
        SharedPreferences.Editor editor = pref.edit();
        EditText t3 = (EditText) findViewById(R.id.name);
        EditText t6 = (EditText) findViewById(R.id.family);
        String name=t3.getText().toString();
        String family=t6.getText().toString();
        editor.putString("username",name);
        editor.putString("userfamily",family);
        editor.apply();
        //update server

    }
}
