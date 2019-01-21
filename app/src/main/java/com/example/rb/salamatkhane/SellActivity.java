package com.example.rb.salamatkhane;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.percent.PercentRelativeLayout;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.InputType;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.TypefaceSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SellActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
      CustomAdapter adapter;
    boolean click1;
    boolean click2;
    boolean click3;
    boolean clickr1;
    boolean clickr2;
    boolean clickr3;
    boolean clickr4;
    boolean clickr5;
    boolean clickr6;
    boolean clickr7;
    boolean clickr8;
    boolean clickr9;
    boolean clickr10;
    boolean clickr11;
    boolean clickr12;
    boolean clickr13;
    boolean clickr14;
    boolean clickr15;
    boolean clickr16;
    boolean clickr17;
    boolean clickr18;
    boolean clickr19;
    boolean clickr20;
    boolean clickr21;
    boolean clickr22;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
       final Typeface tf = Typeface.createFromAsset(getAssets(), "IRANSansMobile(FaNum).ttf");
        pref = getSharedPreferences("SalamatPref", MODE_PRIVATE);
        editor=pref.edit();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TypefaceSpan typefaceSpan = new TypefaceSpan("IRANSansMobile(FaNum).ttf");
        SpannableString str = new SpannableString("درخواست های خرید");
        str.setSpan(typefaceSpan,0, str.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        getSupportActionBar().setTitle(str);
        Drawable dr = getResources().getDrawable(R.drawable.notification);
        Bitmap bitmap = ((BitmapDrawable) dr).getBitmap();
        Drawable d = new BitmapDrawable(getResources(),
                Bitmap.createScaledBitmap(bitmap, 55, 60, true));
        toolbar.setOverflowIcon(d);
       // toolbar.setLogo(R.drawable.salamatkhanelogow);
        TextView t1=(TextView)findViewById(R.id.textView8);
        t1.setTypeface(tf);
        TextView t2=(TextView)findViewById(R.id.textView7);
        t2.setTypeface(tf);
        TextView t3=(TextView)findViewById(R.id.topl);
        t3.setTypeface(tf);
        TextView t4=(TextView)findViewById(R.id.topr);
        t4.setTypeface(tf);
        LayoutInflater inflter;
        inflter = (LayoutInflater.from(getApplicationContext()));
        NavigationView view=(NavigationView)findViewById(R.id.nav_view);
        // View view = inflter.inflate(R.layout.buy_drawer, nv);
        TextView t5=(TextView)view.findViewById(R.id.store);
        t5.setTypeface(tf);
        t5.setText(pref.getString("storename",""));
        //  t5.setText("داروخانه");
        TextView t6=(TextView)view.findViewById(R.id.user);
        t6.setText("نام کاربری: "+pref.getString( "username",""));

       // TextView t6=(TextView)view.findViewById(R.id.user);
        t6.setTypeface(tf);
        TextView t7=(TextView)view.findViewById(R.id.title1);
        t7.setTypeface(tf);
        TextView t8=(TextView)view.findViewById(R.id.title2);
        t8.setTypeface(tf);
        TextView t9=(TextView)view.findViewById(R.id.title3);
        t9.setTypeface(tf);
        TextView t10=(TextView)view.findViewById(R.id.version);
        t10.setTypeface(tf);
        t10.setText("نسخه " +BuildConfig.VERSION_NAME);
        TextView t11=(TextView)view.findViewById(R.id.sellrequest);
        t11.setTypeface(tf);
        TextView t12=(TextView)view.findViewById(R.id.buyrequest);
        t12.setTypeface(tf);
        TextView t13=(TextView)view.findViewById(R.id.stsell);
        t13.setTypeface(tf);
        TextView t14=(TextView)view.findViewById(R.id.stbuy);
        t14.setTypeface(tf);
        TextView t15=(TextView)view.findViewById(R.id.prerequest);
        t15.setTypeface(tf);
        TextView t16=(TextView)view.findViewById(R.id.editstore);
        t16.setTypeface(tf);
        TextView t17=(TextView)view.findViewById(R.id.editinfo);
        t17.setTypeface(tf);
        TextView t18=(TextView)view.findViewById(R.id.account);
        t18.setTypeface(tf);
        TextView t19=(TextView)view.findViewById(R.id.logout);
        t19.setTypeface(tf);
        TextView t20=(TextView)view.findViewById(R.id.rules);
        t20.setTypeface(tf);
        TextView t21=(TextView)view.findViewById(R.id.about);
        t21.setTypeface(tf);
        TextView t22=(TextView)view.findViewById(R.id.contact);
        t22.setTypeface(tf);
        TextView t23=(TextView)view.findViewById(R.id.title4);
        t23.setTypeface(tf);
        TextView t24=(TextView)view.findViewById(R.id.intro);
        t24.setTypeface(tf);
        SpannableString spanString = new SpannableString(t1.getText().toString());
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                Intent i=new Intent(SellActivity.this,SellRequestActivity.class);
                startActivity(i);
            }
            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(getResources().getColor(R.color.gray));
            }
        };

        spanString.setSpan(clickableSpan, 27, 31, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        t1.setText(spanString);
        t1.setMovementMethod(LinkMovementMethod.getInstance());
//pref.getString("deviceid","")
        //BuildConfig.VERSION_NAME
      final  ReceiveDrug apg = new ReceiveDrug(SellActivity.this);
        JSONObject requestjo = new JSONObject();
        try {
            requestjo.put("State", "ForBuy");
            requestjo.put("AppName", "salamatkhane");
            requestjo.put("PhoneNo",pref.getString("phone","") );
            requestjo.put("AppVersion",BuildConfig.VERSION_NAME );
            requestjo.put("DeviceId", pref.getString("deviceid",""));
            requestjo.put("OS", "Android");

            apg.DataRecieve(requestjo, new ReceiveDrug.Recieve() {
                @Override
                public void onDataRecieve(final ArrayList<Drug> drugs, int StatusCode, String Message) {
                   // Toast.makeText(SellActivity.this,apg.msg,Toast.LENGTH_SHORT).show();
                   // Toast.makeText(SellActivity.this,String.valueOf(StatusCode),Toast.LENGTH_SHORT).show();
                    if (StatusCode == 0) {
                      //  Toast.makeText(SellActivity.this,String.valueOf(drugs.size()),Toast.LENGTH_SHORT).show();
                        if(drugs.size()!=0){
                            ImageView im=(ImageView)findViewById(R.id.imageView2);
                            im.setVisibility(View.INVISIBLE);
                            TextView t1=(TextView) findViewById(R.id.textView7);
                            t1.setVisibility(View.INVISIBLE);
                            TextView t2=(TextView) findViewById(R.id.textView8);
                            t2.setVisibility(View.INVISIBLE);
                            TextView t3=(TextView) findViewById(R.id.line);
                            t3.setVisibility(View.INVISIBLE);
                            TextView t4=(TextView) findViewById(R.id.topl);
                            t4.setVisibility(View.VISIBLE);
                            TextView t5=(TextView) findViewById(R.id.topr);
                            t5.setVisibility(View.VISIBLE);
                            TextView t6=(TextView) findViewById(R.id.linev);
                            t6.setVisibility(View.VISIBLE);
                            final ListView lw=(ListView)findViewById(R.id.mobile_list);
                            lw.setVisibility(View.VISIBLE);
                            ImageView im1=(ImageView)findViewById(R.id.imageView8);
                            im1.setVisibility(View.VISIBLE);
                            ImageView im2=(ImageView)findViewById(R.id.imageView9);
                            im2.setVisibility(View.VISIBLE);
                          final String[] dnames=new String[drugs.size()];
                           final  String[] pnames=new String[drugs.size()];
                          final  String[] pnumbers=new String[drugs.size()];
                           final String[] punits=new String[drugs.size()];
                           final String[] pexpires=new String[drugs.size()];
                           final String[] snames=new String[drugs.size()];
                           final String[] sdists=new String[drugs.size()];
                          final  String[] scitys=new String[drugs.size()];
                          final  String[] pmilis=new String[drugs.size()];
                          final  String[] sids=new String[drugs.size()];
                           final int[] ids=new int[drugs.size()];
                           final String[] phones=new String[drugs.size()];
                            for(int i=0;i<drugs.size();i++){
                                dnames[i]=drugs.get(i).fname;
                                pnames[i]=drugs.get(i).company;
                                pnumbers[i]=String.valueOf(drugs.get(i).count);
                                punits[i]=drugs.get(i).unit;
                                pmilis[i]=drugs.get(i).mili;
                                pexpires[i]=drugs.get(i).expire;
                                snames[i]=drugs.get(i).dstore.name;
                                sdists[i]=drugs.get(i).dstore.region;
                                scitys[i]=drugs.get(i).dstore.city;
                                ids[i]=drugs.get(i).id;
                                sids[i]=drugs.get(i).dstore.id;
                                phones[i]=drugs.get(i).dstore.phone;
                            }

                            //images urls
                             adapter = new CustomAdapter(getApplicationContext(), dnames,pnames,pmilis,pnumbers,punits,pexpires,snames,sdists,scitys,"eye",ids,sids,phones);

                            lw.setAdapter(adapter);
                            t4.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    AlertDialog.Builder alt_bld = new AlertDialog.Builder(SellActivity.this);
                                    //alt_bld.setIcon(R.drawable.icon);
                                    final String[]grpname={"تاریخ انقضا نزدیک","تاریخ انقضا دور"};
                                    alt_bld.setTitle("");
                                    alt_bld.setSingleChoiceItems(grpname, -1, new DialogInterface
                                            .OnClickListener() {
                                        public void onClick(DialogInterface dialog, int item) {
                                            if(item==0){

                                                //Collections.sort(arrayList);
                                                //Collections.sort(yourList, Collections.reverseOrder());
                                               Collections.sort(drugs, new Comparator<Drug>() {
                                                    @Override
                                                    public int compare(Drug d1, Drug d2) {
                                                        String[] separated1 = d1.expire.split("/");
                                                        String[] separated2 = d2.expire.split("/");
                                                        if(Integer.parseInt(separated1[0])-Integer.parseInt(separated2[0])==0){
                                                            return Integer.parseInt(separated1[1])-Integer.parseInt(separated2[1]);
                                                        }
                                                        return Integer.parseInt(separated1[0])-Integer.parseInt(separated2[0]); // Ascending
                                                    }
                                                });
                                                for(int i=0;i<drugs.size();i++){
                                                    dnames[i]=drugs.get(i).fname;
                                                    pnames[i]=drugs.get(i).company;
                                                    pnumbers[i]=String.valueOf(drugs.get(i).count);
                                                    punits[i]=drugs.get(i).unit;
                                                    pmilis[i]=drugs.get(i).mili;
                                                    pexpires[i]=drugs.get(i).expire;
                                                    snames[i]=drugs.get(i).dstore.name;
                                                    sdists[i]=drugs.get(i).dstore.region;
                                                    scitys[i]=drugs.get(i).dstore.city;
                                                    ids[i]=drugs.get(i).id;
                                                    sids[i]=drugs.get(i).dstore.id;
                                                    phones[i]=drugs.get(i).dstore.phone;
                                                }
                                                adapter = new CustomAdapter(getApplicationContext(), dnames,pnames,pmilis,pnumbers,punits,pexpires,snames,sdists,scitys,"eye",ids,sids,phones);
                                                lw.setAdapter(adapter);
                                                adapter.notifyDataSetChanged();
                                            }else if(item==1) {
                                                Collections.sort(drugs, new Comparator<Drug>() {
                                                    @Override
                                                    public int compare(Drug d1, Drug d2) {
                                                        String[] separated1 = d1.expire.split("/");
                                                        String[] separated2 = d2.expire.split("/");
                                                        if(Integer.parseInt(separated2[0])-Integer.parseInt(separated1[0])==0){
                                                            return Integer.parseInt(separated2[1])-Integer.parseInt(separated1[1]);
                                                        }
                                                        return Integer.parseInt(separated2[0])-Integer.parseInt(separated1[0]); // Ascending
                                                    }
                                                });
                                                for(int i=0;i<drugs.size();i++){
                                                    dnames[i]=drugs.get(i).fname;
                                                    pnames[i]=drugs.get(i).company;
                                                    pnumbers[i]=String.valueOf(drugs.get(i).count);
                                                    punits[i]=drugs.get(i).unit;
                                                    pmilis[i]=drugs.get(i).mili;
                                                    pexpires[i]=drugs.get(i).expire;
                                                    snames[i]=drugs.get(i).dstore.name;
                                                    sdists[i]=drugs.get(i).dstore.region;
                                                    scitys[i]=drugs.get(i).dstore.city;
                                                    ids[i]=drugs.get(i).id;
                                                    sids[i]=drugs.get(i).dstore.id;
                                                    phones[i]=drugs.get(i).dstore.phone;
                                                }
                                                adapter = new CustomAdapter(getApplicationContext(), dnames,pnames,pmilis,pnumbers,punits,pexpires,snames,sdists,scitys,"eye",ids,sids,phones);
                                                lw.setAdapter(adapter);
                                                adapter.notifyDataSetChanged();
                                            }
                                            dialog.dismiss();// dismiss the alertbox after chose option

                                        }
                                    });
                                    AlertDialog alert = alt_bld.create();
                                    alert.show();
                                }
                            });
                            t5.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    final Dialog dialog = new Dialog(SellActivity.this,R.style.Custom_Dialog);
                                    dialog.setContentView(R.layout.activity_filter);
                                    getWindow()
                                            .setLayout(
                                                    ViewGroup.LayoutParams.FILL_PARENT,
                                                    ViewGroup.LayoutParams.FILL_PARENT
                                            );
                                    set();
                                    final ImageView tg1=(ImageView)dialog.findViewById(R.id.tg1);
                                    final ImageView tg2=(ImageView)dialog.findViewById(R.id.tg2);
                                    final ImageView tg3=(ImageView)dialog.findViewById(R.id.tg3);
                                    final ImageView tr1=(ImageView)dialog.findViewById(R.id.tr1);
                                    final ImageView tr2=(ImageView)dialog.findViewById(R.id.tr2);
                                    final ImageView tr3=(ImageView)dialog.findViewById(R.id.tr3);
                                    final ImageView tr4=(ImageView)dialog.findViewById(R.id.tr4);
                                    final ImageView tr5=(ImageView)dialog.findViewById(R.id.tr5);
                                    final ImageView tr6=(ImageView)dialog.findViewById(R.id.tr6);
                                    final ImageView tr7=(ImageView)dialog.findViewById(R.id.tr7);
                                    final ImageView tr8=(ImageView)dialog.findViewById(R.id.tr8);
                                    final ImageView tr9=(ImageView)dialog.findViewById(R.id.tr9);
                                    final ImageView tr10=(ImageView)dialog.findViewById(R.id.tr10);
                                    final ImageView tr11=(ImageView)dialog.findViewById(R.id.tr11);
                                    final ImageView tr12=(ImageView)dialog.findViewById(R.id.tr12);
                                    final ImageView tr13=(ImageView)dialog.findViewById(R.id.tr13);
                                    final ImageView tr14=(ImageView)dialog.findViewById(R.id.tr14);
                                    final ImageView tr15=(ImageView)dialog.findViewById(R.id.tr15);
                                    final ImageView tr16=(ImageView)dialog.findViewById(R.id.tr16);
                                    final ImageView tr17=(ImageView)dialog.findViewById(R.id.tr17);
                                    final ImageView tr18=(ImageView)dialog.findViewById(R.id.tr18);
                                    final ImageView tr19=(ImageView)dialog.findViewById(R.id.tr19);
                                    final ImageView tr20=(ImageView)dialog.findViewById(R.id.tr20);
                                    final ImageView tr21=(ImageView)dialog.findViewById(R.id.tr21);
                                    final ImageView tr22=(ImageView)dialog.findViewById(R.id.tr22);
                                    setTF(tf,dialog);
                                    final  EditText f5=(EditText) dialog.findViewById(R.id.fromdate);
                                    final EditText f6=(EditText) dialog.findViewById(R.id.todate);
                                    f5.addTextChangedListener(new TextWatcher() {
                                        @Override
                                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                                        }

                                        @Override
                                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                                            String str=s.toString();

                                            if(s.length()>7){
                                                Toast.makeText(SellActivity.this,"تاریخ وارد شده معتبر نیست!",Toast.LENGTH_SHORT).show();
                                                s=s.subSequence(0,7);
                                                String sub=s.subSequence(0,7).toString();
                                                str=sub;
                                                f5.setText(str);
                                                // f5.setCursorVisible(false);
                                                if (!str.matches("\\d{4}/\\d{2}")) {
                                                    Toast.makeText(SellActivity.this,"تاریخ وارد شده معتبر نیست!",Toast.LENGTH_SHORT).show();
                                                }
                                            }

                                        }
                                        @Override
                                        public void afterTextChanged(Editable s) {

                                        }
                                    });

                                    f6.addTextChangedListener(new TextWatcher() {
                                        @Override
                                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                                        }

                                        @Override
                                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                                            String str=s.toString();

                                            if(s.length()>7){
                                                Toast.makeText(SellActivity.this,"تاریخ وارد شده معتبر نیست!",Toast.LENGTH_SHORT).show();
                                                s=s.subSequence(0,7);
                                                String sub=s.subSequence(0,7).toString();
                                                str=sub;
                                                f5.setText(str);
                                                // f5.setCursorVisible(false);
                                                if (!str.matches("\\d{4}/\\d{2}")) {
                                                    Toast.makeText(SellActivity.this,"تاریخ وارد شده معتبر نیست!",Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        }

                                        @Override
                                        public void afterTextChanged(Editable s) {

                                        }
                                    });


                                    tg1.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            if(click1){
                                                tg1.setImageResource(R.drawable.disable);
                                                click1=false;
                                            }else {
                                                tg1.setImageResource(R.drawable.enable);
                                                click1=true;
                                            }
                                        }
                                    });
                                    tg2.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            if(click2){
                                                tg2.setImageResource(R.drawable.disable);
                                                click2=false;
                                            }else {
                                                tg2.setImageResource(R.drawable.enable);
                                                click2=true;
                                            }
                                        }
                                    });
                                    tg3.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            if(click3){
                                                tg3.setImageResource(R.drawable.disable);
                                                click3=false;
                                            }else {
                                                tg3.setImageResource(R.drawable.enable);
                                                click3=true;
                                            }
                                        }
                                    });
                                    tr1.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            if(clickr1){
                                                tr1.setImageResource(R.drawable.disable);
                                                clickr1=false;
                                            }else {
                                                tr1.setImageResource(R.drawable.enable);
                                                clickr1=true;
                                            }
                                        }
                                    });
                                    tr2.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            if(clickr2){
                                                tr2.setImageResource(R.drawable.disable);
                                                clickr2=false;
                                            }else {
                                                tr2.setImageResource(R.drawable.enable);
                                                clickr2=true;
                                            }
                                        }
                                    });
                                    tr3.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            if(clickr3){
                                                tr3.setImageResource(R.drawable.disable);
                                                clickr3=false;
                                            }else {
                                                tr3.setImageResource(R.drawable.enable);
                                                clickr3=true;
                                            }
                                        }
                                    });
                                    tr4.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            if(clickr4){
                                                tr4.setImageResource(R.drawable.disable);
                                                clickr4=false;
                                            }else {
                                                tr4.setImageResource(R.drawable.enable);
                                                clickr4=true;
                                            }
                                        }
                                    });
                                    tr5.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            if(clickr5){
                                                tr5.setImageResource(R.drawable.disable);
                                                clickr5=false;
                                            }else {
                                                tr5.setImageResource(R.drawable.enable);
                                                clickr5=true;
                                            }
                                        }
                                    });
                                    tr6.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            if(clickr6){
                                                tr6.setImageResource(R.drawable.disable);
                                                clickr6=false;
                                            }else {
                                                tr6.setImageResource(R.drawable.enable);
                                                clickr6=true;
                                            }
                                        }
                                    });
                                    tr7.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            if(clickr7){
                                                tr7.setImageResource(R.drawable.disable);
                                                clickr7=false;
                                            }else {
                                                tr7.setImageResource(R.drawable.enable);
                                                clickr7=true;
                                            }
                                        }
                                    });
                                    tr8.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            if(clickr8){
                                                tr8.setImageResource(R.drawable.disable);
                                                clickr8=false;
                                            }else {
                                                tr8.setImageResource(R.drawable.enable);
                                                clickr8=true;
                                            }
                                        }
                                    });
                                    tr9.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            if(clickr9){
                                                tr9.setImageResource(R.drawable.disable);
                                                clickr9=false;
                                            }else {
                                                tr9.setImageResource(R.drawable.enable);
                                                clickr9=true;
                                            }
                                        }
                                    });
                                    tr10.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            if(clickr10){
                                                tr10.setImageResource(R.drawable.disable);
                                                clickr10=false;
                                            }else {
                                                tr10.setImageResource(R.drawable.enable);
                                                clickr10=true;
                                            }
                                        }
                                    });
                                    tr11.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            if(clickr11){
                                                tr11.setImageResource(R.drawable.disable);
                                                clickr11=false;
                                            }else {
                                                tr11.setImageResource(R.drawable.enable);
                                                clickr11=true;
                                            }
                                        }
                                    });
                                    tr12.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            if(clickr12){
                                                tr12.setImageResource(R.drawable.disable);
                                                clickr12=false;
                                            }else {
                                                tr12.setImageResource(R.drawable.enable);
                                                clickr12=true;
                                            }
                                        }
                                    });
                                    tr13.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            if(clickr13){
                                                tr13.setImageResource(R.drawable.disable);
                                                clickr13=false;
                                            }else {
                                                tr13.setImageResource(R.drawable.enable);
                                                clickr13=true;
                                            }
                                        }
                                    });
                                    tr14.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            if(clickr14){
                                                tr14.setImageResource(R.drawable.disable);
                                                clickr14=false;
                                            }else {
                                                tr14.setImageResource(R.drawable.enable);
                                                clickr14=true;
                                            }
                                        }
                                    });
                                    tr15.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            if(clickr15){
                                                tr15.setImageResource(R.drawable.disable);
                                                clickr15=false;
                                            }else {
                                                tr15.setImageResource(R.drawable.enable);
                                                clickr15=true;
                                            }
                                        }
                                    });
                                    tr16.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            if(clickr16){
                                                tr16.setImageResource(R.drawable.disable);
                                                clickr16=false;
                                            }else {
                                                tr16.setImageResource(R.drawable.enable);
                                                clickr16=true;
                                            }
                                        }
                                    });
                                    tr17.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            if(clickr17){
                                                tr17.setImageResource(R.drawable.disable);
                                                clickr17=false;
                                            }else {
                                                tr17.setImageResource(R.drawable.enable);
                                                clickr17=true;
                                            }
                                        }
                                    });
                                    tr18.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            if(clickr18){
                                                tr18.setImageResource(R.drawable.disable);
                                                clickr18=false;
                                            }else {
                                                tr18.setImageResource(R.drawable.enable);
                                                clickr18=true;
                                            }
                                        }
                                    });
                                    tr19.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            if(clickr19){
                                                tr19.setImageResource(R.drawable.disable);
                                                clickr19=false;
                                            }else {
                                                tr19.setImageResource(R.drawable.enable);
                                                clickr19=true;
                                            }
                                        }
                                    });
                                    tr20.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            if(clickr20){
                                                tr20.setImageResource(R.drawable.disable);
                                                clickr20=false;
                                            }else {
                                                tr20.setImageResource(R.drawable.enable);
                                                clickr20=true;
                                            }
                                        }
                                    });
                                    tr21.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            if(clickr21){
                                                tr21.setImageResource(R.drawable.disable);
                                                clickr21=false;
                                            }else {
                                                tr21.setImageResource(R.drawable.enable);
                                                clickr21=true;
                                            }
                                        }
                                    });
                                    tr22.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            if(clickr22){
                                                tr22.setImageResource(R.drawable.disable);
                                                clickr22=false;
                                            }else {
                                                tr22.setImageResource(R.drawable.enable);
                                                clickr22=true;
                                            }
                                        }
                                    });
                                    ImageView close= (ImageView) dialog.findViewById(R.id.imageView4);
                                    close.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            dialog.dismiss();
                                        }
                                    });
                                    Button dialogButton = (Button) dialog.findViewById(R.id.button3);
                                    // if button is clicked, close the custom dialog
                                    dialogButton.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            ArrayList<Drug> ndrugs=new ArrayList<Drug>();
                                            //ndrugs=drugs;
                                            EditText from=(EditText)dialog.findViewById(R.id.fromdate);
                                            String fdate=from.getText().toString();
                                            EditText to=(EditText)dialog.findViewById(R.id.todate);
                                            String tdate=to.getText().toString();
                                            ArrayList<String>regs=new ArrayList<String>();
                                            //  int index=0;
                                            regs=region(clickr1,clickr2,clickr3,clickr4,clickr5,clickr6,clickr7,clickr8,clickr9,clickr10,clickr11,clickr12,clickr13,clickr14,clickr15,clickr16,clickr17,clickr18,clickr19,clickr20,clickr21,clickr22);
                                            if(tdate.matches("")){
                                                tdate="9999/12";
                                            }
                                            if(fdate.matches("")){
                                                fdate="2000/01";
                                            }
                                            int index=regs.size();
                                            for (Drug dr : drugs) {
                                                String[] separated1 = dr.expire.split("/");
                                                String[] separated2 = fdate.split("/");
                                                String[] separated3 = tdate.split("/");
                                                if(Integer.parseInt(separated1[0])>=Integer.parseInt(separated2[0])&&Integer.parseInt(separated1[1])>=Integer.parseInt(separated2[1])&&Integer.parseInt(separated1[0])<=Integer.parseInt(separated3[0])&&Integer.parseInt(separated1[1])<=Integer.parseInt(separated3[1])){
                                                    if(click1&&!click2&&!click3){
                                                        if(dr.category.matches("تجهیزات پزشکی")){
                                                            if(index==0){
                                                                ndrugs.add(dr);
                                                            }else{
                                                                for(int i=0;i<index;i++){
                                                                    if(dr.dstore.region.matches(regs.get(i))){
                                                                        ndrugs.add(dr);
                                                                    }
                                                                }

                                                            }
                                                        }

                                                    }

                                                    if(click2&&!click1&&!click3){
                                                        if(dr.category.matches("دارو")){
                                                            if(index==0){
                                                                ndrugs.add(dr);
                                                            }else{
                                                                for(int i=0;i<index;i++){
                                                                    if(dr.dstore.region.matches(regs.get(i))){
                                                                        ndrugs.add(dr);
                                                                    }
                                                                }

                                                            }
                                                        }
                                                    }
                                                    if(click3&&!click2&&!click1){
                                                        if(dr.category.matches("آرایشی و بهداشتی")){
                                                            if(index==0){
                                                                ndrugs.add(dr);
                                                            }else{
                                                                for(int i=0;i<index;i++){
                                                                    if(dr.dstore.region.matches(regs.get(i))){
                                                                        ndrugs.add(dr);
                                                                    }
                                                                }

                                                            }
                                                        }
                                                    }
                                                    if(click1&&click2&&!click3){
                                                        if(dr.category.matches("تجهیزات پزشکی")&&dr.category.matches("دارو")){
                                                            if(index==0){
                                                                ndrugs.add(dr);
                                                            }else{
                                                                for(int i=0;i<index;i++){
                                                                    if(dr.dstore.region.matches(regs.get(i))){
                                                                        ndrugs.add(dr);
                                                                    }
                                                                }

                                                            }
                                                        }
                                                    }
                                                    if(click1&&click3&&!click2){
                                                        if(dr.category.matches("تجهیزات پزشکی")&&dr.category.matches("آرایشی و بهداشتی")){
                                                            if(index==0){
                                                                ndrugs.add(dr);
                                                            }else{
                                                                for(int i=0;i<index;i++){
                                                                    if(dr.dstore.region.matches(regs.get(i))){
                                                                        ndrugs.add(dr);
                                                                    }
                                                                }

                                                            }
                                                        }
                                                    }
                                                    if(click3&&click2&&!click1){
                                                        if(dr.category.matches("دارو")&&dr.category.matches("آرایشی و بهداشتی")){
                                                            if(index==0){
                                                                ndrugs.add(dr);
                                                            }else{
                                                                for(int i=0;i<index;i++){
                                                                    if(dr.dstore.region.matches(regs.get(i))){
                                                                        ndrugs.add(dr);
                                                                    }
                                                                }

                                                            }
                                                        }
                                                    }
                                                    if(click3&&click2&&click1){
                                                        if(dr.category.matches("تجهیزات پزشکی")&&dr.category.matches("دارو")&&dr.category.matches("آرایشی و بهداشتی")){
                                                            if(index==0){
                                                                ndrugs.add(dr);
                                                            }else{
                                                                for(int i=0;i<index;i++){
                                                                    if(dr.dstore.region.matches(regs.get(i))){
                                                                        ndrugs.add(dr);
                                                                    }
                                                                }

                                                            }
                                                        }
                                                    }
                                                    if(!click1&&!click2&&!click3){
                                                        if(index==0){
                                                            ndrugs.add(dr);
                                                        }else{
                                                            for(int i=0;i<index;i++){
                                                                if(dr.dstore.region.matches(regs.get(i))){
                                                                    ndrugs.add(dr);
                                                                }
                                                            }

                                                        }
                                                    }
                                                }
                                                if(click3&&click2&&click1){
                                                    if(dr.category.matches("تجهیزات پزشکی")&&dr.category.matches("دارو")&&dr.category.matches("آرایشی و بهداشتی")){
                                                        if(index==0){
                                                            ndrugs.add(dr);
                                                        }else{
                                                            for(int i=0;i<index;i++){
                                                                if(dr.dstore.region.matches(regs.get(i))){
                                                                    ndrugs.add(dr);
                                                                }
                                                            }

                                                        }
                                                    }
                                                }
                                            }
                                            final String[] dnames=new String[ndrugs.size()];
                                            final String[] pnames=new String[ndrugs.size()];
                                            final String[] pnumbers=new String[ndrugs.size()];
                                            final String[] punits=new String[ndrugs.size()];
                                            final String[] pexpires=new String[ndrugs.size()];
                                            final String[] snames=new String[ndrugs.size()];
                                            final String[] sdists=new String[ndrugs.size()];
                                            final String[] scitys=new String[ndrugs.size()];
                                            final String[] pmilis=new String[ndrugs.size()];
                                            final String[] sids=new String[ndrugs.size()];
                                            final String[] phones=new String[ndrugs.size()];
                                            final int[] ids=new int[ndrugs.size()];
                                            for(int i=0;i<ndrugs.size();i++){
                                                dnames[i]=ndrugs.get(i).fname;
                                                pnames[i]=ndrugs.get(i).company;
                                                pnumbers[i]=String.valueOf(ndrugs.get(i).count);
                                                punits[i]=ndrugs.get(i).unit;
                                                pmilis[i]=ndrugs.get(i).mili;
                                                pexpires[i]=ndrugs.get(i).expire;
                                                snames[i]=ndrugs.get(i).dstore.name;
                                                sdists[i]=ndrugs.get(i).dstore.region;
                                                scitys[i]=ndrugs.get(i).dstore.city;
                                                ids[i]=ndrugs.get(i).id;
                                                sids[i]=ndrugs.get(i).dstore.id;
                                                phones[i]=ndrugs.get(i).dstore.phone;
                                            }
                                            adapter = new CustomAdapter(getApplicationContext(), dnames,pnames,pmilis,pnumbers,punits,pexpires,snames,sdists,scitys,"eye",ids,sids,phones);
                                            lw.setAdapter(adapter);
                                            adapter.notifyDataSetChanged();
                                            dialog.dismiss();

                                        }
                                    });

                                    dialog.show();
                                    dialog.getWindow().setLayout(PercentRelativeLayout.LayoutParams.MATCH_PARENT, PercentRelativeLayout.LayoutParams.MATCH_PARENT);
                                }
                            });
                        }

                    }     }

            });
        } catch (JSONException e) {
            e.printStackTrace();
        }








        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent t=new Intent(SellActivity.this,SellRequestActivity.class);
                startActivity(t);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        toggle.setHomeAsUpIndicator(R.drawable.menu);
        toggle.setDrawerIndicatorEnabled(true);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sell, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void clickexit(View v){
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawers();
    }
    public void clickitem(View v){
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        switch (v.getId()) {
            case R.id.sellrequest: {
                drawer.closeDrawers();
                Intent intent=new Intent(SellActivity.this,BuyActivity.class);
                startActivity(intent);
                //your code.
            }
            break;
            case R.id.buyrequest: {
                drawer.closeDrawers();

                //your code.
            }
            break;
            case R.id.stsell: {
                drawer.closeDrawers();
                 Intent intent=new Intent(SellActivity.this,MySellActivity.class);
                 startActivity(intent);
                //your code.
            }
            break;
            case R.id.stbuy: {
                drawer.closeDrawers();
                   Intent intent=new Intent(SellActivity.this,MyBuyActivity.class);
                 startActivity(intent);
                //your code.
            }
            break;
            case R.id.prerequest: {
                drawer.closeDrawers();

                //your code.
            }
            break;
            case R.id.editstore: {
                drawer.closeDrawers();
                Toast.makeText(SellActivity.this,"درخواست خود را ایمیل نمایید",Toast.LENGTH_SHORT).show();
                //your code.
            }
            break;
            case R.id.editinfo: {
                drawer.closeDrawers();
                Intent intent=new Intent(SellActivity.this,EditInfoActivity.class);
                startActivity(intent);
                //your code.
            }
            break;
            case R.id.account: {
                drawer.closeDrawers();

                //your code.
            }
            break;
            case R.id.logout: {
                drawer.closeDrawers();
                editor.putBoolean("login",false);
                editor.apply();
                Intent intent=new Intent(SellActivity.this,Main2Activity.class);
                startActivity(intent);
                finish();
                //your code.
            }
            break;
            case R.id.rules: {
                drawer.closeDrawers();

                //your code.
            }
            break;
            case R.id.about: {
                drawer.closeDrawers();

                //your code.
            }
            break;
            case R.id.contact: {
                drawer.closeDrawers();

                //your code.
            }
            break;
            case R.id.intro: {
                drawer.closeDrawers();
                final String phone=pref.getString("phone",null);
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(SellActivity.this);
                alertDialog.setTitle("معرفی کارمندان");
                alertDialog.setMessage("شماره موبایل کارمند را وارد کنید:");
                final EditText input = new EditText(SellActivity.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(lp);
                input.setTextColor(getResources().getColor(R.color.gray));
                input.setInputType(InputType.TYPE_CLASS_NUMBER);
                alertDialog.setCancelable(true);
                alertDialog.setView(input);
                alertDialog.setIcon(android.R.drawable.ic_dialog_info);
                alertDialog.setPositiveButton("تایید",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                if(input.getText().toString().matches("")){
                                    Toast.makeText(SellActivity.this,"شماره ای وارد نشده است!",Toast.LENGTH_SHORT).show();
                                }else {
                                    Intro upd = new Intro(SellActivity.this);
                                    JSONObject requestjo = new JSONObject();
                                    try {
                                        requestjo.put("MemberPhoneNo", input.getText().toString());
                                        requestjo.put("Role", "viewer");
                                        requestjo.put("AppName", "salamatkhane");
                                        requestjo.put("PhoneNo",pref.getString("phone","") );
                                        requestjo.put("AppVersion", BuildConfig.VERSION_NAME);
                                        requestjo.put("DeviceId", pref.getString("deviceid",""));
                                        requestjo.put("OS", "Android");
                                        upd.introRecieve(requestjo, new Intro.intro() {
                                            @Override
                                            public void onintro(int StatusCode, String Message) {

                                                if (StatusCode != 0) {
                                                    Toast.makeText(SellActivity.this, Message, Toast.LENGTH_SHORT).show();
                                                    if(StatusCode==4){
                                                        AlertDialog.Builder builder;
                                                        builder = new AlertDialog.Builder(SellActivity.this);
                                                        builder.setTitle("اخطار")
                                                                .setMessage(" شما نمی توانید معرف خودتان باشید!")
                                                                .setCancelable(true)
                                                                .setPositiveButton("باشه", new DialogInterface.OnClickListener() {
                                                                    public void onClick(DialogInterface dialog, int which) {

                                                                    }
                                                                })
                                                                .setIcon(android.R.drawable.ic_dialog_alert)
                                                                .show();
                                                    }else {
                                                        Toast.makeText(SellActivity.this, "عملیات موفق", Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            }
                                        });
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        });
                alertDialog.show();
                //your code.
            }
            break;
        }
    }
    ArrayList<String> region(boolean c1,boolean c2,boolean c3,boolean c4,boolean c5,boolean c6,boolean c7,boolean c8,boolean c9,boolean c10,boolean c11,boolean c12,boolean c13,boolean c14,boolean c15,boolean c16,boolean c17,boolean c18,boolean c19,boolean c20,boolean c21,boolean c22){
        ArrayList<String> regs=new ArrayList<String>();
        if(c1){
            regs.add("منطقه1");
        }
        if(c2){
            regs.add("منطقه2");
        }
        if(c3){
            regs.add("منطقه3");
        }
        if(c4){
            regs.add("منطقه4");
        }
        if(c5){
            regs.add("منطقه5");
        }
        if(c6){
            regs.add("منطقه6");
        }
        if(c7){
            regs.add("منطقه7");
        }
        if(c8){
            regs.add("منطقه8");
        }
        if(c9){
            regs.add("منطقه9");
        }
        if(c10){
            regs.add("منطقه10");
        }
        if(c11){
            regs.add("منطقه11");
        }
        if(c12){
            regs.add("منطقه12");
        }
        if(c13){
            regs.add("منطقه13");
        }
        if(c14){
            regs.add("منطقه14");
        }
        if(c15){
            regs.add("منطقه15");
        }
        if(c16){
            regs.add("منطقه16");
        }
        if(c17){
            regs.add("منطقه17");
        }
        if(c18){
            regs.add("منطقه18");
        }
        if(c19){
            regs.add("منطقه19");
        }
        if(c20){
            regs.add("منطقه20");
        }
        if(c21){
            regs.add("منطقه21");
        }
        if(c22){
            regs.add("منطقه22");
        }
        return regs;
    }
    public void set(){
        click1=false;
        click2=false;
        click3=false;
        clickr1=false;
        clickr2=false;
        clickr3=false;
        clickr4=false;
        clickr5=false;
        clickr6=false;
        clickr7=false;
        clickr8=false;
        clickr9=false;
        clickr10=false;
        clickr11=false;
        clickr12=false;
        clickr13=false;
        clickr14=false;
        clickr15=false;
        clickr16=false;
        clickr17=false;
        clickr18=false;
        clickr19=false;
        clickr20=false;
        clickr21=false;
        clickr22=false;
    }
    public void setTF(Typeface tf,Dialog dialog){
        TextView f1=(TextView)dialog.findViewById(R.id.part1);
        f1.setTypeface(tf);
        TextView f2=(TextView)dialog.findViewById(R.id.part2);
        f2.setTypeface(tf);
        TextView f3=(TextView)dialog.findViewById(R.id.part3);
        f3.setTypeface(tf);
        Button f4=(Button) dialog.findViewById(R.id.button3);
        f4.setTypeface(tf);
        final  EditText f5=(EditText) dialog.findViewById(R.id.fromdate);
        f5.setTypeface(tf);
        final EditText f6=(EditText) dialog.findViewById(R.id.todate);
        f6.setTypeface(tf);
        TextView f7=(TextView)dialog.findViewById(R.id.g1);
        f7.setTypeface(tf);
        TextView f8=(TextView)dialog.findViewById(R.id.g2);
        f8.setTypeface(tf);
        TextView f9=(TextView)dialog.findViewById(R.id.g3);
        f9.setTypeface(tf);
        TextView f10=(TextView)dialog.findViewById(R.id.r1);
        f10.setTypeface(tf);
        TextView f11=(TextView)dialog.findViewById(R.id.r2);
        f11.setTypeface(tf);
        TextView f12=(TextView)dialog.findViewById(R.id.r3);
        f12.setTypeface(tf);
        TextView f13=(TextView)dialog.findViewById(R.id.r4);
        f13.setTypeface(tf);
        TextView f14=(TextView)dialog.findViewById(R.id.r5);
        f14.setTypeface(tf);
        TextView f15=(TextView)dialog.findViewById(R.id.r6);
        f15.setTypeface(tf);
        TextView f16=(TextView)dialog.findViewById(R.id.r7);
        f16.setTypeface(tf);
        TextView f17=(TextView)dialog.findViewById(R.id.r8);
        f17.setTypeface(tf);
        TextView f18=(TextView)dialog.findViewById(R.id.r9);
        f18.setTypeface(tf);
        TextView f19=(TextView)dialog.findViewById(R.id.r10);
        f19.setTypeface(tf);
        TextView f20=(TextView)dialog.findViewById(R.id.r11);
        f20.setTypeface(tf);
        TextView f21=(TextView)dialog.findViewById(R.id.r12);
        f21.setTypeface(tf);
        TextView f22=(TextView)dialog.findViewById(R.id.r13);
        f22.setTypeface(tf);
        TextView f23=(TextView)dialog.findViewById(R.id.r14);
        f23.setTypeface(tf);
        TextView f24=(TextView)dialog.findViewById(R.id.r15);
        f24.setTypeface(tf);
        TextView f25=(TextView)dialog.findViewById(R.id.r16);
        f25.setTypeface(tf);
        TextView f26=(TextView)dialog.findViewById(R.id.r17);
        f26.setTypeface(tf);
        TextView f27=(TextView)dialog.findViewById(R.id.r18);
        f27.setTypeface(tf);
        TextView f28=(TextView)dialog.findViewById(R.id.r19);
        f28.setTypeface(tf);
        TextView f29=(TextView)dialog.findViewById(R.id.r20);
        f29.setTypeface(tf);
        TextView f30=(TextView)dialog.findViewById(R.id.r21);
        f30.setTypeface(tf);
        TextView f31=(TextView)dialog.findViewById(R.id.r22);
        f31.setTypeface(tf);
        TextView f32=(TextView)dialog.findViewById(R.id.textView14);
        f32.setTypeface(tf);
        TextView f33=(TextView)dialog.findViewById(R.id.textf);
        f33.setTypeface(tf);
        TextView f34=(TextView)dialog.findViewById(R.id.textt);
        f34.setTypeface(tf);
    }
}
