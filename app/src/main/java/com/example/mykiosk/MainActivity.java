package com.example.mykiosk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public void menuOrd(TextView tv){
        String name = tv.getText().toString();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Widget reset */
        TextView menuTV_0, menuTV_1, menuTV_2, menuTV_3, menuTV_4, menuTV_5, menuTV_6, menuTV_7, menuTV_8;
        LinearLayout menuLay_0, menuLay_1, menuLay_2, menuLay_3, menuLay_4, menuLay_5, menuLay_6, menuLay_7, menuLay_8;

        /* db reset */
        MyDBHelper myDBHelper = new MyDBHelper(this);


        /* DAO reset */
        MenuDAO menuDAO = new MenuDAO();
        menuDAO.TABLE_NAME = "groupTBL";


        /* DTO reset */
        MenuDTO menuDTO = new MenuDTO();
        menuDTO.setInitMenu(myDBHelper, menuDAO);

        /* menuLay reset */
        ArrayList<LinearLayout> btnMenuLay = new ArrayList<>();

        menuLay_0 = (LinearLayout) findViewById(R.id.menu_lay1);
        menuLay_1 = (LinearLayout) findViewById(R.id.menu_lay2);
        menuLay_2 = (LinearLayout) findViewById(R.id.menu_lay3);
        menuLay_3 = (LinearLayout) findViewById(R.id.menu_lay4);
        menuLay_4 = (LinearLayout) findViewById(R.id.menu_lay5);
        menuLay_5 = (LinearLayout) findViewById(R.id.menu_lay6);
        menuLay_6 = (LinearLayout) findViewById(R.id.menu_lay7);
        menuLay_7 = (LinearLayout) findViewById(R.id.menu_lay8);
        menuLay_8 = (LinearLayout) findViewById(R.id.menu_lay9);
        btnMenuLay.add(menuLay_0);
        btnMenuLay.add(menuLay_1);
        btnMenuLay.add(menuLay_2);
        btnMenuLay.add(menuLay_3);
        btnMenuLay.add(menuLay_4);
        btnMenuLay.add(menuLay_5);
        btnMenuLay.add(menuLay_6);
        btnMenuLay.add(menuLay_7);
        btnMenuLay.add(menuLay_8);


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

        /* menu title STR setting */
        menuDTO.setViewMenu(myDBHelper, tvMenuArrayList, menuDAO);


        /* menu Lay btn setting */
        for (int i=0; i < btnMenuLay.size(); i++){
            final int count = i;
            btnMenuLay.get(i).setOnTouchListener(new View.OnTouchListener() {
                TextView menu_tv_name = tvMenuArrayList.get(count);
                String title = menu_tv_name.getText().toString();
                String db_title = menuDAO.TABLE_NAME;

                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    Intent intent = new Intent(getApplicationContext(), BuggerView.class);
                    intent.putExtra("name", title);
                    intent.putExtra("db_title", db_title);
                    startActivity(intent);
                    return false;
                }
            });
        }

        /* reset OK */
        Toast.makeText(getApplicationContext(), "db reset", Toast.LENGTH_SHORT).show();
    }

}