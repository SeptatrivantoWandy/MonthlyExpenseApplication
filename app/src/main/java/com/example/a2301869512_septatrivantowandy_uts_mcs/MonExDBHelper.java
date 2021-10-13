package com.example.a2301869512_septatrivantowandy_uts_mcs;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class MonExDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MonExDB.db";
    public static final Integer DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "tblmonex";
    public static final String TABLE_COLUMN_ID = "id";
    public static final String TABLE_COLUMN_NAME = "monex_name";
    public static final String TABLE_COLUMN_PRICE = "monex_price";
    public static final String TABLE_COLUMN_DATE = "monex_date";

    List<MonEx> monexList = new ArrayList<>();


    public MonExDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

//    public void test () {
//        getReadableDatabase();
//    }

    public void insertMonEx(MonEx new_monex) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

//        cv.put(TABLE_COLUMN_ID, new_monex.getMonexId());
        cv.put(TABLE_COLUMN_NAME, new_monex.getMonexName());
        cv.put(TABLE_COLUMN_PRICE, new_monex.getMonexPrice());
        cv.put(TABLE_COLUMN_DATE, new_monex.getMonexDate());

        db.insert(TABLE_NAME, null, cv);

    }

    Cursor readAllMonEx () {
        SQLiteDatabase db = this.getReadableDatabase();
        String MonEx_query = "SELECT * FROM " + TABLE_NAME;

        Cursor cursor = null;

        if (db != null) {
            cursor = db.rawQuery(MonEx_query, null);
        }

        return cursor;

    }

    public boolean updateMonEx (MonEx update_monex) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(TABLE_COLUMN_ID, update_monex.getMonexId());
        cv.put(TABLE_COLUMN_NAME, update_monex.getMonexName());
        cv.put(TABLE_COLUMN_PRICE, update_monex.getMonexPrice());
        cv.put(TABLE_COLUMN_DATE, update_monex.getMonexDate());

        db.update(TABLE_NAME, cv, "id = ?", new String [] { update_monex.getMonexId() } );

        return true;
    }

//    public List<String> getMonEx() {
//        SQLiteDatabase db = this.getReadableDatabase();
//        String get_MonEx_query = "SELECT * FROM " + TABLE_NAME;
//        Cursor cursor = db.rawQuery(get_MonEx_query, null);
//
//        List<String> monex = new Vector<String>();
//
//        if(cursor.moveToFirst() == true) {
//            do {
//                Integer id = cursor.getInt(0);
//                String name = cursor.getString(1);
//                Integer price = cursor.getInt(2);
//                String date = cursor.getString(3);
//
//                String compact = id + ", " + name + ", " + price + ", " + date;
//                monex.add(compact);
//
//            } while (cursor.moveToNext());
//        }
//
//        return monex;
//    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String create_table_query = "CREATE TABLE " + TABLE_NAME + " (" + TABLE_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TABLE_COLUMN_NAME + " TEXT NOT NULL, " + TABLE_COLUMN_PRICE + " INTEGER NOT NULL, " + TABLE_COLUMN_DATE + " TEXT NOT NULL)";

        db.execSQL(create_table_query);
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
