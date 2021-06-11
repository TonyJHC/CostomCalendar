package com.hansung.android.homework3_final_test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
<<<<<<< Updated upstream
=======
import android.util.Log;
>>>>>>> Stashed changes
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;
import java.util.Locale;

public class MainActivity2 extends AppCompatActivity implements OnMapReadyCallback {

    private FusedLocationProviderClient mFusedLocationClient; //통합 위치 정보 제공자 (Fused Location Provider) 클라이언트 객체를 얻어온다.
<<<<<<< Updated upstream
    private GoogleMap googleMap;
=======
    private GoogleMap mGoogleMap = null;
    private Geocoder geocoder; //주소를 위도,경도로 변경해주는 Geocoding
    private Button btn;
    private EditText edt;

>>>>>>> Stashed changes

    private int mYear;
    private int mMonth;
    private int mDay;
    private int mHour;
    private int mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        setTitle("Calender App");

<<<<<<< Updated upstream
        //https://developer88.tistory.com/39 timepicker 설정하는법

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

=======
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
>>>>>>> Stashed changes
        getLastLocation();

        //MainActivity에서 날짜를 넘겨받음
        Bundle extras = getIntent().getExtras();
        mYear = extras.getInt("Year");
        mMonth = extras.getInt("Month");
        mDay = extras.getInt("Day");
        mHour = extras.getInt("Hour");
        mMinute = extras.getInt("Minute");

        //선택된 날짜와 시간을 제목에 표시
        TextView text = findViewById(R.id.date);
        text.setText(mYear + "년 " + mMonth + "월 " + mDay + "일 " + mHour + "시");

        //타임피커를 선택한 시간으로 설정
<<<<<<< Updated upstream
=======
        //출처: https://developer88.tistory.com/39 timepicker 설정하는법
>>>>>>> Stashed changes
        TimePicker tp = findViewById(R.id.time_picker1);
        tp.setCurrentHour(mHour);
        tp.setCurrentMinute(mMinute);

        tp = findViewById(R.id.time_picker2);
        tp.setCurrentHour(mHour+1);
        tp.setCurrentMinute(mMinute);
<<<<<<< Updated upstream
=======


        //위치정보를 얻기위한 권한을 획득, AndroidManifest.xml
        //INTERNET : 구글서버에 접근하기위해서 필요함

        btn = (Button)findViewById(R.id.find);
        edt = (EditText)findViewById(R.id.input);

        geocoder = new Geocoder(this);

        //주소를 위도,경도로 변경해주는 Geocoding을 이용하여 변경된 위도,경도를 지도에 띄움
        //출처: https://bitsoul.tistory.com/135
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //주소를 입력후 찾기버튼 클릭시 해당 위도,경도를 지도에 띄움
                List<Address> list = null;

                String str = edt.getText().toString(); 
                try {
                    list = geocoder.getFromLocationName
                            (str, //지역 이름
                                    10); //읽을 개수
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("test","입출력 오류 - 서버에서 주소변환시 에러발생");
                }

                if (list != null) {
                    Address addr = list.get(0);
                    double lat = addr.getLatitude();
                    double lon = addr.getLongitude();

                    LatLng location2 = new LatLng(lat, lon);

                    mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location2,15));
                    mGoogleMap.addMarker(new MarkerOptions().position(location2));
                }

            }
        });
>>>>>>> Stashed changes
    }

    final int REQUEST_PERMISSIONS_FOR_LAST_KNOWN_LOCATION = 0;
    Location mLastLocation;

<<<<<<< Updated upstream
=======

>>>>>>> Stashed changes
    private void getLastLocation() {
        // 1. 위치 접근에 필요한 권한 검사 및 요청
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    MainActivity2.this,            // MainActivity 액티비티의 객체 인스턴스를 나타냄
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},        // 요청할 권한 목록을 설정한 String 배열
                    REQUEST_PERMISSIONS_FOR_LAST_KNOWN_LOCATION    // 사용자 정의 int 상수. 권한 요청 결과를 받을 때
            );
            return;
        }

        // 2. Task<Location> 객체 반환
        Task task = mFusedLocationClient.getLastLocation();

        // 3. Task가 성공적으로 완료 후 호출되는 OnSuccessListener 등록
        task.addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                // 4. 마지막으로 알려진 위치(location 객체)를 얻음.
                if (location != null) {
                    mLastLocation = location;
                    //updateUI();
                } else
                    Toast.makeText(getApplicationContext(),
                            "No location detected",
                            Toast.LENGTH_SHORT)
                            .show();

                SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.map);
                mapFragment.getMapAsync(MainActivity2.this);
            }
        });
    }


<<<<<<< Updated upstream
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {


=======


    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mGoogleMap = googleMap; //위에 moveCamera랑 겹쳐서 오류가 뜨는것을 방지
>>>>>>> Stashed changes
        //LatLng hansung = new LatLng(37.5817891, 127.008175);
        if (mLastLocation == null)
            return;
        LatLng location = new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude());
        googleMap.addMarker(new MarkerOptions().position(location).title("한성대학교"));
        // move the camera
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15));

<<<<<<< Updated upstream

=======
>>>>>>> Stashed changes
    }

}








