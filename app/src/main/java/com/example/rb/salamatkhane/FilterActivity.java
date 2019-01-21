package com.example.rb.salamatkhane;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.TypefaceSpan;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

public class FilterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        Typeface tf = Typeface.createFromAsset(getAssets(), "IRANSansMobile(FaNum).ttf");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        TypefaceSpan typefaceSpan = new TypefaceSpan("IRANSansMobile(FaNum).ttf");
        SpannableString str = new SpannableString("فیلتر کردن درخواست ها");
        str.setSpan(typefaceSpan,0, str.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        getSupportActionBar().setTitle(str);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back);
        //FrameLayout content=(FrameLayout)findViewById(R.id.t)
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void clickprod(View v){

    }
    public void clickprov(View v){

    }
    public void clickcity(View v){

    }
    public void clickdist(View v){

    }
    public void clickstores(View v){

    }
    public void clickexpire(View v){

    }
    public void clickfilter(View v){

    }
}
