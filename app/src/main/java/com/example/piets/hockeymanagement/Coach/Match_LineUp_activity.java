package com.example.piets.hockeymanagement.Coach;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;
import com.backendless.persistence.LoadRelationsQueryBuilder;
import com.example.piets.hockeymanagement.Classes.BaseActivity;
import com.example.piets.hockeymanagement.Classes.Helper;
import com.example.piets.hockeymanagement.Classes.Matches;
import com.example.piets.hockeymanagement.Classes.Opponent;
import com.example.piets.hockeymanagement.Classes.Players;
import com.example.piets.hockeymanagement.Classes.Teams;
import com.example.piets.hockeymanagement.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.example.piets.hockeymanagement.R.mipmap.ic_logo_foreground;

public class Match_LineUp_activity extends BaseActivity {

    private View mProgressView;
    private View mLoginFormView;
    private TextView tvLoad;

    Matches currentMatch;

    Spinner spinTeams,spinOpponent,spinMatchNumber ,spinLeftForward,spinRightForward,spinCenterForward,spinLeftLink,
    spinRightLink,spinCenterLink,spinLeftBack,spinRightBack,spinCenterback,spinSweeper,spinGoalie,
    spinBench1,spinBench2,spinBench3,spinBench4,spinBench5;

    RatingBar ratingLeftForward,ratingRightForward,ratingCenterForward,ratingLeftLink,
            ratingRightLink,ratingCenterLink,ratingLeftBack,ratingRightBack,ratingCenterback,ratingSweeper,ratingGoalie,
            ratingBench1,ratingBench2,ratingBench3,ratingBench4,ratingBench5;

    EditText edtGLeftForward, edtGRightForward, edtGCenterForward, edtGLeftLink, edtGRightLink,
            edtGCenterLink, edtGLeftBack, edtGRightBack, edtGCenterBack, edtGSweeper, edtGGoalie,
    edtGB1, edtGB2, edtGB3, edtGB4, edtGB5;

    String numberOfMatch;
    String team;
    String opponent;
    String leftForwardPlayer;
    String rightForwardPlayer;
    String centerForwardPlayer;
    String leftLinkPlayer;
    String rightLinkPlayer;
    String centerLinkPlayer;
    String leftBackPlayer;
    String rightBackPlayer;
    String centerBackPlayer;
    String sweeperPlayer;
    String goaliePlayer;
    String benchOnePlayer;
    String benchTwoPlayer;
    String benchThreePlayer;
    String benchFourPlayer;
    String benchFivePlayer;

    float leftForwardRating, rightForwardRating, centerForwardRating, leftLinkRating, rightLinkRating,
    centerLinkRating, leftBackRating, rightBackRating, centerBackRating, sweeperRating, goalieRating,
    benchOneRating, benchTwoRating, benchThreeRating, benchFourRating, benchFiveRating;

    Integer leftForwardGoals, rightForwardGoals, centerForwardGoals, leftLinkGoals, rightLinkGoals,
    centerLinkGoals, leftBackGoals, rightBackGoals, centerBackGoals, sweeperGoals, goalieGoals, benchOneGoals,
    benchTwoGoals,benchThreeGoals, benchFourGoals, benchFiveGoals;

    Matches matches;

