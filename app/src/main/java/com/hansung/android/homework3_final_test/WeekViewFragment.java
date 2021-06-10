package com.hansung.android.homework3_final_test;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WeekViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WeekViewFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";

    // TODO: Rename and change types of parameters
    private int mYear;
    private int mMonth;
    private int mDay;

    public WeekViewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param year Parameter 1.
     * @param month Parameter 2.
     * @param day Parameter 3.
     * @return A new instance of fragment WeekFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WeekViewFragment newInstance(int year, int month, int day) {
        WeekViewFragment fragment = new WeekViewFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, year);
        args.putInt(ARG_PARAM2, month);
        args.putInt(ARG_PARAM3, day);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mYear = getArguments().getInt(ARG_PARAM1);
            mMonth = getArguments().getInt(ARG_PARAM2); // 0 부터 시작 , 0 = 1월
            mDay = getArguments().getInt(ARG_PARAM3);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_week_view, container, false);
        WeekCalenderAdapter weekAdapter = new WeekCalenderAdapter(this);
        weekAdapter.mYear = mYear;
        weekAdapter.mMonth = mMonth;
        weekAdapter.mDay = mDay;

        ViewPager2 vpPager = rootView.findViewById(R.id.vpPager); //fragment_month_view.xml에서 불러오기
        FragmentStateAdapter adapter = weekAdapter;
        vpPager.setAdapter(adapter);
        vpPager.setCurrentItem(50, false); // ViewPager2 객체의 현재 페이지를 설정: 중간 지점
        vpPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) { // 페이지 바뀔때 월 바꾸기 ?

                // 변경된 날짜 구하기
                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.YEAR, mYear);
                cal.set(Calendar.MONTH, mMonth);
                cal.set(Calendar.DATE,  mDay + (position - 50) * 7);
                int y = cal.get(Calendar.YEAR);
                int m = cal.get(Calendar.MONTH) + 1;
                int d = cal.get(Calendar.DATE);

                // 변경된 년월 저장
                MainActivity.setTime(y, m, d, 12, 0);

                ((MainActivity)getActivity()).setTitle(y +"년 " + m + "월");

                Toast.makeText(getActivity(),
                        "Selected page position: " + position, Toast.LENGTH_SHORT).show();
            }
        });


        return rootView;
    }
}