package com.hansung.android.homework3_final_test;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;

import static com.hansung.android.homework3_final_test.MainActivity.mContext;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MonthCalenderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

//view pager에서 페이지가 교체될때마다 프래그먼트를 만드는데 그 만들어진 프래그먼트가 monthCalenadarFragment
//MonthViewFragment는 월간 달력 만들어줘야됨
public class MonthCalenderFragment extends Fragment {


    int year;
    int month;
    int today_of_week; // 오늘이 무슨 요일인지
    int lastDay; //해당 달의 마지막 일수
    int firstDay_of_month; // 해당 달의 1일의 요일



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private int mParam1;
    private int mParam2;

    View mView = null;

    public MonthCalenderFragment() {
        // Required empty public constructor
    }




    //MonthCalendarAdapter 에서 newInstance 호출하여 파라미터 전달
    public static MonthCalenderFragment newInstance(int year, int month) { // Adapter에서 newInstance를 호출해서 변경된 year, month값을 전달해줌
        MonthCalenderFragment fragment = new MonthCalenderFragment(); // 프래그먼트 생성
        Bundle args = new Bundle();

        // MonthCalendarAdapter에서 전달된 year, month를 bundle 객체에 넣기
        args.putInt(ARG_PARAM1, year);
        args.putInt(ARG_PARAM2, month);

        fragment.setArguments(args);
        return fragment; // Adapter에 onCreateView에서 만든 gridview 달력을 넘겨줌
    }


