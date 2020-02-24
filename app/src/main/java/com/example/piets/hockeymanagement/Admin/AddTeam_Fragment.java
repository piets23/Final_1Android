package com.example.piets.hockeymanagement.Admin;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;
import com.example.piets.hockeymanagement.Classes.FragmentBase;
import com.example.piets.hockeymanagement.Classes.Helper;
import com.example.piets.hockeymanagement.Classes.Teams;
import com.example.piets.hockeymanagement.Classes.Users;
import com.example.piets.hockeymanagement.R;
import com.example.piets.hockeymanagement.loadTeamsAddRelation;

import java.util.List;


public class AddTeam_Fragment extends FragmentBase {


    private Spinner spinnerAge;
    private Spinner spinnerCoach;
    EditText edtTeamName;
    Button btnSubmitTeam;


    Users user;

    private String teamName;

    public AddTeam_Fragment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {


         final View rootView = inflater.inflate(R.layout.fragment_add_team_,container,false);

        edtTeamName = (EditText) rootView.findViewById(R.id.edt_team_name_addTeam);
        btnSubmitTeam = (Button) rootView.findViewById(R.id.btn_submit_team);

        spinnerAge = (Spinner) rootView.findViewById(R.id.spin_age_group_spinner);
        ArrayAdapter<CharSequence> ageAdapter= ArrayAdapter
                .createFromResource(getActivity().getApplicationContext(), R.array.age_group_array,
                        android.R.layout.simple_spinner_item);
        ageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAge.setAdapter(ageAdapter);

        //finds all the coaches for the spinner
        String whereClause = "role = 'coach'";
        DataQueryBuilder queryBuilder = DataQueryBuilder.create();
        queryBuilder.setWhereClause( whereClause );

        Backendless.Data.of( Users.class ).find( queryBuilder,
                new AsyncCallback<List<Users>>(){
                    @Override
                    public void handleResponse( List<Users> users )
                    {
                        // the "foundContact" collection now contains instances of the Contact class.
                        // each instance represents an object stored on the server.
                        ArrayAdapter userAdapter = new ArrayAdapter(getActivity().getApplicationContext(), R.layout.spinner, users);
                        spinnerCoach =(Spinner) rootView.findViewById(R.id.spin_coach_for_team_spinner);
                        spinnerCoach.setAdapter(userAdapter);

                    }


                    @Override
                    public void handleFault( BackendlessFault fault )
                    {
                        Toast.makeText(getActivity().getBaseContext(), fault.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });


        btnSubmitTeam.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                user = (Users) ( (Spinner) rootView.findViewById(R.id.spin_coach_for_team_spinner) ).getSelectedItem();

                    String userId = user.getObjectId();


                    teamName = Helper.getText(edtTeamName);
                    if (teamName.isEmpty())
                    {
                        Toast.makeText(getActivity(), "Team Name must be entered" +
                                        "",
                                Toast.LENGTH_SHORT).show();
                    }
                    else

                        {
                        //Save the team object
                        Teams team = new Teams();
                        team.setAgeGroup(spinnerAge.getSelectedItem().toString());
                        team.setTeamName(teamName);

                        //to save team and set relation
                        Backendless.Persistence.save(team, new AsyncCallback<Teams>() {
                            public void handleResponse(Teams response)
                            {

                            }

                            public void handleFault(BackendlessFault fault)
                            {
                                Toast.makeText(getActivity(), fault.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

                        loadTeamsAddRelation a = loadTeamsAddRelation.getInstance().getInstance().getInstance();
                        showProgressDialog("Saving Team....");
                        a.setUserTeamsAsync(userId, teamName, new AsyncCallback<Object>() {
                            @Override
                            public void handleResponse(Object response)
                            {
                               hideProgressDialog();
                            }


                            @Override
                            public void handleFault(BackendlessFault fault)
                            {
                                hideProgressDialog();
                                Toast.makeText(getActivity(), fault.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

                    }


                }

        });


       return rootView;
    }

}
