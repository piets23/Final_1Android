package com.example.piets.hockeymanagement.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.piets.hockeymanagement.Classes.Teams;
import com.example.piets.hockeymanagement.R;

import java.util.List;

/**
 * Created by piets on 2018/09/01.
 */

public class TeamRecycle_Adapter extends RecyclerView.Adapter<TeamRecycle_Adapter.ViewHolder>
{
    private List<Teams> teams;
    ItemClicked activity;
    Context c;

    public interface ItemClicked
    {
        void onItemClicked(int index);
    }

    public TeamRecycle_Adapter(Context context, List<Teams> team)
    {
        teams = team;
        c = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView ivTeam;
        TextView tvTeamTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            ivTeam = itemView.findViewById(R.id.iv_team);
            tvTeamTitle = itemView.findViewById(R.id.tvTeamTitle);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.onItemClicked(teams.indexOf((Teams) v.getTag()));
                }
            });
        }
    }





    @NonNull
    @Override
    public TeamRecycle_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rec_team_card, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TeamRecycle_Adapter.ViewHolder holder, int position)
    {
        holder.itemView.setTag(teams.get(position));
        holder.tvTeamTitle.setText(teams.get(position).getTeamName());
        holder.ivTeam.setImageResource(R.drawable.rechockeyteams);
    }

    @Override
    public int getItemCount()
    {
        return teams.size();
    }
}
