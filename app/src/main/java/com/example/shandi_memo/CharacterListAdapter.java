package com.example.shandi_memo;

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

    OnTapItemSelectedListener listener;

    int layoutType = 0;



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View chararterListView = inflater.inflate(R.layout.character_list_item, parent, false);

        return new ViewHolder(chararterListView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CharacterItem item = items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
    public void addItem(CharacterItem item){
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
    }

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
            charName.setText(item.getCharName());
            className.setText(item.getClassName());
            ItemLv.setText(item.getItemLevel());
        }
    }
}
