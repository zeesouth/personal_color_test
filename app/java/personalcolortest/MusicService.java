package com.example.personalcolortest;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

//배경음악 설정 해주는 클래스
@SuppressWarnings("ALL")
public class MusicService extends Service {

    private static final String TAG = "MusicService";
    MediaPlayer player;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate()");

        player = MediaPlayer.create(this ,R.raw.beat_your_competition);
        player.setLooping(false); // Set looping
    }

    //어플 종료 시 음악이 꺼짐
    @Override
    public void onDestroy() {

        Log.d(TAG, "onDestroy()");
        player.stop();
    }

    //첫 화면 실행 시 음악이 켜진다.
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.d(TAG, "onStart()");
        player.start();
        return super.onStartCommand(intent, flags, startId);
    }

}
