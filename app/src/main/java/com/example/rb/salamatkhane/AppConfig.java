package com.example.rb.salamatkhane;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by bahrampashootan on 6/4/2018 AD.
 */

public class AppConfig {
    private Context context;
    public AppConfig(Context context){
        this.context=context;
    }
    public interface Configuration{
        void onSignUp(String IosVersion, String AndroidVersion,String IosUrl, String AndroidUrl, boolean IosUpdate, boolean AndroidUpdate, int StatusCode, String Message);
    }
    public void signUp(JSONObject requestja, final AppConfig.Configuration osc ){
        final JsonObjectRequest request=new JsonObjectRequest(Request.Method.POST,"Http://www.salamatkhane.com/api/AppConfig",requestja,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String IosVersion = response.getString("IosVersion");
                    String AndroidVersion=response.getString("AndroidVersion");
                    String IosUrl=response.getString("IosDownloadLink");
                    String AndroidUrl=response.getString("AndroidDownloadLink");
                    boolean IosUpdate =response.getBoolean("IosForceUpdate");
                    boolean AndroidUpdate =response.getBoolean("AndroidForceUpdate");
                    int status=response.getInt("StatusCode");
                    String message=response.getString("Message");
                    osc.onSignUp(IosVersion,AndroidVersion,IosUrl,AndroidUrl,IosUpdate,AndroidUpdate,status,message);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                //  osc.onSignUp(false);
              //  Log.i("Get_Error: ", error.toString());

            }
        });
        request.setRetryPolicy(new DefaultRetryPolicy(18000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(context).add(request);

    }

}
