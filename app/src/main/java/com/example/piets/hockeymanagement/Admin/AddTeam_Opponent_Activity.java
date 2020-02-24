package com.example.piets.hockeymanagement.Admin;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.piets.hockeymanagement.R;

import static com.example.piets.hockeymanagement.R.mipmap.ic_logo_foreground;

public class AddTeam_Opponent_Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinnerTeamOpponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_team__opponent_);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setIcon(ic_logo_foreground);
        actionBar.setTitle("Add Team / Opponent");
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(false);


        spinnerTeamOpponent = (Spinner) findViewById(R.id.spin_team_opponent_spinner);
        spinnerTeamOpponent.setOnItemSelectedListener(this);
        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> teamOpponentArrayAdapter = ArrayAdapter
                .createFromResource(this, R.array.team_opponent_array,
                        android.R.layout.simple_spinner_item);
        teamOpponentArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTeamOpponent.setAdapter(teamOpponentArrayAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.addteam_opponent, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        switch (position) {
            case 0:
                AddTeam_Fragment teamFragment = new AddTeam_Fragment();

                teamFragment.setArguments(getIntent().getExtras());

                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.add_team_opponent_fragment_container, teamFragment).commit();
                break;
            case 1:
                AddOpponent_Fragment opponent_fragment = new AddOpponent_Fragment();
                opponent_fragment.setArguments(getIntent().getExtras());
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.add_team_opponent_fragment_container, opponent_fragment).commit();
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.teams_opponents_list:
                Intent intent = new Intent
                        (AddTeam_Opponent_Activity.this,
                                com.example.piets.hockeymanagement.Teams_Opponent_Recycle_Activity.class);
                startActivity(intent);

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed()
    {
        finish();
    }
}





