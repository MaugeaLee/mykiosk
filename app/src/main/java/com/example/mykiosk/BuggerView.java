package com.example.mykiosk;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class BuggerView extends AppCompatActivity {
    /* init primery Value */
    int drink_price = 0;
    int side_price = 0;
    int top_ping_price = 0;
    int add_side_price = 0;
    int result_price = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buggerview);




        /* widget init */
        TextView menu_price;
        Button drink_0, drink_1, drink_2;


        /* getIntent init */
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String db_title = intent.getStringExtra("db_title");


        /* dao init */
        MenuDAO menuDAO = new MenuDAO();
        menuDAO.TABLE_NAME = db_title;


        /* dto init */
        MenuDTO menuDTO = new MenuDTO();


        /* db init */
        MyDBHelper myDBhelper = new MyDBHelper(this);


        /* id init */
        // TextView
        menu_price = (TextView) findViewById(R.id.bugger_price);
        TextView menu_name = (TextView) findViewById(R.id.menu_name);

        // Button
        drink_0 = (Button) findViewById(R.id.drink_cola);
        drink_1 = (Button) findViewById(R.id.drink_cider);
        drink_2 = (Button) findViewById(R.id.drink_milk_shake);


        /* data load */
        MenuDAO menuDAO_load_data = menuDTO.getMenuSearch(myDBhelper, name, menuDAO);


        /* data init */
        menu_name.setText(name);
        result_price =  menuDAO_load_data.price;



        /* view Setting */
        String price = result_price + "원";
        menu_price.setText(price);

        
        /* btn Action init */
        drink_0.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Button[] btn_array = {drink_0, drink_1, drink_2};
                setBtnColorFiller(btn_array, -1);

                drink_price = 0;
                result_price = drink_price + side_price + top_ping_price + add_side_price + menuDAO.price;

                String price_str = result_price + "원";
                menu_price.setText(price_str);

                Toast.makeText(BuggerView.this, "result", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        drink_1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Button[] btn_array = {drink_0, drink_1, drink_2};
                setBtnColorFiller(btn_array, 0);

                drink_price = 0;
                result_price = drink_price + side_price + top_ping_price + add_side_price + menuDAO.price;

                String price_str = result_price + "원";
                menu_price.setText(price_str);

                Toast.makeText(BuggerView.this, "result", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        drink_2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Button[] btn_array = {drink_0, drink_1, drink_2};
                setBtnColorFiller(btn_array, 1);

                drink_price = 1000;
                result_price = drink_price + side_price + top_ping_price + add_side_price + menuDAO.price;

                String price_str = result_price + "원";
                menu_price.setText(price_str);

                Toast.makeText(BuggerView.this, "result", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        Button btnResult = (Button) findViewById(R.id.bugger_exit);
        btnResult.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                finish();
                return false;
            }
        });

    }

    public void setBtnColorFiller(Button[] btn, int order){
        if (order == 0){
            btn[0].setBackground(getDrawable(R.drawable.menu_box_oren_fill));
            btn[1].setBackground(getDrawable(R.drawable.menu_box_oren));
            btn[2].setBackground(getDrawable(R.drawable.menu_box_oren));
            btn[0].setTextColor(getColor(R.color.white));
            btn[1].setTextColor(getColor(R.color.oren));
            btn[2].setTextColor(getColor(R.color.oren));
        }
        else if (order == 1){
            btn[0].setBackground(getDrawable(R.drawable.menu_box_oren));
            btn[1].setBackground(getDrawable(R.drawable.menu_box_oren_fill));
            btn[2].setBackground(getDrawable(R.drawable.menu_box_oren));
            btn[0].setTextColor(getColor(R.color.oren));
            btn[1].setTextColor(getColor(R.color.white));
            btn[2].setTextColor(getColor(R.color.oren));
        }
        else if (order == 2){
            btn[0].setBackground(getDrawable(R.drawable.menu_box_oren));
            btn[1].setBackground(getDrawable(R.drawable.menu_box_oren));
            btn[2].setBackground(getDrawable(R.drawable.menu_box_oren_fill));
            btn[0].setTextColor(getColor(R.color.oren));
            btn[1].setTextColor(getColor(R.color.oren));
            btn[2].setTextColor(getColor(R.color.white));
        }
    }
}
