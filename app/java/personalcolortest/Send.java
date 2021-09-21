package com.example.personalcolortest;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.GregorianCalendar;

//보내기 전에 이름을 입력받아야 하는 클래스 (액티비티 화면)
public class Send extends AppCompatActivity {

    Data data;
    SQLiteDatabase db;

    EditText name;
    TextView tone;

    ProgressDialog progress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.send);
        setTitle("이름을 입력하세요");
        data = new Data(Send.this);
        db = data.getWritableDatabase();
        progress = new ProgressDialog(this);

        //컬러 타입을 Result클래스에서 받아옴
        Intent i = getIntent();
        final String c = i.getStringExtra("COLOR");

        name = (EditText) findViewById(R.id.name); //사용자 이름을 입력받을 에디트텍스트뷰
        tone = (TextView) findViewById(R.id.tone); //컬러 타입을 보여줄 텍스트뷰

        Button ok = (Button) findViewById(R.id.ok); //입력 완료
        Button no = (Button) findViewById(R.id.no); //입력 취소

        tone.setText(c); //받아온 컬러타입을 보여줌

        //입력 완료 눌렀을 때
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String n = name.getText().toString(); //사용자 이름
                String t = tone.getText().toString(); //컬러 타입

                //데이터 저장
                db.execSQL("INSERT INTO colors VALUES (null, '"+n+"', '"+t+"')");

                //입력이 완료되면 로딩창이 뜸
                progress.setCancelable(true);
                progress.setMessage("결과 처리중입니다.");
                progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progress.setProgress(0);
                progress.setMax(100);
                progress.show();

                final Thread tt = new Thread() {

                    public void run () {

                        int time = 0;

                        while(time<100){
                            try{
                                sleep(200);
                                time+=5;
                                progress.setProgress(time);
                            }
                            catch(InterruptedException e){
                                e.printStackTrace();
                            }

                        }



                    }


                };
                tt.start();

                //등룍이 완료된 토스트 메시지
                Toast.makeText(getApplicationContext(),"등록 완료",Toast.LENGTH_SHORT).show();

                //메인 화면으로 돌아감
                finish();

            }
        });

        //취소 버튼을 눌렀을때
        //메인 화면으로 돌아감
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }




}
