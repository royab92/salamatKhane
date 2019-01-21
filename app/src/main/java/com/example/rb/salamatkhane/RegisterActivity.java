package com.example.rb.salamatkhane;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        final Typeface tf = Typeface.createFromAsset(getAssets(), "IRANSansMobile(FaNum).ttf");
        pref = getSharedPreferences("SalamatPref", MODE_PRIVATE);
        editor=pref.edit();
        //BuildConfig.VERSION_NAME
        pref.getString("phone",null);
        pref.getString("deviceid",null);
        GetCities gl = new GetCities(RegisterActivity.this);
        JSONObject requestjo = new JSONObject();
        try {
            requestjo.put("AppName", "salamatkhane");
            requestjo.put("PhoneNo", pref.getString("phone",""));
            requestjo.put("AppVersion", "1.0");
            requestjo.put("DeviceId","" );
            requestjo.put("OS","Android");
            //Toast.makeText(RegisterActivity.this,"try",Toast.LENGTH_SHORT).show();
            gl.CityRecieve(requestjo, new GetCities.Recieve() {

                @Override
                public void onCityRecieve(final ArrayList<State> provs, int status, String message) {
                   // Toast.makeText(RegisterActivity.this,"hello"+String.valueOf(status),Toast.LENGTH_SHORT).show();
                    if (status == 0) { if(provs.size()!=0) {
                        final String [] states=new String[provs.size()+1];
                        states[0]="استان";
                        for(int i=1;i<provs.size()+1;i++){
                            states[i]=provs.get(i-1).name;
                        }
                        Spinner spinner=(Spinner)findViewById(R.id.province);
                        ArrayAdapter adapter = new ArrayAdapter<String>(RegisterActivity.this,R.layout.spinner_item,states){
                            public View getView(int position, View convertView, ViewGroup parent) {
                                View v = super.getView(position, convertView, parent);
                                ((TextView) v).setTypeface(tf);
                                if (position == 0) {
                                    ((TextView) v).setTextColor( getResources().getColor(R.color.black));
                                }
                                return v;
                            }
                            public View getDropDownView(int position,  View convertView,  ViewGroup parent) {
                                View v =super.getDropDownView(position, convertView, parent);
                                ((TextView) v).setTypeface(tf);
                                if (position == 0) {
                                    ((TextView) v).setTextColor( getResources().getColor(R.color.black));
                                }
                                return v;
                            }
                        };
                        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
                        spinner.setAdapter(adapter);
                        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                if(position>0) {
                                    String[] citie = new String[provs.get(position - 1).cities.size() + 1];
                                    editor.putInt("stateid",position);
                                   editor.putString( "statename",provs.get(position-1).name);
                                    editor.apply();
                                    EditText code=(EditText)findViewById(R.id.code);
                                    code.setText(provs.get(position-1).code);
                                    citie[0] = "شهر";
                                    Toast.makeText(RegisterActivity.this,String.valueOf(provs.get(position - 1).cities.size()),Toast.LENGTH_SHORT).show();
                                    for (int j = 1; j < provs.get(position - 1).cities.size()+1; j++) {
                                        citie[j] = provs.get(position- 1).cities.get(j-1).name;
                                    }
                                    ArrayAdapter adapter1 = new ArrayAdapter<String>(RegisterActivity.this, R.layout.spinner_item, citie) {
                                        public View getView(int position, View convertView, ViewGroup parent) {
                                            View v = super.getView(position, convertView, parent);
                                            ((TextView) v).setTypeface(tf);
                                            if (position == 0) {
                                                ((TextView) v).setTextColor(getResources().getColor(R.color.black));
                                            }
                                            return v;
                                        }


                                        public View getDropDownView(int position, View convertView, ViewGroup parent) {
                                            View v = super.getDropDownView(position, convertView, parent);
                                            ((TextView) v).setTypeface(tf);
                                            if (position == 0) {
                                                ((TextView) v).setTextColor(getResources().getColor(R.color.black));
                                            }
                                            return v;
                                        }
                                    };
                                adapter1.setDropDownViewResource(R.layout.spinner_dropdown_item);
                                Spinner spinner1=(Spinner)findViewById(R.id.city);
                                //spinner1.setAdapter(adapter1);
                                adapter1.notifyDataSetChanged(); // this is important for notifying the UI
                                spinner1.setAdapter(adapter1);}else {
                                    String []cities={"شهر"," "};
                                    editor.putInt("stateid",0);
                                    editor.apply();
                                    ArrayAdapter adapter1 = new ArrayAdapter<String>(RegisterActivity.this, R.layout.spinner_item, cities) {
                                        public View getView(int position, View convertView, ViewGroup parent) {
                                            View v = super.getView(position, convertView, parent);
                                            ((TextView) v).setTypeface(tf);
                                            if (position == 0) {
                                                ((TextView) v).setTextColor(getResources().getColor(R.color.black));
                                            }
                                            return v;
                                        }


                                        public View getDropDownView(int position, View convertView, ViewGroup parent) {
                                            View v = super.getDropDownView(position, convertView, parent);
                                            ((TextView) v).setTypeface(tf);
                                            if (position == 0) {
                                                ((TextView) v).setTextColor(getResources().getColor(R.color.black));
                                            }
                                            return v;
                                        }
                                    };
                                    adapter1.setDropDownViewResource(R.layout.spinner_dropdown_item);
                                    Spinner spinner1=(Spinner)findViewById(R.id.city);
                                    //spinner1.setAdapter(adapter1);
                                    adapter1.notifyDataSetChanged(); // this is important for notifying the UI
                                    spinner1.setAdapter(adapter1);
                                    spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                                        @Override
                                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                            if(position>0) {
                                                int spos=pref.getInt("stateid",0);
                                                String[] regions = new String[provs.get(spos-1).cities.get(position-1).regions.size() + 1];
                                                editor.putInt("cityid",position);
                                                if(pref.getInt("stateid",0)!=0){
                                                    editor.putString("cityname", provs.get(spos-1).cities.get(position-1).name);
                                                    editor.apply();
                                                }

                                                editor.apply();
                                                regions[0] = "منطقه";
                                                for (int j = 1; j < provs.get(spos-1).cities.get(position-1).regions.size() + 1; j++) {
                                                    regions[j] = provs.get(spos-1).cities.get(position-1).regions.get(j-1).name;
                                                }
                                                ArrayAdapter adapter2 = new ArrayAdapter<String>(RegisterActivity.this, R.layout.spinner_item, regions) {
                                                    public View getView(int position, View convertView, ViewGroup parent) {
                                                        View v = super.getView(position, convertView, parent);
                                                        ((TextView) v).setTypeface(tf);
                                                        if (position == 0) {
                                                            ((TextView) v).setTextColor(getResources().getColor(R.color.black));
                                                        }
                                                        return v;
                                                    }


                                                    public View getDropDownView(int position, View convertView, ViewGroup parent) {
                                                        View v = super.getDropDownView(position, convertView, parent);
                                                        ((TextView) v).setTypeface(tf);
                                                        if (position == 0) {
                                                            ((TextView) v).setTextColor(getResources().getColor(R.color.black));
                                                        }
                                                        return v;
                                                    }
                                                };
                                                adapter2.setDropDownViewResource(R.layout.spinner_dropdown_item);
                                                Spinner spinner2=(Spinner)findViewById(R.id.distinc);
                                                //spinner1.setAdapter(adapter1);
                                                adapter2.notifyDataSetChanged(); // this is important for notifying the UI
                                                spinner2.setAdapter(adapter2);}else {
                                                String []regions={"منطقه"," "};
                                                editor.putInt("cityid",0);
                                                editor.apply();
                                                ArrayAdapter adapter2 = new ArrayAdapter<String>(RegisterActivity.this, R.layout.spinner_item, regions) {
                                                    public View getView(int position, View convertView, ViewGroup parent) {
                                                        View v = super.getView(position, convertView, parent);
                                                        ((TextView) v).setTypeface(tf);
                                                        if (position == 0) {
                                                            ((TextView) v).setTextColor(getResources().getColor(R.color.black));
                                                        }
                                                        return v;
                                                    }


                                                    public View getDropDownView(int position, View convertView, ViewGroup parent) {
                                                        View v = super.getDropDownView(position, convertView, parent);
                                                        ((TextView) v).setTypeface(tf);
                                                        if (position == 0) {
                                                            ((TextView) v).setTextColor(getResources().getColor(R.color.black));
                                                        }
                                                        return v;
                                                    }
                                                };
                                                adapter2.setDropDownViewResource(R.layout.spinner_dropdown_item);
                                                Spinner spinner2=(Spinner)findViewById(R.id.distinc);
                                                //spinner1.setAdapter(adapter1);
                                                adapter2.notifyDataSetChanged(); // this is important for notifying the UI
                                                spinner2.setAdapter(adapter2);
                                                spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                                                    @Override
                                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                        if (position > 0) {
                                                            editor.putInt("regionid",position);
                                                            if(pref.getInt("stateid",0)!=0&&pref.getInt("cityid",0)!=0){
                                                                editor.putString("regionname", provs.get(pref.getInt("stateid",0)-1)  .cities.get(pref.getInt("cityid",0)-1).regions.get(position-1).name);
                                                                editor.apply();
                                                            }
                                                            editor.apply();
                                                        }else {
                                                            editor.putInt("regionid",0);
                                                            editor.apply();
                                                        }
                                                    }
                                                    @Override
                                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                                        editor.putInt("regionid",0);
                                                        editor.apply();
                                                    }
                                                });
                                            }
                                        }

                                        @Override
                                        public void onNothingSelected(AdapterView<?> adapterView) {
                                            editor.putInt("cityid",0);
                                            editor.apply();
                                        }
                                    });
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {
                                editor.putInt("stateid",0);
                                editor.apply();
                            }
                        });
                    }

                    } else {
                        Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_SHORT).show();
                        Toast.makeText(RegisterActivity.this, "خطا در برقراری ارتباط با سرور", Toast.LENGTH_SHORT).show();
                    }
                    //WriteEA();
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }




        TextView t1=(TextView)findViewById(R.id.textView);
        t1.setTypeface(tf);
        TextView t2=(TextView)findViewById(R.id.textView2);
        t2.setTypeface(tf);
        EditText t3=(EditText)findViewById(R.id.name);
        if(pref.contains("storename")){
            t3.setText(pref.getString("storename",""));
        }
        t3.setTypeface(tf);
        TextView t4=(TextView)findViewById(R.id.textView3);
        t4.setTypeface(tf);
        EditText t5=(EditText)findViewById(R.id.phone);
        if(pref.contains("telephone")){
            t5.setText(pref.getString("telephone",""));
        }
        t5.setTypeface(tf);
        EditText t6=(EditText)findViewById(R.id.code);
        if(pref.contains("phonecode")){
            t6.setText(pref.getString("phonecode",""));
        }
        t6.setTypeface(tf);
        TextView t7=(TextView)findViewById(R.id.textView4);
        t7.setTypeface(tf);
        EditText t8=(EditText)findViewById(R.id.address);
        if(pref.contains("address")){
            t8.setText(pref.getString("address",""));
        }
        t8.setTypeface(tf);
        Button t9=(Button)findViewById(R.id.button3);
        t9.setTypeface(tf);
        if(pref.contains("stateid")){
         int sid=pref.getInt("stateid",0);
         Spinner s1=(Spinner)findViewById(R.id.province);
         s1.setSelection(sid);
        }
        if(pref.contains("cityid")){
            int cid=pref.getInt("cityid",0);
            Spinner s2=(Spinner)findViewById(R.id.city);
            s2.setSelection(cid);
        }
        if(pref.contains("regionid")){
            int rid=pref.getInt("regionid",0);
            Spinner s3=(Spinner)findViewById(R.id.distinc);
            s3.setSelection(rid);
        }
    }
  /*  @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        if (position > 0) {
            //
        }
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }*/
    public void clickSave(View v){
        EditText t3=(EditText)findViewById(R.id.name);
         String sname=t3.getText().toString();
        int sid= pref.getInt("stateid",0);
        int cid=pref.getInt("cityid",0);
        int rid=pref.getInt("regionid",0);
        EditText t4=(EditText)findViewById(R.id.code);
        String phcode=t4.getText().toString();
        EditText t6=(EditText)findViewById(R.id.phone);
        String phonn=t6.getText().toString();
        EditText t5=(EditText)findViewById(R.id.address);
        String address=t5.getText().toString();
        if(sid!=0&&cid!=0&&rid!=0&&!t3.getText().toString().matches("")&&!t4.getText().toString().matches("")&&!t5.getText().toString().matches("")&&!t6.getText().toString().matches("")) {
            Button b = (Button) findViewById(R.id.button3);
            b.setClickable(false);
            b.setEnabled(false);
            b.setFocusable(false);
            b.setBackgroundColor(getColor(R.color.gray));
            editor.putString("storename",t3.getText().toString());
            editor.putString("phonecode",t4.getText().toString());
            editor.putString("telephone",t6.getText().toString());
            editor.putString("address",t5.getText().toString());
            editor.apply();
            final ProgressBar p = (ProgressBar) findViewById(R.id.progressBar);
            p.setVisibility(View.VISIBLE);
            SendInfo upd = new SendInfo(RegisterActivity.this);
            JSONObject requestjo = new JSONObject();
            try {
                requestjo.put("Name", sname);
                requestjo.put("PhoneCode", phcode);
                requestjo.put("DrugstorePhone", phonn);
                requestjo.put("Address", address);
                requestjo.put("StateId", String.valueOf(sid));
                requestjo.put("CityId", String.valueOf(cid));
                requestjo.put("RegionId", String.valueOf(rid));
                requestjo.put("AppName", "salamatkhane");
                requestjo.put("PhoneNo", pref.getString("phone", ""));
                requestjo.put("AppVersion", BuildConfig.VERSION_NAME);
                requestjo.put("DeviceId", pref.getString("deviceid", ""));
                requestjo.put("OS", "Android");

                upd.UpdateRecieve(requestjo, new SendInfo.Update() {
                    @Override
                    public void onUpdate(int StatusCode, String Message) {

                        if (StatusCode != 0) {
                            p.setVisibility(View.INVISIBLE);
                            Toast.makeText(RegisterActivity.this, Message, Toast.LENGTH_SHORT).show();
                        } else {
                            p.setVisibility(View.INVISIBLE);
                            Toast.makeText(RegisterActivity.this, " ثبت موفقیت آمیز اطلاعات", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(RegisterActivity.this, BuyActivity.class);
                            startActivity(i);
                        }
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else {
            Toast.makeText(RegisterActivity.this,"پر کردن تمام فیلدها الزامی است!",Toast.LENGTH_SHORT).show();
        }
    }
}
