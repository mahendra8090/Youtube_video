package com.udnkhatola.youtube_video;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

    public class MainActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener,GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener,com.google.android.gms.location.LocationListener{
    public static final String API_KEY = "AIzaSyAE922zj7HWBloqP-ZI556tTiygazjF7lU";
    int j;
    public static String VIDEO_ID = "rMQ_TUEwQEs";


ArrayList<String> videoIds;
    private GoogleApiClient mgoogleapiclint;
    private LocationRequest locationRequest;
        YouTubePlayerView youTubePlayerView;
        String url=null;
    public   HashMap<String, String> data;
    public   HashMap<String, String> data2;
    public   HashMap<String, String> data3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GeoLocation geoLocation=new GeoLocation(this);
        geoLocation.start();
       Speed_frequency speed_frequency=new Speed_frequency(this);
       speed_frequency.start();

Videofrequencyno.video_frequency_count=5;
        videoIds=new ArrayList<>();
//videoIds.add("rMQ_TUEwQEs");
        //videoIds.add("hm7rTjM1Sac");




      /* mgoogleapiclint=new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
               .build();
*/


        getdata2("http://hostad.in/info2/");
    }

    @Override
    protected void onStart() {
        super.onStart();
      //  mgoogleapiclint.connect();
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        youTubePlayer.setPlayerStateChangeListener(playerStateChangeListener);
        youTubePlayer.setPlaybackEventListener(playbackEventListener);
        Toast.makeText(this, "yes    "+youTubePlayer, Toast.LENGTH_SHORT).show();
       // if(!b) {
            youTubePlayer.cueVideos(videoIds);
           youTubePlayer.loadVideos(videoIds);
        youTubePlayer.setFullscreen(true);
        //}
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(this, "fail"+youTubeInitializationResult, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
    private YouTubePlayer.PlaybackEventListener playbackEventListener = new YouTubePlayer.PlaybackEventListener() {
        @Override
        public void onBuffering(boolean arg0) {
           // Toast.makeText(MainActivity.this, "buffering", Toast.LENGTH_SHORT).show();
        }
        @Override
        public void onPaused() {
        }
        @Override
        public void onPlaying() {
        }
        @Override
        public void onSeekTo(int arg0) {
        }
        @Override
        public void onStopped() {

        }
    };
    private YouTubePlayer.PlayerStateChangeListener playerStateChangeListener = new YouTubePlayer.PlayerStateChangeListener() {
        @Override
        public void onAdStarted() {
        }
        @Override
        public void onError(YouTubePlayer.ErrorReason arg0) {

        }
        @Override
        public void onLoaded(String arg0) {
        }
        @Override
        public void onLoading() {
        }
        @Override
        public void onVideoEnded() {
            VideoCount videoCount=new VideoCount(MainActivity.this);
            videoCount.start(1,videoIds.get(j));
            j++;
           Videofrequencyno.video_frequency_count=Videofrequencyno.video_frequency_count+1;

            if(j==5){
                Intent intent=new Intent(MainActivity.this,FlashActivity.class);
                startActivity(intent);
                finish();


            }
        }
        @Override
        public void onVideoStarted() {
        }
    };

    @Override
    public void onConnected(Bundle bundle) {
        locationRequest=LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(2000);
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED&&ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED){
         return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(mgoogleapiclint,locationRequest,this);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
     //   Toast.makeText(MainActivity.this,""+(Double.toString(location.getAltitude())),Toast.LENGTH_LONG).show();
     /*   data = new HashMap<String, String>();
        data2 = new HashMap<String, String>();
        data3 = new HashMap<String, String>();
        data2.put("key","70b66a89929e93416d2ef535893ea14da331da8991cc7c74010b4f3d7fabfd62");
        data2.put("user_id","auto1");

        data2.put("latitude",(Double.toString(location.getLatitude())));
        data2.put("longitude",(Double.toString(location.getLongitude())));




        data.put("key","70b66a89929e93416d2ef535893ea14da331da8991cc7c74010b4f3d7fabfd62");
        data.put("user_id","auto1");

        data.put("speed",(Double.toString(location.getSpeed())));
        data.put("video_frquency","34");
        VolleyR(data,"http://hostad.in/api/information3/","http://hostad.in/api/information3/");
        VolleyR(data2,"http://hostad.in/api/information1/","http://hostad.in/api/information1/");

        data3.put("key","70b66a89929e93416d2ef535893ea14da331da8991cc7c74010b4f3d7fabfd62");
        data3.put("user_id","auto1");
        data3.put("video_id","random video");
        data3.put("video_count","400");
        VolleyR(data3,"http://hostad.in/api/information2/","http://hostad.in/api/information2/");

*/
    }
    public void VolleyR(HashMap<String,String> data, String Urlf,String url) {

        Toast.makeText(MainActivity.this,"in vooly",Toast.LENGTH_SHORT).show();
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);

        JSONObject urlf = new JSONObject(data);


        final JsonObjectRequest putRequest = new JsonObjectRequest(Request.Method.POST,url, urlf,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(MainActivity.this,"shivam \n"+response,Toast.LENGTH_LONG).show();
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
                        Toast.makeText(MainActivity.this,"volly error   "+error,Toast.LENGTH_SHORT).show();

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
        public  void getdata2(String url){
            final RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {

                    String s = "";
                    int i=0;
                    String device_id="41616116";
                    Toast.makeText(MainActivity.this, "shivan  " + response, Toast.LENGTH_SHORT).show();
                    try {
                       // for(i=0;i<2;i++){
                        JSONObject jsonObject=response.getJSONObject(0);
                      //  String id=jsonObject.getString("User_id");
                     //   if(id=="41616116"){
//break;
                        //}
                       // JSONObject jsonObject=response.getJSONObject(i);
                        for(i=1;i<6;i++){


                        String s1=jsonObject.getString("video_id"+i);
                            videoIds.add(s1);}
                            j=0;
                      //  VIDEO_ID=s1;
                        youTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtube_player);
                       youTubePlayerView.initialize(API_KEY, MainActivity.this);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


//if(s!=object.getString("android_id")) {


                    //  s=object.getString("android_id");


//}






                }

                // trains.setText(""+response);

                // Display the first 500 characters of the response string.


            }, new com.android.volley.Response.ErrorListener()

            {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(MainActivity.this, "error   " + error, Toast.LENGTH_LONG).show();

                }


            });


// Add the request to the RequestQueue.
            queue.add(jsonArrayRequest);

        }

    }


