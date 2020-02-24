package com.example.piets.hockeymanagement.Coach.MatchStats;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
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

import com.example.piets.hockeymanagement.Classes.FragmentBase;
import com.example.piets.hockeymanagement.Classes.Helper;
import com.example.piets.hockeymanagement.R;
import com.example.piets.hockeymanagement.Room.MatchStats;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import static android.graphics.Color.WHITE;


public class circlepenetrationFragment extends FragmentBase
{

    private int firstHalfCounter = 0;
    private int secondHalfCounter = 0;
    private int fHalfCounter1 = 0, fHalfCounter2 = 0, fHalfCounter3 = 0, fHalfCounter4 = 0, fHalfCounter5 = 0;
    private int sHalfCounter1 = 0, sHalfCounter2 = 0, sHalfCounter3 = 0, sHalfCounter4 = 0, sHalfCounter5 = 0;
    private RadioGroup rGroup;
    private RadioButton firstHalfRadioButton, secondHalfRadioButton;

    private String PK;

    Button eraseData;
    TextView first, second, third, fourth, fifth, firstHalfTotal, secondHalfTotal;
    private PieChart mChart;
    List<MatchStats> find;

    public circlepenetrationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        if(savedInstanceState != null)
        {
            fHalfCounter1 = savedInstanceState.getInt("1stCount1");
            fHalfCounter2 = savedInstanceState.getInt("1stCount2");
            fHalfCounter3 = savedInstanceState.getInt("1stCount3");
            fHalfCounter4 = savedInstanceState.getInt("1stCount4");
            fHalfCounter5 = savedInstanceState.getInt("1stCount5");

        }

        }//end onCreate

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

                int fHalfCount1 = (fHalfCounter1);
                int sHalfCount1 = (sHalfCounter1);
                int fHalfCount2 = (fHalfCounter2);
                int sHalfCount2 = (sHalfCounter2);
                int fHalfCount3 = (fHalfCounter3);
                int sHalfCount3 = (sHalfCounter3);
                int fHalfCount4 = (fHalfCounter4);
                int sHalfCount4 = (sHalfCounter4);
                int fHalfCount5 = (fHalfCounter5);
                int sHalfCount5 = (sHalfCounter5);
                int fHalfTotal = (firstHalfCounter);
                int sHalfTotal = (secondHalfCounter);


                MatchStats matchStats = new MatchStats();
                matchStats.setStatsPrimaryKey(PK);
                matchStats.setCirclePenFH1(fHalfCount1);
                matchStats.setCirclePenSH1(sHalfCount1);
                matchStats.setCirclePenFH2(fHalfCount2);
                matchStats.setCirclePenSH2(sHalfCount2);
                matchStats.setCirclePenFH3(fHalfCount3);
                matchStats.setCirclePenSH3(sHalfCount3);
                matchStats.setCirclePenFH4(fHalfCount4);
                matchStats.setCirclePenSH4(sHalfCount4);
                matchStats.setCirclePenFH5(fHalfCount5);
                matchStats.setCirclePenSH5(sHalfCount5);
                matchStats.setCirclePenFHTotal(fHalfTotal);
                matchStats.setCirclePenSHTotal(sHalfTotal);
                AlertDialog diaBox =  Helper.saveMatchStats(matchStats,getContext());
                diaBox.show();

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        PK = ((MainActivity)getActivity()).statsPK.toString();

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_circlepenetration,container, false);

        first = rootView.findViewById(R.id.pointRed);
        second = rootView.findViewById(R.id.pointOrange);
        third = rootView.findViewById(R.id.pointOrangeGoalShots);
        fourth = rootView.findViewById(R.id.pointGreen);
        fifth = rootView.findViewById(R.id.pointBlue);
        rGroup = rootView.findViewById(R.id.rgGoalShots);
        firstHalfTotal = rootView.findViewById(R.id.firstHalfTotal);
        secondHalfTotal = rootView.findViewById(R.id.secondHalfGoalShotsTotal);
        eraseData = rootView.findViewById(R.id.btnEraseCirclePenetrations);
        firstHalfRadioButton = rootView.findViewById(R.id.firstHalf);
        secondHalfRadioButton = rootView.findViewById(R.id.secondHalf);

        mChart = rootView.findViewById(R.id.chart1);

        find = MainActivity.matchStatsDataBase.matchStatsDao().findRecord(PK);
        for(MatchStats m : find)
        {
            int fht = m.getCirclePenFHTotal();
            if (fht == 0)
            {
                firstHalfTotal.setText(Integer.toString(firstHalfCounter));
            } else

            {
                firstHalfCounter = fht;
                firstHalfTotal.setText(Integer.toString(firstHalfCounter));
            }

            int sht = m.getCirclePenSHTotal();
            if (sht == 0)
            {
                secondHalfTotal.setText(Integer.toString(secondHalfCounter));
            } else

            {
                secondHalfCounter = sht;
                secondHalfTotal.setText(Integer.toString(secondHalfCounter));
            }
        }


            if (savedInstanceState != null) {
                first.setText(fHalfCounter1);
                second.setText(fHalfCounter2);
                third.setText(fHalfCounter3);
                fourth.setText(fHalfCounter4);
                fifth.setText(fHalfCounter5);

                firstHalfRadioButton.setChecked(savedInstanceState.getBoolean("rb1stHalf"));
                secondHalfRadioButton.setChecked(savedInstanceState.getBoolean("rb2ndHalf"));
            }

            CreatePieChart();

            return rootView;

    }//end onCreateView

    public void CreatePieChart()
    {
        mChart.setUsePercentValues(false);
        mChart.getDescription().setEnabled(false);
       // mChart.setExtraOffsets(5, 10, 5, 1);

        mChart.setDrawHoleEnabled(false);
        mChart.setHoleColor(WHITE);
        mChart.setTransparentCircleRadius(61f);

        mChart.setMaxAngle(180);
        mChart.setRotationAngle(180);
        mChart.setRotationEnabled(false);
        mChart.getLegend().setEnabled(false);
        final ArrayList<PieEntry> yValues = new ArrayList<>();

        yValues.add(0, new PieEntry(25f));
        yValues.add(1, new PieEntry(25f));
        yValues.add(2, new PieEntry(25f));
        yValues.add(3, new PieEntry(25f));
        yValues.add(4, new PieEntry(25f));

        //mChart.animateY(1000, Easing.EasingOption.EaseInOutCubic);

        final PieDataSet dataSet = new PieDataSet(yValues, "Score");
        mChart.setExtraBottomOffset(-20);
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        dataSet.setDrawValues(false);
        final PieData data = new PieData(dataSet);
        mChart.setData(data);

    }//end CreatePieChart

    @Override
    public void onStart() {
        super.onStart();

        rGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch(radioGroup.getCheckedRadioButtonId())
                {
                    case R.id.firstHalf:

                        for(MatchStats m : find) {

                            int fh1 = m.getCirclePenFH1();
                            if (fh1 == 0)
                            {
                                first.setText(Integer.toString(fHalfCounter1));
                            } else

                            {
                                fHalfCounter1 = fh1;
                                first.setText(Integer.toString(fHalfCounter1));
                            }

                            int fh2 = m.getCirclePenFH2();
                            if (fh2 == 0)
                            {
                                second.setText(Integer.toString(fHalfCounter2));
                            } else

                            {
                                fHalfCounter2 = fh2;
                                second.setText(Integer.toString(fHalfCounter2));
                            }

                            int fh3 = m.getCirclePenFH3();
                            if (fh3 == 0)
                            {
                                third.setText(Integer.toString(fHalfCounter3));
                            } else

                            {
                                fHalfCounter3 = fh3;
                                third.setText(Integer.toString(fHalfCounter3));
                            }

                            int fh4 = m.getCirclePenFH4();
                            if (fh4 == 0)
                            {
                                fourth.setText(Integer.toString(fHalfCounter4));
                            } else

                            {
                                fHalfCounter4 = fh4;
                                fourth.setText(Integer.toString(fHalfCounter4));
                            }

                            int fh5 = m.getCirclePenFH5();
                            if (fh5 == 0)
                            {
                                fifth.setText(Integer.toString(fHalfCounter5));
                            } else

                            {
                                fHalfCounter5 = fh5;
                                fifth.setText(Integer.toString(fHalfCounter5));
                            }
                        }

                        break;

                    case R.id.secondHalf:

                        for(MatchStats m : find) {

                            int sh1 = m.getCirclePenSH1();
                            if (sh1 == 0)
                            {
                                first.setText(Integer.toString(sHalfCounter1));
                            } else

                            {
                                sHalfCounter1 = sh1;
                                first.setText(Integer.toString(sHalfCounter1));
                            }

                            int sh2 = m.getCirclePenSH2();
                            if (sh2 == 0)
                            {
                                second.setText(Integer.toString(sHalfCounter2));
                            } else

                            {
                                sHalfCounter2 = sh2;
                                second.setText(Integer.toString(sHalfCounter2));
                            }

                            int sh3 = m.getCirclePenSH3();
                            if (sh3 == 0)
                            {
                                third.setText(Integer.toString(sHalfCounter3));
                            } else

                            {
                                sHalfCounter3 = sh3;
                                third.setText(Integer.toString(sHalfCounter3));
                            }

                            int sh4 = m.getCirclePenSH4();
                            if (sh4 == 0)
                            {
                                fourth.setText(Integer.toString(sHalfCounter4));
                            } else

                            {
                                sHalfCounter4 = sh4;
                                fourth.setText(Integer.toString(sHalfCounter4));
                            }

                            int sh5 = m.getCirclePenSH5();
                            if (sh5 == 0)
                            {
                                fifth.setText(Integer.toString(sHalfCounter5));
                            } else

                            {
                                sHalfCounter5 = sh5;
                                fifth.setText(Integer.toString(sHalfCounter5));
                            }
                        }

                        break;
                }
            }
        });//end setOnCheckedChangeListener

        eraseData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectionId = rGroup.getCheckedRadioButtonId();
                switch(selectionId)
                {
                    case R.id.firstHalf:
                        firstHalfCounter = 0;
                        fHalfCounter1 = 0;
                        fHalfCounter2 = 0;
                        fHalfCounter3 = 0;
                        fHalfCounter4 = 0;
                        fHalfCounter5 = 0;
                        first.setText(Integer.toString(0));
                        second.setText(Integer.toString(0));
                        third.setText(Integer.toString(0));
                        fourth.setText(Integer.toString(0));
                        fifth.setText(Integer.toString(0));
                        firstHalfTotal.setText(Integer.toString(0));
                        break;
                    case R.id.secondHalf:
                        secondHalfCounter = 0;
                        sHalfCounter1 = 0;
                        sHalfCounter2 = 0;
                        sHalfCounter3 = 0;
                        sHalfCounter4 = 0;
                        sHalfCounter5 = 0;
                        first.setText(Integer.toString(0));
                        second.setText(Integer.toString(0));
                        third.setText(Integer.toString(0));
                        fourth.setText(Integer.toString(0));
                        fifth.setText(Integer.toString(0));
                        secondHalfTotal.setText(Integer.toString(0));
                }
            }
        });//end eraseData onClickListener

        mChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h)
            {
                int selectionId = rGroup.getCheckedRadioButtonId();
                switch (selectionId)
                {
                    case R.id.firstHalf:
                        if (h.getX() == 0.0) {
                            fHalfCounter1++;
                            firstHalfCounter++;
                            first.setText(Integer.toString(fHalfCounter1));
                            firstHalfTotal.setText(Integer.toString(firstHalfCounter));
                        }
                        if (h.getX() == 1.0) {
                            fHalfCounter2++;
                            firstHalfCounter++;
                            second.setText(Integer.toString(fHalfCounter2));
                            firstHalfTotal.setText(Integer.toString(firstHalfCounter));
                        }
                        if (h.getX() == 2.0) {
                            fHalfCounter3++;
                            firstHalfCounter++;
                            third.setText(Integer.toString(fHalfCounter3));
                            firstHalfTotal.setText(Integer.toString(firstHalfCounter));
                        }
                        if (h.getX() == 3.0) {
                            fHalfCounter4++;
                            firstHalfCounter++;
                            fourth.setText(Integer.toString(fHalfCounter4));
                            firstHalfTotal.setText(Integer.toString(firstHalfCounter));
                        }
                        if (h.getX() == 4.0) {
                            fHalfCounter5++;
                            firstHalfCounter++;
                            fifth.setText(Integer.toString(fHalfCounter5));
                            firstHalfTotal.setText(Integer.toString(firstHalfCounter));
                        }
                        break;

                    case R.id.secondHalf:
                        if (h.getX() == 0.0) {
                            sHalfCounter1++;
                            secondHalfCounter++;
                            first.setText(Integer.toString(sHalfCounter1));
                            secondHalfTotal.setText(Integer.toString(secondHalfCounter));
                        }
                        if (h.getX() == 1.0) {
                            sHalfCounter2++;
                            secondHalfCounter++;
                            second.setText(Integer.toString(sHalfCounter2));
                            secondHalfTotal.setText(Integer.toString(secondHalfCounter));
                        }
                        if (h.getX() == 2.0) {
                            sHalfCounter3++;
                            secondHalfCounter++;
                            third.setText(Integer.toString(sHalfCounter3));
                            secondHalfTotal.setText(Integer.toString(secondHalfCounter));
                        }
                        if (h.getX() == 3.0) {
                            sHalfCounter4++;
                            secondHalfCounter++;
                            fourth.setText(Integer.toString(sHalfCounter4));
                            secondHalfTotal.setText(Integer.toString(secondHalfCounter));
                        }
                        if (h.getX() == 4.0) {
                            sHalfCounter5++;
                            secondHalfCounter++;
                            fifth.setText(Integer.toString(sHalfCounter5));
                            secondHalfTotal.setText(Integer.toString(secondHalfCounter));
                        }
                        break;
                }//end switch
            }
            @Override
            public void onNothingSelected() {

            }
        });

    }//end onStart

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("1stCount", firstHalfCounter);
        outState.putInt("1stCount1", fHalfCounter1);
        outState.putInt("1stCount2", fHalfCounter2);
        outState.putInt("1stCount3", fHalfCounter3);
        outState.putInt("1stCount4", fHalfCounter4);
        outState.putInt("1stCount5", fHalfCounter5);

        outState.putInt("2ndCount", secondHalfCounter);
        outState.putInt("2ndCount1", sHalfCounter1);
        outState.putInt("2ndCount2", sHalfCounter2);
        outState.putInt("2ndCount3", sHalfCounter3);
        outState.putInt("2ndCount4", sHalfCounter4);
        outState.putInt("2ndCount5", sHalfCounter5);

        outState.putBoolean("rb1stHalf", firstHalfRadioButton.isChecked());
        outState.putBoolean("rb2ndHalf", secondHalfRadioButton.isChecked());

    }//end onSaveInstanceState

}//end fragment
