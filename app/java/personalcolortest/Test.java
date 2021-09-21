package com.example.personalcolortest;

import android.app.Activity;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import org.w3c.dom.Text;

//퍼스널 컬러 테스트 화면

public class Test extends Activity {

    //구성에 필요한 뷰 변수들 설정
    ImageView fView;
    TextView nView,qView,tView;
    Button back, next,result;
    RadioGroup sView;
    static int select_num = 0;


    //문제에 따라 보여질 이미지가 다르다.
    Integer[] faces = {
            R.drawable.face_1,
            R.drawable.face_2,
            R.drawable.face_3,
            R.drawable.face_4,
            R.drawable.face_5,
            R.drawable.face_6,
            R.drawable.face_7,
            R.drawable.face_8,
            R.drawable.face_9
    };

    //문제에 따라서 보여질 컬러들이 다르고, 안보여지는 것들(보여줄 필요가 없는 것)도 있음
    int color (String color){
        return Color.parseColor(color);
    }
    void setViewColor(int n){
        View v1_1 = (View) findViewById(R.id.v1_1);
        View v1_2 = (View) findViewById(R.id.v1_2);
        View v2_1 = (View) findViewById(R.id.v2_1);
        View v2_2 = (View) findViewById(R.id.v2_2);
        View v3_1 = (View) findViewById(R.id.v3_1);
        View v3_2 = (View) findViewById(R.id.v3_2);

        LinearLayout l3 = (LinearLayout)findViewById(R.id.l3);

        LinearLayout l = (LinearLayout)findViewById(R.id.select_layout);

        if(n == 1){
            v1_1.setBackgroundColor(color("#ECD2BD"));
            v1_2.setBackgroundColor(color("#EACFBC"));
            v2_1.setBackgroundColor(color("#E8C8BF"));
            v2_2.setBackgroundColor(color("#E6C4BD"));
            l3.setVisibility(View.GONE);
            l.setVisibility(View.VISIBLE);
        }
        else if(n == 4){
            v1_1.setBackgroundColor(color("#69522C"));
            v1_2.setBackgroundColor(color("#674A32"));
            v2_1.setBackgroundColor(color("#4B3220"));
            v2_2.setBackgroundColor(color("#4A3D29"));
            v3_1.setBackgroundColor(color("#30302C"));
            v3_2.setBackgroundColor(color("#010101"));
            l3.setVisibility(View.VISIBLE);
            l.setVisibility(View.VISIBLE);

        }
        else if(n == 5){
            v1_1.setBackgroundColor(color("#513918"));
            v1_2.setBackgroundColor(color("#50331B"));
            v2_1.setBackgroundColor(color("#4E1E21"));
            v2_2.setBackgroundColor(color("#4D1623"));
            l3.setVisibility(View.GONE);
            l.setVisibility(View.VISIBLE);
        }
        else if(n == 6){
            v1_1.setBackgroundColor(color("#ECD2BD"));
            v1_2.setBackgroundColor(color("#E8C7BC"));
            v2_1.setBackgroundColor(color("#D1AF9C"));
            v2_2.setBackgroundColor(color("#CFA99E"));
            v3_1.setBackgroundColor(color("#BA967F"));
            v3_2.setBackgroundColor(color("#B88A81"));
            l3.setVisibility(View.VISIBLE);
            l.setVisibility(View.VISIBLE);
        }
        else{
            l.setVisibility(View.GONE);
        }

    }


    //문제 번호 초기 설정
    static int num = 1;
    //선택 값 초기 설정
    static int[] color_test = {-1,-1,-1,-1,-1,-1,-1,-1,-1};

    //결과를 도출할 때 결괏값을 산정해주는 함수
    Integer myTone (int[] test){

        String tone ="";

        int warm = 0, cool = 0;
        int check = 0;
        int result;

        for (int i = 0 ; i<test.length;i++){
            if(i==3||i==5){
                check+=test[i];
            }
            else{
                if(test[i]==0)
                    warm++;
                else
                    cool++;
            }
        }

        if(warm>cool){
            tone="웜톤";
            if(check>=2) {
                tone = "가을 " + tone;
                result = 2;
            }
            else {
                tone = "봄 " + tone;
                result = 0;
            }
        }
        else{
            tone="쿨톤";
            if(check>=2) {
                tone = "겨울 " + tone;
                result = 3;
            }
            else{
                tone="여름 "+tone;
            result = 1;}
        }

        return result;
    }


    //처음 테스트 화면을 실행 시 보여질 화면
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        //화면의 구성에 필요한 뷰의 id들을 불러온다.
        fView = (ImageView) findViewById(R.id.face);
        nView = (TextView) findViewById(R.id.num);
        qView = (TextView) findViewById(R.id.question);
        tView = (TextView) findViewById(R.id.tip);
        sView = (RadioGroup) findViewById(R.id.selection);
        result = (Button) findViewById(R.id.result);



