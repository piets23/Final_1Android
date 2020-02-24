package com.example.piets.hockeymanagement.Admin;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;
import com.example.piets.hockeymanagement.Classes.BaseActivity;
import com.example.piets.hockeymanagement.Classes.Players;
import com.example.piets.hockeymanagement.R;

import java.io.Serializable;
import java.util.List;

import static com.example.piets.hockeymanagement.R.mipmap.ic_logo_foreground;

public class AllPlayersList_Activity extends BaseActivity {

    AllPlayersList_Adapter adapterPlayers;
    ListView lvPlayers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_players_list_);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setIcon(ic_logo_foreground);
        actionBar.setTitle("All Players");
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(false);

        lvPlayers = findViewById(R.id.lvPlayers);

        //Add all players to the List
        DataQueryBuilder queryBuilder = DataQueryBuilder.create();
        queryBuilder.setPageSize( 100 ).setOffset( 0 );

        showProgressDialog("Loading Players....");
        Backendless.Data.of(Players.class).find(queryBuilder,
                new AsyncCallback<List<Players>>() {

                    @Override
                    public void handleResponse(List<Players> response) {
                        adapterPlayers = new AllPlayersList_Adapter(AllPlayersList_Activity.this, response);
                        lvPlayers.setAdapter(adapterPlayers);
                        hideProgressDialog();
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
                        Toast.makeText(AllPlayersList_Activity.this, fault.getMessage(), Toast.LENGTH_LONG).show();
                    }


                });

        lvPlayers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Players selectedPlayer = adapterPlayers.getItem(position);

                Intent intent = new Intent
                        (AllPlayersList_Activity.this,
                                MovePlayers_Activity.class);
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
