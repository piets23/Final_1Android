package com.example.piets.hockeymanagement.Coach;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.LoadRelationsQueryBuilder;
import com.example.piets.hockeymanagement.Classes.BaseActivity;
import com.example.piets.hockeymanagement.Classes.Players;
import com.example.piets.hockeymanagement.Classes.Teams;
import com.example.piets.hockeymanagement.Adapters.MyPlayersList_Adapter;
import com.example.piets.hockeymanagement.R;

import java.io.Serializable;
import java.util.List;

import static com.example.piets.hockeymanagement.R.mipmap.ic_logo_foreground;

public class MyPlayersList_Activity extends BaseActivity {

    MyPlayersList_Adapter adapterPlayers;
    ListView lvCoachPlayers;
    Spinner coachTeamsSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_players_list_);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setIcon(ic_logo_foreground);
        actionBar.setTitle("My Players");
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(false);

        final String user = Backendless.UserService.loggedInUser();

        lvCoachPlayers = findViewById(R.id.lvCoachPlayers);
        coachTeamsSpinner = findViewById(R.id.spin_coach_Teams);

        //Loads Data For The Users Teams Spinner
        LoadRelationsQueryBuilder<Teams> loadRelationsQueryBuilder;
        loadRelationsQueryBuilder = LoadRelationsQueryBuilder.of(Teams.class );
        loadRelationsQueryBuilder.setRelationName( "team" );

        showProgressDialog("Loading Data....");
        Backendless.Data.of(BackendlessUser.class ).loadRelations( user,
                loadRelationsQueryBuilder,
                new AsyncCallback<List<Teams>>() {
                    @Override
                    public void handleResponse(List<Teams> teams)
                    {
                        hideProgressDialog();
                        ArrayAdapter teamAdapter = new ArrayAdapter(getApplicationContext(), R.layout.spinner, teams);
                        coachTeamsSpinner = (Spinner)findViewById(R.id.spin_coach_Teams);
                        coachTeamsSpinner.setAdapter(teamAdapter);
                        coachTeamsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                        {

                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
                            {
                                Teams teams = (Teams) coachTeamsSpinner.getSelectedItem();
                                String teamId = teams.getObjectId();
                                LoadRelationsQueryBuilder<Players> loadRelationsQueryBuilder;
                                loadRelationsQueryBuilder = LoadRelationsQueryBuilder.of( Players.class );
                                loadRelationsQueryBuilder.setRelationName( "RTP" );
                                loadRelationsQueryBuilder.setPageSize(100);

                                showProgressDialog("Loading Players In The Team...");
                                Backendless.Data.of( "teams" ).loadRelations( teamId,
                                        loadRelationsQueryBuilder,
                                        new AsyncCallback<List<Players>>()
                                        {
                                            @Override
                                            public void handleResponse( List<Players> players )
                                            {
                                                adapterPlayers = new MyPlayersList_Adapter(MyPlayersList_Activity.this, players);
                                                lvCoachPlayers.setAdapter(adapterPlayers);
                                                hideProgressDialog();
                                            }

                                            @Override
                                            public void handleFault( BackendlessFault fault )
                                            {

                                            }
                                        } );


                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent)
                            {

                            }
                        });
                    }

                    @Override
                    public void handleFault(BackendlessFault fault)
                    {

                    }
                });


        lvCoachPlayers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Players selectedPlayer = adapterPlayers.getItem(position);

                Intent intent = new Intent
                        (MyPlayersList_Activity.this,
                                PlayerInformation_Activity.class);
                intent.putExtra("Player", (Serializable) selectedPlayer);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed()
    {
        finish();

    }
}
