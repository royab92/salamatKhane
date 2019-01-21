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

public class RemoveActivity extends AppCompatActivity {
    SharedPreferences pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove);
        pref=getSharedPreferences("SalamatPref",MODE_PRIVATE);
        Typeface tf = Typeface.createFromAsset(getAssets(), "IRANSansMobile(FaNum).ttf");
        TextView t1=(TextView)findViewById(R.id.exp);
        t1.setTypeface(tf);
        RemoveReq upd=new RemoveReq(RemoveActivity.this);
        JSONObject requestjo = new JSONObject();
        try {
            requestjo.put("DrugId",pref.getInt("drid",0));
            requestjo.put("AppName", "salamatkhane");
            requestjo.put("PhoneNo", pref.getString("phone",""));
            requestjo.put("AppVersion", BuildConfig.VERSION_NAME);
            requestjo.put("DeviceId", pref.getString("deviceid",""));
            requestjo.put("OS", "Android");

            upd.UpdateRecieve(requestjo,new RemoveReq.Update(){
                @Override
                public void onUpdate(int StatusCode, String Message){

                    if(StatusCode!=0){
                        Toast.makeText(RemoveActivity.this,Message,Toast.LENGTH_SHORT).show();
                        Toast.makeText(RemoveActivity.this," بروز خطا",Toast.LENGTH_SHORT).show();
                        Intent i=new Intent(RemoveActivity.this,BuyActivity.class);
                        startActivity(i);
                        finish();
                    }
                    else{
                        Toast.makeText(RemoveActivity.this," حذف موفقیت آمیز درخواست",Toast.LENGTH_SHORT).show();
                        Intent i=new Intent(RemoveActivity.this,BuyActivity.class);
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
