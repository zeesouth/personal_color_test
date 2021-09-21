package com.example.personalcolortest;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

//퍼스널 컬러에 대한 정의를 알려주는 액티비티
public class What extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.what_is);
        setTitle("What is 'Personal Color?'");

    }

    //퍼스널 컬러를 종류별로 알게 해주는 액티비티에 들어가게 하는 함수
    public void search(View v){
        Intent i = new Intent (What.this, Color.class);
        startActivity(i);
        finish();
    }

    public void home(View v){
        finish();
    }

}
