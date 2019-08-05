package com.example.susmita.curd_operation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by user on 1/31/2018.
 */
public class MyDatabase {

    private MyHalper mh;
    private SQLiteDatabase sdb;

    public MyDatabase(Context c){
        mh = new MyHalper(c, "sus.db", null,1);
    }

    public void open(){
        sdb = mh.getWritableDatabase();
    }

    public void insertEmp(String ename, String esal, String edesig){
        ContentValues cv = new ContentValues();
        cv.put("ename",ename);
        cv.put("esal",esal);
        cv.put("edesig",edesig);
        sdb.insert("emp",null,cv);
    }

    public Cursor queryEmp(){
        Cursor c = null;
        //1st query : read all rows from student table
        c = sdb.query("emp",null,null,null,null,null,null);
        return  c;
    }
    public  void delete(int _id){
        sdb.delete("emp","_id = ?", new String[] {""+_id});

    }

    public void close(){
        if (sdb!= null)
            sdb.close();
    }

        public void update(int id, String letest_name, String letest_sal, String letest_desig) {
            ContentValues cv = new ContentValues();
            cv.put("ename",letest_name);
            cv.put("esal",letest_sal);
            cv.put("edesig",letest_desig);
            sdb.update("emp", cv, "_id = ?", new String[]{""+id});
        }

    public class MyHalper extends SQLiteOpenHelper{

        public MyHalper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL("create table emp(_id integer primary key, ename text, esal text, edesig text);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }
}
