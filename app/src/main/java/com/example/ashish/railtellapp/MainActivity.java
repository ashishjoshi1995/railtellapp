package com.example.ashish.railtellapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText username,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button login = (Button)findViewById(R.id.button_login);
        login.setOnClickListener(this);

         username = (EditText)findViewById(R.id.login_mobile);
         password = (EditText)findViewById(R.id.login_password);

        TextView tvForgotPassword = (TextView)findViewById(R.id.forgot_password);
        TextView tvChangePassword = (TextView)findViewById(R.id.change_password);
        TextView tvNewUser = (TextView)findViewById(R.id.new_user);
        tvForgotPassword.setOnClickListener(this);
        tvChangePassword.setOnClickListener(this);
        tvNewUser.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.change_password:
                CommonUtils.openContainerActivity(this,Constants.CHANGE_PASSWORD);
                break;
            case R.id.forgot_password:
                CommonUtils.openContainerActivity(this,Constants.FORGOT_PASSWORD);
                break;
            case R.id.new_user:
                CommonUtils.openContainerActivity(this,Constants.NEW_USER);
                break;
            case R.id.button_login:
                int responseCode = 0;
                String user = username.getText().toString();
                String pass = password.getText().toString();
                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost("http://49.40.112.2/modis/login.jsp");
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("txtusername", user));
                nameValuePairs.add(new BasicNameValuePair("txtpassword", pass));
                try {
                    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                //HttpResponse response;
                HttpResponse response = null;
                try {
                    response = httpClient.execute(httppost);
                } catch (IOException e) {
                    e.printStackTrace();

                }

                // writing response to logLog.e("Http Response:", response.toString());

                try {
                    Toast.makeText(getApplicationContext(),response.toString(),
                            Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),""+e.getMessage(),
                            Toast.LENGTH_SHORT).show();

                }
                break;
        }

    }
}
