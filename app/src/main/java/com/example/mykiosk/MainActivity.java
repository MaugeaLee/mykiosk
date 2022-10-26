package com.example.mykiosk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    MenuDAO menuDAO;


    public void setInitMenu(MyDBHelper myDBhelper, MenuDAO menu){
        SQLiteDatabase db = myDBhelper.getWritableDatabase();
        myDBhelper.onUpgrade(db, 1, 2);

        for(int count=0; count < menu.menu_first.length; count++) {
            String menu_name = menu.menu_first[count];
            db.execSQL("INSERT INTO groupTBL VALUES(null, '" + menu_name + "', 0, 0, 'null','null2')");

        }
        for(int count=0; count < menu.menu_second.length; count++){
            String menu_name = menu.menu_second[count];
            db.execSQL("INSERT INTO groupTBL VALUES(null, '"+ menu_name +"', 0, 0, 'null', 'null2')");
        }
        for(int count=0; count < menu.menu_third.length; count++){
            String menu_name = menu.menu_third[count];
            db.execSQL("INSERT INTO groupTBL VALUES(null, '"+ menu_name +"', 0, 0, 'null', 'null2')");
        }

        db.close();
    }

    public void setViewMenu(MyDBHelper myDBhelper, ArrayList<TextView> tv){
        SQLiteDatabase db = myDBhelper.getReadableDatabase();
        String sql = "select * from groupTBL";
        Cursor cursor = db.rawQuery(sql, null);

        int count = 0;
        while (cursor.moveToNext()) {
            String name = cursor.getString(1);
            tv.get(count).setText(name);
            count++;

            if (count == tv.size()){
                break;
            }
        }
        cursor.close();
        db.close();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TextView menuTV_0, menuTV_1, menuTV_2, menuTV_3, menuTV_4, menuTV_5, menuTV_6, menuTV_7, menuTV_8;


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* db reset */
//        MyDBHelper myDBHelper = new MyDBHelper(this);
//        SQLiteDatabase sqlDB = myDBHelper.getWritableDatabase();
//        menuDAO = new MenuDAO();
//        setInitMenu(sqlDB, menuDAO);
//        sqlDB.close();

        MyDBHelper myDBHelper = new MyDBHelper(this);
        menuDAO = new MenuDAO();
        setInitMenu(myDBHelper, menuDAO);
        Toast.makeText(getApplicationContext(), "db reset", Toast.LENGTH_SHORT).show();

        /* menuBTN reset */
        ArrayList<ImageView> btnImageArrayList = new ArrayList<>();

        ImageView btnSelect_00 = (ImageView) findViewById(R.id.re_mac1);
        ImageView btnSelect_01 = (ImageView) findViewById(R.id.re_mac2);
        ImageView btnSelect_02 = (ImageView) findViewById(R.id.re_mac3);
        ImageView btnSelect_03 = (ImageView) findViewById(R.id.re_mac4);
        ImageView btnSelect_04 = (ImageView) findViewById(R.id.re_mac5);
        ImageView btnSelect_05 = (ImageView) findViewById(R.id.re_mac6);
        ImageView btnSelect_06 = (ImageView) findViewById(R.id.re_mac7);
        ImageView btnSelect_07 = (ImageView) findViewById(R.id.re_mac8);
        ImageView btnSelect_08 = (ImageView) findViewById(R.id.re_mac9);
        btnImageArrayList.add(btnSelect_00);
        btnImageArrayList.add(btnSelect_01);
        btnImageArrayList.add(btnSelect_02);
        btnImageArrayList.add(btnSelect_03);
        btnImageArrayList.add(btnSelect_04);
        btnImageArrayList.add(btnSelect_05);
        btnImageArrayList.add(btnSelect_06);
        btnImageArrayList.add(btnSelect_07);
        btnImageArrayList.add(btnSelect_08);


        /* menuTV reset */
        ArrayList<TextView> tvMenuArrayList = new ArrayList<>();

        menuTV_0 = (TextView) findViewById(R.id.menu_text1);
        menuTV_1 = (TextView) findViewById(R.id.menu_text2);
        menuTV_2 = (TextView) findViewById(R.id.menu_text3);
        menuTV_3 = (TextView) findViewById(R.id.menu_text4);
        menuTV_4 = (TextView) findViewById(R.id.menu_text5);
        menuTV_5 = (TextView) findViewById(R.id.menu_text6);
        menuTV_6 = (TextView) findViewById(R.id.menu_text7);
        menuTV_7 = (TextView) findViewById(R.id.menu_text8);
        menuTV_8 = (TextView) findViewById(R.id.menu_text9);
        tvMenuArrayList.add(menuTV_0);
        tvMenuArrayList.add(menuTV_1);
        tvMenuArrayList.add(menuTV_2);
        tvMenuArrayList.add(menuTV_3);
        tvMenuArrayList.add(menuTV_4);
        tvMenuArrayList.add(menuTV_5);
        tvMenuArrayList.add(menuTV_6);
        tvMenuArrayList.add(menuTV_7);
        tvMenuArrayList.add(menuTV_8);

        setViewMenu(myDBHelper, tvMenuArrayList);

        btnSelect_00.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Intent intent = new Intent(getApplicationContext(), BuggerView.class);

                startActivity(intent);
                return false;
            }
        });


        btnSelect_01.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch(motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN: {

                        Toast.makeText(getApplicationContext(), "insert", Toast.LENGTH_SHORT).show();
                    }
                }
                return false;
            }
        });



    }
}