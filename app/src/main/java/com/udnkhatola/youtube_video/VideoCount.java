package com.udnkhatola.youtube_video;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;

public class VideoCount extends Thread {
    Context mContext;
    public HashMap<String, String> data3;

    public VideoCount(Context context) {
        mContext = context;
    }

    public void count(int a,String videoid){
        int i=0;


           data3 = new HashMap<String, String>();
           data3.put("key", "70b66a89929e93416d2ef535893ea14da331da8991cc7c74010b4f3d7fabfd62");
           data3.put("user_id", "auto1");
           data3.put("video_id", videoid);
           data3.put("video_count", ""+a);
           VolleyR(data3, "http://hostad.in/api/information2/", "http://hostad.in/api/information2/");


    }
    public void start(int a,String videoid) {
       count(a,videoid);
    }
    public void VolleyR(HashMap<String,String> data, String Urlf,String url) {

        Toast.makeText(mContext,"in vooly",Toast.LENGTH_SHORT).show();
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
