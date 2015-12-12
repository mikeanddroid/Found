package com.mike.givemewingzz.found.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.mike.givemewingzz.found.R;
import com.mike.givemewingzz.found.apihelper.FoundApiHelper;
import com.mike.givemewingzz.found.data.models.YelpWrapper;
import com.mike.givemewingzz.found.parcelable.YelpAuth;
import com.mike.givemewingzz.found.utils.BaseClient;
import com.mike.givemewingzz.found.utils.BaseRetrofitInterface;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Found extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    // Just a test case to test the token interchange from the server.
    // Todo : Delete and modify the data flow. Integrate Asynshronous process within application.
    private static final String CONSUMER_KEY = "Ox-ughsjh_PCpSX6S6jYIA";
    private static final String CONSUMER_SECRET = "EFHJdZKBsu_KLoaSET1KqytTuUw";
    private static final String TOKEN = "NVR_cA0iYEiZwk_8BJh_ySTmyG5LPlhA";
    private static final String TOKEN_SECRET = "zFUI3i3hAxDMdpKOdSpVtGqdoac";

    @Bind(R.id.searchCall)
    Button searchButton;

    YelpAuth yelpAuth;

    public static final String TAG = Found.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.found);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

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
        getMenuInflater().inflate(R.menu.found, menu);
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

//            new TestTask().execute();

            doTestCall();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // Just a test case to test the token interchange from the server.
    // Todo : Delete and modify the data flow. Integrate Asynshronous process within application.
    public class TestTask extends AsyncTask<Bundle, Void, Bundle> {

        @Override
        protected Bundle doInBackground(Bundle... params) {

//            YelpAPI yelpAPI1 = new YelpAPI();
//            yelpAPI1.initializeAuth(CONSUMER_KEY, CONSUMER_SECRET, TOKEN, TOKEN_SECRET);
//            yelpAPI1.searchResultsByQuery("starbucks", "Seattle,WA", 3);

            FoundApiHelper foundApiHelper = new FoundApiHelper();
            foundApiHelper.initializeAuth(CONSUMER_KEY, CONSUMER_SECRET, TOKEN, TOKEN_SECRET);

            return new Bundle();
        }

        @Override
        protected void onPostExecute(Bundle bundle) {

//            yelpAuth = bundle.getParcelable(YelpAuth.YELP_AUTH);
//            Log.d(TAG, "Yelp Auth Values-->" + yelpAuth);

        }
    }

    public void doTestCall() {
        BaseRetrofitInterface baseRetrofitInterface = BaseClient.getBBSIClient();

        baseRetrofitInterface.testParams("Starbucks", "Seattle,WA",3, new Callback<YelpWrapper>() {
            @Override
            public void success(YelpWrapper yelpWrapper, Response response) {
                Log.d(TAG,"Response : " + response.getBody().toString());
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG,"Response Error: " + error.getResponse().getBody().toString());
            }
        });
    }

}
