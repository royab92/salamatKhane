package com.example.rb.salamatkhane;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RequestBS {
    private Context context;
    public RequestBS(Context context){
        this.context=context;
    }
    public interface Requestbs{
        void onRequest(int StatusCode, String Message);
    }
    public void RequestRecieve(JSONObject requestja, final RequestBS.Requestbs osc ){
        final JsonObjectRequest request=new JsonObjectRequest(Request.Method.POST,"Http://www.salamatkhane.com/api/RequestForDrug",requestja,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                // JSONObject nresponse;
                //  Log.i("Get_Response: ", response.toString());
                try {
                    // nresponse= response.getJSONObject("Data");
                    int status=response.getInt("StatusCode");
                    String message=response.getString("Message");
                    osc.onRequest(status,message);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },new Response.ErrorListener(){
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
