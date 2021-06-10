package com.hansung.android.homework3_final_test;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MonthViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MonthViewFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    FragmentActivity listener;


    static int year;
    static int month;
    int count ;

    int today_of_week; // 오늘이 무슨 요일인지
    int lastDay; //해당 달의 마지막 일수
    int firstDay_of_month; // 해당 달의 1일의 요일
    String[] items;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MonthViewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MonthFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MonthViewFragment newInstance(int param1, int param2) {
        MonthViewFragment fragment = new MonthViewFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        args.putInt(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
             year = getArguments().getInt(ARG_PARAM1,-99);
             month = getArguments().getInt(ARG_PARAM2,-99);
        }

    }

    //onAttach() --> 프래그먼트가 액티비티에 연결될 때 호출됨
    //해당 Fragment와 연결된 액티비티의 reference 가져오기 -->어댑터에 연결하기 위함
    public void onAttach(Context context){
        super.onAttach(context);
        if(context instanceof Activity){
            this.listener = (FragmentActivity) context;
        }
    }




    //프래그먼트 수명주기도 액티비티의 수명주기에 종속적
    // onCreateView() --> 프래그먼트의 레이아웃을 생성 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_month_view, container, false);

        //MonthCalenderFragment에  year, month 전달
//        MonthCalenderFragment monthCalenderFragment = new MonthCalenderFragment(); !!!
//        monthCalenderFragment.newInstance(year, month); !!!

        //ViewPager2 객체에 FragmentStateAdapter 객체 설정
        //ViewPager 객체에 앞서 정의한 PagerAdapter 객체를 설정
        ViewPager2 vpPager = rootView.findViewById(R.id.vpPager); //fragment_month_view.xml에서 불러오기
        FragmentStateAdapter adapter = new MonthCalendarAdapter(this);
        vpPager.setAdapter(adapter);
        vpPager.setCurrentItem(50, false); // ViewPager2 객체의 현재 페이지를 설정 :50 --> thirdFragment


        vpPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) { // 페이지 바뀔때 월 바꾸기 ?


                // 변경된 날짜 구하기
                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.YEAR, year);
                cal.set(Calendar.MONTH, month + position - 50);
                cal.set(Calendar.DATE, 1);

                int y = cal.get(Calendar.YEAR);
                int m = cal.get(Calendar.MONTH);

                // 변경된 년월 저장
                MainActivity.setTime(y, m, 1, 12, 0);

                ((MainActivity)getActivity()).setTitle(y +"년 " + m + "월"); //앱바에 년월 표시

                Toast.makeText(getActivity(),
                        "Selected page position: " + position, Toast.LENGTH_SHORT).show();
            }
        });
        // Inflate the layout for this fragment
        return rootView;
    }
}