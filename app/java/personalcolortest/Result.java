package com.example.personalcolortest;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

//검사 결과를 알려주는 함수
public class Result extends AppCompatActivity {

    Color_View color_view;
    TextView ques;
    private TextView type, text;
    private Button close;
    private Button send;


    String color_type="";



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);
        setTitle("RESULT");

        color_view = (Color_View) findViewById(R.id.color_View); //결과에 따른 컬러 표시
        final String[] l_text = getResources().getStringArray(R.array.color); //결과에 따른 컬러타입에 대한 설명
        type = (TextView) findViewById(R.id.type); //타입에 대한 텍스트뷰
        text = (TextView) findViewById(R.id.text); //타입 설명에 대한 텍스트뷰
        close = (Button) findViewById(R.id.close); //닫기버튼

        ques = (TextView) findViewById(R.id.ques); //보낼건지 여부를 물음
        send = (Button) findViewById(R.id.insert); //보내기 버튼

        //결과값을 전달하는 인텐트
        Intent in = getIntent();
        int id = in.getIntExtra("RESULT",4);

        //타입에 따라 다르게 나타남
        if(id == 0) {
            type.setText("봄 (Spring) Type");
            type.setTextColor(android.graphics.Color.parseColor("#DCBCAF"));
            text.setText(l_text[0]);
            color_view.select = 0;
            ques.setText("당신은 봄 웜톤이에요 ! 결과를 등록하시겠어요?");
            color_type = "봄 웜톤";
        }
        else if( id==1){
            type.setText("여름 (Summer) Type");
            type.setTextColor(android.graphics.Color.parseColor("#5FA5A7"));
            text.setText(l_text[1]);
            color_view.select = 1;
            ques.setText("당신은 여름 쿨톤이에요 ! 결과를 등록하시겠어요?");
            color_type = "여름 쿨톤";
        }
        else if( id==2){
            type.setText("가을 (Autumn) Type");
            type.setTextColor(android.graphics.Color.parseColor("#A15942"));
            text.setText(l_text[2]);
            color_view.select = 2;
            ques.setText("당신은 가을 웜톤이에요 ! 결과를 등록하시겠어요?");
            color_type="가을 웜톤";
        }
        else if( id==3){
            type.setText("겨울 (Winter) Type");
            type.setTextColor(android.graphics.Color.parseColor("#492053"));
            text.setText(l_text[3]);
            color_view.select = 3;
            ques.setText("당신은 겨울 쿨톤이에요 ! 결과를 등록하시겠어요?");
            color_type="겨울 쿨톤";
        }

        //보내기 버튼을 눌렀을때, 컬러 타입을 Send클래스로 전달
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Result.this,Send.class);
                i.putExtra("COLOR",color_type);
                startActivity(i);

                finish();
            }
        });




    }



    public void close (View v){
        finish();
    }

}
