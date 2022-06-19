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

//캐릭터 리사이클어뷰에 사용될 캐릭터 리스트 어댑터
public class CharacterListAdapter extends RecyclerView.Adapter<CharacterListAdapter.ViewHolder>  {
    ArrayList<CharacterItem> items;
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
        //아이템의 값에 따라 리스트 생성
        String classname = items.get(position).getClassName();
        switch (classname) {
            case "디스트로이어":
                holder.profileImg.setImageResource(R.drawable.destroyer);
                break;
            case "워로드":
                holder.profileImg.setImageResource(R.drawable.warlord);
                break;
            case "버서커":
                holder.profileImg.setImageResource(R.drawable.berserker);
                break;
            case "홀리나이트":
                holder.profileImg.setImageResource(R.drawable.holyknight);
                break;
            case "배틀마스터":
                holder.profileImg.setImageResource(R.drawable.battlemaster);
                break;
            case "인파이터":
                holder.profileImg.setImageResource(R.drawable.infighter);
                break;
            case "기공사":
                holder.profileImg.setImageResource(R.drawable.soulmaster);
                break;
            case "창술사":
                holder.profileImg.setImageResource(R.drawable.lancemaster);
                break;
            case "스트라이커":
                holder.profileImg.setImageResource(R.drawable.striker);
                break;
            case "데빌헌터":
                holder.profileImg.setImageResource(R.drawable.devilhunter);
                break;
            case "블래스터":
                holder.profileImg.setImageResource(R.drawable.blaster);
                break;
            case "호크아이":
                holder.profileImg.setImageResource(R.drawable.hawkeye);
                break;
            case "스카우터":
                holder.profileImg.setImageResource(R.drawable.scouter);
                break;
            case "건슬링어":
                holder.profileImg.setImageResource(R.drawable.gunslinger);
                break;
            case "바드":
                holder.profileImg.setImageResource(R.drawable.bard);
                break;
            case "서머너":
                holder.profileImg.setImageResource(R.drawable.summoner);
                break;
            case "아르카나":
                holder.profileImg.setImageResource(R.drawable.arcanist);
                break;
            case "소서리스":
                holder.profileImg.setImageResource(R.drawable.sorceress);
                break;
            case "데모닉":
                holder.profileImg.setImageResource(R.drawable.demonic);
                break;
            case "블레이드":
                holder.profileImg.setImageResource(R.drawable.blade);
                break;
            case "리퍼":
                holder.profileImg.setImageResource(R.drawable.reaper);
                break;
            case "도화가":
                holder.profileImg.setImageResource(R.drawable.artist);
                break;
        }
        holder.profileImg.setColorFilter(R.color.black);
        holder.charName.setText(items.get(position).getName());
        holder.className.setText(classname);
        holder.ItemLv.setText(items.get(position).getLevel());
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

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView profileImg;
        TextView charName;
        TextView className;
        TextView ItemLv;

        public ViewHolder(View itemView, final OnTapItemSelectedListener listener){
            super(itemView);

            profileImg = itemView.findViewById(R.id.profileImg);
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
    }
}
