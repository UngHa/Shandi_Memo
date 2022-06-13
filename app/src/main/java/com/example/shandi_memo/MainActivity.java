package com.example.shandi_memo;

import android.app.Dialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    // matchingList, userInformation Fragment 변수
    CharacterManagement characterManagement;
    ContentsManagement contentsManagement;
    CalenderManagement userInformation;

    //타이틀바 변수
    TextView title;

    //네비게이션바 변수
    BottomNavigationView bottomNavigation;

    //CJW : 상단 필터링 이미지 버튼 용 다이얼로그 변수
    Dialog filterDialog;

    //매너평가 화면출력
    ImageButton btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Fragment 전환 코드 YCK
        userInformation = new CalenderManagement();
        contentsManagement = new ContentsManagement();
        characterManagement = new CharacterManagement();

        // +타이틀 변경 YJW
        title = findViewById(R.id.title);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, characterManagement).commit();

        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.tab1:
                        title.setText("캐릭터 관리");
                        Toast.makeText(getApplicationContext(), "캐릭터선택 탭 선택됨", Toast.LENGTH_SHORT).show();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, characterManagement).commit();

                        return true;
                    case R.id.tab2:
                        title.setText("체크리스트");
                        Toast.makeText(getApplicationContext(), "체크리스트 탭 선택됨", Toast.LENGTH_SHORT).show();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, contentsManagement).commit();

                        return true;
                    case R.id.tab3:
                        title.setText("일정 관리");
                        Toast.makeText(getApplicationContext(), "일정관리 탭 선택됨", Toast.LENGTH_SHORT).show();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, userInformation).commit();

                        return true;
                }

                return false;
            }
        });
    }

    //Fragment 전환 코드 YCK
    public void onTabSelected(int position) {
        if (position == 0) {
            bottomNavigation.setSelectedItemId(R.id.tab1);
        } else if (position == 1) {
            bottomNavigation.setSelectedItemId(R.id.tab2);
        } else if (position == 2) {
            bottomNavigation.setSelectedItemId(R.id.tab3);
        }
    }

}
