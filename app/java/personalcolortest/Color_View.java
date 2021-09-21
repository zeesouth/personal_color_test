package com.example.personalcolortest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import static android.graphics.Canvas.*;
import static java.lang.Thread.sleep;


// 퍼스널컬러 타입에 따라 보여질 색깔들이 다른 클래스
public class Color_View extends View {


    int select;

    public Color_View(Context context){
        super(context);
    }

    public Color_View(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    int i = 0;
    int start = 15;


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        int garo = 80;

        // 각각의 컬러들이 그려질 형태와 위치 선정
        for(int j=0;j<i;j++) {
            canvas.drawRect(start, 0, start + garo, canvas.getHeight(), select_color[select][j]);
            start+=garo;
        }

        i++;
        start=15;

        // 13가지의 컬러들이 다 그려지면, 처음부터 다시 그려야 하므로
        // 원래 값으로 설정해준다.
        if(i>13){
            i=0;
        }

        //0.3초마다 컬러들이 점층적으로 쌓임
        try {
            sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        invalidate();
    }

    // 그려질 색을 선정해주는 함수
    Paint makeColor(int color){
        Paint p = new Paint();
        p.setColor(color);
        return p;
    }


    Paint[][] select_color ={ {

            // 봄 타입
            makeColor(Color.parseColor("#DF2D22")),
            makeColor(Color.parseColor("#FD5D10")),
            makeColor(Color.parseColor("#EE8654")),
            makeColor(Color.parseColor("#FAD9C9")),
            makeColor(Color.parseColor("#F1F6B3")),
            makeColor(Color.parseColor("#AAD126")),
            makeColor(Color.parseColor("#A7D615")),
            makeColor(Color.parseColor("#94DEA0")),
            makeColor(Color.parseColor("#5CB877")),
            makeColor(Color.parseColor("#308E27")),
            makeColor(Color.parseColor("#BEEDD9")),
            makeColor(Color.parseColor("#64C2CC")),
            makeColor(Color.parseColor("#306E9F"))
    },
            // 여름 타입
            {
            makeColor(Color.parseColor("#E475A2")),
            makeColor(Color.parseColor("#E9A0BE")),
            makeColor(Color.parseColor("#F3C9E1")),
            makeColor(Color.parseColor("#A97FD4")),
            makeColor(Color.parseColor("#C7B0DC")),
            makeColor(Color.parseColor("#E1DAF5")),
            makeColor(Color.parseColor("#D2E1FA")),
            makeColor(Color.parseColor("#A4C1FA")),
            makeColor(Color.parseColor("#7094EF")),
            makeColor(Color.parseColor("#4D6DAF")),
            makeColor(Color.parseColor("#CBE9F5")),
            makeColor(Color.parseColor("#BBEFDA")),
            makeColor(Color.parseColor("#F3F4B7"))
    },
            // 가을 타입
            {
            makeColor(Color.parseColor("#9A2D27")),
            makeColor(Color.parseColor("#963024")),
            makeColor(Color.parseColor("#EE8652")),
            makeColor(Color.parseColor("#F8CAAA")),
            makeColor(Color.parseColor("#DFBB00")),
            makeColor(Color.parseColor("#A0761C")),
            makeColor(Color.parseColor("#55771A")),
            makeColor(Color.parseColor("#98DCA2")),
            makeColor(Color.parseColor("#8B7650")),
            makeColor(Color.parseColor("#3B2917")),
            makeColor(Color.parseColor("#659091")),
            makeColor(Color.parseColor("#7FC8D1")),
            makeColor(Color.parseColor("#185678"))
    },
            // 겨울 타입
            {
            makeColor(Color.parseColor("#DD334C")),
            makeColor(Color.parseColor("#D789AA")),
            makeColor(Color.parseColor("#872E58")),
            makeColor(Color.parseColor("#2F1452")),
            makeColor(Color.parseColor("#5C2F9B")),
            makeColor(Color.parseColor("#2C1413")),
            makeColor(Color.parseColor("#8C7A78")),
            makeColor(Color.parseColor("#143CB5")),
            makeColor(Color.parseColor("#0E1A2E")),
            makeColor(Color.parseColor("#306DA1")),
            makeColor(Color.parseColor("#B9BFBE")),
            makeColor(Color.parseColor("#165A68")),
            makeColor(Color.parseColor("#132B32"))
    }
    };





}
