package com.example.shandi_memo;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

//user_information Fragment 처리 YCK
public class CalenderManagement extends Fragment {
    RecyclerView calendarList;
    MatchListAdapter adapter;

    Dialog calendarDialog;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.calendar_management, container, false);

        initUI(rootView);

        return rootView;
    }

    private void initUI(ViewGroup rootView){
        calendarList = rootView.findViewById(R.id.matchListRecycler);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        calendarList.setLayoutManager(layoutManager);

        adapter = new MatchListAdapter();

        adapter.addItem(new MatchingItem("일정제목1", "5월 18일", "하브렐슈드 4넴까지"));
        adapter.addItem(new MatchingItem("일정제목2", "5월 19일", "바드님이랑 45캐릭빼기"));
        adapter.addItem(new MatchingItem("일정제목3", "5월 22일", "00님,00님 고정팟"));
        adapter.addItem(new MatchingItem("일정제목4", "5월 23일", "길드 이벤트 참여일"));
        adapter.addItem(new MatchingItem("일정제목5", "5월 25일", "발비쿠하"));
        adapter.addItem(new MatchingItem("일정제목6", "5월 29일", "카양겔"));

        calendarList.setAdapter(adapter);

        //CJW : +버튼 클릭시
        calendarDialog= new Dialog(getContext());
        calendarDialog.setContentView(R.layout.add_plan);             // xml 레이아웃 파일과 연결

        FloatingActionButton createPlan = rootView.findViewById(R.id.createPlan);
        createPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendarDialogShow(); // 다이얼로그 띄우기
                //CJW : 주변 반투명 없애기, 상단 위치조정
                calendarDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                calendarDialog.getWindow().setGravity(Gravity.TOP);

            }
        });

    }

    public void calendarDialogShow(){
        // 취소
        calendarDialog.show();
        Button cancelAddPlan = calendarDialog.findViewById(R.id.cancelAddPlan);
        cancelAddPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendarDialog.dismiss();
            }
        });
        // 적용
        Button submitAddPlan = calendarDialog.findViewById(R.id.submitAddPlan);
        submitAddPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendarDialog.dismiss();
            }
        });
    }
}
