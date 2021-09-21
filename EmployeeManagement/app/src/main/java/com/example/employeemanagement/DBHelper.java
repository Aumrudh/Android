package com.example.employeemanagement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "employeeData.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create Table Employee (empid INTEGER PRIMARY KEY, name TEXT , designation TEXT , experience Integer)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists Employee");
    }

    public boolean insertUserData(int eid,String name,String design,int exp){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues =new ContentValues();
        contentValues.put("empid",eid);
        contentValues.put("name",name);
        contentValues.put("designation",design);
        contentValues.put("experience",eid);
        long result = DB.insert("Employee",null,contentValues);
        if (result==-1)
            return false;
        else
            return true;
    }


    public boolean updateUserData(int eid,String name,String design,int exp){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues =new ContentValues();
        //contentValues.put("name",name);
        contentValues.put("designation",design);
        contentValues.put("experience",exp);
        Cursor cursor = DB.rawQuery("SELECT * FROM Employee WHERE empid =? ",new String[]{String.valueOf(eid) });
        if (cursor.getCount()>0){
            long result = DB.update("Employee",contentValues,"empid= ?",new String[]{String.valueOf(eid)});
            if (result==-1)
                return false;
            else
                return true;
        }
        else
            return false;
    }
    public boolean deleteUserData(int eid) {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * FROM Employee WHERE empid =? ", new String[]{String.valueOf(eid)});
        if (cursor.getCount() > 0) {
            long result = DB.delete("Employee", "empid= ?", new String[]{String.valueOf(eid)});
            if (result == -1)
                return false;
            else
                return true;
        } else
            return false;
    }
    public Cursor updateViewUserData(int eid){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * FROM Employee WHERE empid =? ", new String[]{String.valueOf(eid)});
        return cursor;
    }
    public Cursor viewUserData(){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * FROM Employee WHERE designation= ?",new String[]{"Data Analyst"});
        return cursor;
    }


}

