package com.hansung.android.homework3_final_test;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.Calendar;

public class WeekCalenderAdapter extends FragmentStateAdapter {

    private static int NUM_ITEMS=100; //무한 스와이프

    int mYear;
    int mMonth;
    int mDay;

    public WeekCalenderAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        ArrayList<String> days = new ArrayList<String>();

        int dt = (position - (NUM_ITEMS / 2)) * 7;


        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, mYear);
        cal.set(Calendar.MONTH, mMonth); //1월은 0
        cal.set(Calendar.DATE, mDay);

        int k = cal.get(Calendar.DAY_OF_WEEK);
        //월요일부터 시작하도록 조정
        dt = dt - (k - 1);
        cal.set(Calendar.DAY_OF_MONTH, mDay + dt);
        int sy = cal.get(Calendar.YEAR);
        int sm = cal.get(Calendar.MONTH);
        int sd = cal.get(Calendar.DATE);

        for (int i = 0; i < 7; i++) {
            int j = cal.get(Calendar.DATE);
            days.add(String.format("%d", j));
            cal.add(Calendar.DATE, 1);
        }

        return WeekCalenderFragment.newInstance(days, sy, sm, sd);
    }

    @Override
    public int getItemCount() {
        return NUM_ITEMS;
    }
}
