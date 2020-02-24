package com.example.piets.hockeymanagement.Admin;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;
import com.example.piets.hockeymanagement.Classes.BaseActivity;
import com.example.piets.hockeymanagement.Classes.Players;
import com.example.piets.hockeymanagement.Classes.Teams;
import com.example.piets.hockeymanagement.R;

import java.util.ArrayList;
import java.util.List;

import static com.example.piets.hockeymanagement.R.mipmap.ic_logo_foreground;

public class MovePlayers_Activity extends BaseActivity {



   Spinner  spnTeams, spinCurrentTeam;
   TextView playerName ;
   Button btnMovePlayer;
   Players player;

  Teams oldteam , newTeam ;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_players_);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setIcon(ic_logo_foreground);
        actionBar.setTitle("Move Players");
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(false);



        player = (Players)getIntent().getSerializableExtra("Player");


        playerName =(TextView) findViewById(R.id.tv_player_name);
        btnMovePlayer = (Button) findViewById(R.id.btnMovePlayer);

        String name = player.getName();
        final String objectId = player.getObjectId();
        final String team = player.getTeam();
        playerName.setText(name);



        String whereClause = "teamName = '" + team + "'";
        DataQueryBuilder queryBuilder = DataQueryBuilder.create();
        queryBuilder.setWhereClause( whereClause );


        Backendless.Data.of( Teams.class ).find( queryBuilder,
                new AsyncCallback<List<Teams>>() {
                    @Override
                    public void handleResponse(List<Teams> foundTeams)
                    {

                        ArrayAdapter teamAdapter = new ArrayAdapter(getApplicationContext(), R.layout.spinner, foundTeams);
                        spinCurrentTeam = (Spinner) findViewById(R.id.spin_current_teams);
                        spinCurrentTeam.setAdapter(teamAdapter);

                    }

                    @Override
                    public void handleFault(BackendlessFault fault)
                    {
                        Toast.makeText(getBaseContext(), fault.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });


        final ArrayList<Players> playerCollection = new ArrayList<Players>();
        playerCollection.add( player );


        showProgressDialog("Loading Teams.....");
        Backendless.Data.of( Teams.class ).find(
                new AsyncCallback<List<Teams>>(){

                    @Override
                    public void handleResponse(List<Teams> teams)
                    {
                        ArrayAdapter teamNameAdapter = new ArrayAdapter(getApplicationContext(), R.layout.spinner, teams);
                        spnTeams =(Spinner) findViewById(R.id.all_teams_spinner);
                        spnTeams.setAdapter(teamNameAdapter);
                        hideProgressDialog();
                    }

                    @Override
                    public void handleFault( BackendlessFault fault )
                    {
                        Toast.makeText(getBaseContext(), fault.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        btnMovePlayer.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                oldteam = (Teams) ( (Spinner) findViewById(R.id.spin_current_teams) ).getSelectedItem();
                newTeam = (Teams) ( (Spinner) findViewById(R.id.all_teams_spinner) ).getSelectedItem();
                String newTeamName = (String) ( (Spinner) findViewById(R.id.all_teams_spinner) ).getSelectedItem().toString();


                Backendless.Data.of( Teams.class ).deleteRelation( oldteam, "RTP", playerCollection,
                        new AsyncCallback<Integer>()
                        {
                            @Override
                            public void handleResponse( Integer howManyObjDeleted )
                            {

                            }

                            @Override
                            public void handleFault( BackendlessFault fault )
                            {
                                Toast.makeText(getBaseContext(), fault.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        } );



                Backendless.Data.of( Teams.class ).addRelation( newTeam, "RTP", playerCollection,
                        new AsyncCallback<Integer>()
                        {
                            @Override
                            public void handleResponse( Integer response )
                            {

                            }

                            @Override
                            public void handleFault( BackendlessFault fault )
                            {
                                Toast.makeText(getBaseContext(), fault.getMessage(),Toast.LENGTH_LONG).show();
                            }
                        } );

                player.setTeam(newTeamName);
                showProgressDialog("Moving Player....");
                Backendless.Persistence.save( player, new AsyncCallback<Players>() {
                    public void handleResponse( Players response )
                    {
                        hideProgressDialog();
                    }

                    public void handleFault( BackendlessFault fault )
                    {
                        Toast.makeText(getBaseContext(), fault.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

            }


        });

    }

    @Override
    public void onBackPressed()
    {
        finish();
    }

}
