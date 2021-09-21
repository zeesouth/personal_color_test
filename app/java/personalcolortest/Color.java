package com.example.personalcolortest;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.ColorSpace;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Color extends AppCompatActivity {

    // 이용할 변수들 참조
    private TextView type, text;
    private Button close;
    Color_View color_view;


    // 컬러 화면 초기 설정 - 첫 화면은 봄타입으로 보여줌
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_color_type);

        final String[] l_text = getResources().getStringArray(R.array.color); //컬러타입 설명
        type = (TextView) findViewById(R.id.type); //컬러 타입
        text = (TextView) findViewById(R.id.text); //컬러타입 설명을 보여줄 텍스트뷰
        color_view = (Color_View) findViewById(R.id.color_View); //컬러타입 변수 설정

        setTitle("Spring");
        type.setText("봄 (Spring) Type");
        type.setTextColor(android.graphics.Color.parseColor("#DCBCAF"));
        text.setText(l_text[0]);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    //메뉴 선택 항목에 따라서 컬러타입을 알 수 있음
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        final String[] l_text = getResources().getStringArray(R.array.color);

        switch(item.getItemId())
        {
            // 봄 타입을 선택했을때
            case R.id.spring:
                setTitle("Spring");
                type.setText("봄 (Spring) Type");
                type.setTextColor(android.graphics.Color.parseColor("#DCBCAF"));
                text.setText(l_text[0]);
                color_view.select = 0;

                return true;

            // 여름 타입을 선택했을때
            case R.id.summer:
                setTitle("Summer");
                type.setText("여름 (Summer) Type");
                type.setTextColor(android.graphics.Color.parseColor("#5FA5A7"));
                text.setText(l_text[1]);
                color_view.select = 1;

                return true;

            // 가을 타입을 선택했을때
            case R.id.fall:
                setTitle("Autumn");
                type.setText("가을 (Autumn) Type");
                type.setTextColor(android.graphics.Color.parseColor("#A15942"));
                text.setText(l_text[2]);
                color_view.select = 2;

                return true;

            // 겨울 타입을 선택했을때
            case R.id.winter:
                setTitle("Winter");
                type.setText("겨울 (Winter) Type");
                type.setTextColor(android.graphics.Color.parseColor("#492053"));
                text.setText(l_text[3]);
                color_view.select = 3;

                return true;
            default:
                return super.onOptionsItemSelected(item);}
    }

    //닫기버튼을 눌렀을 때
    //메인화면으로 돌아감
    public void close(View v){
        finish();
    }



}
