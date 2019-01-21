package com.example.rb.salamatkhane;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private static final int MyPermissionRequest=1;
    SharedPreferences pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
     goMaps();


    }

    public void goMaps() {
        if (isOnline()) {

            //if(Build.VERSION.SDK_INT >= 23)
             //   if (MainActivity.this.checkSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED){}
                //String a=""+TelephonyManager.getDeviceId();
            String android_id = Settings.Secure.getString(getApplicationContext().getContentResolver(),
                    Settings.Secure.ANDROID_ID);
            pref = getSharedPreferences("SalamatPref", MODE_PRIVATE);
            SharedPreferences.Editor editor=pref.edit();
            editor.putString("deviceid",android_id);
            editor.apply();
            AppConfig apg = new AppConfig(MainActivity.this);
            JSONObject requestjo = new JSONObject();
            try {
                requestjo.put("AppName", "salamatkhane");
                requestjo.put("PhoneNo","");
                requestjo.put("AppVersion", BuildConfig.VERSION_NAME);
                requestjo.put("DeviceId", android_id);
                requestjo.put("OS", "Android");

                apg.signUp(requestjo, new AppConfig.Configuration() {
                    @Override
                    public void onSignUp( String IosVersion, String AndroidVersion, String IosUrl, String AndroidUrl,boolean IosUpdate, boolean AndroidUpdate, int StatusCode, String Message) {

                        if (StatusCode == 0) {
                            if(AndroidUpdate){
                                final String aurl = AndroidUrl;
                                //namayesh peygham baray update ejbari app
                                AlertDialog.Builder builder;
                                builder = new AlertDialog.Builder(MainActivity.this);
                                builder.setTitle("بروزرسانی")
                                        .setMessage("برای استفاده از برنامه باید آخرین نسخه را دریافت کنید.")
                                        .setCancelable(false)
                                        .setPositiveButton("تایید", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                Intent inte = new Intent();
                                                inte.setAction(Intent.ACTION_VIEW);
                                                inte.addCategory(Intent.CATEGORY_BROWSABLE);
                                                inte.setData(Uri.parse(aurl));
                                                startActivity(inte);
                                            }
                                        })
                                        .setIcon(android.R.drawable.ic_dialog_alert)
                                        .show();

                            }else{
                                Thread RegisterThread = new Thread() {
                                    @Override
                                    public void run() {
                                        try {
                                            super.run();
                                            sleep(2000);  //Delay of 2 seconds
                                        } catch (Exception e) {

                                        } finally {
                                            if(pref.getBoolean("login",false)){
                                                Intent i=new Intent(MainActivity.this,BuyActivity.class);
                                                startActivity(i);
                                                finish();
                                            }
                                            else{
                                                Intent i=new Intent(MainActivity.this,Main2Activity.class);
                                                startActivity(i);}
                                            finish();
                                        }
                                    }
                                };
                                RegisterThread.start();
                            }
                        } else {
                           Toast.makeText(MainActivity.this,Message,Toast.LENGTH_SHORT).show();
                        }
                    }

                });
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(MainActivity.this,"error",Toast.LENGTH_SHORT).show();
            }
        }else {
            new CountDownTimer(10000, 100) {
                public void onTick(long millisUntilFinished) {
                }

                public void onFinish() {
                    AlertDialog.Builder builder;
                    builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("خطا")
                            .setMessage("لطفا اینترنت خود را متصل نمایید!")
                            .setCancelable(true)
                            .setPositiveButton("تلاش مجدد", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                    goMaps();

                                }
                            })
                            .setIcon(R.drawable.wifi)
                            .show();
                }
            }.start();
        }
    }
    public boolean isOnline(){
        ConnectivityManager connectivityManager=(ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
        if(networkInfo==null||!networkInfo.isConnected()||!networkInfo.isAvailable()){
            return false;
        }
        return true;
    }
}