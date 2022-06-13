package com.example.shandi_memo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class AddCharacter extends DialogFragment {

    public AddCharacter(){}

    public static AddCharacter getInstance(){
        AddCharacter addCharacter = new AddCharacter();
        return addCharacter;
    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.add_character, container);
        Spinner sp = (Spinner)v.findViewById(R.id.classSpinner);
        ImageView profileImg = (ImageView)v.findViewById(R.id.profileImg);

        ArrayAdapter<String> classAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item,
                getResources().getStringArray(R.array.classArray));
        classAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(classAdapter);

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0 : profileImg.setImageResource(R.drawable.destroyer); break;
                    case 1 : profileImg.setImageResource(R.drawable.warlord);break;
                    case 2 : profileImg.setImageResource(R.drawable.berserker);break;
                    case 3 : profileImg.setImageResource(R.drawable.holyknight);break;
                    case 4 : profileImg.setImageResource(R.drawable.battlemaster);break;
                    case 5 : profileImg.setImageResource(R.drawable.infighter);break;
                    case 6 : profileImg.setImageResource(R.drawable.soulmaster);break;
                    case 7 : profileImg.setImageResource(R.drawable.lancemaster);break;
                    case 8 : profileImg.setImageResource(R.drawable.striker);break;
                    case 9 : profileImg.setImageResource(R.drawable.devilhunter);break;
                    case 10 : profileImg.setImageResource(R.drawable.blaster);break;
                    case 11 : profileImg.setImageResource(R.drawable.hawkeye);break;
                    case 12 : profileImg.setImageResource(R.drawable.scouter);break;
                    case 13 : profileImg.setImageResource(R.drawable.gunslinger);break;
                    case 14 : profileImg.setImageResource(R.drawable.bard);break;
                    case 15 : profileImg.setImageResource(R.drawable.summoner);break;
                    case 16 : profileImg.setImageResource(R.drawable.arcanist);break;
                    case 17 : profileImg.setImageResource(R.drawable.sorceress);break;
                    case 18 : profileImg.setImageResource(R.drawable.demonic);break;
                    case 19 : profileImg.setImageResource(R.drawable.blade);break;
                    case 20 : profileImg.setImageResource(R.drawable.reaper);break;
                    case 21 : profileImg.setImageResource(R.drawable.artist);break;
                }
                profileImg.setColorFilter(getResources().getColor(R.color.black));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Button saveBtn = (Button)v.findViewById(R.id.saveBtn);
        Button cancelBtn = (Button)v.findViewById(R.id.cancelBtn);

        // 취소
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        // 적용
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        //화면터치시 꺼짐 막기
        setCancelable(false);

        return v;
    }
}