package com.example.shandi_memo;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

//캐릭터 정보 다이얼로그 프래그먼트
public class CharacterInfo extends DialogFragment {
    //캐릭터를 수정, 삭제하기위한 데이터베이스 참조 변수
    DatabaseReference RootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference characterRef = RootRef.child("Character");
    DatabaseReference editedCharRef, editedNameRef, editedClassNameRef, editedLevelRef;
    DatabaseReference charRef, levelRef;

    //아이템으로부터 값을 가져올 변수
    String name, className, level;

    public CharacterInfo(){}

    public static CharacterInfo getInstance(){
        CharacterInfo characterInfo = new CharacterInfo();
        return characterInfo;
    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.character_info, container, false);

        //레이아웃 연결
        ImageButton nameEditBtn = v.findViewById(R.id.nameEditBtn);
        ImageButton levelEditBtn = v.findViewById(R.id.levelEditBtn);
        ImageButton deleteBtn = v.findViewById(R.id.deleteBtn);
        Button cancelBtn = v.findViewById(R.id.cancelBtn);
        Button saveBtn = v.findViewById(R.id.saveBtn);
        TextView classNameText = v.findViewById(R.id.classNameText);
        EditText nameEdit = (EditText)v.findViewById(R.id.nameEdit);
        EditText levelEdit = (EditText)v.findViewById(R.id.levelEdit);
        ImageView profileImg = v.findViewById(R.id.profileImg);

        //선택한 캐릭터 리스트로부터 데이터를 가져옴
        Bundle args = getArguments();
        name = args.getString("name");
        className = args.getString("className");
        level = args.getString("level");

        //가져온 데이터의 직업이름에 따라 이미지 변경
        switch (className) {
            case "디스트로이어":
                profileImg.setImageResource(R.drawable.destroyer);
                break;
            case "워로드":
                profileImg.setImageResource(R.drawable.warlord);
                break;
            case "버서커":
                profileImg.setImageResource(R.drawable.berserker);
                break;
            case "홀리나이트":
                profileImg.setImageResource(R.drawable.holyknight);
                break;
            case "배틀마스터":
                profileImg.setImageResource(R.drawable.battlemaster);
                break;
            case "인파이터":
                profileImg.setImageResource(R.drawable.infighter);
                break;
            case "기공사":
                profileImg.setImageResource(R.drawable.soulmaster);
                break;
            case "창술사":
                profileImg.setImageResource(R.drawable.lancemaster);
                break;
            case "스트라이커":
                profileImg.setImageResource(R.drawable.striker);
                break;
            case "데빌헌터":
                profileImg.setImageResource(R.drawable.devilhunter);
                break;
            case "블래스터":
                profileImg.setImageResource(R.drawable.blaster);
                break;
            case "호크아이":
                profileImg.setImageResource(R.drawable.hawkeye);
                break;
            case "스카우터":
                profileImg.setImageResource(R.drawable.scouter);
                break;
            case "건슬링어":
                profileImg.setImageResource(R.drawable.gunslinger);
                break;
            case "바드":
                profileImg.setImageResource(R.drawable.bard);
                break;
            case "서머너":
                profileImg.setImageResource(R.drawable.summoner);
                break;
            case "아르카나":
                profileImg.setImageResource(R.drawable.arcanist);
                break;
            case "소서리스":
                profileImg.setImageResource(R.drawable.sorceress);
                break;
            case "데모닉":
                profileImg.setImageResource(R.drawable.demonic);
                break;
            case "블레이드":
                profileImg.setImageResource(R.drawable.blade);
                break;
            case "리퍼":
                profileImg.setImageResource(R.drawable.reaper);
                break;
            case "도화가":
                profileImg.setImageResource(R.drawable.artist);
                break;
        }
        profileImg.setColorFilter(R.color.black);

        //데이터를 받아옴
        nameEdit.setText(name);
        classNameText.setText(className);
        levelEdit.setText(level);

        //받아온 데이터로 캐릭터 참조 변수 설정
        charRef = characterRef.child(name);

        //삭제 버튼 클릭 리스너
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //삭제시 재확인 다이얼로그 출력
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setNegativeButton("취소", null);
                builder.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //캐릭터 삭제및 토스트메시지 출력
                        charRef.setValue(null);
                        Toast.makeText(getContext(), "삭제되었습니다.", Toast.LENGTH_SHORT).show();

                        dismiss(); // 다이얼로그 닫기
                    }
                });
                final AlertDialog dlg = builder.create();
                dlg.setTitle("캐릭터 삭제");
                dlg.setMessage("삭제하시겠습니까?");
                dlg.setOnShowListener( new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface args) {
                        dlg.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.RED);
                        dlg.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.BLACK);
                    }
                });
                dlg.show();
            }
        });

        //닉네임 수정 토글 버튼 클릭 리스너
        nameEditBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //버튼 클릭시 아이콘에 색이 생기고 닉네임 수정칸이 활성화, 재 클릭시 비활성화
                if (nameEdit.isEnabled()) {
                    nameEdit.setEnabled(false);
                    nameEditBtn.setBackgroundResource(R.drawable.edit);
                } else {
                    nameEdit.setEnabled(true);
                    nameEditBtn.setBackgroundResource(R.drawable.edit_selected);
                }
            }
        });

        //아이템레벨 수정 토글 버튼 클릭 리스너
        levelEditBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //버튼 클릭시 아이콘에 색이 생기고 닉네임 수정칸이 활성화, 재 클릭시 비활성화
                if (levelEdit.isEnabled()) {
                    levelEdit.setEnabled(false);
                    levelEditBtn.setBackgroundResource(R.drawable.edit);
                } else {
                    levelEdit.setEnabled(true);
                    levelEditBtn.setBackgroundResource(R.drawable.edit_selected);
                }

            }
        });

        // 취소 버튼 클릭 리스너
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        // 저장(데이터 수정) 버튼 클릭 리스너
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //각 변수에 입력값을 가져옴
                String editedName = nameEdit.getText().toString();
                String editedLevel = levelEdit.getText().toString();
                String className = classNameText.getText().toString();

                //입력칸에 빈칸이 있을경우 실행되지않고 토스트메시지출력
                if (!editedName.trim().isEmpty() && !editedLevel.trim().isEmpty()) {
                    //이름은 변경되지않고 아이템레벨만 변경될경우 기존 데이터수정
                    //이름이 변경될경우 기존 데이터 삭제후 새 데이터 추가
                    if (name.equals(editedName)) {
                        levelRef = charRef.child("level");
                        levelRef.setValue(editedLevel);
                    } else {
                        charRef.setValue(null);
                        editedCharRef = characterRef.child(editedName);
                        editedNameRef = editedCharRef.child("name");
                        editedClassNameRef = editedCharRef.child("className");
                        editedLevelRef = editedCharRef.child("level");

                        editedCharRef.setValue(editedName);
                        editedNameRef.setValue(editedName);
                        editedClassNameRef.setValue(className);
                        editedLevelRef.setValue(editedLevel);
                    }

                    Toast.makeText(getContext(), "수정되었습니다.", Toast.LENGTH_SHORT).show();
                    dismiss();
                }
                else
                    Toast.makeText(getContext(), "값을 입력하세요.", Toast.LENGTH_SHORT).show();
            }
        });

        //화면터치시 꺼짐 막기
        setCancelable(false);

        return v;
    }
}



