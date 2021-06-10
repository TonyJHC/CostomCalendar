package com.hansung.android.homework3_final_test;


import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;

import static java.lang.Integer.MAX_VALUE;

//FragmentStateAdapter를 재정의하여 몇 개의 페이지가 존재하며, 각 페이지를 나타내는 프레그먼트가 무엇인가를 정의
//viewpager2와 fragment를 연결하기 위해서는 Adapter가 필요하다 --> FragmentStateAdapter를 재정의
public class MonthCalendarAdapter extends FragmentStateAdapter {
    private static int NUM_ITEMS=100; //무한 스와이프

    static int year;
    int month;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public MonthCalendarAdapter(Fragment fa) {
        super(fa);
    }


    // 각 페이지를 나타내는 프래그먼트 반환
    // 여기에 알고리즘 ???
    // 여기서 프래그먼트를 만들어줘야됨(MonthCalendarFragement에 year랑 month를 파라미터로 제공하면 MonthCalenderFragment에서 프래그먼트를 만들어서 return)
    @Override
    public Fragment createFragment(int position){
//        MonthViewFragment monthFragment = new MonthViewFragment(); !!!

        System.out.println("createFragement ");
        // Bundle bundle = new Bundle();
        // position == 프레그먼트를 (어떠한 프레그먼트인지) 요청하는 index 넘버 ?
        //여기서부터 이제 프레그 먼트 만들어서 넘겨주면됨 (ex)50을 2021년 4월로 기준 잡고 밑으로 0~ 50은 무슨 달력인지 50 ~ 100까지는 무슨 달력인지)

         year =0; // position에  따라 year를 계산하는 식은 따로 정의( 알고리즘 짜야됨)
         month =0; //position에 따라 month를 계산 하는 식은 따로 정의 (지금 이것도 걍 임의로 해놓은 것)

        //이번달이 어느달인지에따라

        System.out.println("position"+position);
        //position정보를 가지고 year, month를 만들어줘야됨
        //postion :50 을 만약 2021년 5월로 설정하면 postion : 51이면 6월로 됨
        //position : 49 는 4월
        //MonthCalenderFragment 객체를 생성해서 6 * 7 grid뷰를 만든것을 넘겨 받음


        /*======================position정보에 따라 year, month 설정=====================*/
        //년도가 넘어 가는 position -> position은 100까지 : 58 , 70, 82, 94
        if(position >= 50) { //오른쪽으로 스와이프 했을때 ( position > 50)

                    if(position >= 50 && position <= 57){
                        year = Calendar.getInstance().get(Calendar.YEAR);
                        month = Calendar.getInstance().get(Calendar.MONTH) + position % 50; //현재 5월이면 month == 4 -> +1 해줘야됨
                    }
                    else {
                         if (position >= 58 && position <= 69) {
                            year = Calendar.getInstance().get(Calendar.YEAR) + 1;
                            month = position - 58;
                        } else if (position >= 70 && position <= 81) {
                            year = Calendar.getInstance().get(Calendar.YEAR) + 2;
                            month = position - 70;
                        } else if (position >= 82 && position <= 93) {
                            year = Calendar.getInstance().get(Calendar.YEAR) + 3;
                            month = position - 82;
                        } else if (position >= 94 && position <= 100) {
                            year = Calendar.getInstance().get(Calendar.YEAR) + 4;
                            month = position - 94;
                        }
                    }
        }
        if(position < 50) { //오른쪽으로 스와이프 했을때 ( position > 50)

            if(position <= 49 && position >= 46){
                year = Calendar.getInstance().get(Calendar.YEAR);
                month = Calendar.getInstance().get(Calendar.MONTH) + position - 50; //현재 5월이면 month == 4 -> +1 해줘야됨
            }
            else {
                if (position <=45 && position >= 34) {
                    year = Calendar.getInstance().get(Calendar.YEAR) -1;
                    month = position - 34 ;
                } else if (position <= 33 && position >= 22) {
                    year = Calendar.getInstance().get(Calendar.YEAR) - 2;
                    month = position - 22;
                } else if (position <= 21 && position >= 10) {
                    year = Calendar.getInstance().get(Calendar.YEAR) - 3;
                    month = position - 10;
                } else if (position <= 9 && position >= 1) {
                    year = Calendar.getInstance().get(Calendar.YEAR) - 4;
                    month = position + 2;
                }
            }
        }
        /*=========================================================================*/

        //MonthCalenderFragment는 year와 month에 해당되는 월간 달력(MonthCalenderFragment)을 만들어서 return해준다
        return MonthCalenderFragment.newInstance(year,month);  //달에 따라 year, month 변경내용 newInstance()에 파라미터로 넘김


    }


    // 전체 페이지 개수 반환ㄴㄴ
   @Override
    public int getItemCount(){
        return NUM_ITEMS; // 무한 스와이프
    }
}
