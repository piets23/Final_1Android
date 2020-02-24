package com.example.piets.hockeymanagement.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.piets.hockeymanagement.Classes.Opponent;
import com.example.piets.hockeymanagement.R;


import java.util.List;

public class OpponentRecycle_Adapter extends RecyclerView.Adapter<OpponentRecycle_Adapter.ViewHolder>
{
    private List<Opponent> opponents;
    ItemClicked activity;
    Context c;

    public interface ItemClicked
    {
        void onItemClicked(int index);
    }

    public OpponentRecycle_Adapter(Context context, List<Opponent> opponent)
    {
        opponents = opponent;
        c = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView ivOpponent;
        TextView tvOpponentTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            ivOpponent = itemView.findViewById(R.id.iv_opponents);
            tvOpponentTitle = itemView.findViewById(R.id.tvOpponentTitle);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.onItemClicked(opponents.indexOf((Opponent) v.getTag()));
                }
            });
        }
    }

    @NonNull
    @Override
    public OpponentRecycle_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rec_opponent_card, parent, false);

        return new ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull OpponentRecycle_Adapter.ViewHolder holder, int position)
    {
        holder.itemView.setTag(opponents.get(position));
        holder.tvOpponentTitle.setText(opponents.get(position).getName());
        holder.ivOpponent.setImageResource(R.drawable.rechockeyopponents);
    }

    @Override
    public int getItemCount()
    {
        return opponents.size();
    }
}