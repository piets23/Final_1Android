package com.example.piets.hockeymanagement.Coach.MatchStats;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

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

public class goalshotsFragment extends Fragment {

    private int firstHalfCounter = 0;
    private int secondHalfCounter = 0;
    private int fHalfCounter1 = 0, fHalfCounter2 = 0, fHalfCounter3 = 0, fHalfCounter4 = 0, fHalfCounter5 = 0;
    private int sHalfCounter1 = 0, sHalfCounter2 = 0, sHalfCounter3 = 0, sHalfCounter4 = 0, sHalfCounter5 = 0;
    private RadioGroup rGroup;

    private String PK;
    List<MatchStats> find;

    Button eraseData;
    TextView first, second, third, fourth, fifth, firstHalfTotal, secondHalfTotal;
    private PieChart mChart;

    public goalshotsFragment() {
        // Required empty public constructor
    }

    public static goalshotsFragment newInstance(String param1, String param2) {
        goalshotsFragment fragment = new goalshotsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

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
                matchStats.setGoalShotsFH1(fHalfCount1);
                matchStats.setGoalShotsSH1(sHalfCount1);
                matchStats.setGoalShotsFH2(fHalfCount2);
                matchStats.setGoalShotsSH2(sHalfCount2);
                matchStats.setGoalShotsFH3(fHalfCount3);
                matchStats.setGoalShotsSH3(sHalfCount3);
                matchStats.setGoalShotsFH4(fHalfCount4);
                matchStats.setGoalShotsSH4(sHalfCount4);
                matchStats.setGoalShotsFH5(fHalfCount5);
                matchStats.setGoalShotsSH5(sHalfCount5);
                matchStats.setGoalShotsFHTotal(fHalfTotal);
                matchStats.setGoalShotsSHTotal(sHalfTotal);

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
        View rootView = inflater.inflate(R.layout.fragment_goalshots,container, false);

        first = rootView.findViewById(R.id.pointRedGoalsShots);
        second = rootView.findViewById(R.id.pointOrangeGoalShots);
        third = rootView.findViewById(R.id.pointYellowGoalShots);
        fourth = rootView.findViewById(R.id.pointGreenGoalShots);
        fifth = rootView.findViewById(R.id.pointBlueGoalShots);
        rGroup = rootView.findViewById(R.id.rgGoalShots);
        firstHalfTotal = rootView.findViewById(R.id.firsthalfGoalShotsTotal);
        secondHalfTotal = rootView.findViewById(R.id.secondHalfGoalShotsTotal);
        eraseData = rootView.findViewById(R.id.btnEraseGoalShots);

        mChart = rootView.findViewById(R.id.pieChartFieldGoalShots);

        find = MainActivity.matchStatsDataBase.matchStatsDao().findRecord(PK);

        for(MatchStats m : find)
        {
            int fht = m.getGoalShotsFHTotal();
            if (fht == 0)
            {
                firstHalfTotal.setText(Integer.toString(firstHalfCounter));
            } else

            {
                firstHalfCounter = fht;
                firstHalfTotal.setText(Integer.toString(firstHalfCounter));
            }

            int sht = m.getGoalShotsSHTotal();
            if (sht == 0)
            {
                secondHalfTotal.setText(Integer.toString(secondHalfCounter));
            } else

            {
                secondHalfCounter = sht;
                secondHalfTotal.setText(Integer.toString(secondHalfCounter));
            }
        }

        CreatePieChart();

        return rootView;
    }//end onCreateView

    public void CreatePieChart()
    {
        mChart.setUsePercentValues(false);
        mChart.getDescription().setEnabled(false);


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
                    case R.id.radioButton1stHalf:
                        for(MatchStats m : find) {

                            int fh1 = m.getGoalShotsFH1();
                            if (fh1 == 0)
                            {
                                first.setText(Integer.toString(fHalfCounter1));
                            } else

                            {
                                fHalfCounter1 = fh1;
                                first.setText(Integer.toString(fHalfCounter1));
                            }

                            int fh2 = m.getGoalShotsFH2();
                            if (fh2 == 0)
                            {
                                second.setText(Integer.toString(fHalfCounter2));
                            } else

                            {
                                fHalfCounter2 = fh2;
                                second.setText(Integer.toString(fHalfCounter2));
                            }

                            int fh3 = m.getGoalShotsFH3();
                            if (fh3 == 0)
                            {
                                third.setText(Integer.toString(fHalfCounter3));
                            } else

                            {
                                fHalfCounter3 = fh3;
                                third.setText(Integer.toString(fHalfCounter3));
                            }

                            int fh4 = m.getGoalShotsFH4();
                            if (fh4 == 0)
                            {
                                fourth.setText(Integer.toString(fHalfCounter4));
                            } else

                            {
                                fHalfCounter4 = fh4;
                                fourth.setText(Integer.toString(fHalfCounter4));
                            }

                            int fh5 = m.getGoalShotsFH5();
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

                    case R.id.radioButton2ndHalf:
                        for(MatchStats m : find) {

                            int sh1 = m.getGoalShotsSH1();
                            if (sh1 == 0)
                            {
                                first.setText(Integer.toString(sHalfCounter1));
                            } else

                            {
                                sHalfCounter1 = sh1;
                                first.setText(Integer.toString(sHalfCounter1));
                            }

                            int sh2 = m.getGoalShotsSH2();
                            if (sh2 == 0)
                            {
                                second.setText(Integer.toString(sHalfCounter2));
                            } else

                            {
                                sHalfCounter2 = sh2;
                                second.setText(Integer.toString(sHalfCounter2));
                            }

                            int sh3 = m.getGoalShotsSH3();
                            if (sh3 == 0)
                            {
                                third.setText(Integer.toString(sHalfCounter3));
                            } else

                            {
                                sHalfCounter3 = sh3;
                                third.setText(Integer.toString(sHalfCounter3));
                            }

                            int sh4 = m.getGoalShotsSH4();
                            if (sh4 == 0)
                            {
                                fourth.setText(Integer.toString(sHalfCounter4));
                            } else

                            {
                                sHalfCounter4 = sh4;
                                fourth.setText(Integer.toString(sHalfCounter4));
                            }

                            int sh5 = m.getGoalShotsSH5();
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
                    case R.id.radioButton1stHalf:
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
                    case R.id.radioButton2ndHalf:
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
                    case R.id.radioButton1stHalf:
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

                    case R.id.radioButton2ndHalf:
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
        });//end setOnChartValueSelectedListener

    }//end onStart

}//end goalShotFragment