    ArrayAdapter teamAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match__line_up_activity);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setIcon(ic_logo_foreground);
        actionBar.setTitle("Match Lineup");
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(false);

        mLoginFormView = findViewById(R.id.line_up_form);
        mProgressView = findViewById(R.id.login_progress);
        tvLoad = findViewById(R.id.tvLoad);

        final String user = Backendless.UserService.loggedInUser();

        edtGLeftForward = findViewById(R.id.edtGoalLeftForward);
        edtGRightForward = findViewById(R.id.edtRightForward);
        edtGCenterForward = findViewById(R.id.edtGoalCenterForward);
        edtGLeftLink = findViewById(R.id.edtGoalsLeftLink);
        edtGRightLink = findViewById(R.id.edtGoalsRightLink);
        edtGCenterLink = findViewById(R.id.edtGoalsCenterLink);
        edtGLeftBack = findViewById(R.id.edtGoalsLeftBack);
        edtGRightBack = findViewById(R.id.edtGoalsRightBack);
        edtGCenterBack = findViewById(R.id.edtGoalsCenterBack);
        edtGSweeper = findViewById(R.id.edtGoalsSweeper);
        edtGGoalie = findViewById(R.id.edtGoalsGoalie);
        edtGB1 = findViewById(R.id.edtGoalsBench1);
        edtGB2 = findViewById(R.id.edtGoalsBench2);
        edtGB3 = findViewById(R.id.edtGoalsBench3);
        edtGB4 = findViewById(R.id.edtGoalsBench4);
        edtGB5 = findViewById(R.id.edtGoalsBench5);

        ratingLeftForward = findViewById(R.id.ratingLeftForward);
        ratingRightForward = findViewById(R.id.ratingRightForward);
        ratingCenterForward = findViewById(R.id.ratingCenterForward);
        ratingLeftLink = findViewById(R.id.ratingLeftLink);
        ratingRightLink = findViewById(R.id.ratingRightLink);
        ratingCenterLink = findViewById(R.id.ratingCenterLink);
        ratingLeftBack = findViewById(R.id.ratingLeftBack);
        ratingRightBack = findViewById(R.id.ratingRightBack);
        ratingCenterback = findViewById(R.id.ratingCenterBack);
        ratingSweeper = findViewById(R.id.ratingSweeper);
        ratingGoalie = findViewById(R.id.ratingGoalie);
        ratingBench1 = findViewById(R.id.ratingBench1);
        ratingBench2 = findViewById(R.id.ratingBench2);
        ratingBench3 = findViewById(R.id.ratingBench3);
        ratingBench4 = findViewById(R.id.ratingBench4);
        ratingBench5 = findViewById(R.id.ratingBench5);

        currentMatch = (Matches) getIntent().getSerializableExtra("ThisMatch");

        if(currentMatch != null)
        {
            loadSpinnersDataSavedMatch(user);
            loadRatingbarSaved();
            loadGoalsSaved();
        }

        else
        {

            loadSpinnersData(user);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.lineupmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.saveMenuMatch:


                leftForwardPlayer = ((Spinner) findViewById(R.id.spin_left_forward)).getSelectedItem().toString();
                leftForwardRating = ratingLeftForward.getRating();
                if(Helper.isEmpty(edtGLeftForward))
                {
                    leftForwardGoals = Integer.parseInt(String.valueOf("0"));
                }
                else
                {
                    leftForwardGoals = Integer.parseInt(String.valueOf(edtGLeftForward.getText()));
                }

                rightForwardPlayer =((Spinner) findViewById(R.id.spin_right_forward)).getSelectedItem().toString();
                rightForwardRating = ratingRightForward.getRating();
                if(Helper.isEmpty(edtGRightForward))
                {
                    rightForwardGoals = Integer.parseInt(String.valueOf("0"));
                }
                else
                {
                    rightForwardGoals = Integer.parseInt(String.valueOf(edtGRightForward.getText()));
                }

                centerForwardPlayer = ((Spinner) findViewById(R.id.spin_center_forward)).getSelectedItem().toString();
                centerForwardRating = ratingCenterForward.getRating();
                if(Helper.isEmpty(edtGCenterForward))
                {
                    centerForwardGoals = Integer.parseInt(String.valueOf("0"));
                }
                else
                {
                    centerForwardGoals = Integer.parseInt(String.valueOf(edtGCenterForward.getText()));
                }

                leftLinkPlayer = ((Spinner) findViewById(R.id.spin_left_link)).getSelectedItem().toString();
                leftLinkRating = ratingLeftLink.getRating();
                if(Helper.isEmpty(edtGLeftLink))
                {
                    leftLinkGoals = Integer.parseInt(String.valueOf("0"));
                }
                else
                {
                    leftLinkGoals = Integer.parseInt(String.valueOf(edtGLeftLink.getText()));
                }

                rightLinkPlayer = ((Spinner) findViewById(R.id.spin_right_link)).getSelectedItem().toString();
                rightLinkRating = ratingRightLink.getRating();
                if(Helper.isEmpty(edtGRightLink))
                {
                    rightLinkGoals = Integer.parseInt(String.valueOf("0"));
                }
                else
                {
                    rightLinkGoals = Integer.parseInt(String.valueOf(edtGRightLink.getText()));
                }


                centerLinkPlayer = ((Spinner) findViewById(R.id.spin_center_link)).getSelectedItem().toString();
                centerLinkRating = ratingCenterLink.getRating();
                if(Helper.isEmpty(edtGCenterLink))
                {
                    centerLinkGoals = Integer.parseInt(String.valueOf("0"));
                }
                else
                {
                    centerLinkGoals = Integer.parseInt(String.valueOf(edtGCenterLink.getText()));
                }


                leftBackPlayer = ((Spinner) findViewById(R.id.spin_left_back)).getSelectedItem().toString();
                leftBackRating = ratingLeftBack.getRating();
                if(Helper.isEmpty(edtGLeftBack))
                {
                    leftBackGoals = Integer.parseInt(String.valueOf("0"));
                }
                else
                {
                    leftBackGoals = Integer.parseInt(String.valueOf(edtGLeftBack.getText()));
                }


                rightBackPlayer = ((Spinner) findViewById(R.id.spin_right_back)).getSelectedItem().toString();
                rightBackRating = ratingRightBack.getRating();
                if(Helper.isEmpty(edtGRightBack))
                {
                    rightBackGoals = Integer.parseInt(String.valueOf("0"));
                }
                else
                {
                    rightBackGoals = Integer.parseInt(String.valueOf(edtGRightBack.getText()));
                }

                centerBackPlayer = ((Spinner) findViewById(R.id.spin_center_back)).getSelectedItem().toString();
                centerBackRating = ratingCenterback.getRating();
                if(Helper.isEmpty(edtGCenterBack))
                {
                    centerBackGoals = Integer.parseInt(String.valueOf("0"));
                }
                else
                {
                    centerBackGoals = Integer.parseInt(String.valueOf(edtGCenterBack.getText()));
                }

                sweeperPlayer = ((Spinner) findViewById(R.id.spin_sweeper)).getSelectedItem().toString();
                sweeperRating = ratingSweeper.getRating();
                if(Helper.isEmpty(edtGSweeper))
                {
                    sweeperGoals = Integer.parseInt(String.valueOf("0"));
                }
                else
                {
                    sweeperGoals = Integer.parseInt(String.valueOf(edtGSweeper.getText()));
                }

                goaliePlayer = ((Spinner) findViewById(R.id.spin_goalie)).getSelectedItem().toString();
                goalieRating = ratingGoalie.getRating();
                if(Helper.isEmpty(edtGGoalie))
                {
                    goalieGoals = Integer.parseInt(String.valueOf("0"));
                }
                else
                {
                    goalieGoals = Integer.parseInt(String.valueOf(edtGGoalie.getText()));
                }

                benchOnePlayer = ((Spinner) findViewById(R.id.selectBench1)).getSelectedItem().toString();
                benchOneRating = ratingBench1.getRating();
                if(Helper.isEmpty(edtGB1))
                {
                    benchOneGoals = Integer.parseInt(String.valueOf("0"));
                }
                else
                {
                    benchOneGoals = Integer.parseInt(String.valueOf(edtGB1.getText()));
                }

                benchTwoPlayer = ((Spinner) findViewById(R.id.selectBench2)).getSelectedItem().toString();
                benchTwoRating = ratingBench2.getRating();
                if(Helper.isEmpty(edtGB2))
                {
                    benchTwoGoals = Integer.parseInt(String.valueOf("0"));
                }
                else
                {
                    benchTwoGoals = Integer.parseInt(String.valueOf(edtGB2.getText()));
                }

                benchThreePlayer = ((Spinner) findViewById(R.id.selectBench3)).getSelectedItem().toString();
                benchThreeRating = ratingBench3.getRating();
                if(Helper.isEmpty(edtGB3))
                {
                    benchThreeGoals = Integer.parseInt(String.valueOf("0"));
                }
                else
                {
                    benchThreeGoals = Integer.parseInt(String.valueOf(edtGB3.getText()));
                }

                benchFourPlayer = ((Spinner) findViewById(R.id.selectBench4)).getSelectedItem().toString();
                benchFourRating = ratingBench4.getRating();
                if(Helper.isEmpty(edtGB4))
                {
                    benchFourGoals = Integer.parseInt(String.valueOf("0"));
                }
                else
                {
                    benchFourGoals = Integer.parseInt(String.valueOf(edtGB4.getText()));
                }

                benchFivePlayer = ((Spinner) findViewById(R.id.selectBench5)).getSelectedItem().toString();
                benchFiveRating = ratingBench5.getRating();
                if(Helper.isEmpty(edtGB5))
                {
                    benchFiveGoals = Integer.parseInt(String.valueOf("0"));
                }
                else
                {
                    benchFiveGoals = Integer.parseInt(String.valueOf(edtGB5.getText()));
                }

                numberOfMatch = ((Spinner) findViewById(R.id.spin_match_number)).getSelectedItem().toString();
                team = ((Spinner) findViewById(R.id.spin_user_teams)).getSelectedItem().toString();
                opponent = ((Spinner) findViewById(R.id.spin_all_opponents)).getSelectedItem().toString();
                String whereClause = "opponent = " + "'" + opponent + "' and team = '" + team + "' and numberOfMatch = '" + numberOfMatch + "'" ;
                DataQueryBuilder queryBuilder1 = DataQueryBuilder.create();
                queryBuilder1.setWhereClause( whereClause );
                Backendless.Data.of( Matches.class ).find( queryBuilder1,
                        new AsyncCallback<List<Matches>>(){
                            @Override
                            public void handleResponse( List<Matches> foundMatch )
                            {
                                if (foundMatch.isEmpty())
                                {
                                    matches = new Matches();
                                    matches.setTeam(team);
                                    matches.setOpponent(opponent);
                                    matches.setNumberOfMatch(numberOfMatch);
                                    matches.setLeftForwardPlayer(leftForwardPlayer);
                                    matches.setLeftForwardRating(leftForwardRating);
                                    matches.setLeftForwardGoals(leftForwardGoals);
                                    matches.setRightForwardPlayer(rightForwardPlayer);
                                    matches.setRightForwardRating(rightForwardRating);
                                    matches.setRightForwardGoals(rightForwardGoals);
                                    matches.setCenterForwardPlayer(centerForwardPlayer);
                                    matches.setCenterForwardRating(centerForwardRating);
                                    matches.setCenterForwardGoals(centerForwardGoals);
                                    matches.setLeftLinkPlayer(leftLinkPlayer);
                                    matches.setLeftLinkRating(leftLinkRating);
                                    matches.setLeftLinkGoals(leftLinkGoals);
                                    matches.setRightLinkPlayer(rightLinkPlayer);
                                    matches.setRightLinkRating(rightLinkRating);
                                    matches.setRightLinkGoals(rightLinkGoals);
                                    matches.setCenterLinkPlayer(centerLinkPlayer);
                                    matches.setCenterLinkRating(centerLinkRating);
                                    matches.setCenterLinkGoals(centerLinkGoals);
                                    matches.setLeftBackPlayer(leftBackPlayer);
                                    matches.setLeftBackRating(leftBackRating);
                                    matches.setLeftBackGoals(leftBackGoals);
                                    matches.setRightBackPlayer(rightBackPlayer);
                                    matches.setRightBackRating(rightBackRating);
                                    matches.setRightBackGoals(rightBackGoals);
                                    matches.setCenterBackPlayer(centerBackPlayer);
                                    matches.setCenterBackRating(centerBackRating);
                                    matches.setCenterBackGoals(centerBackGoals);
                                    matches.setSweeperPlayer(sweeperPlayer);
                                    matches.setSweeperRating(sweeperRating);
                                    matches.setSweeperGoals(sweeperGoals);
                                    matches.setGoaliePlayer(goaliePlayer);
                                    matches.setGoalieRating(goalieRating);
                                    matches.setGoalieGoals(goalieGoals);
                                    matches.setBenchOnePlayer(benchOnePlayer);
                                    matches.setBenchOneRating(benchOneRating);
                                    matches.setBenchOneGoals(benchOneGoals);
                                    matches.setBenchTwoPlayer(benchTwoPlayer);
                                    matches.setBenchTwoRating(benchTwoRating);
                                    matches.setBenchTwoGoals(benchTwoGoals);
                                    matches.setBenchThreePlayer(benchThreePlayer);
                                    matches.setBenchThreeRating(benchThreeRating);
                                    matches.setBenchThreeGoals(benchThreeGoals);
                                    matches.setBenchFourPlayer(benchFourPlayer);
                                    matches.setBenchFourRating(benchFourRating);
                                    matches.setBenchFourGoals(benchFourGoals);
                                    matches.setBenchFivePlayer(benchFivePlayer);
                                    matches.setBenchFiveRating(benchFiveRating);
                                    matches.setBenchFiveGoals(benchFiveGoals);

                                    showProgress(true);
                                    tvLoad.setText("Saving Match Data.......");
                                    Backendless.Persistence.save( matches, new AsyncCallback<Matches>() {
                                        public void handleResponse( Matches response )
                                        {
                                            Toast.makeText(getBaseContext(),"match is saved",Toast.LENGTH_SHORT).show();
                                            showProgress(false);
                                        }

                                        public void handleFault( BackendlessFault fault )
                                        {
                                            Toast.makeText(getBaseContext(), fault.getMessage(), Toast.LENGTH_LONG).show();
                                        }
                                    });
                                }
                                else
                                {
                                    Helper.customToastA(Match_LineUp_activity.this,"match already exists");
                                }
                            }
                            @Override
                            public void handleFault( BackendlessFault fault )
                            {
                                Helper.customToastA(Match_LineUp_activity.this,"match not found");
                            }
                        });

        }
        return super.onOptionsItemSelected(item);
    }



    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {

                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });

            tvLoad.setVisibility(show ? View.VISIBLE : View.GONE);
            tvLoad.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    tvLoad.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            tvLoad.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }


    public void loadSpinnersDataSavedMatch(String user)
    {
        //shows backendless loading data
        showProgress(true);
        tvLoad.setText("Loading Match Data.......");

        //Loads Data For The Users Teams Spinner
        LoadRelationsQueryBuilder<Teams> loadRelationsQueryBuilder;
        loadRelationsQueryBuilder = LoadRelationsQueryBuilder.of(Teams.class);
        loadRelationsQueryBuilder.setRelationName("team");

        //Gets the teams related to the current user
        Backendless.Data.of(BackendlessUser.class).loadRelations(user,
                loadRelationsQueryBuilder,
                new AsyncCallback<List<Teams>>() {

                    @Override
                    public void handleResponse(List<Teams> teams) {
                        showProgress(false);

                        teamAdapter = new ArrayAdapter(getApplicationContext(), R.layout.spinner, teams);
                        spinTeams = (Spinner) findViewById(R.id.spin_user_teams);
                        spinTeams.setAdapter(teamAdapter);


                        team = currentMatch.getTeam();
                        int a = Helper.getIndexSpinner(spinTeams, team);
                        spinTeams.setSelection(a);




                        spinTeams.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                        {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                Teams teams = (Teams) spinTeams.getSelectedItem();
                                String teamId = teams.getObjectId();

                                LoadRelationsQueryBuilder<Map<String, Object>> query = LoadRelationsQueryBuilder.ofMap();
                                query.setRelationName("RTP");
                                query.setPageSize(100);
                                Backendless.Data.of("teams").loadRelations(teamId, query, new AsyncCallback<List<Map<String, Object>>>() {
                                    @Override
                                    public void handleResponse(List<Map<String, Object>> rtpObjects) {

                                        ArrayList<String> names = new ArrayList<>();

                                        for (Map playerNames : rtpObjects) {
                                            String name = (String) playerNames.get("name");
                                            String surname = (String) playerNames.get("surname");
                                            String spinName = surname + "," + name;
                                            names.add(spinName);
                                        }
                                        ArrayAdapter teamAdapter = new ArrayAdapter(getApplicationContext(), R.layout.spinner, names);
                                        spinLeftForward = (Spinner) findViewById(R.id.spin_left_forward);
                                        spinLeftForward.setAdapter(teamAdapter);

                                        leftForwardPlayer = currentMatch.getLeftForwardPlayer();
                                        int lfp = Helper.getIndexSpinner(spinLeftForward, leftForwardPlayer);
                                        spinLeftForward.setSelection(lfp);


                                        spinRightForward = (Spinner) findViewById(R.id.spin_right_forward);
                                        spinRightForward.setAdapter(teamAdapter);

                                        rightForwardPlayer = currentMatch.getRightForwardPlayer();
                                        int rfp = Helper.getIndexSpinner(spinRightForward, rightForwardPlayer);
                                        spinRightForward.setSelection(rfp);

                                        spinCenterForward = (Spinner) findViewById(R.id.spin_center_forward);
                                        spinCenterForward.setAdapter(teamAdapter);

                                        centerForwardPlayer = currentMatch.getCenterForwardPlayer();
                                        int cfp = Helper.getIndexSpinner(spinCenterForward, centerForwardPlayer);
                                        spinCenterForward.setSelection(cfp);


                                        spinLeftLink = (Spinner) findViewById(R.id.spin_left_link);
                                        spinLeftLink.setAdapter(teamAdapter);

                                        leftLinkPlayer = currentMatch.getLeftLinkPlayer();
                                        int llp = Helper.getIndexSpinner(spinLeftLink, leftLinkPlayer);
                                        spinLeftLink.setSelection(llp);


                                        spinRightLink = (Spinner) findViewById(R.id.spin_right_link);
                                        spinRightLink.setAdapter(teamAdapter);

                                        rightLinkPlayer = currentMatch.getRightLinkPlayer();
                                        int rlp = Helper.getIndexSpinner(spinRightLink, rightLinkPlayer);
                                        spinRightLink.setSelection(rlp);

                                        spinCenterLink = (Spinner) findViewById(R.id.spin_center_link);
                                        spinCenterLink.setAdapter(teamAdapter);

                                        centerLinkPlayer = currentMatch.getCenterLinkPlayer();
                                        int clp = Helper.getIndexSpinner(spinCenterLink, centerLinkPlayer);
                                        spinCenterLink.setSelection(clp);

                                        spinLeftBack = (Spinner) findViewById(R.id.spin_left_back);
                                        spinLeftBack.setAdapter(teamAdapter);

                                        leftBackPlayer = currentMatch.getLeftBackPlayer();
                                        int lbp = Helper.getIndexSpinner(spinLeftBack, leftBackPlayer);
                                        spinLeftBack.setSelection(lbp);

                                        spinRightBack = (Spinner) findViewById(R.id.spin_right_back);
                                        spinRightBack.setAdapter(teamAdapter);

                                        rightBackPlayer = currentMatch.getRightBackPlayer();
                                        int rbp = Helper.getIndexSpinner(spinRightBack, rightBackPlayer);
                                        spinRightBack.setSelection(rbp);

                                        spinCenterback = (Spinner) findViewById(R.id.spin_center_back);
                                        spinCenterback.setAdapter(teamAdapter);

                                        centerBackPlayer = currentMatch.getCenterBackPlayer();
                                        int cbp = Helper.getIndexSpinner(spinCenterback, centerBackPlayer);
                                        spinCenterback.setSelection(cbp);


                                        spinSweeper = (Spinner) findViewById(R.id.spin_sweeper);
                                        spinSweeper.setAdapter(teamAdapter);

                                        sweeperPlayer = currentMatch.getSweeperPlayer();
                                        int sp = Helper.getIndexSpinner(spinSweeper, sweeperPlayer);
                                        spinSweeper.setSelection(sp);

                                        spinGoalie = (Spinner) findViewById(R.id.spin_goalie);
                                        spinGoalie.setAdapter(teamAdapter);

                                        goaliePlayer = currentMatch.getGoaliePlayer();
                                        int gp = Helper.getIndexSpinner(spinGoalie, goaliePlayer);
                                        spinGoalie.setSelection(gp);


                                        spinBench1 = (Spinner) findViewById(R.id.selectBench1);
                                        spinBench1.setAdapter(teamAdapter);

                                        benchOnePlayer = currentMatch.getBenchOnePlayer();
                                        int b1 = Helper.getIndexSpinner(spinBench1, benchOnePlayer);
                                        spinBench1.setSelection(b1);


                                        spinBench2 = (Spinner) findViewById(R.id.selectBench2);
                                        spinBench2.setAdapter(teamAdapter);

                                        benchTwoPlayer = currentMatch.getBenchTwoPlayer();
                                        int b2 = Helper.getIndexSpinner(spinBench2,benchTwoPlayer );
                                        spinBench2.setSelection(b2);


                                        spinBench3 = (Spinner) findViewById(R.id.selectBench3);
                                        spinBench3.setAdapter(teamAdapter);

                                        benchThreePlayer = currentMatch.getBenchThreePlayer();
                                        int b3 = Helper.getIndexSpinner(spinBench3, benchThreePlayer);
                                        spinBench3.setSelection(b3);


                                        spinBench4 = (Spinner) findViewById(R.id.selectBench4);
                                        spinBench4.setAdapter(teamAdapter);

                                        benchFourPlayer = currentMatch.getBenchFourPlayer();
                                        int b4 = Helper.getIndexSpinner(spinBench4, benchFourPlayer);
                                        spinBench4.setSelection(b4);


                                        spinBench5 = (Spinner) findViewById(R.id.selectBench5);
                                        spinBench5.setAdapter(teamAdapter);

                                        benchFivePlayer = currentMatch.getBenchFivePlayer();
                                        int b5 = Helper.getIndexSpinner(spinBench5, benchFivePlayer);
                                        spinBench5.setSelection(b5);

                                    }

                                    @Override
                                    public void handleFault(BackendlessFault fault) {
                                        Toast.makeText(getBaseContext(), fault.getMessage(), Toast.LENGTH_LONG).show();
                                    }
                                });

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
                        Toast.makeText(getBaseContext(), fault.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });


        //Loads Data For The Opponents Spinner
        Backendless.Data.of(Opponent.class).find(new AsyncCallback<List<Opponent>>() {
            @Override
            public void handleResponse(List<Opponent> foundOpponents) {
                showProgress(false);
                ArrayAdapter teamAdapter = new ArrayAdapter(getApplicationContext(), R.layout.spinner, foundOpponents);
                spinOpponent = (Spinner) findViewById(R.id.spin_all_opponents);
                spinOpponent.setAdapter(teamAdapter);

                opponent = currentMatch.getOpponent();
                int a = Helper.getIndexSpinner(spinOpponent, opponent);
                spinOpponent.setSelection(a);

            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Toast.makeText(getBaseContext(), fault.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        List<String> listMatchNumber = new ArrayList<String>();
        listMatchNumber.add("1");
        listMatchNumber.add("2");
        listMatchNumber.add("3");
        listMatchNumber.add("4");
        listMatchNumber.add("5");
        ArrayAdapter matchNumberAdapter = new ArrayAdapter(getApplicationContext(), R.layout.spinner, listMatchNumber);
        spinMatchNumber = (Spinner) findViewById(R.id.spin_match_number);
        spinMatchNumber.setAdapter(matchNumberAdapter);

        numberOfMatch = currentMatch.getNumberOfMatch();
        int nm = Helper.getIndexSpinner(spinMatchNumber, numberOfMatch);
        spinMatchNumber.setSelection(nm);


    }

    public void loadSpinnersData(String user)
    {
        //shows backendless loading data
        showProgress(true);
        tvLoad.setText("Loading Match Data.......");

        //Loads Data For The Users Teams Spinner
        LoadRelationsQueryBuilder<Teams> loadRelationsQueryBuilder;
        loadRelationsQueryBuilder = LoadRelationsQueryBuilder.of(Teams.class);
        loadRelationsQueryBuilder.setRelationName("team");

        //Gets the teams related to the current user
        Backendless.Data.of(BackendlessUser.class).loadRelations(user,
                loadRelationsQueryBuilder,
                new AsyncCallback<List<Teams>>() {

                    @Override
                    public void handleResponse(List<Teams> teams) {
                        showProgress(false);

                        teamAdapter = new ArrayAdapter(getApplicationContext(), R.layout.spinner, teams);
                        spinTeams = (Spinner) findViewById(R.id.spin_user_teams);
                        spinTeams.setAdapter(teamAdapter);
                        spinTeams.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                Teams teams = (Teams) spinTeams.getSelectedItem();
                                String teamId = teams.getObjectId();

                                LoadRelationsQueryBuilder<Map<String, Object>> query = LoadRelationsQueryBuilder.ofMap();
                                query.setRelationName("RTP");
                                query.setPageSize(100);
                                Backendless.Data.of("teams").loadRelations(teamId, query, new AsyncCallback<List<Map<String, Object>>>() {
                                    @Override
                                    public void handleResponse(List<Map<String, Object>> rtpObjects) {

                                        ArrayList<String> names = new ArrayList<>();

                                        for (Map playerNames : rtpObjects) {
                                            String name = (String) playerNames.get("name");
                                            String surname = (String) playerNames.get("surname");
                                            String spinName = surname + "," + name;
                                            names.add(spinName);
                                        }
                                        ArrayAdapter teamAdapter = new ArrayAdapter(getApplicationContext(), R.layout.spinner, names);
                                        spinLeftForward = (Spinner) findViewById(R.id.spin_left_forward);
                                        spinLeftForward.setAdapter(teamAdapter);
                                        spinRightForward = (Spinner) findViewById(R.id.spin_right_forward);
                                        spinRightForward.setAdapter(teamAdapter);
                                        spinCenterForward = (Spinner) findViewById(R.id.spin_center_forward);
                                        spinCenterForward.setAdapter(teamAdapter);
                                        spinLeftLink = (Spinner) findViewById(R.id.spin_left_link);
                                        spinLeftLink.setAdapter(teamAdapter);
                                        spinRightLink = (Spinner) findViewById(R.id.spin_right_link);
                                        spinRightLink.setAdapter(teamAdapter);
                                        spinCenterLink = (Spinner) findViewById(R.id.spin_center_link);
                                        spinCenterLink.setAdapter(teamAdapter);
                                        spinLeftBack = (Spinner) findViewById(R.id.spin_left_back);
                                        spinLeftBack.setAdapter(teamAdapter);
                                        spinRightBack = (Spinner) findViewById(R.id.spin_right_back);
                                        spinRightBack.setAdapter(teamAdapter);
                                        spinCenterback = (Spinner) findViewById(R.id.spin_center_back);
                                        spinCenterback.setAdapter(teamAdapter);
                                        spinSweeper = (Spinner) findViewById(R.id.spin_sweeper);
                                        spinSweeper.setAdapter(teamAdapter);
                                        spinGoalie = (Spinner) findViewById(R.id.spin_goalie);
                                        spinGoalie.setAdapter(teamAdapter);
                                        spinBench1 = (Spinner) findViewById(R.id.selectBench1);
                                        spinBench1.setAdapter(teamAdapter);
                                        spinBench2 = (Spinner) findViewById(R.id.selectBench2);
                                        spinBench2.setAdapter(teamAdapter);
                                        spinBench3 = (Spinner) findViewById(R.id.selectBench3);
                                        spinBench3.setAdapter(teamAdapter);
                                        spinBench4 = (Spinner) findViewById(R.id.selectBench4);
                                        spinBench4.setAdapter(teamAdapter);
                                        spinBench5 = (Spinner) findViewById(R.id.selectBench5);
                                        spinBench5.setAdapter(teamAdapter);

                                    }

                                    @Override
                                    public void handleFault(BackendlessFault fault) {
                                        Toast.makeText(getBaseContext(), fault.getMessage(), Toast.LENGTH_LONG).show();
                                    }
                                });

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
                        Toast.makeText(getBaseContext(), fault.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });


        //Loads Data For The Opponents Spinner
        Backendless.Data.of(Opponent.class).find(new AsyncCallback<List<Opponent>>() {
            @Override
            public void handleResponse(List<Opponent> foundOpponents) {
                showProgress(false);
                ArrayAdapter teamAdapter = new ArrayAdapter(getApplicationContext(), R.layout.spinner, foundOpponents);
                spinOpponent = (Spinner) findViewById(R.id.spin_all_opponents);
                spinOpponent.setAdapter(teamAdapter);

            }

            @Override
            public void handleFault(BackendlessFault fault) {
                Toast.makeText(getBaseContext(), fault.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        List<String> listMatchNumber = new ArrayList<String>();
        listMatchNumber.add("1");
        listMatchNumber.add("2");
        listMatchNumber.add("3");
        listMatchNumber.add("4");
        listMatchNumber.add("5");
        ArrayAdapter matchNumberAdapter = new ArrayAdapter(getApplicationContext(), R.layout.spinner, listMatchNumber);
        spinMatchNumber = (Spinner) findViewById(R.id.spin_match_number);
        spinMatchNumber.setAdapter(matchNumberAdapter);
    }

    public void loadRatingbarSaved()
    {
       leftForwardRating = currentMatch.getLeftForwardRating();
       ratingLeftForward.setRating(leftForwardRating);

       rightForwardRating =  currentMatch.getRightForwardRating();
       ratingRightForward.setRating(rightForwardRating);

       centerForwardRating = currentMatch.getCenterForwardRating();
       ratingCenterForward.setRating(centerForwardRating);

       leftLinkRating = currentMatch.getLeftLinkRating();
       ratingLeftLink.setRating(leftLinkRating);

       rightLinkRating = currentMatch.getRightLinkRating();
       ratingRightLink.setRating(rightLinkRating);

       centerLinkRating = currentMatch.getCenterLinkRating();
       ratingCenterLink.setRating(centerLinkRating);

       leftBackRating = currentMatch.getLeftBackRating();
       ratingLeftBack.setRating(leftBackRating);

       rightBackRating = currentMatch.getRightBackRating();
       ratingRightBack.setRating(rightBackRating);

       centerBackRating = currentMatch.getCenterBackRating();
       ratingCenterback.setRating(centerBackRating);

       sweeperRating = currentMatch.getSweeperRating();
       ratingSweeper.setRating(sweeperRating);

       goalieRating = currentMatch.getGoalieRating();
       ratingGoalie.setRating(goalieRating);

       benchOneRating = currentMatch.getBenchOneRating();
       ratingBench1.setRating(benchOneRating);

       benchTwoRating = currentMatch.getBenchTwoRating();
       ratingBench2.setRating(benchTwoRating);

       benchThreeRating = currentMatch.getBenchThreeRating();
       ratingBench3.setRating(benchThreeRating);

       benchFourRating = currentMatch.getBenchFourRating();
       ratingBench4.setRating(benchFourRating);

       benchFiveRating = currentMatch.getBenchFiveRating();
       ratingBench5.setRating(benchFiveRating);

    }

    public void loadGoalsSaved()
    {
        leftForwardGoals = currentMatch.getLeftForwardGoals();
        String lfg = Integer.toString(leftForwardGoals);
        edtGLeftForward.setText(lfg);

        rightForwardGoals = currentMatch.getRightForwardGoals();
        String rfg = Integer.toString(rightForwardGoals);
        edtGRightForward.setText(rfg);

        centerForwardGoals = currentMatch.getCenterForwardGoals();
        String cfg = Integer.toString(centerForwardGoals);
        edtGCenterForward.setText(cfg);

        leftLinkGoals = currentMatch.getLeftLinkGoals();
        String llg = Integer.toString(leftLinkGoals);
        edtGLeftLink.setText(llg);

        rightLinkGoals = currentMatch.getRightLinkGoals();
        String rlg = Integer.toString(rightLinkGoals);
        edtGRightLink.setText(rlg);

        centerLinkGoals = currentMatch.getCenterLinkGoals();
        String clg = Integer.toString(centerLinkGoals);
        edtGCenterLink.setText(clg);

        leftBackGoals = currentMatch.getLeftBackGoals();
        String lbg = Integer.toString(leftBackGoals);
        edtGLeftBack.setText(lbg);

        rightBackGoals = currentMatch.getRightBackGoals();
        String rbg = Integer.toString(rightBackGoals);
        edtGRightBack.setText(rbg);

        centerBackGoals = currentMatch.getCenterBackGoals();
        String cbg = Integer.toString(centerBackGoals);
        edtGCenterBack.setText(cbg);

        sweeperGoals = currentMatch.getSweeperGoals();
        String sg = Integer.toString(sweeperGoals);
        edtGSweeper.setText(sg);

        goalieGoals = currentMatch.getGoalieGoals();
        String gg = Integer.toString(goalieGoals);
        edtGGoalie.setText(gg);

        benchOneGoals = currentMatch.getBenchOneGoals();
        String b1g = Integer.toString(benchOneGoals);
        edtGB1.setText(b1g);

        benchTwoGoals = currentMatch.getBenchTwoGoals();
        String b2g = Integer.toString(benchTwoGoals);
        edtGB2.setText(b2g);

        benchThreeGoals = currentMatch.getBenchThreeGoals();
        String b3g = Integer.toString(benchThreeGoals);
        edtGB3.setText(b3g);

        benchFourGoals = currentMatch.getBenchFourGoals();
        String b4g = Integer.toString(benchFourGoals);
        edtGB4.setText(b4g);

        benchFiveGoals = currentMatch.getBenchFiveGoals();
        String b5g = Integer.toString(benchFiveGoals);
        edtGB5.setText(b5g);

    }

    @Override
    public void onBackPressed()
    {
        finish();
    }

}
