package com.example.rb.salamatkhane;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ReceiveRequests {
    private Context context; public ReceiveRequests(Context context){
        this.context=context;
    }
    public interface Recieve{
        void onDataRecieve(ArrayList<Drug> drugs, int StatusCode, String Message);
    }
    public void DataRecieve(JSONObject requestja, final ReceiveRequests.Recieve osc ) {
        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, "Http://www.salamatkhane.com/api/UserRequests", requestja, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray items=response.getJSONArray("items");
                    ArrayList<Drug> drugs=new ArrayList<Drug>();
                    for(int i=0;i<items.length();i++){
                        Drug tempd=new Drug();
                        tempd.id=items.getJSONObject(i).getInt("DrugId");
                        tempd.fname=items.getJSONObject(i).getString("FaName");
                        tempd.ename=items.getJSONObject(i).getString("EnName");
                        tempd.cfname=items.getJSONObject(i).getString("FaCommercialName");
                        tempd.cename=items.getJSONObject(i).getString("EnCommercialName");
                        tempd.company=items.getJSONObject(i).getString("Company");
                        tempd.state=items.getJSONObject(i).getString("State");
                        tempd.mili=items.getJSONObject(i).getString("Miligram");
                        tempd.unit=items.getJSONObject(i).getString("Unit");
                        tempd.expire=items.getJSONObject(i).getString("ExpireDate");
                        tempd.count=items.getJSONObject(i).getInt("Count");
                        tempd.category=items.getJSONObject(i).getString("Category");
                        drugs.add(tempd);
                    }
                    int status=response.getInt("StatusCode");
                    String message=response.getString("Message");
                    osc.onDataRecieve(drugs, status, message);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //  osc.onSignUp(false);
                //Log.i("Get_Error: ", error.toString());
            }
        });
        request.setRetryPolicy(new DefaultRetryPolicy(18000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(context).add(request);
    }
}
