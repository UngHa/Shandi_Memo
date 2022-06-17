package com.example.shandi_memo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MatchListAdapter extends RecyclerView.Adapter<MatchListAdapter.ViewHolder>  {
    ArrayList<MatchingItem> items = new ArrayList<MatchingItem>();

    OnTapItemSelectedListener listener;

    int layoutType = 0;



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View matchListView = inflater.inflate(R.layout.list_item, parent, false);

        return new ViewHolder(matchListView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MatchingItem item = items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
    public void addItem(MatchingItem item){
        items.add(item);
    }

    public void setItems(ArrayList<MatchingItem> items){
        this.items = items;
    }

    public MatchingItem getItem(int position){
        return items.get(position);
    }

    public void setOnItemClickListener(OnTapItemSelectedListener listener){
        this.listener = listener;
    }

    public void onItemClick(ViewHolder holder, View view, int position){
        if(listener != null){
            listener.onItemClick(holder, view, position);
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView profileImageView;
        TextView matchName;
        TextView matchDate;
        TextView matchLocation;

        public ViewHolder(View itemView, final OnTapItemSelectedListener listener){
            super(itemView);

            profileImageView = itemView.findViewById(R.id.profileImageView);
            matchName = itemView.findViewById(R.id.matchName);
            matchDate = itemView.findViewById(R.id.matchDate);
            matchLocation = itemView.findViewById(R.id.matchLocation);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    if(listener != null){
                        listener.onItemClick(ViewHolder.this, view, position);
                    }
                }
            });

        }

        public void setItem(MatchingItem item){
            profileImageView.setImageResource(R.drawable.ic_launcher_foreground);
            matchName.setText(item.getPlanName());
            matchDate.setText(item.getPlanDate());
            matchLocation.setText(item.getPlanText());
        }
    }
}
