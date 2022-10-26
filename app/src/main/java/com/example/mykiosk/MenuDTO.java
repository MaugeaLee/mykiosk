package com.example.mykiosk;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.TextView;

import java.util.ArrayList;

public class MenuDTO {


    public MenuDTO(){
    }

    public void setInitMenu(MyDBHelper myDBhelper, MenuDAO menu){
        MenuDAO menuDAO = menu;
        String db_title = menuDAO.TABLE_NAME;

        SQLiteDatabase db = myDBhelper.getWritableDatabase();
        myDBhelper.onUpgrade(db, 1, 2);

        for(int count=0; count < menu.menu_first.length; count++) {
            String menu_name = menu.menu_first[count];
            String sql = "INSERT INTO " + db_title + " VALUES(null, '" + menu_name + "', 0, 0, 'null','null2')";
            db.execSQL(sql);

        }
        for(int count=0; count < menu.menu_second.length; count++){
            String menu_name = menu.menu_second[count];
            String sql = "INSERT INTO " + db_title + " VALUES(null, '" + menu_name + "', 0, 0, 'null','null2')";
            db.execSQL(sql);
        }
        for(int count=0; count < menu.menu_third.length; count++){
            String menu_name = menu.menu_third[count];
            String sql = "INSERT INTO " + db_title + " VALUES(null, '" + menu_name + "', 0, 0, 'null','null2')";
            db.execSQL(sql);
        }

        db.close();
    }

    public void setViewMenu(MyDBHelper myDBhelper, ArrayList<TextView> tv, MenuDAO menuDAO){
        String db_title = menuDAO.TABLE_NAME;

        SQLiteDatabase db = myDBhelper.getReadableDatabase();
        String sql = "select * from " + db_title;
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

    public MenuDAO getMenuSearch(MyDBHelper myDBhelper,String menu_name, MenuDAO menuDAO) {
        MenuDAO new_menuDAO = menuDAO;

        String db_title = new_menuDAO.TABLE_NAME;

        SQLiteDatabase db = myDBhelper.getReadableDatabase();
        String sql = "select * from " + db_title + " where name= '" + menu_name + "';";
        Cursor cursor = db.rawQuery(sql, null);


        while (cursor.moveToNext()) {
            new_menuDAO.id = cursor.getInt(0);
            new_menuDAO.name = cursor.getString(1);
            new_menuDAO.price = cursor.getInt(2);
            new_menuDAO.count = cursor.getInt(3);
            new_menuDAO.event = cursor.getString(4);
            new_menuDAO.type = cursor.getString(5);
        }

        db.close();
        return new_menuDAO;

    }

}
