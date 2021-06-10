package com.hansung.android.homework3_final_test;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WeekCalenderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WeekCalenderFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";
    private static final String ARG_PARAM4 = "param4";

    // TODO: Rename and change types of parameters
    private ArrayList<String> mDays;
    private int mSelYear;
    private int mSelMonth;
    private int mSelDay;
    private ArrayList<TextView> mTextViews;
    private TextView mSelDayText = null;

    ArrayAdapter<String> mAdapt = null;
    GridView mGridview = null;
    View mSelectedCellView = null;

    public WeekCalenderFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param days Parameter 1.
     * @return A new instance of fragment WeekCalenderFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WeekCalenderFragment newInstance(ArrayList<String> days, int year, int month, int day) {
        WeekCalenderFragment fragment = new WeekCalenderFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, days);
        args.putInt(ARG_PARAM2, year);
        args.putInt(ARG_PARAM3, month);
        args.putInt(ARG_PARAM4, day);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mDays = (ArrayList<String>)getArguments().getSerializable(ARG_PARAM1);
            mSelYear = getArguments().getInt(ARG_PARAM2);
            mSelMonth = getArguments().getInt(ARG_PARAM3);
            mSelDay = getArguments().getInt(ARG_PARAM4);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_week_calender, container, false);

        mGridview = (GridView)rootView.findViewById(R.id.weekGridView);
        String[] items  = new String[168];
        for ( int i = 0; i< 168; i++ )
        {
            items[i] = (String) "";
        }

        mAdapt = new ArrayAdapter<String>(
                getActivity(),
                R.layout.grid_item2,
                R.id.label,
                items);

        mGridview.setAdapter(mAdapt);
        mGridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) { //클릭한 위치를 가지고 해당 셀 뷰를 구해서 색상을 변경
                final String item = (String) mAdapt.getItem(position);
                if ( mSelectedCellView != null ) //이전에 선택된것을 하얀색으로(그리드 부분)
                {
                    mSelectedCellView.setBackgroundColor(Color.WHITE);
                }
                mSelectedCellView = view.findViewById(R.id.cell2); //지금 선택된거
                mSelectedCellView.setBackgroundColor(Color.CYAN);
                if ( mSelDayText != null ) //이전에 선택된것을 하얀색으로(날짜 부분)
                {
                    mSelDayText.setBackgroundColor(Color.WHITE);
                }
                mSelDayText = mTextViews.get(position % 7); //현재 선택된것을 찾아 색을 바꿈꿈
                mSelDayText.setBackgroundColor(Color.CYAN);

                // 클릭한 곳의 날짜 구하기
                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.YEAR, mSelYear);
                cal.set(Calendar.MONTH, mSelMonth);
                cal.set(Calendar.DATE, mSelDay);
                cal.add(Calendar.DATE, position % 7);

                int y = cal.get(Calendar.YEAR);
                int m = cal.get(Calendar.MONTH) + 1;
                int d = cal.get(Calendar.DATE);
                int h = position / 7; //position 0~6은 모두 0

                // 선택한 날짜를 저장
                MainActivity.setTime(y, m, d, h, 0);

                Toast.makeText(MainActivity.getAppContext(),
                        y + "." + m + "." + d + ". " + h + "시", Toast.LENGTH_SHORT).show();
            }
        });

        mTextViews = new ArrayList<TextView>();

        TextView text = rootView.findViewById(R.id.weekDay1);
        text.setText(mDays.get(0));
        mTextViews.add(text);
        text = rootView.findViewById(R.id.weekDay2);
        text.setText(mDays.get(1));
        mTextViews.add(text);
        text = rootView.findViewById(R.id.weekDay3);
        text.setText(mDays.get(2));
        mTextViews.add(text);
        text = rootView.findViewById(R.id.weekDay4);
        text.setText(mDays.get(3));
        mTextViews.add(text);
        text = rootView.findViewById(R.id.weekDay5);
        text.setText(mDays.get(4));
        mTextViews.add(text);
        text = rootView.findViewById(R.id.weekDay6);
        text.setText(mDays.get(5));
        mTextViews.add(text);
        text = rootView.findViewById(R.id.weekDay7);
        text.setText(mDays.get(6));
        mTextViews.add(text);

        return rootView;
    }
}