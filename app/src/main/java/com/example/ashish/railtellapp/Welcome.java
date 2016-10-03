package com.example.ashish.railtellapp;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.app.Fragment;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.ashish.railtellapp.fragments.Classification;
import com.example.ashish.railtellapp.fragments.CropMonitoring;
import com.example.ashish.railtellapp.fragments.Gallery;
import com.example.ashish.railtellapp.fragments.Home;
import com.example.ashish.railtellapp.fragments.ResearchTeam;

public class Welcome extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    int navItemIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.welcome, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
            navItemIndex = 0;
            Fragment newFragment = getHomeFragment();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();


        } else if (id == R.id.nav_classification) {
            navItemIndex = 2;
            Fragment newFragment = getHomeFragment();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.frame, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();

        } else if (id == R.id.nav_crop_monitor) {
            navItemIndex = 1;
            Fragment newFragment = getHomeFragment();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.frame, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();

        } else if (id == R.id.nav_gallery) {
            navItemIndex = 3;
            Fragment newFragment = getHomeFragment();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.frame, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();

        } else if (id == R.id.nav_logout) {
            //navItemIndex = 1;
            //Fragment newFragment = getHomeFragment();
            //FragmentTransaction transaction = getFragmentManager().beginTransaction();
            //transaction.replace(R.id.frame, newFragment);
           // transaction.addToBackStack(null);
            //transaction.commit();

        } else if (id == R.id.nav_research_team) {
            navItemIndex = 4;
            Fragment newFragment = getHomeFragment();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.frame, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private Fragment getHomeFragment() {
        {
            switch (navItemIndex) {
                case 0:
                    // home

                    Home homeFragment = new Home();
                    return homeFragment;
                case 1:
                    // photos
                    CropMonitoring cropMonitoring = new CropMonitoring();
                    return cropMonitoring;
                case 2:
                    // movies fragment
                    Classification classification = new Classification();
                    return classification;
                case 3:
                    // notifications fragment
                    Gallery gallery = new Gallery();
                    return gallery;

                case 4:
                    // settings fragment
                    ResearchTeam researchTeam = new ResearchTeam();
                    return researchTeam;
                default:
                    return new Home();
            }
        }
    }
}