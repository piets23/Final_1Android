package com.example.piets.hockeymanagement.Admin;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.example.piets.hockeymanagement.Classes.Helper;
import com.example.piets.hockeymanagement.Classes.Users;
import com.example.piets.hockeymanagement.Coach.CoachMenu_Activity;
import com.example.piets.hockeymanagement.R;

import static com.example.piets.hockeymanagement.R.mipmap.ic_logo_foreground;

public class AdminMenu_Activity extends AppCompatActivity {


    String userMail;
    TextView userEmail , admin;
    Button btnAddAdmin , btnMoveAdmin , btnSetAdmin , btnCoachAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu_);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setIcon(ic_logo_foreground);
        actionBar.setTitle("Admin Menu");
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(false);

        userEmail = findViewById(R.id.tv_user_logged_in);
        final String user = Backendless.UserService.loggedInUser().trim();
        Backendless.Data.of(BackendlessUser.class).findById(user, new AsyncCallback<BackendlessUser>()
        {
            @Override
            public void handleResponse(BackendlessUser response)
            {
                userMail = response.getEmail();
                userEmail.setText(userMail);
            }

            @Override
            public void handleFault(BackendlessFault fault) {

            }
        });

        admin = findViewById(R.id.tv_admin);
        btnAddAdmin = findViewById(R.id.btn_add_team_admin);
        btnMoveAdmin = findViewById(R.id.btn_move_admin);
        btnSetAdmin = findViewById(R.id.btn_set_admin);
        btnCoachAdmin = findViewById(R.id.btn_coach_admin);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.adminmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

//click event for buttons
    public void adminMenu_Click(View view) {
        switch (view.getId())
        {
            case R.id.btn_add_team_admin:
                Intent intent = new Intent
                        (AdminMenu_Activity.this,
                                AddTeam_Opponent_Activity.class);
                startActivity(intent);
                break;
            case  R.id.btn_move_admin:
                Intent intentb = new Intent
                        (AdminMenu_Activity.this,
                                AllPlayersList_Activity.class);
                startActivity(intentb);
                break;
            case R.id.btn_set_admin:
                Intent intenta = new Intent
                        (AdminMenu_Activity.this,
                                SetRoles_Activity.class);
                startActivity(intenta);
                break;
            case R.id.btn_coach_admin:
                Intent intentc = new Intent
                        (AdminMenu_Activity.this,
                                CoachMenu_Activity.class);
                startActivity(intentc);
                break;
        }
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.logout:
                Backendless.UserService.logout(new AsyncCallback<Void>() {
                    @Override
                    public void handleResponse(Void response) {
                        Helper.customToastA(AdminMenu_Activity.this,"Admin User Has Been Logged Out");
                        Intent intent = new Intent
                                (AdminMenu_Activity.this,
                                        com.example.piets.hockeymanagement.Login_Activity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void handleFault(BackendlessFault fault)
                    {
                        Toast.makeText(getBaseContext(), fault.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });


        }
        return super.onOptionsItemSelected(item);
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

}
