package com.example.json_from_php;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyDBName.db";
    public static final String CONTACTS_TABLE_NAME = "contacts";
    public static final String CONTACTS_COLUMN_ID = "id";
    public static final String CONTACTS_COLUMN_NAME = "name";


    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL("create table contacts (id integer primary key, name text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(db);
    }

    public boolean insertContact  (int id, String name)//, String street,String place)
    {

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor res =  db.rawQuery( "select * from contacts where  id="+id+"", null );
        res.moveToFirst();

        if(res.isAfterLast()) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("id", id);
            contentValues.put("name", name);
            long result = db.insert("contacts", null, contentValues);
            return true;
        }else
            return false;
    }
    //*************
    public Cursor getData(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from contacts where id="+id+"", null );
        return res;
    }
    //*************
    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, CONTACTS_TABLE_NAME);
        return numRows;
    }
    //*************
    public boolean updateContact (Integer wher, Integer id, String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("name", name);

        db.update("contacts", contentValues, "id = ? ", new String[] { Integer.toString(wher) } );

        return true;
    }
    //*************
    public Integer deleteContact (int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("contacts","id = ? ", new String[] { Integer.toString(id) });
    }
    //*************
    public ArrayList<String> getAllContacts()
    {

        ArrayList<String> array_list = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from contacts", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(" "+res.getString(res.getColumnIndex(CONTACTS_COLUMN_ID))
                    +", "+ res.getString(res.getColumnIndex(CONTACTS_COLUMN_NAME))

            );
            res.moveToNext();
        }
        return array_list;
    }

    //*************

    public Cursor get(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from contacts where id="+id+"", null );
        return res;
    }
    //*************
    public Cursor getAllData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from contacts", null );
        return res;
    }
    //*************
    public void deleteAllData(){
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete("contacts", null, null);
    }


    //*************
    public Cursor getAllDatawhere(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from contacts where id="+id+"", null );
        return res;
    }
}