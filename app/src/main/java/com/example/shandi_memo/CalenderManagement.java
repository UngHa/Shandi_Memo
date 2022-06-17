package com.example.shandi_memo;

import static android.content.ContentValues.TAG;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Date;

public class CalenderManagement extends Fragment {
    RecyclerView calendarList;
    MatchListAdapter adapter;
    ImageView chaosGate, fieldBoss, goastShip;

    Dialog calendarDialog;

    String title = "example";


    int year, month, day;
    SwipeRefreshLayout mSwipeRefreshLayout;

    DatabaseReference RootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference PlanRef = RootRef.child("Plan");
    DatabaseReference titleRef;
    DatabaseReference textRef;
    DatabaseReference monthRef;
    DatabaseReference dayRef;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.calendar_management, container, false);

        initUI(rootView);
        prokionCompass(rootView);

        return rootView;
    }


    private void initUI(ViewGroup rootView) {


        calendarList = rootView.findViewById(R.id.matchListRecycler);
        mSwipeRefreshLayout = rootView.findViewById(R.id.swiperefreshlayout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                final Handler handler = new Handler();
                ft.detach(CalenderManagement.this).attach(CalenderManagement.this).commit();

                adapter.notifyDataSetChanged(); // 변경되었음을 어답터에 알려준다.
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                }, 1000);
            }
        });


        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        calendarList.setLayoutManager(layoutManager);

        adapter = new MatchListAdapter();

        calendarList.setAdapter(adapter);

        //CJW : +버튼 클릭시
        calendarDialog = new Dialog(getContext());
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

    public void calendarDialogShow() {

        EditText createRoomName = (EditText) calendarDialog.findViewById(R.id.createRoomName);
        EditText textField = (EditText) calendarDialog.findViewById(R.id.textField);
        DatePicker datePicker = (DatePicker) calendarDialog.findViewById(R.id.datePicker);
        calendarDialog.show();
        createRoomName.setText("");
        textField.setText("");
        datePicker.setMinDate(System.currentTimeMillis());
        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int y, int m, int d) {
                year = y;
                month = m + 1;
                day = d;
            }
        });

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
                title = createRoomName.getText().toString();

                if (!title.trim().isEmpty()) {

                    titleRef = PlanRef.child(title);
                    textRef = titleRef.child("Text");
                    monthRef = titleRef.child("Month");
                    dayRef = titleRef.child("Day");

                    titleRef.setValue(title);
                    textRef.setValue(textField.getText().toString());
                    monthRef.setValue(Integer.toString(month));
                    dayRef.setValue(Integer.toString(day));
                    ReadGetPlanInf();
                    calendarDialog.dismiss();
                } else
                    Toast.makeText(getContext(), "제목을 입력하세요.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void prokionCompass(ViewGroup rootView) {
        chaosGate = rootView.findViewById(R.id.chaosGate);
        fieldBoss = rootView.findViewById(R.id.fieldBoss);
        goastShip = rootView.findViewById(R.id.goastShip);
        int i = getCurrentWeek();
        switch (i) {
            case 0:
                chaosGate.setImageResource(R.drawable.chaos_gate_img);
                fieldBoss.setImageResource(R.drawable.none_mococo_img);
                goastShip.setImageResource(R.drawable.none_mococo_img);
                break;
            case 1:
                chaosGate.setImageResource(R.drawable.none_mococo_img);
                fieldBoss.setImageResource(R.drawable.field_boss_img);
                goastShip.setImageResource(R.drawable.goast_ship_img);
                break;
            case 2:
                chaosGate.setImageResource(R.drawable.none_mococo_img);
                fieldBoss.setImageResource(R.drawable.none_mococo_img);
                goastShip.setImageResource(R.drawable.none_mococo_img);
                break;
            case 3:
                chaosGate.setImageResource(R.drawable.chaos_gate_img);
                fieldBoss.setImageResource(R.drawable.none_mococo_img);
                goastShip.setImageResource(R.drawable.goast_ship_img);
                break;
            case 4:
                chaosGate.setImageResource(R.drawable.none_mococo_img);
                fieldBoss.setImageResource(R.drawable.field_boss_img);
                goastShip.setImageResource(R.drawable.none_mococo_img);
                break;
            case 5:
                chaosGate.setImageResource(R.drawable.chaos_gate_img);
                fieldBoss.setImageResource(R.drawable.none_mococo_img);
                goastShip.setImageResource(R.drawable.goast_ship_img);
                break;
            case 6:
                chaosGate.setImageResource(R.drawable.chaos_gate_img);
                fieldBoss.setImageResource(R.drawable.field_boss_img);
                goastShip.setImageResource(R.drawable.none_mococo_img);
                break;
        }
    }

    public static int getCurrentWeek() {
        Date currentDate = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);

        int week = calendar.get(Calendar.DAY_OF_WEEK);

        return week;
    }

    public static String planName, planDate, planText;

    public void ReadGetPlanInf() {
        RootRef.child("Plan").child(title).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                GetPlanInf gpi = dataSnapshot.getValue(GetPlanInf.class);
                planName = gpi.getTitle();
                planDate = gpi.getMonth() +"월"+ gpi.getDay()+"일";
                planText = gpi.getText();
                Log.d("test", "ValueEventListener : " + planName);
                Log.d("test", "ValueEventListener : " + planDate);
                Log.d("test", "ValueEventListener : " + planText);

                //dapter.addItem(new MatchingItem(planName, "창술사", "1540"));


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(TAG, "loadPost:onCancelled", error.toException());
            }
        });


    }

}
