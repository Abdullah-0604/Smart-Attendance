package com.example.joker.smartattendance;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.renderscript.Sampler;

/**
 * Created by joker on 12/28/2017.
 */

public class DatabaseHelperLogin extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "logins.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "logins";
    public static final String COL_ID = "ID";
    public static final String COL_UNAME = "UNAME";
    public static final String COL_PASS = "PASS";

    public DatabaseHelperLogin(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String TABLE_CREATE = " CREATE TABLE " + TABLE_NAME + "(" + COL_ID + " INTEGER PRIMARY KEY autoincrement , " + COL_UNAME + " TEXT ," + COL_PASS + " TEXT " + ")";
        db.execSQL(TABLE_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertdata(String uname, String pass){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_UNAME,uname);
        values.put(COL_PASS,pass);
        long id = db.insert(TABLE_NAME,null, values);
        db.close();
        if( id == -1)
         {
             return false;
         }
        else
        {
            return true;
        }

    }
}
