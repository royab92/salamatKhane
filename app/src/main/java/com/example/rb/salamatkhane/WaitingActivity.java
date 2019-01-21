package com.example.rb.salamatkhane;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class WaitingActivity extends AppCompatActivity {
    private String code;
    SharedPreferences pref;
    private String phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting);
        pref=getSharedPreferences("SalamatPref",MODE_PRIVATE);
        phone=pref.getString("phone",null);
        code=pref.getString("code",null);
        Typeface tf = Typeface.createFromAsset(getAssets(), "IRANSansMobile(FaNum).ttf");
        TextView t1=(TextView)findViewById(R.id.exp);
        t1.setTypeface(tf);
        Activation asd=new Activation(WaitingActivity.this);
        JSONObject requestjo=new JSONObject();
        try{
            requestjo.put("Code",code);
            requestjo.put("ReagentNo",pref.getString("moaref",""));
            requestjo.put("FirstName","");
            requestjo.put("LastName","");
            requestjo.put("AppName","salamatkhane");
            requestjo.put("PhoneNo",phone);
            requestjo.put("AppVersion",BuildConfig.VERSION_NAME);
            requestjo.put("DeviceId",pref.getString("deviceid",null));
            requestjo.put("OS","Android");
            asd.Response(requestjo,new Activation.Responses(){
                @Override
                public void getResponse(boolean should,int status,String message ) {
                    if(status==0){
                        if(should) {
                            //msg.setText(message);
                            SharedPreferences.Editor editor=getSharedPreferences("SalamatPref",MODE_PRIVATE).edit();
                            editor.remove("phone");
                            editor.apply();
                            //Toast.makeText(Waiting.this,String.valueOf(status),Toast.LENGTH_SHORT).show();
                            Toast.makeText(WaitingActivity.this,"بروز خطا، اطلاعات داروخانه کامل نیست",Toast.LENGTH_SHORT).show();
                            Toast.makeText(WaitingActivity.this,message,Toast.LENGTH_SHORT).show();
                            Intent ret2=new Intent(WaitingActivity.this,RegisterActivity.class);
                            startActivity(ret2);
                            finish();
                        }else {
                        Toast.makeText(WaitingActivity.this, "فعال سازی و ورود موفق", Toast.LENGTH_SHORT).show();
                        SharedPreferences.Editor editor=getSharedPreferences("SalamatPref",MODE_PRIVATE).edit();
                        editor.putBoolean("login",true);
                        editor.apply();
                        Intent i=new Intent(WaitingActivity.this,
                                BuyActivity.class);
                        startActivity(i);
                        finish();}
                    }
                    if(status==1) {
                        //msg.setText(message);
                        SharedPreferences.Editor editor=getSharedPreferences("SalamatPref",MODE_PRIVATE).edit();
                        editor.remove("phone");
                        editor.apply();
                        //Toast.makeText(Waiting.this,String.valueOf(status),Toast.LENGTH_SHORT).show();
                        Toast.makeText(WaitingActivity.this,"بروز خطا, کد وارد شده اشتباه است",Toast.LENGTH_SHORT).show();
                        Toast.makeText(WaitingActivity.this,message,Toast.LENGTH_SHORT).show();
                        Intent ret2=new Intent(WaitingActivity.this,Main2Activity.class);
                        startActivity(ret2);
                        finish();
                    }
                    if(status==2) {
                        SharedPreferences.Editor editor=getSharedPreferences("SalamatPref",MODE_PRIVATE).edit();
                        editor.remove("phone");
                        editor.apply();
                        //Toast.makeText(Waiting.this,String.valueOf(status),Toast.LENGTH_SHORT).show();
                        Toast.makeText(WaitingActivity.this,"بروز خطا, کد معرف اشتباه است",Toast.LENGTH_SHORT).show();
                        Toast.makeText(WaitingActivity.this,message,Toast.LENGTH_SHORT).show();
                        Intent ret2=new Intent(WaitingActivity.this,Main2Activity.class);
                        startActivity(ret2);
                        finish();
                    }
                }
            });
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {

    }
}