        // 문제에 필요한 내용들을 strings.xml을 참조하여 불러왔음.
        // 문제의 번호에 따라 내용이 달라진다.
        final String[] qArray = getResources().getStringArray(R.array.question);
        final String[] tArray = getResources().getStringArray(R.array.tip);
        final String[][] sArray = {
                getResources().getStringArray(R.array.a1),
                getResources().getStringArray(R.array.a2),
                getResources().getStringArray(R.array.a3),
                getResources().getStringArray(R.array.a4),
                getResources().getStringArray(R.array.a5),
                getResources().getStringArray(R.array.a6),
                getResources().getStringArray(R.array.a7),
                getResources().getStringArray(R.array.a8),
                getResources().getStringArray(R.array.a9)
        };

        //1번 문제 (초기 문제 설정)
        back = (Button) findViewById(R.id.back);
        next = (Button) findViewById(R.id.next);
        nView.setText("(" + num + " / 9)");
        fView.setImageResource(faces[num - 1]);
        qView.setText(qArray[num - 1]);
        tView.setText(tArray[num - 1]);
        RadioButton radioButton;

        //문항수대로 라디오 버튼 추가
        for (int i = 0; i < sArray[num - 1].length; i++) {
            radioButton = new RadioButton(Test.this);
            radioButton.setId(i);
            radioButton.setText(sArray[num - 1][i]);
            radioButton.setPadding(0,15,0,15);
            sView.addView(radioButton);
        }
        //문제에 따라 보여질 컬러 view들이 다르다.
        setViewColor(num);

        back.setVisibility(View.INVISIBLE);
        result.setVisibility(View.INVISIBLE);


        // 선택한 옵션을 문제-1 과 같은 color_text의 인덱스에 넣는다.
        sView.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup r, int i) {
                if (i == -1)
                    return;
                else {
                    color_test[num - 1] = i;
                }

            }
        });

        //다음 문제로 넘어가는 버튼
        next.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                //선택한 옵션이 있을 때
                if (color_test[num - 1] != -1) {

                    back.setVisibility(View.VISIBLE);

                    //문제 번호, 번호에 따른 질문, 내용이 바뀜
                    sView.removeAllViewsInLayout();
                    sView.clearCheck();

                    num++;
                    nView.setText("(" + num + " / 9)");
                    fView.setImageResource(faces[num - 1]);
                    qView.setText(qArray[num - 1]);
                    tView.setText(tArray[num - 1]);

                    for (int i = 0; i < sArray[num - 1].length; i++) {
                        RadioButton radioButton = new RadioButton(Test.this);
                        radioButton.setId(i);
                        radioButton.setText(sArray[num - 1][i]);
                        radioButton.setPadding(0,15,0,15);
                        sView.addView(radioButton);
                    }
                    setViewColor(num);

                    if (color_test[num - 1] != -1)
                        sView.check(color_test[num - 1]);

                }

                //선택한 옵션이 없을 때
                else {
                    Toast.makeText(Test.this, "선택된 옵션이 없어요. 선택해주세요!", Toast.LENGTH_LONG).show();
                }

                if(num==9) {
                    //다음으로 넘어가는 버튼 실행 안됨
                    //결과를 받아볼 수 있음
                    next.setVisibility(View.INVISIBLE);
                    result.setVisibility(View.VISIBLE);
                }

            }
        });

        //뒤로 가기 버튼
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                next.setVisibility(View.VISIBLE);
                result.setVisibility(View.INVISIBLE);

                    //문제 번호, 번호에 따른 질문, 내용이 바뀜
                    sView.removeAllViewsInLayout();
                    sView.clearCheck();

                    num--;
                    nView.setText("(" + num + " / 9)");
                    fView.setImageResource(faces[num - 1]);
                    qView.setText(qArray[num - 1]);
                    tView.setText(tArray[num - 1]);

                    for (int i = 0; i < sArray[num - 1].length; i++) {
                        RadioButton radioButton = new RadioButton(Test.this);
                        radioButton.setId(i);
                        radioButton.setText(sArray[num - 1][i]);
                        radioButton.setPadding(0,15,0,15);
                        sView.addView(radioButton);
                    }

                    setViewColor(num);

                    sView.check(color_test[num - 1]);


                //다음으로 넘어가는 버튼 실행 안됨
                //결과를 받아볼 수 있음
                if (num == 1) {
                    back.setVisibility(View.INVISIBLE);
                }


            }

        });

    }

    //뒤로 가기 키를 눌러 일시정지 되었을 때도, 초기 화면으로 돌아가게 해줌
    @Override
    protected void onPause() {
        super.onPause();
        num = 1;

        for(int i=0;i<color_test.length;i++)
            color_test[i]=-1;
    }

    public void result (View v){

        //결과 화면이 뜨게 해줌.
        if(color_test[num-1] !=-1) {
            Intent i = new Intent(Test.this, Result.class);
            i.putExtra("RESULT", myTone(color_test));
            int end = 0;

            startActivity(i);

            //변수 초기 설정
            num = 1;

            for (int k = 0; k < color_test.length; k++)
                color_test[k] = -1;

            finish();
        }
        //선택 된 옵션이 없을 때
        else{
            Toast.makeText(Test.this,"선택된 옵션이 없어요. 선택해주세요!",Toast.LENGTH_LONG).show();
        }

    }



    //강제 종료 시 초기화면 설정
    public void close (View v){

        num = 1;

        for(int i=0;i<color_test.length;i++)
            color_test[i]=-1;

        finish();

    }
}