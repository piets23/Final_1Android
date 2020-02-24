package com.example.piets.hockeymanagement;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.example.piets.hockeymanagement.Extras.Google_Maps_Activity;
import com.example.piets.hockeymanagement.Extras.RSS_activity;

import static com.example.piets.hockeymanagement.R.mipmap.ic_logo_foreground;

public class Extra_Activity extends AppCompatActivity  {

    Button web , rss , google ,camera;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extra_);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setIcon(ic_logo_foreground);
        actionBar.setTitle("Extra Features");
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(false);

        web = findViewById(R.id.btn_webView_coach);
        web.setOnClickListener(onExtraClick);
        rss = findViewById(R.id.btn_rssFeed);
        rss.setOnClickListener(onExtraClick);
        google = findViewById(R.id.btnGooglemaps);
        google.setOnClickListener(onExtraClick);
        camera = findViewById(R.id.btnCamera);
        camera.setOnClickListener(onExtraClick);


    }

    private View.OnClickListener onExtraClick = new View.OnClickListener() {
        @Override
        public void onClick(View view)
        {
            switch (view.getId())
            {
                case R.id.btn_webView_coach:
                    Intent intent = new Intent
                            (Extra_Activity.this,
                                    WebView_Activity.class);
                    startActivity(intent);
                    break;

                case R.id.btn_rssFeed:
                    Intent intentrss = new Intent
                            (Extra_Activity.this,
                                    RSS_activity.class);
                    startActivity(intentrss);
                    break;

                case R.id.btnGooglemaps:
                    Uri gmmIntentUri = Uri.parse("geo:-29,26");
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");
                    if (mapIntent.resolveActivity(getPackageManager()) != null) {
                        startActivity(mapIntent);
                    }
//                Intent intentgoogle = new Intent
//                        (Extra_Activity.this,
//                                Google_Maps_Activity.class);
                //startActivity(intentgoogle);
                    break;

                case R.id.btnCamera:
                    Intent intentcamera = new Intent
                            ("android.media.action.IMAGE_CAPTURE");
                    startActivity(intentcamera);
                    break;



            }
        }
    };

    @Override
    public void onBackPressed()
    {
        finish();
    }
}
