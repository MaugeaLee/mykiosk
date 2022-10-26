package com.example.mykiosk;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHelper extends SQLiteOpenHelper {
    public MyDBHelper(Context context){
        super(context, "groupDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE groupTBL (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT" +
                ", name VARCHAR(20)" +
                ", price INTEGER" +
                ", count INTEGER" +
                ", event VARCHAR(20)" +
                ", type VARCHAR(20)" +
                ");");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS groupTBL");
        onCreate(db);
    }
}
