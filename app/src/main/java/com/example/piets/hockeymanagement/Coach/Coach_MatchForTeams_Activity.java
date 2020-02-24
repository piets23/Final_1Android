package com.example.piets.hockeymanagement.Coach;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.example.piets.hockeymanagement.Classes.BaseActivity;
import com.example.piets.hockeymanagement.Classes.Matches;
import com.example.piets.hockeymanagement.Coach.MatchStats.MainActivity;
import com.example.piets.hockeymanagement.R;

import java.io.Serializable;
import java.util.List;

import static com.example.piets.hockeymanagement.R.mipmap.ic_logo_foreground;

public class Coach_MatchForTeams_Activity extends BaseActivity {

    AllMatchesList_Adapter adapterMatches;
    ListView lvMatchesForCoach;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coach__match_for_teams_);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setIcon(ic_logo_foreground);
        actionBar.setTitle("My Matches");
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(false);

        lvMatchesForCoach = findViewById(R.id.lvCoachMatches);

        showProgressDialog("Loading Matches");
        Backendless.Data.of(Matches.class).find(new AsyncCallback<List<Matches>>(){
            @Override
            public void handleResponse( List<Matches> matches )
            {
                hideProgressDialog();
                adapterMatches = new AllMatchesList_Adapter(Coach_MatchForTeams_Activity.this, matches);
                lvMatchesForCoach.setAdapter(adapterMatches);
            }
            @Override
            public void handleFault( BackendlessFault fault )
            {

            }
        });

        lvMatchesForCoach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Matches selectedMatch = adapterMatches.getItem(position);

                Intent intent = new Intent
                        (Coach_MatchForTeams_Activity.this,
                                MainActivity.class);
                intent.putExtra("Match", (Serializable) selectedMatch);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.matches_list_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.add_Match:

                Intent intent = new Intent
                        (Coach_MatchForTeams_Activity.this,
                                Match_LineUp_activity.class);
                startActivity(intent);


        }
        return super.onOptionsItemSelected(item);
    }
}
