package com.example.piets.hockeymanagement.Coach;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.example.piets.hockeymanagement.Classes.BaseActivity;
import com.example.piets.hockeymanagement.Classes.Helper;
import com.example.piets.hockeymanagement.Classes.Players;
import com.example.piets.hockeymanagement.R;

import static com.example.piets.hockeymanagement.R.mipmap.ic_logo_foreground;

public class EditPlayer_Activity extends BaseActivity {

    EditText editName,editSurname,editMedName,editMedPlan,editMedNum,editAllergies,editParent1,editParent2;
    Button btnEditupdate;
    TextView tvPlayerName;

    String name,surName,medName,medPlan,medNum,allergies,parent1,parent2;


    @Override
    protected void onCreate(final Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_player_);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setIcon(ic_logo_foreground);
        actionBar.setTitle("Edit Player");
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(false);

        final Players getPlayer = (Players)getIntent().getSerializableExtra("infoPlayer");
        String playerName = getPlayer.getName();

        tvPlayerName = findViewById(R.id.edit_activity_player_name);

        editName = findViewById(R.id.edit_player_name);
        editSurname = findViewById(R.id.edit_player_surname);
        editMedName = findViewById(R.id.edit_medical_aid_name);
        editMedPlan = findViewById(R.id.edit_medical_aid_plan);
        editMedNum = findViewById(R.id.edit_medical_aid_num);
        editAllergies = findViewById(R.id.edit_medical_allergies);
        editParent1 = findViewById(R.id.edit_medical_1st_parent_cell);
        editParent2 = findViewById(R.id.edit_medical_2nd_parent_cell);

        btnEditupdate =findViewById(R.id.btn_medical_update_info);

        name = getPlayer.getName();
        surName = getPlayer.getSurname();
        medName = getPlayer.getMedAidName();
        medPlan = getPlayer.getMedAidPlan();
        medNum = getPlayer.getMedAidNumber();
        allergies = getPlayer.getAllergies();
        parent1 = getPlayer.getParentNumber1();
        parent2 = getPlayer.getParentNumber2();

        tvPlayerName.setText(surName + "," + name);
        editName.setText(name);
        editSurname.setText(surName);
        editMedName.setText(medName);
        editMedPlan.setText(medPlan);
        editMedNum.setText(medNum);
        editAllergies.setText(allergies);
        editParent1.setText(parent1);
        editParent2.setText(parent2);

        btnEditupdate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
              name = Helper.getText(editName);
              surName = Helper.getText(editSurname);
              medName = Helper.getText(editMedName);
              medPlan = Helper.getText(editMedPlan);
              medNum = Helper.getText(editMedNum);
              allergies = Helper.getText(editAllergies);
              parent1 = Helper.getText(editParent1);
              parent2 = Helper.getText(editParent2);



                Backendless.Persistence.save( getPlayer, new AsyncCallback<Players>() {
                    public void handleResponse( Players updatePlayer )
                    {
                        updatePlayer.setName(name);
                        updatePlayer.setSurname(surName);
                        updatePlayer.setMedAidName(medName);
                        updatePlayer.setMedAidPlan(medPlan);
                        updatePlayer.setMedAidNumber(medNum);
                        updatePlayer.setAllergies(allergies);
                        updatePlayer.setParentNumber1(parent1);
                        updatePlayer.setParentNumber2(parent2);

                        showProgressDialog("Updating Player Information...");
                        Backendless.Persistence.save( updatePlayer, new AsyncCallback<Players>() {
                            @Override
                            public void handleResponse( Players response )
                            {
                                hideProgressDialog();
                                Helper.clearText(editName);
                                Helper.clearText(editSurname);
                                Helper.clearText(editMedName);
                                Helper.clearText(editMedPlan);
                                Helper.clearText(editMedNum);
                                Helper.clearText(editAllergies);
                                Helper.clearText(editParent1);
                                Helper.clearText(editParent2);
                                Intent intentb = new Intent
                                        (EditPlayer_Activity.this,
                                                MyPlayersList_Activity.class);
                                startActivity(intentb);
                                finish();
                            }
                            @Override
                            public void handleFault( BackendlessFault fault )
                            {
                                Toast.makeText(EditPlayer_Activity.this, fault.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        } );
                    }
                    @Override
                    public void handleFault( BackendlessFault fault )
                    {
                        // an error has occurred, the error code can be retrieved with fault.getCode()
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
