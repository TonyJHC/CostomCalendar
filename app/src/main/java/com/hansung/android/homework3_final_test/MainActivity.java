package com.hansung.android.homework3_final_test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager(); // 안드로이드 이전 버전들에서도 프래그먼트를 사용할 수 있도록 만든 ... 지원하는 기능으로, 현재 액티비티가 AppCompatActivity를 확장하여 만든 경우에 사용함
        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.main_container, new MonthViewFragment());
        fragmentTransaction.commit();

        //ViewPager2 vpPager = findViewById(R.id.vpPager);
        //FragmentStateAdapter adapter = new MonthCalendarAdapter(this);
        //vpPager.setAdapter(adapter);

        int mParam1 = Calendar.getInstance().get(Calendar.YEAR);  //Calender 객체를 return : 호출 시점의 년도
        int mParam2 = Calendar.getInstance().get(Calendar.MONTH);  //Calender 객체를 return : 호출 시점의 월 --> 월 정보는 0~ 11까지 나옴 따라서 +1

        MonthViewFragment monthViewFragment = MonthViewFragment.newInstance(mParam1,mParam2); //newInstance에서 MonthFragment 객체를 받았음


        getSupportFragmentManager().beginTransaction().replace(R.id.main_container,monthViewFragment ).commit();



    }

    //액션 및 오버플로 메뉴(옵션 메뉴)는 현재 액티비티와 관련된 여러 가지 동작이나 선택사항을 설정하는 메뉴
    //액션 및 오버플로 메뉴를 생성하려면 onCreateOptionMenu() 메소드를 재정의 한다.
    // main_menu.xml 에 의하여 정의된 메뉴 리소스가 Menu 객체로 팽창된다.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //사용자가 옵션 메뉴에서 항목(주, 월)을 선택하면, 시스템이 액티비티의 onOptionsItemSelected()메소드를 호출
    //onOptionItemSelected메서드는 선택된 메뉴 항목(액션 아이템)에 대한 MenuItem 객체를 전달함.
    // 항목을 식별(선택)하기 위해서 getItemId()를 호출하여 메뉴 항목에 대한 고유 ID를 얻을 수 있습니다.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.month_view: // 월 항목 선택시
                //---동적 프래그 먼트 추가-----------------------------
                FragmentManager fragmentManager = getSupportFragmentManager(); // 안드로이드 이전 버전들에서도 프래그먼트를 사용할 수 있도록 만든 ... 지원하는 기능으로, 현재 액티비티가 AppCompatActivity를 확장하여 만든 경우에 사용함
                //FragmentTransaction: 프로그먼트를 추가, 삭제 또는 교체 등의 작업 등의 작업 수행 중에 오류가 발생하면 다시 원래 상태로 되돌릴 수 있도록 해주는 기능을 구현한 클래스
                //FragmentManger의 beginTranaction() 메소드 호출을 통해 FragmentTransaction의 인스턴스를 얻어옴
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                // 얻어온 fragmentTransaction에 대해 수행하고자 하는 모든 변경 사항을 설정하려면 add(), remove(), 및 replace()와 같은 메서드를 사용함
                fragmentTransaction.replace(R.id.main_container, new MonthViewFragment());
                //주어진 트랜잭션에 대해 수행하고자 하는 모든 변경 사항을 적용하려면 FragmentTransaction의 commit()을 호출해야 함
                fragmentTransaction.commit();
                //---------------------------------------------------

                Toast.makeText(getApplicationContext(), "월간 달력", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.week_view: // 주 항목 선택시
                //---동적 프래그 먼트 추가-----------------------------
                 fragmentManager = getSupportFragmentManager(); // 안드로이드 이전 버전들에서도 프래그먼트를 사용할 수 있도록 만든 ... 지원하는 기능으로, 현재 액티비티가 AppCompatActivity를 확장하여 만든 경우에 사용함
                 fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.main_container, new WeekViewFragment());
                fragmentTransaction.commit();

                //---------------------------------------------------
                Toast.makeText(getApplicationContext(), "주간 달력", Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}