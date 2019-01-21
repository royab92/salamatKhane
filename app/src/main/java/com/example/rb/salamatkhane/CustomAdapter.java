package com.example.rb.salamatkhane;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import static android.content.Context.MODE_PRIVATE;

public class CustomAdapter extends BaseAdapter {
    Context context;
    String[] dnames;
    String[] pnames;
    String[] pnumbers;
    String[] punits;
    String[] pexpires;
    String[] snames;
    String[] sdists;
    String[] scitys;
    String[] urls;
    String[] pmili;
    LayoutInflater inflter;
    Typeface tf;
    String image;
    final int[]ids;
    String[]sids;
    String[] phones;
    Context ctx;
    public CustomAdapter(Context applicationContext,  String[] dnames, String[] pnames ,String[] pmili,String[] pnumbers,String[] punits, String[] pexpires,String[] snames, String[] sdists, String[] scitys,String image,int[]ids,String[]sids,String[]phones ) {
        this.context = applicationContext;
        this.dnames = dnames;
        this.pnames = pnames;
        this.pnumbers=pnumbers;
        this.pexpires=pexpires;
        this.snames=snames;
        this.sdists=sdists;
        this.scitys=scitys;
        this.punits=punits;
        this.pmili=pmili;
        this.image=image;
        this.ids=ids;
        this.sids=sids;
        this.phones=phones;
        ctx=applicationContext;
tf=Typeface.createFromAsset(applicationContext.getAssets(),"IRANSansMobile(FaNum).ttf");
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return dnames.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.list_item, null);
        final View vi=view;
        TextView drnam = (TextView)view.findViewById(R.id.name);
        drnam.setTypeface(tf);
        drnam.setText(dnames[i]);
        TextView prnam = (TextView)view.findViewById(R.id.producer);
        prnam.setTypeface(tf);
        prnam.setText(pnames[i]+" "+pmili[i]);
        TextView num = (TextView)view.findViewById(R.id.number);
        num.setTypeface(tf);
        num.setText("تعداد: "+ pnumbers[i]+" "+punits[i]);
        TextView dexp = (TextView)view.findViewById(R.id.expire);
        dexp.setTypeface(tf);
        dexp.setText("تاریخ انقضا: "+pexpires[i]);
        TextView stnam = (TextView)view.findViewById(R.id.sname);
        stnam.setTypeface(tf);
        stnam.setText(snames[i]);
        TextView sdis = (TextView)view.findViewById(R.id.sdist);
        sdis.setTypeface(tf);
        sdis.setText(sdists[i]);
        TextView scity = (TextView)view.findViewById(R.id.scity);
        scity.setTypeface(tf);
        scity.setText(scitys[i]);
        TextView tel = (TextView)view.findViewById(R.id.telephone);
        tel.setTypeface(tf);
        tel.setText(phones[i]);
        // ImageView icon = (ImageView) view.findViewById(R.id.icon);
      //  icon.setImageResource(flags[i]);
        ImageView eye = (ImageView) view.findViewById(R.id.imageView13);
        eye.setImageResource(R.drawable.hide);
        if(image.matches("eye")){
            SharedPreferences pref=context.getSharedPreferences("SalamatPref",MODE_PRIVATE);
           final SharedPreferences.Editor editor=pref.edit();
        ImageView info = (ImageView) view.findViewById(R.id.imageView12);
        info.setImageResource(R.drawable.report);
            info.setClickable(true);
            info.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder;
                    builder = new AlertDialog.Builder(vi.getRootView().getContext());
                    builder.setTitle("")
                            .setMessage("آیا می خواهید این آگهی را گزارش دهید؟")
                            .setCancelable(true)
                            .setPositiveButton("بله، معتبر نمی باشد", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    editor.putInt("drid",ids[i]);
                                    editor.putString("stid",sids[i]);
                                    editor.apply();
                                    Intent intent=new Intent(context,ReportActivity.class);
                                    context.startActivity(intent);
                                   // ((Activity)ctx).finish();
                                }
                            })
                            .setNegativeButton("خیر، گزارش نشود", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_info)
                            .show();

                }});
        }else{
            SharedPreferences pref=ctx.getSharedPreferences("SalamatPref",MODE_PRIVATE);
            final SharedPreferences.Editor editor=pref.edit();
            ImageView trash = (ImageView) view.findViewById(R.id.imageView12);
            trash.setImageResource(R.drawable.deleteicon);
            trash.setClickable(true);
            trash.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder;
                    builder = new AlertDialog.Builder(vi.getRootView().getContext());
                    builder.setTitle("")
                            .setMessage("آیا می خواهید درخواست خود را حذف کنید؟")
                            .setCancelable(true)
                            .setPositiveButton("بله، حذف شود", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    editor.putInt("drid",ids[i]);
                                    editor.apply();
                                    Intent intent=new Intent(context,RemoveActivity.class);
                                    context.startActivity(intent);
                                   // ((MySellActivity)vi.getRootView().getContext()).finish();
                                }
                            })
                            .setNegativeButton("خیر، حذف نشود", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_info)
                            .show();

                }});
        }
        ImageView cap = (ImageView) view.findViewById(R.id.imageView11);
        cap.setImageResource(R.drawable.capsule);
        ImageView amp = (ImageView) view.findViewById(R.id.imageView10);
        amp.setImageResource(R.drawable.ampoule);
        return view;}
}
