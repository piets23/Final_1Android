package com.example.piets.hockeymanagement.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.piets.hockeymanagement.Classes.Players;
import com.example.piets.hockeymanagement.R;

import java.util.List;

/**
 * Created by piets on 2018/09/04.
 */

public class MyPlayersList_Adapter extends ArrayAdapter<Players>
{
    private Context context;
    private List<Players> players;

    public MyPlayersList_Adapter(Context context, List<Players> playersList )
    {
        super(context, R.layout.activity_my_players_list_,playersList);
        this.context = context;
        this.players = playersList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(R.layout.all_payers_list_row, parent, false);

        TextView tvName = convertView.findViewById(R.id.tvName);
        TextView tvAmount = convertView.findViewById(R.id.tvSurname);

        tvName.setText(players.get(position).getName().toString());
        tvAmount.setText(players.get(position).getSurname().toString());

        return convertView;
    }

    public Players getItem(Players player)
    {
        return player;
    }
}
