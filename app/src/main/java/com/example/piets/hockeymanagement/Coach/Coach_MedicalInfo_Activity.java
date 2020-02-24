package com.example.piets.hockeymanagement.Coach;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
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
import com.example.piets.hockeymanagement.Classes.Teams;
import com.example.piets.hockeymanagement.R;

import java.util.ArrayList;

import static com.example.piets.hockeymanagement.R.mipmap.ic_logo_foreground;

public class Coach_MedicalInfo_Activity extends BaseActivity {

    EditText medicalAidName, medicalAidPlan, medicalAidNum,
            allergies, parentNum1, parentNum2;
    Button saveMedicalInfo;
    TextView tvDisplayPlayerName;

    private String medAidName, medAidPlan, medAllergies,
            medAidNum, parentCell1, parentCell2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coach__medical_info_);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setIcon(ic_logo_foreground);
        actionBar.setTitle("Add Medical Info");
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(false);

         final Players newPlayer = (Players)getIntent().getSerializableExtra("newPlayer");
        String playerName = newPlayer.getName();

        medicalAidName = findViewById(R.id.edt_medical_aid_name);
        medicalAidPlan = findViewById(R.id.edt_medical_aid_plan);
        medicalAidNum = findViewById(R.id.edt_medical_aid_num);
        allergies = findViewById(R.id.edt_medical_allergies);
        parentNum1 = findViewById(R.id.edt_medical_1st_parent_cell);
        parentNum2 = findViewById(R.id.edt_medical_2nd_parent_cell);
        saveMedicalInfo = findViewById(R.id.btn_medical_save_info);
        tvDisplayPlayerName = findViewById(R.id.tv_coach_medical_info);

        //Displays name that was entered in previous layout
        tvDisplayPlayerName.setText("Enter Medical Info For : " + playerName);


        saveMedicalInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Backendless.Persistence.save(newPlayer , new AsyncCallback<Players>() {
                    public void handleResponse( Players savedPlayer )
                    {
                        final Teams playerTeam = (Teams) getIntent().getSerializableExtra("playersTeam");

                        medAidName = Helper.getText(medicalAidName);
                        medAidPlan = Helper.getText(medicalAidPlan);
                        medAllergies = Helper.getText(allergies);
                        medAidNum = Helper.getText(medicalAidNum);
                        parentCell1 = Helper.getText(parentNum1);
                        parentCell2 = Helper.getText(parentNum2);

                       savedPlayer.setMedAidName(medAidName);
                       savedPlayer.setMedAidPlan(medAidPlan);
                       savedPlayer.setAllergies(medAllergies);
                       savedPlayer.setMedAidNumber(medAidNum);
                       savedPlayer.setParentNumber1(parentCell1);
                       savedPlayer.setParentNumber2(parentCell2);

                       showProgressDialog("Saving Medical Information...");
                        Backendless.Persistence.save( savedPlayer, new AsyncCallback<Players>() {
                            @Override
                            public void handleResponse( Players response )
                            {
                                hideProgressDialog();

                                ArrayList<Players> playersCollection = new ArrayList<Players>();
                                playersCollection.add( response );
                                showProgressDialog("Adding Player To Team...");
                                Backendless.Data.of( Teams.class ).setRelation( playerTeam, "RTP", playersCollection,
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
                            public void handleFault( BackendlessFault fault )
                            {
                                Toast.makeText(getBaseContext(), fault.getMessage(), Toast.LENGTH_LONG).show();
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
}

