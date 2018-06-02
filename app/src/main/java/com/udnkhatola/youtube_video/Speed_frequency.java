package com.udnkhatola.youtube_video;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import org.json.JSONObject;

import java.util.HashMap;

public class Speed_frequency extends Thread implements GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener,com.google.android.gms.location.LocationListener {
    private GoogleApiClient mgoogleapiclint;
    private LocationRequest locationRequest;
    HashMap  data2;
    HashMap data;
    Context mContext;
    public Speed_frequency(Context context) {
        mContext=context;
    }

    public  void takedata(){
        mgoogleapiclint=new GoogleApiClient.Builder(mContext)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
        mgoogleapiclint.connect();
       // data = new HashMap<String, String>();
        //data.put("key","70b66a89929e93416d2ef535893ea14da331da8991cc7c74010b4f3d7fabfd62");
        //data.put("user_id","auto1");

      //  data.put("speed",(Double.toString(location.getSpeed())));
       // data.put("video_frquency","34");
      //  VolleyR(data,"http://hostad.in/api/information3/","http://hostad.in/api/information3/");

    }
    public void start(){
        takedata();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        locationRequest=LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(10000);
        if(ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED&&ActivityCompat.checkSelfPermission(mContext,Manifest.permission.ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED){
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(mgoogleapiclint,locationRequest,this);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        data2 = new HashMap<String, String>();
        data2.put("key","70b66a89929e93416d2ef535893ea14da331da8991cc7c74010b4f3d7fabfd62");
        data2.put("user_id","auto1");
       int c= 6;
               //Videofrequencyno.video_frequency_count;
        data2.put("video_frquency",c);
      //  Videofrequencyno.video_frequency_count=0;

        data2.put("speed",(Double.toString(location.getSpeed())));
        VolleyR(data2,"http://hostad.in/api/information3/","http://hostad.in/api/information3/");
    }
    public void VolleyR(HashMap<String,String> data, String Urlf,String url) {

        Toast.makeText(mContext,"in volley",Toast.LENGTH_SHORT).show();
        RequestQueue queue = Volley.newRequestQueue(mContext);

        JSONObject urlf = new JSONObject(data);


        final JsonObjectRequest putRequest = new JsonObjectRequest(Request.Method.POST,url, urlf,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(mContext,""+response,Toast.LENGTH_LONG).show();
                        // response0
                        // Toast.makeText(MainActivity.this,"in responce",Toast.LENGTH_SHORT).show();
                        // For a given earthquake, extract the JSONObject associated with the
                        // key called "properties", which represents a list of all properties
                        // for that earthquake.


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(mContext,"volly error   "+error,Toast.LENGTH_SHORT).show();

                    }
                }
        ) {
            @Override
            public HashMap<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("Content-Type", "application/json");
                return params;
            }


        };


        queue.add(putRequest);


    }
}
