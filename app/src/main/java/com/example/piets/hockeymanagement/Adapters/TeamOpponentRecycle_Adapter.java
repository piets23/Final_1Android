package com.example.piets.hockeymanagement.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.piets.hockeymanagement.Classes.Players;
import com.example.piets.hockeymanagement.R;

import java.util.List;

/**
 * Created by piets on 2018/09/01.
 */

public class TeamOpponentRecycle_Adapter extends RecyclerView.Adapter<TeamOpponentRecycle_Adapter.ViewHolder>
{

    private List<Players> opponent;




public TeamOpponentRecycle_Adapter(Context context, List<Players> teamsList)
{
    opponent= teamsList;

}

public class ViewHolder extends RecyclerView.ViewHolder
{


    TextView teamsTV ;
    public ViewHolder(View itemView)
    {
        super(itemView);
        teamsTV = itemView.findViewById(R.id.rv_team_text);


    }
}

@Override
    public TeamOpponentRecycle_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType)
    {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.team_opponent_recycle,viewGroup,false);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull TeamOpponentRecycle_Adapter.ViewHolder holder, int position)
    {
       holder.itemView.setTag(opponent.get(position));

       holder.teamsTV.setText(opponent.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
