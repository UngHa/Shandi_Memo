package com.example.shandi_memo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

//일정 리사이클어뷰에 사용될 일정 리스트 어댑터
public class PlanListAdapter extends RecyclerView.Adapter<PlanListAdapter.ViewHolder>  {
    ArrayList<GetPlanInf> items;
    Context context;
    OnTapItemSelectedListener listener;

    public PlanListAdapter(ArrayList<GetPlanInf> items, Context context) {
        this.items = items;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View matchListView = inflater.inflate(R.layout.list_item, parent, false);

        return new ViewHolder(matchListView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.planName.setText(items.get(position).getTitle());
        holder.planDate.setText(items.get(position).getMonth() + "월 " + items.get(position).getDay()+"일");
        holder.planText.setText(items.get(position).getText());
        context = holder.itemView.getContext();

        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return items.size();
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
        TextView planName;
        TextView planDate;
        TextView planText;

        public ViewHolder(View itemView, final OnTapItemSelectedListener listener){
            super(itemView);

            profileImageView = itemView.findViewById(R.id.profileImageView);
            planName = itemView.findViewById(R.id.planName);
            planDate = itemView.findViewById(R.id.planDate);
            planText = itemView.findViewById(R.id.planText);

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
    }
}
