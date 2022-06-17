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

public class CharacterListAdapter extends RecyclerView.Adapter<CharacterListAdapter.ViewHolder>  {
    ArrayList<CharacterItem> items = new ArrayList<CharacterItem>();
    Context context;
    OnTapItemSelectedListener listener;

    public CharacterListAdapter(ArrayList<CharacterItem> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View chararterListView = inflater.inflate(R.layout.character_list_item, parent, false);

        return new ViewHolder(chararterListView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (holder.className.getText() == "디스트로이어") {
            holder.profileIcon.setImageResource(R.drawable.destroyer);
        } else if (holder.className.getText() == "워로드") {
            holder.profileIcon.setImageResource(R.drawable.warlord);
        } else if (holder.className.getText() == "버서커") {
            holder.profileIcon.setImageResource(R.drawable.berserker);
        } else if (holder.className.getText() == "홀리나이트") {
            holder.profileIcon.setImageResource(R.drawable.holyknight);
        } else if (holder.className.getText() == "배틀마스터") {
            holder.profileIcon.setImageResource(R.drawable.battlemaster);
        } else if (holder.className.getText() == "인파이터") {
            holder.profileIcon.setImageResource(R.drawable.infighter);
        } else if (holder.className.getText() == "기공사") {
            holder.profileIcon.setImageResource(R.drawable.soulmaster);
        } else if (holder.className.getText() == "창술사") {
            holder.profileIcon.setImageResource(R.drawable.lancemaster);
        } else if (holder.className.getText() == "스트라이커") {
            holder.profileIcon.setImageResource(R.drawable.striker);
        } else if (holder.className.getText() == "데빌헌터") {
            holder.profileIcon.setImageResource(R.drawable.devilhunter);
        } else if (holder.className.getText() == "블래스터") {
            holder.profileIcon.setImageResource(R.drawable.blaster);
        } else if (holder.className.getText() == "호크아이") {
            holder.profileIcon.setImageResource(R.drawable.hawkeye);
        } else if (holder.className.getText() == "스카우터") {
            holder.profileIcon.setImageResource(R.drawable.scouter);
        } else if (holder.className.getText() == "건슬링어") {
            holder.profileIcon.setImageResource(R.drawable.gunslinger);
        } else if (holder.className.getText() == "바드") {
            holder.profileIcon.setImageResource(R.drawable.bard);
        } else if (holder.className.getText() == "서머너") {
            holder.profileIcon.setImageResource(R.drawable.summoner);
        } else if (holder.className.getText() == "아르카나") {
            holder.profileIcon.setImageResource(R.drawable.arcanist);
        } else if (holder.className.getText() == "소서리스") {
            holder.profileIcon.setImageResource(R.drawable.sorceress);
        } else if (holder.className.getText() == "데모닉") {
            holder.profileIcon.setImageResource(R.drawable.demonic);
        } else if (holder.className.getText() == "블레이드") {
            holder.profileIcon.setImageResource(R.drawable.blade);
        } else if (holder.className.getText() == "리퍼") {
            holder.profileIcon.setImageResource(R.drawable.reaper);
        } else if (holder.className.getText() == "도화가") {
            holder.profileIcon.setImageResource(R.drawable.artist);
        }
        holder.charName.setText(items.get(position).getName());
        holder.className.setText(items.get(position).getClassName());
        holder.ItemLv.setText(items.get(position).getLevel());
        context = holder.itemView.getContext();

        //CharacterItem item = items.get(position);
       // holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    /*public void addItem(CharacterItem item){
        items.add(item);
    }

    public void setItems(ArrayList<CharacterItem> items){
        this.items = items;
    }

    public CharacterItem getItem(int position){
        return items.get(position);
    }

    public void setOnItemClickListener(OnTapItemSelectedListener listener){
        this.listener = listener;
    }

    public void onItemClick(ViewHolder holder, View view, int position){
        if(listener != null){
            listener.onItemClick(holder, view, position);
        }
    }*/

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView profileIcon;
        TextView charName;
        TextView className;
        TextView ItemLv;

        public ViewHolder(View itemView, final OnTapItemSelectedListener listener){
            super(itemView);

            profileIcon = itemView.findViewById(R.id.profileIcon);
            charName = itemView.findViewById(R.id.charName);
            className = itemView.findViewById(R.id.className);
            ItemLv = itemView.findViewById(R.id.itemLevel);

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

        public void setItem(CharacterItem item){
            profileIcon.setImageResource(R.drawable.ic_launcher_foreground);
            charName.setText(item.getName());
            className.setText(item.getClassName());
            ItemLv.setText(item.getLevel());
        }
    }
}
