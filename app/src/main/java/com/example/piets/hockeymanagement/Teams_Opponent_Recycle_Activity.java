package com.example.piets.hockeymanagement;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;
import com.example.piets.hockeymanagement.Adapters.OpponentRecycle_Adapter;
import com.example.piets.hockeymanagement.Adapters.TeamRecycle_Adapter;
import com.example.piets.hockeymanagement.Classes.Helper;
import com.example.piets.hockeymanagement.Classes.Opponent;
import com.example.piets.hockeymanagement.Classes.Teams;

import java.util.List;

public class Teams_Opponent_Recycle_Activity extends AppCompatActivity
{
    RecyclerView teamREC, OpponentREC;
    RecyclerView.Adapter teamAdapter, OpponentAdapter;
    RecyclerView.LayoutManager layoutManagerOpponent , layoutManagerTeam;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teams__opponent__recycle_);

        teamREC = findViewById(R.id.TeamREC);
        OpponentREC = findViewById(R.id.opponentREC);

        teamREC.setHasFixedSize(true);
        OpponentREC.setHasFixedSize(true);

        layoutManagerOpponent = new LinearLayoutManager(this);
        layoutManagerTeam = new LinearLayoutManager(this);

        teamREC.setLayoutManager(layoutManagerTeam);
        OpponentREC.setLayoutManager(layoutManagerOpponent);


        DataQueryBuilder queryBuilder = DataQueryBuilder.create();
        queryBuilder.setPageSize( 100 ).setOffset( 0 );
        Backendless.Data.of(Teams.class).find(queryBuilder, new AsyncCallback<List<Teams>>() {
            @Override
            public void handleResponse(List<Teams> response)
            {
                teamAdapter = new TeamRecycle_Adapter(Teams_Opponent_Recycle_Activity.this , response);
                teamREC.setAdapter(teamAdapter);
                teamREC.setClickable(false);
            }

            @Override
            public void handleFault(BackendlessFault fault)
            {
                Toast.makeText(getBaseContext(), fault.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        Backendless.Data.of(Opponent.class).find(queryBuilder, new AsyncCallback<List<Opponent>>() {
            @Override
            public void handleResponse(List<Opponent> response)
            {
                OpponentAdapter = new OpponentRecycle_Adapter(Teams_Opponent_Recycle_Activity.this, response);
                OpponentREC.setAdapter(OpponentAdapter);
            }

            @Override
            public void handleFault(BackendlessFault fault)
            {
                Toast.makeText(getBaseContext(), fault.getMessage(), Toast.LENGTH_LONG).show();
            }
        });



    }

    @Override
    public void onBackPressed()
    {
        finish();
    }
}
