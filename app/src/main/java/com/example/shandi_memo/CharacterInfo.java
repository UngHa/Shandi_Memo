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

public class CharacterInfo extends DialogFragment {
    DatabaseReference RootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference characterRef = RootRef.child("Character");
    DatabaseReference editedCharRef, editedNameRef, editedClassNameRef, editedLevelRef;
    DatabaseReference charRef, levelRef;
    String name, className, level;

    public CharacterInfo(){}

    public static CharacterInfo getInstance(){
        CharacterInfo characterInfo = new CharacterInfo();
        return characterInfo;
    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.character_info, container, false);
        ImageButton nameEditBtn = v.findViewById(R.id.nameEditBtn);
        ImageButton levelEditBtn = v.findViewById(R.id.levelEditBtn);
        ImageButton deleteBtn = v.findViewById(R.id.deleteBtn);
        Button cancelBtn = v.findViewById(R.id.cancelBtn);
        Button saveBtn = v.findViewById(R.id.saveBtn);
        TextView classNameText = v.findViewById(R.id.classNameText);
        EditText nameEdit = (EditText)v.findViewById(R.id.nameEdit);
        EditText levelEdit = (EditText)v.findViewById(R.id.levelEdit);
        ImageView profileImg = v.findViewById(R.id.profileImg);

        Bundle args = getArguments();
        name = args.getString("name");
        className = args.getString("className");
        level = args.getString("level");

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
        nameEdit.setText(name);
        classNameText.setText(className);
        levelEdit.setText(level);
        charRef = characterRef.child(name);

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setNegativeButton("취소", null);
                builder.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
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

        nameEditBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nameEdit.isEnabled()) {
                    nameEdit.setEnabled(false);
                    nameEditBtn.setBackgroundResource(R.drawable.edit);
                } else {
                    nameEdit.setEnabled(true);
                    nameEditBtn.setBackgroundResource(R.drawable.edit_selected);
                }

            }
        });

        levelEditBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (levelEdit.isEnabled()) {
                    levelEdit.setEnabled(false);
                    levelEditBtn.setBackgroundResource(R.drawable.edit);
                } else {
                    levelEdit.setEnabled(true);
                    levelEditBtn.setBackgroundResource(R.drawable.edit_selected);
                }

            }
        });

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
                String editedName = nameEdit.getText().toString();
                String editedLevel = levelEdit.getText().toString();
                String className = classNameText.getText().toString();
                if (!editedName.trim().isEmpty() && !editedLevel.trim().isEmpty()) {
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



