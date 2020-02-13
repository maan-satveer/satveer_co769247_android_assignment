package com.example.satveer_c0769247_labassignment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "FavoriteDatabase";
    private static final int DATABASE_VERSION =1;
    private static final String TABLE_NAME = "places";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_LONG = "longi";
    private static final String COLUMN_LAT = "lati";
    private static final String COLUMN_DATE = "date";


    public DatabaseHelper(@Nullable Context context ) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_ID +" INTEGER NOT NULL CONSTRAINT employee_pk PRIMARY KEY AUTOINCREMENT," +
                COLUMN_NAME + " varchar(200) NOT NULL," +
                COLUMN_LONG + " varchar(200) NOT NULL," +
                COLUMN_LAT + " varchar(200) NOT NULL," +
                COLUMN_DATE + " varchar(200) NOT NULL);";
        db.execSQL(sql);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // WE ARE JUST DROPPING THE TABLE AND RECREATE IT

        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME + ";";
        db.execSQL(sql);
        onCreate(db);


    }
    boolean addPlace(String name,String longitude ,String latitude , String date){

        //INORDER OT INSERT ITEM INTO DATABAASE
        //WE NEED A WRITABLE DATABASE
        //THIS METHOS RETURN A SQL DATABASE
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        //WE NEED TO DEFINE A CONTENT INSTANCE

        ContentValues cv = new ContentValues();

        //THE FIRST ARGUMENT OF THE PUT METHOD IS THE COLUMN NAME AND THE SECOND VALUE IS THE SHOWN AS BELOW

        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_LONG, longitude);
        cv.put(COLUMN_LAT, latitude);
        cv.put(COLUMN_DATE, date);

        //insert method returns row number if the inseriton is successfully and -1 if the unsuccessfull

        return   sqLiteDatabase.insert(TABLE_NAME, null, cv) != -1;


    }

    Cursor getAllPlaces(){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }
    boolean updatePlace(int id,String name,String longitude ,String latitude, String date){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_LONG, longitude);
        cv.put(COLUMN_LAT, latitude);


        //this method returns the number of rows effected

        return sqLiteDatabase.update(TABLE_NAME, cv, COLUMN_ID + "=?", new String[]{String.valueOf(id)}) > 0;

    }
    boolean deleteEmployee(int id){
        SQLiteDatabase sqLiteDatabase  = getWritableDatabase();

        //the delete method returns the  number of rows effected
        return sqLiteDatabase.delete(TABLE_NAME, COLUMN_ID +"=?", new String[]{String.valueOf(id)}) > 0;
    }
}
