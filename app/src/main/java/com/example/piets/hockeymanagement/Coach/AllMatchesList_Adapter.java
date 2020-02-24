package com.example.piets.hockeymanagement.Coach;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.piets.hockeymanagement.Classes.Matches;
import com.example.piets.hockeymanagement.Classes.Players;
import com.example.piets.hockeymanagement.R;

import java.util.List;

/**
 * Created by piets on 2018/09/28.
 */

public class AllMatchesList_Adapter extends ArrayAdapter<Matches>
{
    private Context context;
    private List<Matches> matches;

    public AllMatchesList_Adapter (Context context, List<Matches> matchesList )
    {
        super(context, R.layout.activity_coach__match_for_teams_,matchesList);
        this.context = context;
        this.matches = matchesList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(R.layout.all_matches_list_row, parent, false);

        TextView tvOpponent = convertView.findViewById(R.id.tvOpponent);
        TextView tvTeam = convertView.findViewById(R.id.tvTeam);
        TextView tvNumberMatch = convertView.findViewById(R.id.tvNumberMatch);

        tvOpponent.setText(matches.get(position).getOpponent().toString());
        tvTeam.setText(matches.get(position).getTeam().toString());
        tvNumberMatch.setText(matches.get(position).getNumberOfMatch().toString());

        return convertView;
    }

    public Players getItem(Players player)
    {
        return player;
    }
}
