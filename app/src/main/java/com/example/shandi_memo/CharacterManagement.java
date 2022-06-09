package com.example.shandi_memo;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CharacterManagement extends Fragment {
    RecyclerView characterList;
    CharacterListAdapter adapter;

    Context context;
    OnTapItemSelectedListener listener;

    public void onAttach(Context context){
        super.onAttach(context);

        this.context=context;

        if(context instanceof OnTapItemSelectedListener){
            listener = (OnTapItemSelectedListener) context;
        }
    }

    public void onDetach(){
        super.onDetach();

        if(context != null){
            context = null;
            listener = null;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.character_management, container, false);
        initUI(rootView);
        return rootView;
    }

    private void initUI(ViewGroup rootView){

        characterList = rootView.findViewById(R.id.characterListRecycler);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        characterList.setLayoutManager(layoutManager);

        //RecyclerView에 아이템을 추가
        adapter = new CharacterListAdapter();
        adapter.addItem(new CharacterItem("신비는고양이", "창술사", "1540"));
        adapter.addItem(new CharacterItem("다같이꼭다시만나", "홀리나이트", "1460"));
        characterList.setAdapter(adapter);

        //매칭리스트 리스너
        /*adapter.setOnItemClickListener(new OnTapItemSelectedListener() {
            @Override
            public void onItemClick(MatchListAdapter.ViewHolder holder, View view, int position) {

                AlertDialog.Builder dlg = new AlertDialog.Builder(getContext());

                search = View.inflate(getContext(), R.layout.search_filtering_dialog,null);
                dlg.setView(search);
                dlg.setNegativeButton("닫기", null);
                dlg.setPositiveButton("참여", null);
                dlg.show();

                CharacterItem item = adapter.getItem(position);
                Toast.makeText(getContext(), position + "번째 매칭 선택됨", Toast.LENGTH_SHORT).show();
            }
        });*/

        /*FloatingActionButton roundingCreate;
        roundingCreate = rootView.findViewById(R.id.roundingCreateButton);
        roundingCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intend = new Intent(getContext(),RoundingCreate.class);
                startActivity(intend);
            }
        });*/
    }

}
