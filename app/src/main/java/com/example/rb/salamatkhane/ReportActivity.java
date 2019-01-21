package com.example.rb.salamatkhane;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class ReportActivity extends AppCompatActivity {
    SharedPreferences pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        pref=getSharedPreferences("SalamatPref",MODE_PRIVATE);
        Typeface tf = Typeface.createFromAsset(getAssets(), "IRANSansMobile(FaNum).ttf");
        TextView t1=(TextView)findViewById(R.id.exp);
        t1.setTypeface(tf);
        ReportReq upd=new ReportReq(ReportActivity.this);
        JSONObject requestjo = new JSONObject();
        try {

            requestjo.put("DrugId",pref.getInt("drid",0));
            requestjo.put("DrugstoreId",pref.getString("stid",""));
            requestjo.put("Description","تمام شده");
            requestjo.put("AppName", "salamatkhane");
            requestjo.put("PhoneNo", pref.getString("phone",""));
            requestjo.put("AppVersion", BuildConfig.VERSION_NAME);
            requestjo.put("DeviceId", pref.getString("deviceid",""));
            requestjo.put("OS", "Android");

            upd.UpdateRecieve(requestjo,new ReportReq.Update(){
                @Override
                public void onUpdate(int StatusCode, String Message){

                    if(StatusCode!=0){
                        Toast.makeText(ReportActivity.this,Message,Toast.LENGTH_SHORT).show();
                        Toast.makeText(ReportActivity.this," بروز خطا",Toast.LENGTH_SHORT).show();
                        Intent i=new Intent(ReportActivity.this,BuyActivity.class);
                        startActivity(i);
                        finish();
                    }
                    else{
                        Toast.makeText(ReportActivity.this," ثبت موفقیت آمیز گزارش",Toast.LENGTH_SHORT).show();
                        Intent i=new Intent(ReportActivity.this,BuyActivity.class);
                        startActivity(i);
                        finish();
                    }
                }
            });
        }catch (JSONException e) {
            e.printStackTrace();
        }

    }
    @Override
    public void onBackPressed() {

    }
}
