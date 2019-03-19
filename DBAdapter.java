package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;


public class DBAdapter {
    private static final String DB_NAME = "student.db";
    private static final String DB_TABLE = "studentinfo";
    private static final Integer DB_VERSION =1;

    private static final String STU_INFO ="info";

    private SQLiteDatabase db;
    private final Context context;

    private static class DBOpenHelper extends SQLiteOpenHelper{
        DBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }
        @Override
        public void onCreate(SQLiteDatabase _db) {
            _db.execSQL("CREATE TABLE IF NOT EXISTS student (studentinfo String primary key autoincrement)");
        }
        @Override
        public void onUpgrade(SQLiteDatabase _db, int oldVersion, int newVersion) {
            _db.execSQL("DROP TABLE IF EXISTS student");
            onCreate(_db);
        }
    }
    public DBAdapter(DialogInterface.OnClickListener context) {
        this.context = (Context) context;
    }
    public void open()throws SQLiteException{
        DBOpenHelper dbOpenHelper = new DBOpenHelper(context, DB_NAME, null, DB_VERSION);
        try {
            db = dbOpenHelper.getWritableDatabase();
        }catch (SQLiteException ex){
            db = dbOpenHelper.getReadableDatabase();
        }
    }
    public void cloce(){
        if(db !=null){
            db.close();
            db = null;
        }
    }
    public long insert(Student student){
        ContentValues newValues = new ContentValues();
        newValues.put(STU_INFO,Student.getStudent().info);
        return db.insert(DB_TABLE,null,newValues);
    }

}
