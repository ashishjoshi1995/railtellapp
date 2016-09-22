package com.example.ashish.railtellapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

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

public class AfterLogin extends AppCompatActivity {

    Button show;
    ImageView view1,view2,view3;
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

                new RequestTask().execute("http://49.40.112.2/modis/index?cboyear=2016"+
                        "&cbojuliandate=01/Jan-08/Jan&cbostate=Punjab&cbodistrict=Bathinda");
/*

                int responseCode = 0;
                //String user = username.getText().toString();
                //String pass = password.getText().toString();
                HttpClient httpClient = new DefaultHttpClient();
                HttpGet httppost = new HttpGet("http://49.40.112.2/modis/index?cboyear=2016"+
                        "&cbojuliandate=01/Jan-08/Jan&cbostate=Punjab&cbodistrict=Bathinda");
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

                HttpResponse response = null;
                try {
                    response = httpClient.execute(httppost);
                } catch (Exception e) {
                    e.printStackTrace();

                }


                try {

                    //Log.e("getting response",""+response.getEntity().getContent().toString());
                    Log.e("HELLO","HELLO");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
                    String json = reader.readLine();
                    Log.e("hahaha",""+json);
                    String[] ar = json.split(",");
                    String one = "http://49.40.112.2/modis/img/tmp/2015001"+"407"+".jpg";
                    String one1 = "http://49.40.112.2/modis/img/tmp/2016001"+"407"+".jpg";
                    String one2 = "http://49.40.112.2/modis/img/dist/" + "28"+"/Bathinda.jpg";


                    Picasso.with(getApplication())
                            .load(one)
                            .resize(400,400)                        // optional
                            .into(view1);
                    Picasso.with(getApplication())
                            .load(one1)
                            .placeholder(R.drawable.pic1a)   // optional
                            .error(R.drawable.pic1a)      // optional
                            .resize(400,400)                        // optional
                            .into(view2);
                    Picasso.with(getApplication())
                            .load(one2)
                            .placeholder(R.drawable.pic1a)   // optional
                            .error(R.drawable.pic1a)      // optional
                            .resize(400,400)                        // optional
                            .into(view3);


                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),""+e.getMessage()+"nnnnnnnnnnn "+e.getCause(),
                            Toast.LENGTH_SHORT).show();

                }
                */
            }
        });

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
//            String[] ar = result.split(",");
            String one = "http://49.40.112.2/modis/img/tmp/2015001"+"407"+".jpg";
            String one1 = "http://49.40.112.2/modis/img/tmp/2016001"+"407"+".jpg";
            String one2 = "http://49.40.112.2/modis/img/dist/" + "28"+"/Bathinda.jpg";


            Picasso.with(getApplication())
                    .load(one)
                    .placeholder(R.drawable.pic1a)   // optional
                    .error(R.drawable.pic1a)
                    .resize(400,400)                        // optional
                    .into(view1);
            Picasso.with(getApplication())
                    .load(one1)
                    .placeholder(R.drawable.pic1a)   // optional
                    .error(R.drawable.pic1a)      // optional
                    .resize(400,400)                        // optional
                    .into(view2);
            Picasso.with(getApplication())
                    .load(one2)
                    .placeholder(R.drawable.pic1a)   // optional
                    .error(R.drawable.pic1a)      // optional
                    .resize(400,400)                        // optional
                    .into(view3);

        }
    }}
