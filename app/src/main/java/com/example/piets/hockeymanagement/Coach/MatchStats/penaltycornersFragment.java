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

public class penaltycornersFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private int firstHalfCounterTotal = 0;
    private int secondHalfCounterTotal = 0;
    private int fHalfCounter1 = 0, fHalfCounter2 = 0, fHalfCounter3 = 0, fHalfCounter4 = 0, fHalfCounter5 = 0;
    private int sHalfCounter1 = 0, sHalfCounter2 = 0, sHalfCounter3 = 0, sHalfCounter4 = 0, sHalfCounter5 = 0;
    private RadioGroup rGroup;

    private String PK;

    Button eraseData;
    TextView first, second, third, fourth, fifth, firstHalfTotal, secondHalfTotal;
    private PieChart mChart;
    List<MatchStats> find;

    public penaltycornersFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static penaltycornersFragment newInstance(String param1, String param2) {
        penaltycornersFragment fragment = new penaltycornersFragment();
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
                int fHalfTotal = (firstHalfCounterTotal);
                int sHalfTotal = (secondHalfCounterTotal);

                MatchStats matchStats = new MatchStats();
                matchStats.setStatsPrimaryKey(PK);
                matchStats.setPenaltyShotsFH1(fHalfCount1);
                matchStats.setPenaltyShotsSH1(sHalfCount1);
                matchStats.setPenaltyShotsFH2(fHalfCount2);
                matchStats.setPenaltyShotsSH2(sHalfCount2);
                matchStats.setPenaltyShotsFH3(fHalfCount3);
                matchStats.setPenaltyShotsSH3(sHalfCount3);
                matchStats.setPenaltyShotsFH4(fHalfCount4);
                matchStats.setPenaltyShotsSH4(sHalfCount4);
                matchStats.setPenaltyShotsFH5(fHalfCount5);
                matchStats.setPenaltyShotsSH5(sHalfCount5);
                matchStats.setPenaltyShotsFHTotal(fHalfTotal);
                matchStats.setPenaltyShotsSHTotal(sHalfTotal);

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
        View rootView = inflater.inflate(R.layout.fragment_penaltycorners,container, false);

        first = rootView.findViewById(R.id.pointRedPenalty);
        second = rootView.findViewById(R.id.pointOrangePenalty);
        third = rootView.findViewById(R.id.pointYellowPenalty);
        fourth = rootView.findViewById(R.id.pointGreenPenalty);
        fifth = rootView.findViewById(R.id.pointedBluePenalty);
        rGroup = rootView.findViewById(R.id.rgPenaltyCorners);
        firstHalfTotal = rootView.findViewById(R.id.firstHalfPenalties);
        secondHalfTotal = rootView.findViewById(R.id.secondhalfPenalty);
        eraseData = rootView.findViewById(R.id.btnErasePenaltyCorners);

        mChart = rootView.findViewById(R.id.pieChartPenaltyCorners);
        CreatePieChart();

        find = MainActivity.matchStatsDataBase.matchStatsDao().findRecord(PK);
        for(MatchStats m : find)
        {
            int fht = m.getPenaltyShotsFHTotal();
            if (fht == 0)
            {
                firstHalfTotal.setText(Integer.toString(firstHalfCounterTotal));
            } else

            {
                firstHalfCounterTotal = fht;
                firstHalfTotal.setText(Integer.toString(firstHalfCounterTotal));
            }

            int sht = m.getPenaltyShotsSHTotal();
            if (sht == 0)
            {
                secondHalfTotal.setText(Integer.toString(secondHalfCounterTotal));
            } else

            {
                secondHalfCounterTotal = sht;
                secondHalfTotal.setText(Integer.toString(secondHalfCounterTotal));
            }
        }

        return rootView;
    }//end onCreateView

    @Override
    public void onStart() {
        super.onStart();

        rGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch(radioGroup.getCheckedRadioButtonId())
                {
                    case R.id.rbFirstHalfPenalty:
                        for(MatchStats m : find) {

                            int fh1 = m.getPenaltyShotsFH1();
                            if (fh1 == 0)
                            {
                                first.setText(Integer.toString(fHalfCounter1));
                            } else

                            {
                                fHalfCounter1 = fh1;
                                first.setText(Integer.toString(fHalfCounter1));
                            }

                            int fh2 = m.getPenaltyShotsFH2();
                            if (fh2 == 0)
                            {
                                second.setText(Integer.toString(fHalfCounter2));
                            } else

                            {
                                fHalfCounter2 = fh2;
                                second.setText(Integer.toString(fHalfCounter2));
                            }

                            int fh3 = m.getPenaltyShotsFH3();
                            if (fh3 == 0)
                            {
                                third.setText(Integer.toString(fHalfCounter3));
                            } else

                            {
                                fHalfCounter3 = fh3;
                                third.setText(Integer.toString(fHalfCounter3));
                            }

                            int fh4 = m.getPenaltyShotsFH4();
                            if (fh4 == 0)
                            {
                                fourth.setText(Integer.toString(fHalfCounter4));
                            } else

                            {
                                fHalfCounter4 = fh4;
                                fourth.setText(Integer.toString(fHalfCounter4));
                            }

                            int fh5 = m.getPenaltyShotsFH5();
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

                    case R.id.rbSecondHalfPenalty:
                        for(MatchStats m : find) {

                            int sh1 = m.getPenaltyShotsSH1();
                            if (sh1 == 0)
                            {
                                first.setText(Integer.toString(sHalfCounter1));
                            } else

                            {
                                sHalfCounter1 = sh1;
                                first.setText(Integer.toString(sHalfCounter1));
                            }

                            int sh2 = m.getPenaltyShotsSH2();
                            if (sh2 == 0)
                            {
                                second.setText(Integer.toString(sHalfCounter2));
                            } else

                            {
                                sHalfCounter2 = sh2;
                                second.setText(Integer.toString(sHalfCounter2));
                            }

                            int sh3 = m.getPenaltyShotsSH3();
                            if (sh3 == 0)
                            {
                                third.setText(Integer.toString(sHalfCounter3));
                            } else

                            {
                                sHalfCounter3 = sh3;
                                third.setText(Integer.toString(sHalfCounter3));
                            }

                            int sh4 = m.getPenaltyShotsSH4();
                            if (sh4 == 0)
                            {
                                fourth.setText(Integer.toString(sHalfCounter4));
                            } else

                            {
                                sHalfCounter4 = sh4;
                                fourth.setText(Integer.toString(sHalfCounter4));
                            }

                            int sh5 = m.getPenaltyShotsSH5();
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
                    case R.id.rbFirstHalfPenalty:
                        fHalfCounter1 = 0;
                        fHalfCounter2 = 0;
                        fHalfCounter3 = 0;
                        fHalfCounter4 = 0;
                        fHalfCounter5 = 0;
                        firstHalfCounterTotal = 0;
                        first.setText(Integer.toString(0));
                        second.setText(Integer.toString(0));
                        third.setText(Integer.toString(0));
                        fourth.setText(Integer.toString(0));
                        fifth.setText(Integer.toString(0));
                        firstHalfTotal.setText(Integer.toString(0));
                        break;

                    case R.id.rbSecondHalfPenalty:
                        sHalfCounter1 = 0;
                        sHalfCounter2 = 0;
                        sHalfCounter3 = 0;
                        sHalfCounter4 = 0;
                        sHalfCounter5 = 0;
                        secondHalfCounterTotal = 0;
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
                    case R.id.rbFirstHalfPenalty:
                        if (h.getX() == 0.0) {
                            fHalfCounter1++;
                            firstHalfCounterTotal++;
                            first.setText(Integer.toString(fHalfCounter1));
                            firstHalfTotal.setText(Integer.toString(firstHalfCounterTotal));
                        }
                        if (h.getX() == 1.0) {
                            fHalfCounter2++;
                            firstHalfCounterTotal++;
                            second.setText(Integer.toString(fHalfCounter2));
                            firstHalfTotal.setText(Integer.toString(firstHalfCounterTotal));
                        }
                        if (h.getX() == 2.0) {
                            fHalfCounter3++;
                            firstHalfCounterTotal++;
                            third.setText(Integer.toString(fHalfCounter3));
                            firstHalfTotal.setText(Integer.toString(firstHalfCounterTotal));
                        }
                        if (h.getX() == 3.0) {
                            fHalfCounter4++;
                            firstHalfCounterTotal++;
                            fourth.setText(Integer.toString(fHalfCounter4));
                            firstHalfTotal.setText(Integer.toString(firstHalfCounterTotal));
                        }
                        if (h.getX() == 4.0) {
                            fHalfCounter5++;
                            firstHalfCounterTotal++;
                            fifth.setText(Integer.toString(fHalfCounter5));
                            firstHalfTotal.setText(Integer.toString(firstHalfCounterTotal));
                        }
                        break;

                    case R.id.rbSecondHalfPenalty:
                        if (h.getX() == 0.0) {
                            sHalfCounter1++;
                            secondHalfCounterTotal++;
                            first.setText(Integer.toString(sHalfCounter1));
                            secondHalfTotal.setText(Integer.toString(secondHalfCounterTotal));
                        }
                        if (h.getX() == 1.0) {
                            sHalfCounter2++;
                            secondHalfCounterTotal++;
                            second.setText(Integer.toString(sHalfCounter2));
                            secondHalfTotal.setText(Integer.toString(secondHalfCounterTotal));
                        }
                        if (h.getX() == 2.0) {
                            sHalfCounter3++;
                            secondHalfCounterTotal++;
                            third.setText(Integer.toString(sHalfCounter3));
                            secondHalfTotal.setText(Integer.toString(secondHalfCounterTotal));
                        }
                        if (h.getX() == 3.0) {
                            sHalfCounter4++;
                            secondHalfCounterTotal++;
                            fourth.setText(Integer.toString(sHalfCounter4));
                            secondHalfTotal.setText(Integer.toString(secondHalfCounterTotal));
                        }
                        if (h.getX() == 4.0) {
                            sHalfCounter5++;
                            secondHalfCounterTotal++;
                            fifth.setText(Integer.toString(sHalfCounter5));
                            secondHalfTotal.setText(Integer.toString(secondHalfCounterTotal));
                        }
                        break;
                }//end switch
            }
            @Override
            public void onNothingSelected() {

            }
        });//end setOnChartValueSelectedListener

    }//end onStart

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
}//end class penaltycornersFragment
