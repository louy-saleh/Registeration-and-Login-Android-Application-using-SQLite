package com.example.finalhomework;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.view.contentcapture.DataRemovalRequest;

public class DatabaseManager {
    private DatabaseHelper dbHelper;
    private Context context;
    ////initialise database
    private SQLiteDatabase database;

    //Constructor
    public DatabaseManager(Context ctx){
        context=ctx;
    }

    //opening database method
    public DatabaseManager open() throws SQLException {
        //took the object from helper class
        dbHelper=new DatabaseHelper(context);
        //initialise database and accessing database by helper class
        database=dbHelper.getWritableDatabase();
        return this;
    }
    //closing database method
    public void close(){
        dbHelper.close();
    }

    //inserting data
    public void insert(String username,String password){

        //initialise Content Values
        ContentValues contentValues=new ContentValues();
        contentValues.put(DatabaseHelper.USERNAME,username);
        contentValues.put(DatabaseHelper.USER_PASSWORD,password);
        //inserting values to our database table
        database.insert(DatabaseHelper.DATABASE_TABLE,null,contentValues);
    }

    //checking username and password
    public Boolean checking_username_password(String username,String password){
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from "+DatabaseHelper.DATABASE_TABLE+
                " where "+DatabaseHelper.USERNAME+"=? and "+DatabaseHelper.USER_PASSWORD+"=?",
                new String[]{username,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    //updating data
    public Boolean update_username(String usernameold,String usernameNew){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(DatabaseHelper.USERNAME,usernameNew);
        Cursor cursor=db.rawQuery("select * from "+DatabaseHelper.DATABASE_TABLE+
        " where "+DatabaseHelper.USERNAME+"=?",new String[]{usernameold});

        if(cursor.getCount()>0) {
            long result = db.update(DatabaseHelper.DATABASE_TABLE,
                    contentValues,
                    DatabaseHelper.USERNAME + "=?",
                    new String[]{usernameold});
            if(result==-1){
                return false;
            }else{
                return true;
            }
        }else{
            return false;
        }
    }

    //deleting data
    public Boolean delete(String username){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from "+DatabaseHelper.DATABASE_TABLE+
                " where "+DatabaseHelper.USERNAME+"=?",new String[]{username});
        if(cursor.getCount()>0){
            long result=db.delete(DatabaseHelper.DATABASE_TABLE,
                    DatabaseHelper.USERNAME+"=?",new String[]{username});
            //testing if deleted
            if(result==-1){
                return false;
            }else{
                return  true;
            }
        }
        else{
            return false;
        }
    }


}