package com.example.piets.hockeymanagement.Coach;


import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.LoadRelationsQueryBuilder;
import com.example.piets.hockeymanagement.Classes.BaseActivity;
import com.example.piets.hockeymanagement.Classes.Helper;
import com.example.piets.hockeymanagement.Classes.Players;
import com.example.piets.hockeymanagement.Classes.Teams;
import com.example.piets.hockeymanagement.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static com.example.piets.hockeymanagement.R.mipmap.ic_logo_foreground;

public class Coach_AddPlayers_Activity extends BaseActivity {

    EditText playerName, playerSurname;
    Button btnMedical, btnSavePlayer;
    Spinner spinCoachTeams;

    Teams playerTeam;

    private String name, surName, team;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coach__add_players_);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setIcon(ic_logo_foreground);
        actionBar.setTitle("Add Player");
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(false);

        String user = Backendless.UserService.loggedInUser();

        spinCoachTeams = findViewById(R.id.spin_coach_add_player);
        playerName = findViewById(R.id.edt_coach_player_name);
        playerSurname = findViewById(R.id.edt_coach_player_surname);
        btnMedical = findViewById(R.id.btn_coach_medical_info);
        btnSavePlayer = findViewById(R.id.btn_coach_save_player);


        LoadRelationsQueryBuilder<Teams> loadRelationsQueryBuilder;
        loadRelationsQueryBuilder = LoadRelationsQueryBuilder.of(Teams.class );
        loadRelationsQueryBuilder.setRelationName( "team" );
        showProgressDialog("Loading Data.....");
        Backendless.Data.of(BackendlessUser.class ).loadRelations( user,
                loadRelationsQueryBuilder,
                new AsyncCallback<List<Teams>>()
                {

                    @Override
                    public void handleResponse(List<Teams> teams)
                    {

                            ArrayAdapter teamAdapter = new ArrayAdapter(getApplicationContext(), R.layout.spinner, teams);
                            spinCoachTeams = (Spinner) findViewById(R.id.spin_coach_add_player);
                            spinCoachTeams.setAdapter(teamAdapter);
                            hideProgressDialog();

                    }

                    @Override
                    public void handleFault( BackendlessFault fault )
                    {
                        Toast.makeText(getBaseContext(), fault.getMessage(), Toast.LENGTH_LONG).show();
                    }
                } );


        btnSavePlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                playerTeam = (Teams) ( (Spinner) findViewById(R.id.spin_coach_add_player) ).getSelectedItem();
                if(Helper.isEmpty(playerName) || Helper.isEmpty(playerSurname))
                {
                    Helper.customToastA(Coach_AddPlayers_Activity.this,"Enter Name and Surname");
                }

                else
                {
                    name = Helper.getText(playerName);
                    surName = Helper.getText(playerSurname);
                    team = ((Spinner) findViewById(R.id.spin_coach_add_player)).getSelectedItem().toString();

                    Players player = new Players();
                    player.setName(name);
                    player.setSurname(surName);
                    player.setTeam(team);
                    showProgressDialog("Saving Player.....");
                    Backendless.Persistence.save(player, new AsyncCallback<Players>() {
                        @Override
                        public void handleResponse(Players response)
                        {
                            hideProgressDialog();
                            ArrayList<Players> playersCollection = new ArrayList<Players>();
                            playersCollection.add( response );
                            showProgressDialog("Adding Player To Selected Team....");
                            Backendless.Data.of( Teams.class ).addRelation( playerTeam, "RTP", playersCollection,
                                    new AsyncCallback<Integer>()
                                    {
                                        @Override
                                        public void handleResponse( Integer response )
                                        {
                                            hideProgressDialog();
                                        }

                                        @Override
                                        public void handleFault( BackendlessFault fault )
                                        {
                                            Toast.makeText(getBaseContext(), fault.getMessage(),Toast.LENGTH_LONG).show();
                                        }
                                    } );
                        }

                        @Override
                        public void handleFault(BackendlessFault fault)
                        {
                            Toast.makeText(getBaseContext(), fault.getMessage(),Toast.LENGTH_LONG).show();
                        }


                    });

                }
            }//end onClick
        });//end onClickListener

        btnMedical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                playerTeam = (Teams) ( (Spinner) findViewById(R.id.spin_coach_add_player) ).getSelectedItem();
                name = Helper.getText(playerName);
                surName = Helper.getText(playerSurname);
                team = ((Spinner) findViewById(R.id.spin_coach_add_player)).getSelectedItem().toString();
                if(Helper.isEmpty(playerName) || Helper.isEmpty(playerSurname))
                {
                    Helper.customToastA(Coach_AddPlayers_Activity.this,"Please Enter Player Name and Surname");
                }
                else
                {
                    Players player = new Players();
                    player.setName(name);
                    player.setSurname(surName);
                    player.setTeam(team);


                Intent intent = new Intent
                        (Coach_AddPlayers_Activity.this,
                                Coach_MedicalInfo_Activity.class);
                intent.putExtra("newPlayer", (Serializable) player);
                intent.putExtra("playersTeam" ,(Serializable) playerTeam);
                startActivity(intent);
                }
            }
        });


    }

    @Override
    public void onBackPressed()
    {
        finish();

    }


}
