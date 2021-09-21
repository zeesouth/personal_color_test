package com.example.personalcolortest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.BatteryManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

//시작 화면
public class MainActivity extends AppCompatActivity {

    //배경음악 태그
    private static final String TAG = "MusicService";
    //배터리 잔량에 관련된 텍스트뷰

    TextView textfield;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startService(new Intent(this,MusicService.class));
        textfield = (TextView) findViewById(R.id.textfield);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // 배터리 잔량 & sd카드 연결 상태에 관한 인텐트 필터
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_BATTERY_CHANGED);
        filter.addAction(Intent.ACTION_BATTERY_LOW);
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        filter.addAction(Intent.ACTION_POWER_CONNECTED);
        filter.addAction(Intent.ACTION_MEDIA_MOUNTED);
        filter.addAction(Intent.ACTION_MEDIA_REMOVED);

        // 방송 수수신자 등록
        registerReceiver(receiver, filter);

    }

    //어플이 종료되면 배경음악 종료
    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(new Intent(this,MusicService.class));
    }

    //어플이 배경 밖에 있으면 방송수신자 종료
    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }



    public void onClick(View view) {
        Intent intent =null;
        switch(view.getId()){

            case R.id.test: //테스트 실행 버튼
                intent = new Intent(MainActivity.this,Test.class);
                break;
            case R.id.result: //나열된 결과 실행 버튼
                intent =new Intent(MainActivity.this,List.class);
                break;
            case R.id.what: //퍼스널 컬러 정의 실행 버튼
                intent =new Intent(MainActivity.this,What.class);
                break;
            case R.id.hp: //colorz (퍼스널 컬러 관련 홈페이지) 실행 버튼
                intent =new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.coloz.co.kr/coloz/"));
                break;
        }

        //버튼을 선택하면 각각의 액티비티가 실행됨
        if(intent !=null)
            startActivity(intent);
    }

    //음악 토글 버튼
    public void music (View view) {
        boolean on = ((ToggleButton) view).isChecked();

        if(on){
            Log.d(TAG, "music() start"); //배경음악 실행
            startService(new Intent(this,MusicService.class));
        }
        else{
            Log.d(TAG, "music() stop"); //배경음악 종료
            stopService(new Intent(this,MusicService.class));
        }
    }

    //방송 수신자 등록
    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            textfield.setText(action);

            //배터리 잔량 & SD카드 장착 여부에 따라서 알림이 실행되고,
            //메인에 이러한 상태가 상황에 따라 달라진다.
            if (action.equals(Intent.ACTION_BATTERY_CHANGED)) {
                int maxvalue = intent
                        .getIntExtra(BatteryManager.EXTRA_SCALE, 0);
                int value = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
                int level = value * 100 / maxvalue;
                textfield.setText("\n현재 배터리 레벨 " + level + "%");
            } else if (action.equals(Intent.ACTION_BATTERY_LOW)) {
                textfield.setText("\n배터리 부족");
            } else if (action.equals(Intent.ACTION_MEDIA_MOUNTED)) {
                textfield.setText(action + "\nSD카드 장착");
            } else if (action.equals(Intent.ACTION_BATTERY_LOW)) {
                textfield.setText(action + "\nSD카드 장착 해제");
            }
        }
    };



}
