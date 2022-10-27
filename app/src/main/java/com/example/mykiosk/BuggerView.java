package com.example.mykiosk;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BuggerView extends AppCompatActivity {
    /* init primery Value */
    int id = 0;
    int bugger_id = 0;
    int drink_price = 0;
    int side_price = 0;
    int top_ping_price = 0;
    int add_side_price = 0;
    int result_price = 0;

    int menu_num = 1;

    boolean topping_color_count_0 = false;
    boolean topping_color_count_1 = false;
    boolean topping_color_count_2 = false;
    boolean topping_color_count_3 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buggerview);

        /* widget init */
        TextView menu_price;
        TextView menu_count;

        Button drink_0, drink_1, drink_2;
        Button side_0, side_1, side_2;
        Button topping_0, topping_1, topping_2, topping_3;

        Button btnSum;
        Button btnResult;


        ImageView menu_minus, menu_plus;

        /* getIntent init */
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
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
        menu_count = (TextView) findViewById(R.id.menu_count);

        // Button
        drink_0 = (Button) findViewById(R.id.drink_cola);
        drink_1 = (Button) findViewById(R.id.drink_cider);
        drink_2 = (Button) findViewById(R.id.drink_milk_shake);

        side_0 = (Button) findViewById(R.id.side_small);
        side_1 = (Button) findViewById(R.id.side_mideum);
        side_2 = (Button) findViewById(R.id.side_large);

        topping_0 = (Button) findViewById(R.id.topping_cheese);
        topping_1 = (Button) findViewById(R.id.topping_bacon);
        topping_2 = (Button) findViewById(R.id.topping_patty);
        topping_3 = (Button) findViewById(R.id.topping_hash);

        menu_minus = (ImageView) findViewById(R.id.menu_minus);
        menu_plus = (ImageView) findViewById(R.id.menu_plus);

        btnResult = (Button) findViewById(R.id.bugger_exit);
        btnSum = (Button) findViewById(R.id.menu_sum);

        Button[] drink_btn_array = {drink_0, drink_1, drink_2};
        Button[] side_btn_array = {side_0, side_1, side_2};
        Button[] topping_btn_array = {topping_0, topping_1, topping_2, topping_3};


        /* data load */
        MenuDAO menuDAO_load_data = menuDTO.getMenuSearch(myDBhelper, name, menuDAO);


        /* data init */
        menu_name.setText(name);
        result_price =  menuDAO_load_data.price;
        bugger_id = menuDAO_load_data.id;


        /* view Setting */
        String price = result_price + "원";
        menu_price.setText(price);


        /* btn Action init */
        drink_0.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                setBtnColorSelectFiller(drink_btn_array, 0);

                drink_price = 0;
                result_price = setResultPriceInt(drink_price, side_price, top_ping_price, add_side_price, menuDAO.price, menu_num);
                menu_price.setText(String.format("%d원", result_price));

                return false;
            }
        });

        drink_1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                setBtnColorSelectFiller(drink_btn_array, 1);

                drink_price = 0;
                result_price = setResultPriceInt(drink_price, side_price, top_ping_price, add_side_price, menuDAO.price, menu_num);
                menu_price.setText(String.format("%d원", result_price));

                return false;
            }
        });

        drink_2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                setBtnColorSelectFiller(drink_btn_array, 2);

                drink_price = 1000;
                result_price = setResultPriceInt(drink_price, side_price, top_ping_price, add_side_price, menuDAO.price, menu_num);
                menu_price.setText(String.format("%d원", result_price));

                return false;
            }
        });

        side_0.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                setBtnColorSelectFiller(side_btn_array, 0);

                side_price = 0;
                result_price = setResultPriceInt(drink_price, side_price, top_ping_price, add_side_price, menuDAO.price, menu_num);
                menu_price.setText(String.format("%d원", result_price));

                return false;
            }
        });

        side_1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                setBtnColorSelectFiller(side_btn_array, 1);

                side_price = 500;
                result_price = setResultPriceInt(drink_price, side_price, top_ping_price, add_side_price, menuDAO.price, menu_num);
                menu_price.setText(String.format("%d원", result_price));

                return false;
            }
        });

        side_2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                setBtnColorSelectFiller(side_btn_array, 2);

                side_price = 1000;
                result_price = setResultPriceInt(drink_price, side_price, top_ping_price, add_side_price, menuDAO.price, menu_num);
                menu_price.setText(String.format("%d원", result_price));

                return false;
            }
        });

        topping_0.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch(motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        topping_color_count_0 = setBtnColorFiller(topping_0, topping_color_count_0);

                        if (topping_color_count_0==true) {
                            add_side_price += 800;
                        } else{
                            add_side_price -= 800;
                        }

                        result_price = setResultPriceInt(drink_price, side_price, top_ping_price, add_side_price, menuDAO.price, menu_num);
                        menu_price.setText(String.format("%d원", result_price));
                    }
                }
                return false;
            }
        });

        topping_1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch(motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        topping_color_count_1 = setBtnColorFiller(topping_1, topping_color_count_1);

                        if (topping_color_count_1==true) {
                            add_side_price += 1000;
                        } else{
                            add_side_price -= 1000;
                        }

                        result_price = setResultPriceInt(drink_price, side_price, top_ping_price, add_side_price, menuDAO.price, menu_num);
                        menu_price.setText(String.format("%d원", result_price));
                    }
                }
                return false;
            }
        });

        topping_2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch(motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        topping_color_count_2 = setBtnColorFiller(topping_2, topping_color_count_2);

                        if (topping_color_count_2==true) {
                            add_side_price += 1200;
                        } else{
                            add_side_price -= 1200;
                        }

                        result_price = setResultPriceInt(drink_price, side_price, top_ping_price, add_side_price, menuDAO.price, menu_num);
                        menu_price.setText(String.format("%d원", result_price));
                    }
                }
                return false;
            }
        });

        topping_3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch(motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        topping_color_count_3 = setBtnColorFiller(topping_3, topping_color_count_3);

                        if (topping_color_count_3==true) {
                            add_side_price += 1200;
                        } else{
                            add_side_price -= 1200;
                        }

                        result_price = setResultPriceInt(drink_price, side_price, top_ping_price, add_side_price, menuDAO.price, menu_num);
                        menu_price.setText(String.format("%d원", result_price));
                    }
                }
                return false;
            }
        });

        menu_minus.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        if (menu_num > 1){
                            menu_num -= 1;
                        } else{
                            menu_num = 1;
                        }
                        menu_count.setText(String.format("%d", menu_num));
                        result_price = setResultPriceInt(drink_price, side_price, top_ping_price, add_side_price, menuDAO.price, menu_num);
                        menu_price.setText(String.format("%d원", result_price));
                    }
                }
                return false;
            }
        });

        menu_plus.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        if (menu_num == 9){
                            menu_num = 9;
                        } else{
                            menu_num += 1;
                        }
                        menu_count.setText(String.format("%d", menu_num));
                        result_price = setResultPriceInt(drink_price, side_price, top_ping_price, add_side_price, menuDAO.price, menu_num);
                        menu_price.setText(String.format("%d원", result_price));
                    }
                }
                return false;
            }
        });


        btnResult.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Intent intentout = new Intent(getApplicationContext(), MainActivity.class);
                intentout.putExtra("id", id);
                setResult(RESULT_CANCELED, intentout);
                finish();
                return false;
            }
        });

        btnSum.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Intent intentout = new Intent(getApplicationContext(), MainActivity.class);
                /*
                id
                drink_price;
                side_price;
                top_ping_price;
                add_side_price;
                result_price;
                */
                id += 1;
                intentout.putExtra("id", id);
                intentout.putExtra("bugger_id", bugger_id);
                intentout.putExtra("drink_price", drink_price);
                intentout.putExtra("side_price", side_price);
                intentout.putExtra("top_ping_price", top_ping_price);
                intentout.putExtra("add_side_price", add_side_price);
                intentout.putExtra("menu_num", menu_num);
                intentout.putExtra("result_price", result_price);
                setResult(RESULT_OK, intentout);
                finish();
                // 돌아가는거
                return false;
            }
        });



    }

    public void setBtnColorSelectFiller(Button[] btn, int order){
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

    public boolean setBtnColorFiller(Button btn, boolean color){
        boolean result = true;

        if (color == true){
            btn.setBackground(getDrawable(R.drawable.menu_box_oren));
            btn.setTextColor(getColor(R.color.oren));
            result = false;
        } else{
            btn.setBackground(getDrawable(R.drawable.menu_box_oren_fill));
            btn.setTextColor(getColor(R.color.white));
            result = true;
        }

        return result;
    }

    public int setResultPriceInt(int drink_price, int side_price, int top_ping_price, int add_side_price, int bugger_price, int menu_num){
        int result_price_func = drink_price + side_price + top_ping_price + add_side_price + bugger_price;
        int price_int = 0;
        if (result_price_func == 0){
            price_int = result_price_func;
        } else {
            price_int = result_price_func * menu_num;
        }
        return price_int;
    }
}
