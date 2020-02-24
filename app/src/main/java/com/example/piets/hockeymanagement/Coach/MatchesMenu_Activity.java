package com.example.piets.hockeymanagement.Coach;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.example.piets.hockeymanagement.Classes.BaseActivity;
import com.example.piets.hockeymanagement.R;

import static com.example.piets.hockeymanagement.R.mipmap.ic_logo_foreground;

public class MatchesMenu_Activity extends BaseActivity {

    String userMail;
    TextView userEmail , coach;
    Button btnMatchLineup , btnMatchStats ;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matches_menu_);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setIcon(ic_logo_foreground);
        actionBar.setTitle("Matches Menu");
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(false);

        userEmail = findViewById(R.id.tv_user_logged_in_coach);
        final String user = Backendless.UserService.loggedInUser().trim();
        showProgressDialog("Loading Data...");
        Backendless.Data.of(BackendlessUser.class).findById(user, new AsyncCallback<BackendlessUser>() {
            @Override
            public void handleResponse(BackendlessUser response)
            {
                hideProgressDialog();
                userMail = response.getEmail();
                userEmail.setText(userMail);
            }

            @Override
            public void handleFault(BackendlessFault fault) {

            }
        });
        coach = findViewById(R.id.tv_coach);

        btnMatchLineup = findViewById(R.id.btn_match_line_up);
        btnMatchStats = findViewById(R.id.btn_enter_match_stats);
        btnMatchStats.setOnClickListener(onMatchMenuClickListener);
        btnMatchLineup.setOnClickListener(onMatchMenuClickListener);




    }

    private View.OnClickListener onMatchMenuClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view)
        {
            switch (view.getId())
            {
                case R.id.btn_match_line_up:
                    Intent intent = new Intent
                            (MatchesMenu_Activity.this,
                                    Coach_AllMatchesLineUp_List.class);
                    startActivity(intent);
                    break;

                case R.id.btn_enter_match_stats:
                    Intent intenta = new Intent
                            (MatchesMenu_Activity.this,
                                    Coach_MatchForTeams_Activity.class);
                    startActivity(intenta);
                    break;
        }
    };


};

    @Override
    public void onBackPressed()
    {
        finish();

    }
}
