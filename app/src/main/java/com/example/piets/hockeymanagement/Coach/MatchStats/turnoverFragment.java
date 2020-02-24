package com.example.piets.hockeymanagement.Coach.MatchStats;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.piets.hockeymanagement.Classes.Helper;
import com.example.piets.hockeymanagement.R;
import com.example.piets.hockeymanagement.Room.MatchStats;

import java.util.List;

public class turnoverFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String PK;

    int homeScore ;
    int awayScore ;


    private TextView firstTurnOverTeamCount, firstTurnOverVsCount, secondTurnOverTeamCount, secondTurnOverVsCount, firstGoalTeamCount,
            firstGoalVsCount, secondGoalTeamCount, secondGoalVsCount;


    private int firstTeamTurnOverCounter = 0, firstVsTurnOverCounter = 0, secondTeamTurnOverCounter = 0, secondVsTurnOverCounter = 0;
    private int firstTeamGoalsCounter = 0, firstVsGoalsCounter = 0, secondTeamGoalsCounter = 0, secondVsGoalsCounter = 0;
    private Button turnOverTeamAdd, turnOverTeamMinus, turnOverVsAdd, turnOverVsMinus;
    private Button goalTeamAdd, goalTeamMinus, goalVsAdd, goalVsMinus;
    private OnFragmentInteractionListener mListener;
    private RadioGroup teamHalf;
    private RadioButton selectionHalf;




    public turnoverFragment() {
        // Required empty public constructor
    }

    public static turnoverFragment newInstance(String param1, String param2) {
        turnoverFragment fragment = new turnoverFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        inflater.inflate(R.menu.lineupmenu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.saveMenuMatch:

               int ftTurn = (firstTeamTurnOverCounter);
               int fvTurn = (firstVsTurnOverCounter);
               int stTurn = (secondTeamTurnOverCounter);
               int svTurn = (secondVsTurnOverCounter);
               int ftGoals = (firstTeamGoalsCounter);
               int stGoals = (secondTeamGoalsCounter);
               int fvGoals = (firstVsGoalsCounter);
               int svGoals = (secondVsGoalsCounter);

                MatchStats matchStats = new MatchStats();
                matchStats.setStatsPrimaryKey(PK);
                matchStats.setFirstTeamTurnOver(ftTurn);
                matchStats.setFirstVSTurnOver(fvTurn);
                matchStats.setSecondTeamTurnOver(stTurn);
                matchStats.setSecondVsTurnOver(svTurn);
                matchStats.setFirstTeamGoals(ftGoals);
                matchStats.setSecondTeamGoals(stGoals);
                matchStats.setFirsVsGoals(fvGoals);
                matchStats.setSecondVSGoals(svGoals);

                AlertDialog diaBox =  Helper.saveMatchStats(matchStats,getContext());
                diaBox.show();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        PK = ((MainActivity)getActivity()).statsPK.toString();

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_turnover, container, false);

        firstTurnOverTeamCount = rootView.findViewById(R.id.turnOverTeamFirstCount);
        firstTurnOverVsCount = rootView.findViewById(R.id.turnOverVsFirstCount);
        secondTurnOverTeamCount = rootView.findViewById(R.id.turnOverTeamSecondCount);
        secondTurnOverVsCount = rootView.findViewById(R.id.turnOverVsSecondCount);
        firstGoalTeamCount = rootView.findViewById(R.id.goalsTeamFirstCounter);
        firstGoalVsCount = rootView.findViewById(R.id.goalsVsFirstCounter);
        secondGoalTeamCount = rootView.findViewById(R.id.goalsTeamSecondCounter);
        secondGoalVsCount = rootView.findViewById(R.id.VsSecondGoals);

        teamHalf = rootView.findViewById(R.id.rgGoalShots);

        List<MatchStats> find = MainActivity.matchStatsDataBase.matchStatsDao().findRecord(PK);

        for(MatchStats m : find)
        {

            int ftt =  m.getFirstTeamTurnOver();
            if(ftt == 0)
            {
                firstTurnOverTeamCount.setText("" + firstTeamTurnOverCounter);
            }
            else
            {
                firstTeamTurnOverCounter = ftt;
                firstTurnOverTeamCount.setText("" + firstTeamTurnOverCounter);
            }

           int fvt = m.getFirstVSTurnOver();
            if(fvt == 0)
            {
                firstTurnOverVsCount.setText("" +firstVsTurnOverCounter);
            }
            else
            {
                firstVsTurnOverCounter = fvt;
                firstTurnOverVsCount.setText("" + firstVsTurnOverCounter);
            }

            int stt = m.getSecondTeamTurnOver();
            if(stt == 0)
            {
                secondTurnOverTeamCount.setText("" +secondTeamTurnOverCounter);
            }
            else
            {
                secondTeamTurnOverCounter = stt;
                secondTurnOverTeamCount.setText("" + secondTeamTurnOverCounter);
            }

            int svt = m.getSecondVsTurnOver();
            if(svt == 0)
            {
                secondTurnOverVsCount.setText("" +secondVsTurnOverCounter);
            }
            else
            {
                secondVsTurnOverCounter = svt;
                secondTurnOverVsCount.setText("" + secondVsTurnOverCounter);
            }

            int ftg = m.getFirstTeamGoals();
            if(ftg == 0)
            {
                firstGoalTeamCount.setText("" + firstTeamGoalsCounter);
            }
            else
            {
                firstTeamGoalsCounter= ftg;
                firstGoalTeamCount.setText("" +firstTeamGoalsCounter );
            }

            int stg = m.getSecondTeamGoals();
            if(stg == 0)
            {
                secondGoalTeamCount.setText("" + secondTeamGoalsCounter);
            }
            else
            {
                secondTeamGoalsCounter= stg;
                secondGoalTeamCount.setText("" +secondTeamGoalsCounter );
            }

            int fvg = m.getFirsVsGoals();
            if(fvg == 0)
            {
                firstGoalVsCount.setText("" + firstVsGoalsCounter);
            }
            else
            {
                firstVsGoalsCounter= fvg;
                firstGoalVsCount.setText("" +firstVsGoalsCounter );
            }

            int svg = m.getSecondVSGoals();
            if(svg == 0)
            {
                secondGoalVsCount.setText("" + secondVsTurnOverCounter);
            }
            else
            {
                secondVsGoalsCounter= svg;
                secondGoalVsCount.setText("" + secondVsTurnOverCounter);
            }


        }

        turnOverTeamAdd = rootView.findViewById(R.id.button);

        turnOverTeamAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int selectionId = teamHalf.getCheckedRadioButtonId();
                switch(selectionId){
                    case R.id.firstHalf:
                        firstTeamTurnOverCounter ++;

                        firstTurnOverTeamCount.setText(""+ firstTeamTurnOverCounter);
                    break;
                    case R.id.secondHalf:
                        secondTeamTurnOverCounter++;

                        secondTurnOverTeamCount.setText(""+ secondTeamTurnOverCounter);
                   break;
                   default:
                       Toast.makeText(getContext(), "Select one of two options(FirstHalf  or SecondHalf ", Toast.LENGTH_SHORT).show();
                }
            }
        });

        turnOverTeamMinus = (Button) rootView.findViewById(R.id.turnOverTeamMinus);
        turnOverTeamMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectionId = teamHalf.getCheckedRadioButtonId();
                switch(selectionId) {
                    case R.id.firstHalf:

                        if (firstTeamTurnOverCounter > 0) {

                            firstTeamTurnOverCounter--;
                            int firstCountTurnOverMinus = firstTeamTurnOverCounter;

                            firstTurnOverTeamCount.setText("" + firstCountTurnOverMinus);
                        }else{
                            Toast.makeText(getContext(), "Can't go to negative value", Toast.LENGTH_SHORT).show();
                        }
                    break;
                    case R.id.secondHalf:
                        if (secondTeamTurnOverCounter > 0) {

                            secondTeamTurnOverCounter--;


                            secondTurnOverTeamCount.setText("" + secondTeamTurnOverCounter);
                        }else{
                            Toast.makeText(getContext(), "Can't go to negative value", Toast.LENGTH_SHORT).show();
                        }
                    break;
                    default:
                        Toast.makeText(getContext(), "Select one of two options(FirstHalf  or SecondHalf ", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        firstTurnOverVsCount = (TextView) rootView.findViewById(R.id.turnOverVsFirstCount);
        secondTurnOverTeamCount = (TextView) rootView.findViewById(R.id.turnOverTeamSecondCount);
        secondTurnOverVsCount = (TextView) rootView.findViewById(R.id.turnOverVsSecondCount);

        turnOverVsAdd = (Button) rootView.findViewById(R.id.turnOverVsAdd);
        turnOverVsAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectionId = teamHalf.getCheckedRadioButtonId();
                switch(selectionId){
                    case R.id.firstHalf:
                        firstVsTurnOverCounter ++;

                        firstTurnOverVsCount.setText(""+ firstVsTurnOverCounter);
                        break;
                    case R.id.secondHalf:
                        secondVsTurnOverCounter++;

                        secondTurnOverVsCount.setText(""+ secondVsTurnOverCounter);
                        break;
                    default:
                        Toast.makeText(getContext(), "Select one of two options(FirstHalf  or SecondHalf ", Toast.LENGTH_SHORT).show();
                }
            }
        });

        turnOverVsMinus = (Button) rootView.findViewById(R.id.turnOverVsMinus);
        turnOverVsMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectionId = teamHalf.getCheckedRadioButtonId();
                switch(selectionId) {
                    case R.id.firstHalf:

                        if (firstVsTurnOverCounter > 0) {

                            firstVsTurnOverCounter--;
                            int firstCountTurnOverMinus = firstVsTurnOverCounter;

                            firstTurnOverVsCount.setText("" + firstCountTurnOverMinus);
                        }else{
                            Toast.makeText(getContext(), "Can't go to negative value", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case R.id.secondHalf:
                        if (secondVsTurnOverCounter > 0) {

                            secondVsTurnOverCounter--;


                            secondTurnOverVsCount.setText("" + secondVsTurnOverCounter);
                        }else{
                            Toast.makeText(getContext(), "Can't go to negative value", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    default:
                        Toast.makeText(getContext(), "Select one of two options(FirstHalf  or SecondHalf ", Toast.LENGTH_SHORT).show();
                        break;

                }


            }
        });



        goalTeamAdd = (Button) rootView.findViewById(R.id.goalsTeamAdd);
        goalTeamAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectionId = teamHalf.getCheckedRadioButtonId();
                switch(selectionId){
                    case R.id.firstHalf:
                        firstTeamGoalsCounter ++;
                        homeScore += firstTeamGoalsCounter;

                        firstGoalTeamCount.setText(""+ firstTeamGoalsCounter);
                        break;
                    case R.id.secondHalf:
                        secondTeamGoalsCounter++;
                            homeScore += secondTeamGoalsCounter;
                        secondGoalTeamCount.setText(""+ secondTeamGoalsCounter);
                        break;
                    default:
                        Toast.makeText(getContext(), "Select one of two options(FirstHalf  or SecondHalf ", Toast.LENGTH_SHORT).show();
                }
            }
        });

        goalTeamMinus = rootView.findViewById(R.id.goalOurTeamMinus);
        goalTeamMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectionId = teamHalf.getCheckedRadioButtonId();
                switch(selectionId) {
                    case R.id.firstHalf:

                        if (firstTeamGoalsCounter > 0) {

                            firstTeamGoalsCounter--;
                            homeScore -= firstTeamGoalsCounter;
                            int firstCountTurnOverMinus = firstTeamGoalsCounter;

                            firstGoalTeamCount.setText("" + firstCountTurnOverMinus);
                        }else{
                            Toast.makeText(getContext(), "Can't go to negative value", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case R.id.secondHalf:
                        if (secondVsGoalsCounter > 0) {

                            secondVsGoalsCounter--;
                            homeScore -= secondVsGoalsCounter;

                                    secondGoalTeamCount.setText("" + secondVsGoalsCounter);
                        }else{
                            Toast.makeText(getContext(), "Can't go to negative value", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    default:
                        Toast.makeText(getContext(), "Select one of two options(FirstHalf  or SecondHalf ", Toast.LENGTH_SHORT).show();
                        break;

                }
            }
        });

        goalVsAdd = (Button) rootView.findViewById(R.id.goalsVsAdd);
        goalVsAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectionId = teamHalf.getCheckedRadioButtonId();
                switch(selectionId){
                    case R.id.firstHalf:
                        firstVsGoalsCounter ++;

                        firstGoalVsCount.setText(""+ firstVsGoalsCounter);
                        break;
                    case R.id.secondHalf:
                        secondTeamTurnOverCounter++;

                        secondGoalVsCount.setText(""+ secondTeamTurnOverCounter);
                        break;
                    default:
                        Toast.makeText(getContext(), "Select one of two options(FirstHalf  or SecondHalf ", Toast.LENGTH_SHORT).show();
                }

            }
        });

        goalVsMinus = (Button) rootView.findViewById(R.id.goalVsMinus);
        goalVsMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectionId = teamHalf.getCheckedRadioButtonId();
                switch(selectionId) {
                    case R.id.firstHalf:

                        if (firstTeamTurnOverCounter > 0) {

                            firstTeamTurnOverCounter--;
                            int firstCountTurnOverMinus = firstTeamTurnOverCounter;

                            firstGoalVsCount.setText("" + firstCountTurnOverMinus);
                        }else{
                            Toast.makeText(getContext(), "Can't go to negative value", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case R.id.secondHalf:
                        if (secondTeamTurnOverCounter > 0) {

                            secondTeamTurnOverCounter--;

                            secondGoalVsCount.setText("" + secondTeamTurnOverCounter);
                        }else {
                            Toast.makeText(getContext(), "Can't go to negative value", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    default:
                        Toast.makeText(getContext(), "Select one of two options(FirstHalf  or SecondHalf ", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
                ((AppCompatActivity) getActivity()).getSupportActionBar().setSubtitle("Score: " + "( " + homeScore + " - "  +awayScore+" )" );
        return rootView;




    }//end onCreateView

    @Override
    public void onStart() {
        super.onStart();



    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }



    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


}
