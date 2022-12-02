package com.example.category2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Category> arrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true); // 리사이클러뷰 성능강화
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>(); // Category 객체를 담을 어레이 리스트

        database = FirebaseDatabase.getInstance(); // 파이어베이스 데이터베이스 연동

        ArrayList arrayListCtype = new ArrayList<>(); // 스피너 카테고리 목록
        arrayListCtype.add("상의");
        arrayListCtype.add("하의");
        arrayListCtype.add("원피스");
        arrayListCtype.add("신발");
        arrayListCtype.add("모자");
        arrayListCtype.add("가방");
        arrayListCtype.add("장신구");

        ArrayList arrayListCcolor = new ArrayList<>(); // 스피너 색깔 목록
        arrayListCcolor.add("빨간색");
        arrayListCcolor.add("주황색");
        arrayListCcolor.add("노란색");
        arrayListCcolor.add("초록색");
        arrayListCcolor.add("파랑색");
        arrayListCcolor.add("남색");
        arrayListCcolor.add("보라색");

        ArrayList arrayListCSeason = new ArrayList<>(); // 스피너 Cseason 목록
        arrayListCSeason.add("하절기");
        arrayListCSeason.add("동절기");

        Spinner typeSpinner = (Spinner) findViewById(R.id.typeSpinner);
        Spinner colorSpinner = (Spinner) findViewById(R.id.colorSpinner);
        Spinner seasonSpinner = (Spinner) findViewById(R.id.seasonSpinner);

        ArrayAdapter arrayAdapterCtype = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, arrayListCtype);
        ArrayAdapter arrayAdapterCcolor = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, arrayListCcolor);
        ArrayAdapter arrayAdapterCseason = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, arrayListCSeason);

        typeSpinner.setAdapter(arrayAdapterCtype);
        colorSpinner.setAdapter(arrayAdapterCcolor);
        seasonSpinner.setAdapter(arrayAdapterCseason);


//        String typeInquiry = typeSpinner.getSelectedItem().toString();
//        String colorInquiry = colorSpinner.getSelectedItem().toString();
//        String seasonInquiry = seasonSpinner.getSelectedItem().toString();

        typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                //String type = arrayListCtype.get(position).toString();
                //Toast.makeText(MainActivity.this,"선택된 아이템: "+typeSpinner.getItemAtPosition(position),Toast.LENGTH_SHORT).show();
                String type = typeSpinner.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
    public void btnClicked(View v){ // 버튼 클릭시 실행 이벤트
        databaseReference = database.getReference("itemlist"); //DB테이블 연결
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                // 파이어베이스 데이터베이스의 데이터를 받아오는 곳
                arrayList.clear(); // 기존 배열리스트가 존재하지 않도록 초기화

                for (DataSnapshot snapshot : datasnapshot.getChildren()) { // 반복문으로 데이터 추출
                    Category category = snapshot.getValue(Category.class); // 만들어뒀던 Category 객체에 데이터를 담음

                    //Query에 해당하는 값 가져오기





                    //Query newPlaceDatabaseQuery = FirebaseDatabase.getInstance().getReference("itemlist").child("cType").limitToLast(5);
                    arrayList.add(category); // 담은 데이터들을 배열리스트에 넣고 리사이클러뷰로 보낼 준비
                }

                adapter.notifyDataSetChanged(); // 리스트 저장 및 새로고침

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // DB를 가져오던 중 에러 발생 시 띄울 문구
                Log.e("MainActivity", String.valueOf(databaseError.toException())); // 에러문 출력

            }

        });

        adapter = new CustomAdapter(arrayList, this);
        recyclerView.setAdapter(adapter); // 리사이클러뷰에 어댑터 연결

    }



}

