package com.example.piets.hockeymanagement.Extras;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.piets.hockeymanagement.Adapters.FeedAdapter;
import com.example.piets.hockeymanagement.Coach.CoachMenu_Activity;
import com.example.piets.hockeymanagement.Extras.Model.RSSObject;
import com.example.piets.hockeymanagement.R;
import com.google.gson.Gson;

import static com.example.piets.hockeymanagement.R.mipmap.ic_logo_foreground;

/**
 * Created by piets on 2018/09/18.
 */

public class RSS_activity extends AppCompatActivity
{
    Toolbar toolbar ;
    RecyclerView recyclerView;
    RSSObject rssObject;

    //RSS link
    private final String RSS_link = "http://rss.nytimes.com/services/xml/rss/nyt/Hockey.xml";
    private final String RSS_Convert = "https://api.rss2json.com/v1/api.json?rss_url=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rssrecview);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setIcon(ic_logo_foreground);
        actionBar.setTitle("HockeyNews");
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(false);
        /*toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Hockey News");
        setSupportActionBar(toolbar);*/

        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getBaseContext()
                ,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

        loadRSS();
        recyclerView.setOnTouchListener(new OnSwipeTouchListener(RSS_activity.this){
            public void onSwipeTop() {
            }
            public void onSwipeRight() {

            }
            @Override
            public void onSwipeLeft() {
                Intent intentrss = new Intent
                        (RSS_activity.this, CoachMenu_Activity.class);
                startActivity(intentrss);

            }
            public void onSwipeBottom() {
            }

        });
    }

    private void loadRSS() {
        AsyncTask<String,String,String> loadRSSAsync = new AsyncTask<String, String, String>()
        {
            ProgressDialog dialog = new ProgressDialog(RSS_activity.this);

            @Override
            protected void onPreExecute() {
                dialog.setMessage("Please Wait...");
                dialog.show();
            }

            @Override
            protected String doInBackground(String... strings) {
                String result;
                HTTPDataHandler http = new HTTPDataHandler();
                result = http.GetHTTPData(strings[0]);
                return result;
            }

            @Override
            protected void onPostExecute(String s) {
                dialog.dismiss();
                rssObject = new Gson().fromJson(s,RSSObject.class);
                FeedAdapter adapter = new FeedAdapter(rssObject,getBaseContext());
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        };
        loadRSSAsync.execute(RSS_Convert + RSS_link);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menu_refresh)
        {
            loadRSS();
        }
        return true;
    }

}
