package com.example.ashish.railtellapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.os.StrictMode;

import com.example.ashish.railtellapp.fragments.Gallery;
import com.example.ashish.railtellapp.fragments.ProgressDialog;
import com.example.ashish.railtellapp.fragments.ResearchTeam;
import com.example.ashish.railtellapp.home.home;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText username,password;
    Button b1,b3;
    public Button s4,s5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        Button login = (Button)findViewById(R.id.btnLogin);
        Button newUser = (Button)findViewById(R.id.btnLinkToRegisterScreen);
        login.setOnClickListener(this);
        newUser.setOnClickListener(this);

         username = (EditText)findViewById(R.id.email);
         password = (EditText)findViewById(R.id.password);

        Button tvForgotPassword = (Button)findViewById(R.id.b7);
        b1 = (Button) findViewById(R.id.b1);
        b1.setOnClickListener(this);

        s4=(Button)findViewById(R.id.b5);
        s4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent research=new Intent(MainActivity.this,team.class);
                startActivity(research);
            }
        });

        b3 = (Button)findViewById(R.id.b3);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent research=new Intent(MainActivity.this,gallery.class);
                startActivity(research);
            }
        });

        tvForgotPassword.setOnClickListener(this);

        //tvNewUser.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.b1:
                Intent intro = new Intent(MainActivity.this, home.class);
                intro.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intro);
                break;
            case R.id.b7:
                CommonUtils.openContainerActivity(this,Constants.FORGOT_PASSWORD);
                break;
            case R.id.btnLinkToRegisterScreen:
                CommonUtils.openContainerActivity(this,Constants.NEW_USER);
                break;
            case R.id.btnLogin:
                int responseCode = 0;
                String user = username.getText().toString();
                String pass = password.getText().toString();
                final ProgressDialog dialog = ProgressDialog.createDialog(this);
                dialog.show();
                HttpClient httpClient = new DefaultHttpClient();
                HttpGet httppost = new HttpGet("http://aisiitr.in/modis/login?txtusername="+user+"&txtpassword="+pass);
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

                HttpResponse response = null;
                try {
                    response = httpClient.execute(httppost);
                } catch (Exception e) {
                    e.printStackTrace();

                }

                // writing response to logLog.e("Http Response:", response.toString());

                try {

                    //Log.e("getting response",""+response.getEntity().getContent().toString());
                    Log.e("HELLO","HELLO");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
                    String json = reader.readLine();
                    Log.e("hahaha",""+json);
                    dialog.dismiss();
                    Intent i = new Intent(MainActivity.this,Welcome.class);
                    startActivity(i);

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),""+e.getMessage()+"nnnnnnnnnnn "+e.getCause(),
                            Toast.LENGTH_SHORT).show();
                    e.printStackTrace();

                }
                break;
        }

    }
}
