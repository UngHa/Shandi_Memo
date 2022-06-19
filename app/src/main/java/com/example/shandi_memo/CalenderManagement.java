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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

//일정관리 탭 프래그먼트
public class CalenderManagement extends Fragment {
    //레이아웃 연결을 위한 변수
    RecyclerView calendarList;
    PlanListAdapter adapter;
    ImageView chaosGate, fieldBoss, goastShip;
    SwipeRefreshLayout mSwipeRefreshLayout;

    //일정 리스트 호출을 위한 변수
    String planTitle, planDate, planText, planMonth, planDay;

    //일정의 데이터값을 넣어둘 ArrayList
    ArrayList<GetPlanInf> planList = new ArrayList<GetPlanInf>();

    //일정 추가 다이얼로그 변수
    Dialog calendarDialog;
    int year, month, day;


    //파이어베이스 참조를 위한 변수
    DatabaseReference RootRef = FirebaseDatabase.getInstance().getReference(); //데이터베이스 호출
    DatabaseReference planRef = RootRef.child("Plan");                         //일정 데이터베이스 참조
    DatabaseReference planTitleRef;     //각 일정 참조
    DatabaseReference textRef;          //일정 내용 참조
    DatabaseReference monthRef;         //날짜의 월 참조
    DatabaseReference dayRef;           //날짜의 일 참조
    DatabaseReference titleRef;         //일정 이름 참조

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.calendar_management, container, false);

        initUI(rootView);
        prokionCompass(rootView);

        return rootView;
    }


    private void initUI(ViewGroup rootView) {

        //레이아웃 연결
        calendarList = rootView.findViewById(R.id.matchListRecycler);
        mSwipeRefreshLayout = rootView.findViewById(R.id.swiperefreshlayout);

        //SwipeRefreshLayout 설정
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                final Handler handler = new Handler();
                ft.detach(CalenderManagement.this).attach(CalenderManagement.this).commit();

                adapter.notifyDataSetChanged(); // 변경되었음을 어댑터에 알려준다.
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                }, 1000);
            }
        });

        //일정 데이터베이스에 변화가 생길 경우, 리스트 재호출
        planRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                //데이터베이스로부터 각 변수에 해당하는 값을 가져옴

                //리스트 구성 전 초기화
                planList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    planTitle = dataSnapshot.child("title").getValue(String.class);
                    planMonth = dataSnapshot.child("month").getValue(String.class);
                    planDay = dataSnapshot.child("day").getValue(String.class);
                    planText = dataSnapshot.child("text").getValue(String.class);
                    GetPlanInf getPlanInf= new GetPlanInf(planTitle, planDay, planMonth, planText);
                    planList.add(getPlanInf);

                    //날짜를 ~월 ~일 형식으로 재구성
                    planDate = planMonth +"월 "+ planDay +"일";
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(TAG, "loadPost:onCancelled", error.toException());
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        calendarList.setLayoutManager(layoutManager);

        //어댑터와 리스트 연결
        adapter = new PlanListAdapter(planList, getContext());
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

    //일정 추가 다이얼로그 출력
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

        //취소버튼 클릭 이벤트
        Button cancelAddPlan = calendarDialog.findViewById(R.id.cancelAddPlan);
        cancelAddPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendarDialog.dismiss();
            }
        });

        // 적용버튼 클릭 이벤트
        Button submitAddPlan = calendarDialog.findViewById(R.id.submitAddPlan);
        submitAddPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //일정 이름값을 가져옴
                String title = createRoomName.getText().toString();

                //일정 이름이 빈칸일 경우 실행되지 않고 토스트메시지 출력
                if (!title.trim().isEmpty()) {

                    //사용자가 입력한 title값으로 데이터베이스 생성, 데이터베이스는 일정내용 text, 날짜(월) month, 날짜(일) day, 일정 이름 title 값을 자식으로 둔다.
                    planTitleRef = planRef.child(title);
                    textRef = planTitleRef.child("text");
                    monthRef = planTitleRef.child("month");
                    dayRef = planTitleRef.child("day");
                    titleRef = planTitleRef.child("title");

                    //데이터베이스 추가
                    planTitleRef.setValue(title);
                    textRef.setValue(textField.getText().toString());
                    monthRef.setValue(Integer.toString(month));
                    dayRef.setValue(Integer.toString(day));
                    titleRef.setValue(title);

                    calendarDialog.dismiss();
                } else
                    Toast.makeText(getContext(), "제목을 입력하세요.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //화면 상단 프로키온의 나침반을 출력
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
}
