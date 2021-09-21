package com.example.personalcolortest;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

//결과 리스트들을 보여주는 함수
public class List extends AppCompatActivity {

    Data data;
    SQLiteDatabase db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
        setTitle("RESULT LIST");


        data = new Data(List.this);
        //데이터를 불러와서 리스트 형태로 나타내줌
        //이름과 컬러 형태로 나타내주는 리스트뷰
        db = data.getWritableDatabase();
        final Cursor cursor = db.rawQuery("SELECT * FROM colors", null);

        startManagingCursor(cursor);

        String[] from = {"name", "color"};
        int[] to = {android.R.id.text1, android.R.id.text2};

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_expandable_list_item_2, cursor, from, to);

        final ListView list = (ListView) findViewById(R.id.list);

        //리스트 뷰의 항목을 꾹 누르면, 삭제를 할건지 여부를 물음
        list.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(List.this);
                alertDialogBuilder.setMessage("삭제하시겠습니까? \n(※ 삭제되면 복구 안됨)");
                //삭제를 원할 때
                alertDialogBuilder.setPositiveButton("네", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        //데이터 삭제 기능 구현
                        cursor.moveToPosition(position);
                        String sql = "delete from colors where _id = ?";
                        db.execSQL(sql, new String[]{cursor.getString(0)});

                        //삭제가 완료되면 토스트 메시지를 뜸
                        Toast.makeText(List.this,"삭제가 완료되었습니다.",Toast.LENGTH_SHORT).show();

                        //삭제되고 나면 화면을 다시 보여줌
                        recreate();
                    }
                });

                //삭제를 원하지 않을 때
                alertDialogBuilder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                AlertDialog alertDialog =  alertDialogBuilder.create();
                alertDialog.show();

                return false;

            }

        });

        list.setAdapter(adapter);


    }

    /*
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);

    }

     */

    public void closed (View v){
        finish();
    }
}
