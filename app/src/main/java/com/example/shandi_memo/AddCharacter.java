package com.example.shandi_memo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

//캐릭터 추가 다이얼로그 프래그먼트
public class AddCharacter extends DialogFragment {

    //파이어베이스 참조를 위한 변수
    DatabaseReference RootRef = FirebaseDatabase.getInstance().getReference(); //데이터베이스 호출
    DatabaseReference characterRef = RootRef.child("Character");               //캐릭터 데이터베이스 참조
    DatabaseReference charRef;      //각 캐릭터 참조
    DatabaseReference nameRef;      //캐릭터의 닉네임 참조
    DatabaseReference classNameRef; //캐릭터의 직업이름 참조
    DatabaseReference levelRef;     //캐릭터의 아이템레벨 참조

    //레이아웃 연결을 위한 변수
    ImageView profileImg;   //프로필 사진
    Spinner classSpinner;   //직업선택 드롭다운 스피너
    EditText nameEdit;      //닉네임 입력칸
    EditText levelEdit;     //레벨 입력칸
    Button saveBtn;         //저장버튼
    Button cancelBtn;       //취소버튼
    
    public AddCharacter(){}

    public static AddCharacter getInstance(){
        AddCharacter addCharacter = new AddCharacter();
        return addCharacter;
    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.add_character, container);

        //레이아웃과 연결
        classSpinner = (Spinner)v.findViewById(R.id.classSpinner);
        profileImg = (ImageView)v.findViewById(R.id.profileImg);
        nameEdit = (EditText)v.findViewById(R.id.nameEdit);
        levelEdit = (EditText)v.findViewById(R.id.levelEdit);
        saveBtn = (Button)v.findViewById(R.id.saveBtn);
        cancelBtn = (Button)v.findViewById(R.id.cancelBtn);

        //ArrayAdapter에 strings.xml에 직업목록을 모아둔 string-array를 불러옴
        ArrayAdapter<String> classAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item,
                getResources().getStringArray(R.array.classArray));

        //스피너 설정
        classAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        classSpinner.setAdapter(classAdapter);
       
        //스피너를 통해 직업을 선택할 경우 선택한 아이템의 포지션값에 따라 프로필 사진 변경
        classSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

        // 취소버튼 클릭 리스너
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        
        // 저장버튼 클릭 리스너
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //닉네임과 레벨값을 가져옴
                String name = nameEdit.getText().toString();
                String level = levelEdit.getText().toString();

                //닉네임또는 레벨입력칸이 빈칸일 경우 실행되지않고 토스트메시지출력
                if (!name.trim().isEmpty() && !level.trim().isEmpty()) {
                    
                    //사용자가 입력한 name값으로 데이터베이스 생성, 데이터베이스는 닉네임 name, 직업이름 className, 아이템레벨 level값을 자식으로 둔다.
                    charRef = characterRef.child(name);
                    nameRef = charRef.child("name");
                    classNameRef = charRef.child("className");
                    levelRef = charRef.child("level");

                    //데이터베이스 추가
                    charRef.setValue(name);
                    nameRef.setValue(name);
                    classNameRef.setValue(classSpinner.getSelectedItem().toString());
                    levelRef.setValue(level);

                    dismiss();
                }
                else
                    //닉네임또는 레벨입력칸이 빈칸일 경우 실행되지않고 토스트메시지출력
                    Toast.makeText(getContext(), "값을 입력하세요.", Toast.LENGTH_SHORT).show();
            }
        });

        //화면터치시 꺼짐 막기
        setCancelable(false);
        
        return v;
    }
}



