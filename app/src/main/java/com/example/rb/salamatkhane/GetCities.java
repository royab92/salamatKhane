package com.example.rb.salamatkhane;

import android.content.Context;
import android.location.Location;

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

public class GetCities {
    private Context context;

    public GetCities(Context context){
        this.context=context;
    }
    public interface Recieve{
        void onCityRecieve(ArrayList<State>provs, int status, String message);
    }
    public void CityRecieve(JSONObject requestja, final GetCities.Recieve osc ){
        final JsonObjectRequest request=new JsonObjectRequest(Request.Method.POST,"Http://www.salamatkhane.com/api/GetCities",requestja,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    int status=response.getInt("StatusCode");
                    String message=response.getString("Message");
                    JSONArray states=response.getJSONArray("states");
                    ArrayList<State> provs=new ArrayList<State>();

                    for(int i=0;i<states.length();i++){
                     State temps=new State();
                        JSONArray cities=states.getJSONObject(i).getJSONArray("Cities");
                        ArrayList<City> cits=new ArrayList<City>();
                        for(int j=0;j<cities.length();j++){
                            City tempc=new City();
                            JSONArray regions=cities.getJSONObject(j).getJSONArray("Regions");
                            ArrayList<Region> regs=new ArrayList<Region>();
                            for(int k=0;k<regions.length();k++){
                                Region tempr=new Region();
                                tempr.id=regions.getJSONObject(k).getInt("RegionId");
                                tempr.name=regions.getJSONObject(k).getString("Name");
                                tempr.cid=regions.getJSONObject(k).getString("CityId");
                                regs.add(tempr);
                            }
                            tempc.regions=regs;
                            tempc.id=cities.getJSONObject(j).getInt("CityId");
                            tempc.name=cities.getJSONObject(j).getString("Name");
                            tempc.prid=cities.getJSONObject(j).getString("ProvinceId");
                            cits.add(tempc);
                        }
                        temps.cities=cits;
                        temps.code=states.getJSONObject(i).getString("PhoneCode");
                        temps.name=states.getJSONObject(i).getString("Name");
                        temps.id=states.getJSONObject(i).getInt("StateId");
                        provs.add(temps);
                    }

                    osc.onCityRecieve(provs,status,message);
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
