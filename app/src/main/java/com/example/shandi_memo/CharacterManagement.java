package com.example.shandi_memo;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

//캐릭터 탭 프래그먼트
public class CharacterManagement extends Fragment {
    //레이아웃 연결을 위한 변수
    RecyclerView characterListRecycler;
    CharacterListAdapter adapter;
    Context context;
    OnTapItemSelectedListener listener;
    FloatingActionButton addChar;

    //캐릭터 아이템 ArrayList
    ArrayList<CharacterItem> characterList;

    //데이터베이스 참조를 위한 변수
    DatabaseReference RootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference characterRef = RootRef.child("Character");

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
        //레이아웃 연결
        characterListRecycler = rootView.findViewById(R.id.characterListRecycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        characterListRecycler.setLayoutManager(layoutManager);
        addChar = rootView.findViewById(R.id.addCharacterButton);

        //캐릭터 리스트
        characterList = new ArrayList<>();

        characterRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //데이터베이스로부터 각 변수에 해당하는 값을 가져옴

                //리스트 구성전 초기화
                characterList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String className = dataSnapshot.child("className").getValue(String.class);
                    String level = dataSnapshot.child("level").getValue(String.class);
                    String name = dataSnapshot.child("name").getValue(String.class);
                    CharacterItem characterItem = new CharacterItem(name, className, level);
                    characterList.add(characterItem);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //어댑터와 리스트 연결
        adapter = new CharacterListAdapter(characterList, getContext());
        characterListRecycler.setAdapter(adapter);

        //캐릭터 리스트 클릭 리스너
        adapter.setOnItemClickListener(new OnTapItemSelectedListener() {

            @Override
            public void onItemClick(PlanListAdapter.ViewHolder holder, View view, int position) {

            }

            @Override
            //리스트 클릭시 다이얼로그 프래그먼트 출력 및, 해당 리스트의 데이터를 Bundle에 넣어 다이얼로그에 보냄
            public void onItemClick(CharacterListAdapter.ViewHolder holder, View view, int position) {
               Bundle args = new Bundle();
               args.putString("name", characterList.get(position).getName());
               args.putString("className", characterList.get(position).getClassName());
               args.putString("level", characterList.get(position).getLevel());
               CharacterInfo ReceiverDialog1 = CharacterInfo.getInstance();
               ReceiverDialog1.setArguments(args);
               ReceiverDialog1.show(getFragmentManager(), "character_info");
            }
        });

        //캐릭터 추가 버튼 클릭 리스너
        addChar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddCharacter ReceiverDialog2 = AddCharacter.getInstance();
                ReceiverDialog2.show(getFragmentManager(), "add_charcarter");
            }
        });
    }
}