    //프래그먼트가 생성되면 최초로 onCreate함수를 호출
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
             //newInstance함수로 받아온 year, month값을 번틀 객체에
            year = getArguments().getInt(ARG_PARAM1,-99); // getArguments로 번들객체를 불러옴
            month = getArguments().getInt(ARG_PARAM2,-99);
        }


    }


    //onCreateView함수에서 캘린더 화면을 만들어서 넘겨줘야됨
    //Adapter에서 MonthCalenderFragment.newInstance(year,month); 를 호출해서 newInstance에서 MonthCalenderFragment를 생성하면
    //onCreateView 함수가 호출되면서 해당 함수에서 6 * 7 gridview를 만들어 데이터를 적절한 알고리즘을 통해 넣어줘야함
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //girdview를 설정하는것을 만들어주면 됨( 알고리즘 ) --> gridview에 연결될 어댑터를 설정해줘야됨
        //adapter에 표시될 데이터원본을 어댑터에 연결해줘야됨
        View rootView = inflater.inflate(R.layout.fragment_month_calender, container, false);//view 객체 만들어서

        /// TextView tx = rootView.findViewById(R.id.year_month);
        //tx.setText(year+"년 "+month+"월 달력");


        //여기서 에러 남 month가 12월 이상으로 나옴
        //***********************************************************************
        // ***********************************************************************
    //***********************************************************************
    //**********************************에러에러에러에러*************************************
    //***********************************************************************
    //***********************************************************************
        if (month >= 12) {  //12월이 넘어가면 13 14 .. 가 되는것을 방지

            System.out.println("초기 month"+month);
            System.out.println("초기 month"+month);
            System.out.println("초기 month"+month);
            System.out.println("초기 month"+month);
            month = 1;
        }

        if (month == -1) {
            month = 11;
        }

        ///if (year == -99 || month == -99 ){  //처음 실행했을때만
            //현재 년도와 월을 알아보기
            //해당 되는 월의 마지막날은 언제인지도 필요함 (인터넷)
            //Calendar.getInstance()  : 현재 시간 저장
           // year = Calendar.getInstance().get(Calendar.YEAR);  //Calender 객체를 return : 호출 시점의 년도
           // month = Calendar.getInstance().get(Calendar.MONTH);  //Calender 객체를 return : 호출 시점의 월 --> 월 정보는 0~ 11까지 나옴 따라서 +1
            //lastDay = Calendar.getInstance().get(Calendar.DATE); //이번달 마지막일수
            //오늘이 무슨 요일인지 return : ex) 3월 31일이
            //today_of_week = Calendar.getInstance().get(Calendar.DAY_OF_WEEK); //일요일:1 ~ 토요일 : 7
            //이번달의 마지막 일 구하기
           // lastDay = Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH); //이번달 일 수
       // }


        //해당 달의 첫 일을 구하기 위한 Calender 객체 생성
        Calendar cal = Calendar.getInstance();
        // 객체 초기 세팅
        cal.set(Calendar.YEAR,year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DATE,1);
        int tmp = cal.get(Calendar.DAY_OF_WEEK); // 1일이 무슨 요일인지 임시로 저장

        //1일의 요일 구하기
        if ( tmp == 1)  //일요일
            firstDay_of_month = 0;
        else if ( tmp == 2) //월요일
            firstDay_of_month = 1;
        else if ( tmp == 3) //화요일
            firstDay_of_month = 2;
        else if ( tmp == 4) //수요일
            firstDay_of_month = 3;
        else if ( tmp == 5) //목요일
            firstDay_of_month = 4;
        else if ( tmp == 6) //금요일
            firstDay_of_month = 5;
        else if ( tmp == 7) //토요일
            firstDay_of_month = 6;


        lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);  //이번달 일 수
        lastDay += 7; //6 x 7그리드뷰의 빈공간이 없도록
        System.out.println("lastDay"+lastDay);


        //데이터 원본 준비 (Adapter에 연결할)
        String[] items  = new String[lastDay]; //마지막 날짜를 받아와서 인덱스로
        int j = 0;
        //---------------데이터(items 스트링 배열) 구축 알고리즘 -------------------
        //첫주에대한 들여쓰기(1일의 요일에 따라)
        for(int i = 0; i< firstDay_of_month; i++){ //월요일 : firstDay_of_week == 1
            items[i] = (String) "";
            ++j ;
        }

        //날짜 채우기
        int k = lastDay-7+j ; // 들여쓰기 만큼 더해주기
        for(int i = j; i < k; i++){ //들여쓰기 이후부터 날짜 채우기
            items[i] = (String) (i-j+1 + "");
        }

        for(int i = k; i < lastDay; i++  ){
            items[i] = (String) "";
        }


        //어댑터와 어댑터뷰(GridView)를 연결
        //id를 바탕으로 화면 레이아웃에 정의된 GridView 객체 로딩
        GridView gridview = (GridView) rootView.findViewById(R.id.gridview);
        ArrayAdapter<String> adapt
                = new ArrayAdapter<String>(
                getActivity(),
                R.layout.grid_item,
                R.id.label,
                items);


        //어댑터를 GridView 객체에 연결
        gridview.setAdapter(adapt);


        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.P)
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final String item = (String) adapt.getItem(position);
                if ( mView != null )
                {
                    mView.setBackgroundColor(Color.WHITE);
                }
                mView = view.findViewById(R.id.cell);
                mView.setBackgroundColor(Color.CYAN);

                if ( item.length() > 0 ) {
                    // 선택한 날짜를 저장
                    MainActivity.setTime(year, month + 1, Integer.parseInt(item), 12, 0);
                }

                Toast.makeText(MainActivity.getAppContext(), year + "." + (month + 1) + "." + item, Toast.LENGTH_SHORT).show();

            }
        });





        //버튼과  년월 텍스트를 id로 참조
        //TextView yearMonth = rootView.findViewById(R.id.year_month);
        //yearMonth.setText(year +"년 " + (month+1) + "월"); //text뷰 컨텐츠 내용을 실행 시 해당 내용으로 변경 해줌 --> 현재 년 월 을 알아야 함

        //onAttach() --> 프래그먼트가 액티비티에 연결될 때 호출됨
        //해당 Fragment와 연결된 액티비티의 reference 가져오기 -->어댑터에 연결하기 위함





        // Inflate the layout for this fragment
        return rootView;//넘겨줌 //inflater.inflate(R.layout.fragment_month_calender, container, false);
    }


}