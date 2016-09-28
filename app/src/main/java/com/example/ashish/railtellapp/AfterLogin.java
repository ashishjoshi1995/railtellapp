package com.example.ashish.railtellapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class AfterLogin extends AppCompatActivity implements OnMapReadyCallback {

    Button show;
    ImageView view1,view2,view3;
    private GoogleMap mgoogleMap;
    static final LatLng TutorialsPoint = new LatLng(21 , 57);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_login);

        show = (Button)findViewById(R.id.show_data);
        view1 = (ImageView) findViewById(R.id.view1);
        view2 = (ImageView) findViewById(R.id.view2);
        view3 = (ImageView) findViewById(R.id.view3);

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new RequestTask().execute("http://aisiitr.in/modis/index?cboyear=2015"+
                        "&cbojuliandate=001&cbostate=Uttrakhand&cbodistrict=Haridwar");
                //30.18
                //75
//http://aisiitr.in/modis/indexndviprofile?cbojuliandate1=001&cbostate1=Uttrakhand&cbodistrict1=Haridwar
            }
        });


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

}

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mgoogleMap = googleMap;
        mgoogleMap.getUiSettings().setScrollGesturesEnabled(false);

    }

    class RequestTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... uri) {
            Log.e("test","hello");
            HttpClient httpclient = new DefaultHttpClient();
            HttpResponse response;
            String responseString = null;
            try {
                response = httpclient.execute(new HttpGet(uri[0]));
                StatusLine statusLine = response.getStatusLine();
                if(statusLine.getStatusCode() == HttpStatus.SC_OK){
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    response.getEntity().writeTo(out);
                    responseString = out.toString();
                    out.close();
                } else{
                    //Closes the connection.
                    response.getEntity().getContent().close();
                    throw new IOException(statusLine.getReasonPhrase());
                }
            } catch (ClientProtocolException e) {
                //TODO Handle problems..
            } catch (IOException e) {
                //TODO Handle problems..
            }
            return responseString;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            //Do anything with response..
            Log.e("test",result+"");
            String[] ar=null;
            try {
                ar = result.split(",");
            }
            catch (Exception e){
                e.printStackTrace();
            }
            String one = "http://aisiitr.in/modis/img/tmp/2015001"+ar[0]+".jpg";
            String one1 = "http://aisiitr.in/modis/img/tmp/2016001"+ar[0]+".jpg";
            String one2 = "http://aisiitr.in/modis/img/dist/" +"34"+"/Haridwar.jpg";


            Picasso.with(getApplication())
                    .load(one)
                    //.placeholder(R.drawable.pic1a)   // optional
                    //.error(R.drawable.pic1a)
                    //.resize(400,400)                        // optional
                    .into(view1);
            Picasso.with(getApplication())
                    .load(one1)
                    //.placeholder(R.drawable.pic1a)   // optional
                    //.error(R.drawable.pic1a)      // optional
                    //.resize(400,400)                        // optional
                    .into(view2);
            Picasso.with(getApplication())
                    .load(one2)
                    //.placeholder(R.drawable.pic1a)   // optional
                    //.error(R.drawable.pic1a)      // optional
                    //.resize(400,400)                        // optional
                    .into(view3);
            try {
               LatLng sydney = new LatLng(Double.parseDouble(ar[1]), Double.parseDouble(ar[2]));
                mgoogleMap.addMarker(new MarkerOptions().position(sydney));
                mgoogleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
                CameraPosition position = CameraPosition.builder()
                        .target(sydney)
                        .zoom(12f)
                        .bearing(0.0f)
                        .tilt(0.0f)
                        .build();
                mgoogleMap.animateCamera(CameraUpdateFactory
                        .newCameraPosition(position), null);
                mgoogleMap.getUiSettings().setZoomControlsEnabled(true);

            }
            catch (Exception e){
                e.printStackTrace();
            }

        }
    }
    private void initCamera(LatLng latLng) {
        CameraPosition position = CameraPosition.builder()
                .target(latLng)
                .zoom(16f)
                .bearing(0.0f)
                .tilt(0.0f)
                .build();

        mgoogleMap.animateCamera(CameraUpdateFactory
                .newCameraPosition(position), null);

        mgoogleMap.getUiSettings().setZoomControlsEnabled(true);
    }
}
