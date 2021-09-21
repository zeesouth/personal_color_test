package com.example.personalcolortest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

//데이터 저장에 필요한 클래스
public class Data extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "mycolors.db";
    private static final int DATABASE_VERSION = 2;

    public Data(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //데이터를 저장할 테이블 설정
    //이름과 퍼스널 컬러 타입이 항목으로 들어감
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE colors ( _id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, color TEXT)");

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            db.execSQL("DROP TABLE IF EXISTS colors");
            onCreate(db);
    }


}
