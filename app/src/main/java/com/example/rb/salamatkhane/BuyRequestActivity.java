package com.example.rb.salamatkhane;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.TypefaceSpan;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class BuyRequestActivity extends AppCompatActivity {
    SharedPreferences pref;
    private static int RESULT_LOAD_IMAGE = 1;
    private static final int CAMERA_REQUEST = 1888;
    private ImageView imageView;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_request);
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        Typeface tf = Typeface.createFromAsset(getAssets(), "IRANSansMobile(FaNum).ttf");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       // toolbar.setLogo(R.drawable.salamatkhanelogow);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        pref = getSharedPreferences("SalamatPref", MODE_PRIVATE);
      //  getSupportActionBar().setDisplayShowTitleEnabled(false);
        TypefaceSpan typefaceSpan = new TypefaceSpan("IRANSansMobile(FaNum).ttf");
        SpannableString str = new SpannableString("ثبت درخواست خرید");
        str.setSpan(typefaceSpan,0, str.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        getSupportActionBar().setTitle(str);
        //getSupportActionBar().setTitle("ثبت درخواست خرید");
        Drawable dr = getResources().getDrawable(R.drawable.back);
        Bitmap bitmap = ((BitmapDrawable) dr).getBitmap();
        Drawable d = new BitmapDrawable(getResources(),
                Bitmap.createScaledBitmap(bitmap, 60, 60, true));
        getSupportActionBar().setHomeAsUpIndicator(d);
        TextView t1=(TextView)findViewById(R.id.textView);
        t1.setTypeface(tf);
        TextView t2=(TextView)findViewById(R.id.textView4);
        t2.setTypeface(tf);
        EditText t3=(EditText) findViewById(R.id.group);
        t3.setTypeface(tf);
        TextView t4=(TextView)findViewById(R.id.textView5);
        t4.setTypeface(tf);
        EditText t5=(EditText) findViewById(R.id.gname);
        t5.setTypeface(tf);
        TextView t6=(TextView)findViewById(R.id.textView6);
        t6.setTypeface(tf);
        EditText t7=(EditText) findViewById(R.id.producer);
        t7.setTypeface(tf);
        TextView t8=(TextView)findViewById(R.id.textView7);
        t8.setTypeface(tf);
        EditText t9=(EditText) findViewById(R.id.number);
        t9.setTypeface(tf);
        TextView t10=(TextView)findViewById(R.id.textView8);
        t10.setTypeface(tf);
        EditText t11=(EditText) findViewById(R.id.vahed);
        t11.setTypeface(tf);
        TextView t12=(TextView)findViewById(R.id.textView9);
        t12.setTypeface(tf);
       // EditText t13=(EditText) findViewById(R.id.expire);
       // t13.setTypeface(tf);
        Button t14=(Button) findViewById(R.id.button3);
        t14.setTypeface(tf);
        TextView t15=(TextView)findViewById(R.id.textView10);
        t15.setTypeface(tf);
        TextView t16=(TextView)findViewById(R.id.textView11);
        t16.setTypeface(tf);
        EditText t17=(EditText) findViewById(R.id.mili);
        t17.setTypeface(tf);
    }
    public void clickSave(View v){
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(BuyRequestActivity.this);
        builder.setTitle("")
                .setMessage("آیا می خواهید درخواست خود را ثبت کنید؟")
                .setCancelable(true)
                .setPositiveButton("بله، ذخیره شود", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        EditText t3=(EditText) findViewById(R.id.group);
                        EditText t5=(EditText) findViewById(R.id.gname);
                        EditText t7=(EditText) findViewById(R.id.producer);
                        EditText t9=(EditText) findViewById(R.id.number);
                        EditText t11=(EditText) findViewById(R.id.vahed);
                        EditText t13=(EditText) findViewById(R.id.expire);
                        String name=t5.getText().toString();
                        String cate=t3.getText().toString();
                        String company=t7.getText().toString();
                        String count=t9.getText().toString();
                        String unit=t11.getText().toString();
                        EditText t17=(EditText) findViewById(R.id.mili);
                        String mili=t17.getText().toString();
                        if(!name.matches("")&&!cate.matches("")&&!company.matches("")&&!count.matches("")&&!unit.matches("")&&!mili.matches("")){
                        //String expire=t13.getText().toString();
                        RequestBS upd=new RequestBS(BuyRequestActivity.this);
                        JSONObject requestjo = new JSONObject();
                        try {
                            requestjo.put("FaName",name);
                            requestjo.put("EnName","");
                            requestjo.put("EnCommercialName",name);
                            requestjo.put("FaCommercialName",name);
                            requestjo.put("Company",company);
                            requestjo.put("Miligram",mili);
                            requestjo.put("Unit",unit);
                            requestjo.put("Count",Integer.parseInt(count));
                            requestjo.put("State","ForBuy");
                            requestjo.put("ExpireDate","");
                            requestjo.put("AppName", "salamatkhane");
                            requestjo.put("PhoneNo", pref.getString("phone",""));
                            requestjo.put("AppVersion", BuildConfig.VERSION_NAME);
                            requestjo.put("DeviceId", pref.getString("deviceid",""));
                            requestjo.put("OS", "Android");
                            requestjo.put("Category",cate);
                            upd.RequestRecieve(requestjo,new RequestBS.Requestbs(){
                                @Override
                                public void onRequest(int StatusCode, String Message){

                                    if(StatusCode!=0){
                                        Toast.makeText(BuyRequestActivity.this,Message,Toast.LENGTH_SHORT).show();
                                    }
                                    else{

                                        Toast.makeText(BuyRequestActivity.this," ثبت موفقیت آمیز درخواست",Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });
                        }catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }else{
                            Toast.makeText(BuyRequestActivity.this,"پر کردن تمام فیلدها الزامی است!",Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("خیر، حذف شود", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent=new Intent(BuyRequestActivity.this,SellRequestActivity.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_info)
                .show();

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
    public void clickpic(View v){
        AlertDialog.Builder builder;
        builder = new AlertDialog.Builder(BuyRequestActivity.this);
        builder
                .setMessage("از طریق یکی از دو روش زیر تصویر کالا را انتخاب نمایید:")
                .setCancelable(true)
                .setPositiveButton("دوربین", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (checkSelfPermission(Manifest.permission.CAMERA)
                                != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(BuyRequestActivity.this,new String[]{Manifest.permission.CAMERA},
                                    MY_CAMERA_PERMISSION_CODE);
                        } else {
                            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(cameraIntent, CAMERA_REQUEST);
                        }
                    }
                })
        .setNegativeButton("گالری", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new
                        Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            } else {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }

        }}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            ImageView imageView = (ImageView) findViewById(R.id.pic);
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
            Uri imageUri = data.getData();
            imageView.setImageURI(imageUri);
        }
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageView = (ImageView) findViewById(R.id.pic);
            imageView.setImageBitmap(photo);
        }
    }
}
