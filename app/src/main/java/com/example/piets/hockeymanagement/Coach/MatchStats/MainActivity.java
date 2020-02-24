package com.example.piets.hockeymanagement.Coach.MatchStats;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.piets.hockeymanagement.Classes.Matches;
import com.example.piets.hockeymanagement.Classes.Players;
import com.example.piets.hockeymanagement.R;
import com.example.piets.hockeymanagement.Room.MatchStats;
import com.example.piets.hockeymanagement.Room.MatchStatsDataBase;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    String team,opponent,matchNum;

    String statsPK;


    public static  MatchStatsDataBase matchStatsDataBase;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            boolean T = false;
            switch (item.getItemId()) {
                case R.id.turnover_goals:
                    loadFragment(new turnoverFragment());
                    T = true;
                    break;
                case R.id.circle_penetration:
                    loadFragment(new circlepenetrationFragment());
                    T = true;
                    break;
                case R.id.penalty_corners:
                    loadFragment(new penaltycornersFragment());
                    T = true;
                    break;
                case R.id.goal_shots:
                    loadFragment(new goalshotsFragment());
                    T = true;
                    break;
            }
            return T;
        }
    };//end clickListener

    private BottomNavigationView.OnNavigationItemReselectedListener onreselectedListener
            = new BottomNavigationView.OnNavigationItemReselectedListener() {
        @Override
        public void onNavigationItemReselected(@NonNull MenuItem item) {

        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        matchStatsDataBase = Room.databaseBuilder(getApplicationContext(),
                MatchStatsDataBase.class, "match_stats_database").allowMainThreadQueries().build();

        final Matches matches = (Matches) getIntent().getSerializableExtra("Match");
        team = matches.getTeam();
        opponent = matches.getOpponent();
        matchNum = matches.getNumberOfMatch();

        statsPK = team + opponent + matchNum;


        BottomNavigationView navigation = findViewById(R.id.navigation);

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        loadFragment(new turnoverFragment());

        List<MatchStats> find = matchStatsDataBase.matchStatsDao().findRecord(statsPK);

        if(find.isEmpty())
        {
            MatchStats matchStats = new MatchStats();
            matchStats.setStatsPrimaryKey(statsPK);
            matchStats.setFirstTeamTurnOver(0);
            matchStats.setFirstVSTurnOver(0);
            matchStats.setSecondTeamTurnOver(0);
            matchStats.setSecondVsTurnOver(0);
            matchStats.setFirstTeamGoals(0);
            matchStats.setSecondTeamGoals(0);
            matchStats.setFirsVsGoals(0);
            matchStats.setSecondVSGoals(0);


            matchStats.setCirclePenFH1(0);
            matchStats.setCirclePenSH1(0);
            matchStats.setCirclePenFH2(0);
            matchStats.setCirclePenSH2(0);
            matchStats.setCirclePenFH3(0);
            matchStats.setCirclePenSH3(0);
            matchStats.setCirclePenFH4(0);
            matchStats.setCirclePenSH4(0);
            matchStats.setCirclePenFH5(0);
            matchStats.setCirclePenSH5(0);
            matchStats.setCirclePenFHTotal(0);
            matchStats.setCirclePenSHTotal(0);

            matchStats.setPenaltyShotsFH1(0);
            matchStats.setPenaltyShotsSH1(0);
            matchStats.setPenaltyShotsFH2(0);
            matchStats.setPenaltyShotsSH2(0);
            matchStats.setPenaltyShotsFH3(0);
            matchStats.setPenaltyShotsSH3(0);
            matchStats.setPenaltyShotsFH4(0);
            matchStats.setPenaltyShotsSH4(0);
            matchStats.setPenaltyShotsFH5(0);
            matchStats.setPenaltyShotsSH5(0);
            matchStats.setPenaltyShotsFHTotal(0);
            matchStats.setPenaltyShotsSHTotal(0);

            matchStats.setGoalShotsFH1(0);
            matchStats.setGoalShotsSH1(0);
            matchStats.setGoalShotsFH2(0);
            matchStats.setGoalShotsSH2(0);
            matchStats.setGoalShotsFH3(0);
            matchStats.setGoalShotsSH3(0);
            matchStats.setGoalShotsFH4(0);
            matchStats.setGoalShotsSH4(0);
            matchStats.setGoalShotsFH5(0);
            matchStats.setGoalShotsSH5(0);
            matchStats.setGoalShotsFHTotal(0);
            matchStats.setGoalShotsSHTotal(0);
            matchStatsDataBase.matchStatsDao().addMatchstats(matchStats);

            //Sends primary key to the fragments

        }
        else
        {
            Toast.makeText(getBaseContext(), "The row has been found",Toast.LENGTH_LONG).show();
            //Sends primary key to the fragments

        }


//Saves Primary Key to Room





    }

    private void loadFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }

    @Override
    public void onBackPressed()
    {
        finish();
    }



}//end MainActivity
