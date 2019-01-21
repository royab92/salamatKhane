package com.example.rb.salamatkhane;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

public class AdminRegisterActivity extends AppCompatActivity {
    int pstatus = 0;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_register);
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
        EditText t5 = (EditText) findViewById(R.id.mphone);
        t5.setTypeface(tf);
        EditText t6 = (EditText) findViewById(R.id.family);
        t6.setTypeface(tf);
        TextView t7 = (TextView) findViewById(R.id.textView4);
        t7.setTypeface(tf);
        Button t9 = (Button) findViewById(R.id.button3);
        t9.setTypeface(tf);
        TextView t10 = (TextView) findViewById(R.id.textView5);
        t10.setTypeface(tf);
        TextView t14 = (TextView) findViewById(R.id.textView6);
        t14.setTypeface(tf);
        final EditText t11 = (EditText) findViewById(R.id.acode);
        t11.setTypeface(tf);
        final EditText t15 = (EditText) findViewById(R.id.moaref);
        t15.setTypeface(tf);

        final TextView t12 = (TextView) findViewById(R.id.timer);
        t12.setTypeface(tf);
        Button t13 = (Button) findViewById(R.id.button4);
        t13.setTypeface(tf);
        t11.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 5) {
                    t11.setCursorVisible(false);
                    t11.setFocusable(false);
                    SharedPreferences.Editor editor = getSharedPreferences("SalamatPref", MODE_PRIVATE).edit();
                    String code = t11.getText().toString();
                    editor.putString("code", code);
                    editor.apply();
                    Intent wait = new Intent(AdminRegisterActivity.this, WaitingActivity.class);
                    startActivity(wait);
                    finish();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    public void Retry(View v) {

        Intent h = new Intent(AdminRegisterActivity.this,
                AdminRegisterActivity.class);
        startActivity(h);
        finish();
        //bad az 1 min emkan darad
    }

    public void clickSave(View v) {
        EditText t5 = (EditText) findViewById(R.id.mphone);

        TextView mo=(TextView)findViewById(R.id.textView6);
        EditText t1 = (EditText) findViewById(R.id.moaref);

        String mob = "";
        mob = t5.getText().toString();
        String moaref = "";
        moaref = t1.getText().toString();
        if (moaref != null && moaref.length() == 11) {
            if (moaref.contains(" ") || moaref.contains(",") || moaref.contains(".") || moaref.contains(";") || moaref.contains("-") || moaref.contains("/") || moaref.contains("N") || moaref.contains("*") || moaref.contains("#") || moaref.contains("(") || moaref.contains(")") || moaref.contains("+")) {
                Toast.makeText(AdminRegisterActivity.this, "شماره معرف به درستی وارد نشده است!", Toast.LENGTH_SHORT).show();
            }
        }



        if (mob != null && mob.length() == 11) {
            if (mob.contains(" ") || mob.contains(",") || mob.contains(".") || mob.contains(";") || mob.contains("-") || mob.contains("/") || mob.contains("N") || mob.contains("*") || mob.contains("#") || mob.contains("(") || mob.contains(")") || mob.contains("+")) {
                Toast.makeText(AdminRegisterActivity.this, "شماره موبایل به درستی وارد نشده است!", Toast.LENGTH_SHORT).show();
            } else {
                EditText t3 = (EditText) findViewById(R.id.name);
                EditText t6 = (EditText) findViewById(R.id.family);
                if(!t3.getText().toString().matches("")&&!t6.getText().toString().matches("")) {
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("phone", mob);
                    editor.putString("username",t3.getText().toString());
                    editor.putString("userfamily",t6.getText().toString());
                    editor.apply();
                    t5.setFocusable(false);
                    t5.setEnabled(false);
                    t5.setClickable(false);
                    t1.setFocusable(false);
                    t1.setEnabled(false);
                    t1.setClickable(false);
                    Button t9 = (Button) findViewById(R.id.button3);
                    t9.setBackgroundColor(getResources().getColor(R.color.gray));
                    t9.setClickable(false);
                    t9.setEnabled(false);
                    mo.setVisibility(View.GONE);
                    t1.setVisibility(View.GONE);

                    t3.setFocusable(false);
                    t3.setEnabled(false);
                    t3.setClickable(false);

                    t6.setFocusable(false);
                    t6.setEnabled(false);
                    t6.setClickable(false);

                    Register asd = new Register(AdminRegisterActivity.this);
                    JSONObject requestjo = new JSONObject();
                    try {
                        requestjo.put("AppName", "salamatkhane");
                        requestjo.put("PhoneNo", mob);
                        requestjo.put("AppVersion", BuildConfig.VERSION_NAME);
                        requestjo.put("Deviceid", pref.getString("deviceid", null));
                        requestjo.put("OS", "Android");

                        asd.signUp(requestjo, new Register.onSignUpComplate() {
                            @Override
                            public void onSignUp(String code, int status, String message) {
                                if (status == 0) {
                                    TextView t10 = (TextView) findViewById(R.id.textView5);
                                    t10.setVisibility(View.VISIBLE);
                                    final EditText t11 = (EditText) findViewById(R.id.acode);
                                    t11.setVisibility(View.VISIBLE);
                                    final TextView t12 = (TextView) findViewById(R.id.timer);
                                    t12.setVisibility(View.VISIBLE);
                                    final ProgressBar load = (android.widget.ProgressBar) findViewById(R.id.load);
                                    load.setVisibility(View.VISIBLE);
                                    load.setProgress(0);
                                    load.setMax(60);

                                    new CountDownTimer(60000, 1000) {
                                        public void onTick(long millisUntilFinished) {
                                            pstatus += 1;
                                            load.setProgress(pstatus);
                                            t12.setText("" + String.format("%d ثانیه",
                                                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                                        }

                                        public void onFinish() {
                                            t12.setText(" ");
                                            load.setVisibility(View.GONE);
                                            Button t13 = (Button) findViewById(R.id.button4);
                                            t13.setEnabled(true);
                                            t13.setVisibility(View.VISIBLE);
                                        }
                                    }.start();
                                } else {
                                    Toast.makeText(AdminRegisterActivity.this, message, Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }else{
                    Toast.makeText(AdminRegisterActivity.this,"پر کردن تمام فیلدها الزامی است!",Toast.LENGTH_SHORT).show();
                }
            }
        }else{
            Toast.makeText(AdminRegisterActivity.this, "شماره موبایل به درستی وارد نشده است!", Toast.LENGTH_SHORT).show();
        }
    }
}
