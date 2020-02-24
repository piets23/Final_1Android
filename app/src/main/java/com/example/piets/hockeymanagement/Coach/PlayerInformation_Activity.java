package com.example.piets.hockeymanagement.Coach;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.example.piets.hockeymanagement.Classes.BaseActivity;
import com.example.piets.hockeymanagement.Classes.Players;
import com.example.piets.hockeymanagement.R;

import java.io.Serializable;

import static com.example.piets.hockeymanagement.R.mipmap.ic_logo_foreground;

public class PlayerInformation_Activity extends BaseActivity {

    Players player;
    TextView tvPlayerName,tvMedName,tvMedPlan,tvMedNr,tvAllergies;
    Button btnParent1,btnParent2;

    String name,surname,medName,medPlan,medNr,allergies;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_information_);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setIcon(ic_logo_foreground);
        actionBar.setTitle("Player Info");
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(false);

        player = (Players)getIntent().getSerializableExtra("Player");

        tvPlayerName = findViewById(R.id.info_player_name);
        tvMedName = findViewById(R.id.tv_med_aid_name_data);
        tvMedPlan = findViewById(R.id.tv_med_aid_plan_data);
        tvMedNr = findViewById(R.id.tv_med_aid_nr_data);
        tvAllergies = findViewById(R.id.tv_allergies_data);

        btnParent1 = findViewById(R.id.btn_call_parent1);
        btnParent1.setOnClickListener(onParentCall);
        btnParent2 = findViewById(R.id.btn_call_parent2);
        btnParent2.setOnClickListener(onParentCall);


        name = player.getName();
        surname = player.getSurname();
        medName = player.getMedAidName();
        medPlan = player.getMedAidPlan();
        medNr = player.getMedAidNumber();
        allergies = player.getAllergies();

        tvPlayerName.setText(surname + "," + name);
        tvMedName.setText(medName);
        tvMedPlan.setText(medPlan);
        tvMedNr.setText(medNr);
        tvAllergies.setText(allergies);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.playerinformationmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }



    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.deletePlayer:
                AlertDialog diaBox = AskOption();
                diaBox.show();
                break;
            case R.id.editPlayer:
                Intent intent = new Intent
                        (PlayerInformation_Activity.this,
                                EditPlayer_Activity.class);
                intent.putExtra("infoPlayer" ,(Serializable) player);
                startActivity(intent);
                finish();

        }
        return super.onOptionsItemSelected(item);
    }

    private View.OnClickListener onParentCall = new View.OnClickListener() {
        @Override
        public void onClick(View view)
        {
            switch (view.getId())
            {
                case R.id.btn_call_parent1:
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", player.getParentNumber1(), null));
                    startActivity(intent);
                    break;

                case R.id.btn_call_parent2:
                    Intent intenta = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", player.getParentNumber2(), null));
                    startActivity(intenta);
                    break;

            }
        }
    };

    private AlertDialog AskOption()
    {
        AlertDialog myQuittingDialogBox =new AlertDialog.Builder(this)
                //set message, title, and icon
                .setTitle("Delete")
                .setMessage("Are You Sure You Want To Delete This Player?")
                .setIcon(R.drawable.ic_delete)

                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        Backendless.Persistence.save( player, new AsyncCallback<Players>()
                        {
                            public void handleResponse( Players savedPlayer )
                            {
                                showProgressDialog("Deleting Player...");
                                Backendless.Persistence.of( Players.class ).remove( savedPlayer,
                                        new AsyncCallback<Long>()
                                        {
                                            public void handleResponse( Long response )
                                            {
                                                hideProgressDialog();
                                                Intent intent = new Intent
                                                        (PlayerInformation_Activity.this,
                                                                MyPlayersList_Activity.class);
                                                startActivity(intent);
                                                finish();
                                            }
                                            public void handleFault( BackendlessFault fault )
                                            {
                                                // an error has occurred, the error code can be
                                                // retrieved with fault.getCode()
                                            }
                                        } );
                            }
                            @Override
                            public void handleFault( BackendlessFault fault )
                            {
                                // an error has occurred, the error code can be retrieved with fault.getCode()
                            }
                        });
                        dialog.dismiss();
                    }

                })



                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();

                    }
                })
                .create();
        return myQuittingDialogBox;

    }

    @Override
    public void onBackPressed()
    {
        finish();
    }
}


