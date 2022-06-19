package com.example.shandi_memo;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

//메인 액티비티 클래스
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    // 캐틱터관리 탭, 체크리스트 탭, 일정관리 탭 변수
    CharacterManagement characterManagement;
    ContentsManagement contentsManagement;
    CalenderManagement calenderManagemnet;

    //타이틀바 변수
    TextView title;

    //네비게이션바 변수
    BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Fragment 전환 코드
        calenderManagemnet = new CalenderManagement();
        contentsManagement = new ContentsManagement();
        characterManagement = new CharacterManagement();
        title = findViewById(R.id.title);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, characterManagement).commit();

        //네비게이션 바 선택시 해당하는 탭을 프래그먼트로 호출
        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.tab1:
                        title.setText("캐릭터 관리"); //캐릭터관리 탭 호출및 타이틀 변경
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, characterManagement).commit();
                        return true;

                    case R.id.tab2:
                        title.setText("체크리스트"); //체크리스트 탭 호출및 타이틀 변경
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, contentsManagement).commit();
                        return true;

                    case R.id.tab3:
                        title.setText("일정 관리"); //일정관리 탭 호출및 타이틀 변경
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.container, calenderManagemnet).commit();
                        return true;
                }

                return false;
            }
        });
    }

    //Fragment 전환 코드
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
