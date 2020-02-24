package com.example.piets.hockeymanagement.Coach;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.example.piets.hockeymanagement.Classes.Helper;
import com.example.piets.hockeymanagement.Extra_Activity;
import com.example.piets.hockeymanagement.Extras.OnSwipeTouchListener;
import com.example.piets.hockeymanagement.R;
import com.example.piets.hockeymanagement.Extras.RSS_activity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import static com.example.piets.hockeymanagement.R.mipmap.ic_logo_foreground;

public class CoachMenu_Activity extends AppCompatActivity
{

    String userMail;
    TextView userEmail , coach;
    Button btnAddPlayersCoach , btnMyPlayersCoach , btnMatches, btnExtras;
    LinearLayout linearLayout;

    static final int REQUEST_IMAGE_CAPTURE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coach_menu_);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setIcon(ic_logo_foreground);
        actionBar.setTitle("Coach Menu");
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(false);

        userEmail = findViewById(R.id.tv_user_logged_in_coach);
        final String user = Backendless.UserService.loggedInUser().trim();
        Backendless.Data.of(BackendlessUser.class).findById(user, new AsyncCallback<BackendlessUser>() {
            @Override
            public void handleResponse(BackendlessUser response) {
                userMail = response.getEmail();
                userEmail.setText(userMail);
            }

            @Override
            public void handleFault(BackendlessFault fault) {

            }
        });


        coach = findViewById(R.id.tv_coach);
        btnAddPlayersCoach = findViewById(R.id.btn_add_players_coach);
        btnMyPlayersCoach = findViewById(R.id.btn_players_coach);
        btnMatches = findViewById(R.id.btn_matches_coach);
        btnExtras = findViewById(R.id.btn_ekstras_coach);
        linearLayout = findViewById(R.id.linearLayout);

        //almal assign hier onder een event listener vir reusabilty
        btnAddPlayersCoach.setOnClickListener(onCoachMenuClickListener);
        btnMyPlayersCoach.setOnClickListener(onCoachMenuClickListener);
        btnMatches.setOnClickListener(onCoachMenuClickListener);
        btnExtras.setOnClickListener(onCoachMenuClickListener);

        linearLayout.setOnTouchListener(new OnSwipeTouchListener(CoachMenu_Activity.this){
            public void onSwipeTop() {
            }
            public void onSwipeRight() {
            }
            @Override
            public void onSwipeLeft() {
                Intent intentrss = new Intent
                        (CoachMenu_Activity.this, RSS_activity.class);
                startActivity(intentrss);
            }
            public void onSwipeBottom() {
            }

        });

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK)
        {

            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.coachmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }



    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.logout:
                Backendless.UserService.logout(new AsyncCallback<Void>() {
                    @Override
                    public void handleResponse(Void response)
                    {

                        Intent intent = new Intent
                                (CoachMenu_Activity.this,
                                        com.example.piets.hockeymanagement.Login_Activity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void handleFault(BackendlessFault fault)
                    {
                        Toast.makeText(getBaseContext(), fault.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

                break;

            case R.id.camera:


                    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                    }

       }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE ) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            savePhoto(imageBitmap);

        }
    }

    private View.OnClickListener onCoachMenuClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view)
        {
            switch (view.getId())
            {
                case R.id.btn_add_players_coach:
                    Intent intent = new Intent
                            (CoachMenu_Activity.this,
                                    Coach_AddPlayers_Activity.class);
                    startActivity(intent);
                    break;

                case R.id.btn_matches_coach:
                    Intent intenta = new Intent
                            (CoachMenu_Activity.this,
                                    MatchesMenu_Activity.class);
                    startActivity(intenta);
                    break;

                case R.id.btn_players_coach:
                    Intent intentb = new Intent
                            (CoachMenu_Activity.this,
                                    MyPlayersList_Activity.class);
                    startActivity(intentb);
                    break;

                case R.id.btn_ekstras_coach:
                    Intent intentc = new Intent
                            (CoachMenu_Activity.this,
                                    Extra_Activity.class);
                    startActivity(intentc);
                    break;

            }
        }
    };

    public boolean onTouchEvent(MotionEvent event) {
        int action = MotionEventCompat.getActionMasked(event);

        switch (action) {
            case (MotionEvent.ACTION_UP):
                Intent intentrss = new Intent
                        (CoachMenu_Activity.this,
                                RSS_activity.class);
                startActivity(intentrss);
                return true;
            default:
                return super.onTouchEvent(event);

        }
    }

    private void savePhoto(Bitmap finalBitmap) {

        String root = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES).toString();
        File myDir = new File(root + "/saved_images");
        myDir.mkdirs();
        Random generator = new Random();

        int n = 10000;
        n = generator.nextInt(n);
        String fname = "Image-"+ n +".jpg";
        File file = new File (myDir, fname);
        if (file.exists ()) file.delete ();
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        MediaScannerConnection.scanFile(this, new String[]{file.toString()}, null,
                new MediaScannerConnection.OnScanCompletedListener() {
                    public void onScanCompleted(String path, Uri uri) {
                        Log.i("ExternalStorage", "Scanned " + path + ":");
                        Log.i("ExternalStorage", "-> uri=" + uri);
                    }
                });
    }

}
