package com.example.rb.salamatkhane;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.TypefaceSpan;
import android.view.LayoutInflater;
import android.view.SubMenu;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MySellActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_sell);
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        Typeface tf = Typeface.createFromAsset(getAssets(), "IRANSansMobile(FaNum).ttf");
        pref = getSharedPreferences("SalamatPref", MODE_PRIVATE);
        editor=pref.edit();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TypefaceSpan typefaceSpan = new TypefaceSpan("IRANSansMobile(FaNum).ttf");
        SpannableString str = new SpannableString("درخواست های فروش داروخانه");
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
                Intent i=new Intent(MySellActivity.this,SellRequestActivity.class);
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

        ReceiveRequests apg = new ReceiveRequests(MySellActivity.this);
        JSONObject requestjo = new JSONObject();
        try {
            //requestjo.put("State", "ForSale");
            requestjo.put("AppName", "salamatkhane");
            requestjo.put("PhoneNo",pref.getString("phone","") );
            requestjo.put("AppVersion", BuildConfig.VERSION_NAME);
            requestjo.put("DeviceId", pref.getString("deviceid",""));
            requestjo.put("OS", "Android");

            apg.DataRecieve(requestjo, new ReceiveRequests.Recieve() {
                @Override
                public void onDataRecieve(ArrayList<Drug> drugs, int StatusCode, String Message) {
                  //  Toast.makeText(MySellActivity.this,String.valueOf(StatusCode),Toast.LENGTH_SHORT).show();
                    if (StatusCode == 0) {
                     //   Toast.makeText(MySellActivity.this,String.valueOf(drugs.size()),Toast.LENGTH_SHORT).show();
                        if(drugs.size()!=0){
                            int dsize=0;
                            for(int j=0;j<drugs.size();j++){
                                if(drugs.get(j).state.matches("ForSale")){
                                    dsize=dsize+1;
                                }
                            }
                            if(dsize!=0){
                                ImageView im=(ImageView)findViewById(R.id.imageView2);
                                im.setVisibility(View.INVISIBLE);
                                TextView t1=(TextView) findViewById(R.id.textView7);
                                t1.setVisibility(View.INVISIBLE);
                                TextView t2=(TextView) findViewById(R.id.textView8);
                                t2.setVisibility(View.INVISIBLE);
                                TextView t3=(TextView) findViewById(R.id.line);
                                t3.setVisibility(View.INVISIBLE);
                                ListView lw=(ListView)findViewById(R.id.mobile_list);
                                lw.setVisibility(View.VISIBLE);

                                String[] dnames=new String[dsize];
                                String[] pnames=new String[dsize];
                                String[] pnumbers=new String[dsize];
                                String[] punits=new String[dsize];
                                String[] pexpires=new String[dsize];
                                String[] snames=new String[dsize];
                                String[] sdists=new String[dsize];
                                String[] scitys=new String[dsize];
                                String[] pmilis=new String[dsize];
                                String[] phones=new String[drugs.size()];
                                int[] ids=new int[dsize];
                                String[] sids=new String[dsize];
                                int j=0;
                                for(int i=0;i<drugs.size();i++) {
                                    if (drugs.get(i).state.matches("ForSale")) {
                                        dnames[j] = drugs.get(i).fname;
                                        pnames[j] = drugs.get(i).company;
                                        pnumbers[j] = String.valueOf(drugs.get(i).count);
                                        punits[j] = drugs.get(i).unit;
                                        pmilis[j] = drugs.get(i).mili;
                                        pexpires[j] = drugs.get(i).expire;
                                        snames[j] = pref.getString("storename","");
                                        sdists[j] = pref.getString("regionname","");
                                        scitys[j] = pref.getString("cityname","");
                                        ids[j]=drugs.get(i).id;
                                        sids[j]="storeid";
                                        phones[j] = pref.getString("telephone","");
                                    }
                                }
                                //images urls
                                CustomAdapter adapter = new CustomAdapter(getApplicationContext(), dnames,pnames,pmilis,pnumbers,punits,pexpires,snames,sdists,scitys,"trash",ids,sids,phones);

                                lw.setAdapter(adapter);
                            }}

                    }     }

            });
        } catch (JSONException e) {
            e.printStackTrace();
        }



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent t=new Intent(MySellActivity.this,SellRequestActivity.class);
                startActivity(t);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);


        ActionBarDrawerToggle toggle1 = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle1);
        toggle1.syncState();
        toggle1.setHomeAsUpIndicator(R.drawable.menu);
        toggle1.setDrawerIndicatorEnabled(true);
        // toggle1.setDrawerArrowDrawable(getResources().getDrawable(R.drawable.menu));
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        Menu m = navigationView .getMenu();

        for (int i=0;i<m.size();i++) {
            MenuItem mi = m.getItem(i);

            //for applying a font to subMenu ...
            SubMenu subMenu = mi.getSubMenu();
            if (subMenu!=null && subMenu.size() >0 ) {
                for (int j=0; j <subMenu.size();j++) {
                    MenuItem subMenuItem = subMenu.getItem(j);
                    SpannableString s = new SpannableString(subMenuItem.getTitle());

                    s.setSpan(new TypefaceSpan("IRANSansMobile(FaNum).ttf"), 0, s.length(),
                            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    subMenuItem.setTitle(s);
                }
            }
        }
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
        getMenuInflater().inflate(R.menu.buy, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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
                Intent intent=new Intent(MySellActivity.this,BuyActivity.class);
                startActivity(intent);
                //your code.
            }
            break;
            case R.id.buyrequest: {
                drawer.closeDrawers();
                Intent intent=new Intent(MySellActivity.this,SellActivity.class);
                startActivity(intent);
                //your code.
            }
            break;
            case R.id.stsell: {
                drawer.closeDrawers();
                // Intent intent=new Intent(BuyActivity.this,MySellActivity.class);
                // startActivity(intent);
                //your code.
            }
            break;
            case R.id.stbuy: {
                drawer.closeDrawers();
                  Intent intent=new Intent(MySellActivity.this,MyBuyActivity.class);
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
                Toast.makeText(MySellActivity.this,"درخواست خود را ایمیل نمایید",Toast.LENGTH_SHORT).show();
                //your code.
            }
            break;
            case R.id.editinfo: {
                drawer.closeDrawers();
                 Intent intent=new Intent(MySellActivity.this,EditInfoActivity.class);
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
                Intent intent=new Intent(MySellActivity.this,Main2Activity.class);
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
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(MySellActivity.this);
                alertDialog.setTitle("معرفی کارمندان");
                alertDialog.setMessage("شماره موبایل کارمند را وارد کنید:");
                final EditText input = new EditText(MySellActivity.this);
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
                                    Toast.makeText(MySellActivity.this,"شماره ای وارد نشده است!",Toast.LENGTH_SHORT).show();
                                }else {
                                    Intro upd = new Intro(MySellActivity.this);
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
                                                    Toast.makeText(MySellActivity.this, Message, Toast.LENGTH_SHORT).show();
                                                    if(StatusCode==4){
                                                        AlertDialog.Builder builder;
                                                        builder = new AlertDialog.Builder(MySellActivity.this);
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
                                                        Toast.makeText(MySellActivity.this, "عملیات موفق", Toast.LENGTH_SHORT).show();
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
}
