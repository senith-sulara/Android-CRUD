package com.example.studentapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Student.db";
    public static final String TABLE_NAME = "student_tbl";
    public static final String col_1 = "ID";
    public static final String col_2 = "NAME";
    public static final String col_3 = "SURNAME";
    public static final String col_4 = "SUBJECT";
    public static final String col_5 = "MARKS";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,SURNAME TEXT,SUBJECT TEXT,MARKS INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String name,String surname,String subject,String marks) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(col_2,name);
        cv.put(col_3,surname);
        cv.put(col_4,subject);
        cv.put(col_5,marks);
        long result = db.insert(TABLE_NAME,null ,cv);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData (){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

    public boolean updateData(String id,String name,String surname,String subject,String marks){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(col_1,id);
        cv.put(col_2,name);
        cv.put(col_3,surname);
        cv.put(col_4,subject);
        cv.put(col_5,marks);
        db.update(TABLE_NAME,cv,"ID = ?",new String[]{id});
        return true;
    }

    public Integer delete(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return  db.delete(TABLE_NAME, "ID = ?",new String[]{id});
    }

}
