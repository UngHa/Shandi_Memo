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
        String classname = items.get(position).getClassName();
        switch (classname) {
            case "디스트로이어":
                holder.profileIcon.setImageResource(R.drawable.destroyer);
                break;
            case "워로드":
                holder.profileIcon.setImageResource(R.drawable.warlord);
                break;
            case "버서커":
                holder.profileIcon.setImageResource(R.drawable.berserker);
                break;
            case "홀리나이트":
                holder.profileIcon.setImageResource(R.drawable.holyknight);
                break;
            case "배틀마스터":
                holder.profileIcon.setImageResource(R.drawable.battlemaster);
                break;
            case "인파이터":
                holder.profileIcon.setImageResource(R.drawable.infighter);
                break;
            case "기공사":
                holder.profileIcon.setImageResource(R.drawable.soulmaster);
                break;
            case "창술사":
                holder.profileIcon.setImageResource(R.drawable.lancemaster);
                break;
            case "스트라이커":
                holder.profileIcon.setImageResource(R.drawable.striker);
                break;
            case "데빌헌터":
                holder.profileIcon.setImageResource(R.drawable.devilhunter);
                break;
            case "블래스터":
                holder.profileIcon.setImageResource(R.drawable.blaster);
                break;
            case "호크아이":
                holder.profileIcon.setImageResource(R.drawable.hawkeye);
                break;
            case "스카우터":
                holder.profileIcon.setImageResource(R.drawable.scouter);
                break;
            case "건슬링어":
                holder.profileIcon.setImageResource(R.drawable.gunslinger);
                break;
            case "바드":
                holder.profileIcon.setImageResource(R.drawable.bard);
                break;
            case "서머너":
                holder.profileIcon.setImageResource(R.drawable.summoner);
                break;
            case "아르카나":
                holder.profileIcon.setImageResource(R.drawable.arcanist);
                break;
            case "소서리스":
                holder.profileIcon.setImageResource(R.drawable.sorceress);
                break;
            case "데모닉":
                holder.profileIcon.setImageResource(R.drawable.demonic);
                break;
            case "블레이드":
                holder.profileIcon.setImageResource(R.drawable.blade);
                break;
            case "리퍼":
                holder.profileIcon.setImageResource(R.drawable.reaper);
                break;
            case "도화가":
                holder.profileIcon.setImageResource(R.drawable.artist);
                break;
        }
        holder.profileIcon.setColorFilter(R.color.black);
        holder.charName.setText(items.get(position).getName());
        holder.className.setText(classname);
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
