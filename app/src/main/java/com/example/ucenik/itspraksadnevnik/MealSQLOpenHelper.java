package com.example.ucenik.itspraksadnevnik;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Ucenik on 07/06/2016.
 */
public class MealSQLOpenHelper extends SQLiteOpenHelper {

    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "Meals.db";
    public static final String DB_TABLE_NAME = "meals";
    public static final String DB_CREATE = "CREATE TABLE meals " +
            "(id integer primary key auto_increment, meal_type text, name text, " +
            "calorie_number integer, date text, time text)";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TYPE = "meal_type";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_CALORIES = "calorie_number";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_TIME = "time";

    public MealSQLOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, DB_NAME, factory, DB_VERSION, errorHandler);
    }

    public MealSQLOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DB_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS meals");
        onCreate(db);
    }

    public void addMeal(String type, String name, int calories, String date, String time){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TYPE, type);
        contentValues.put(COLUMN_NAME, name);
        contentValues.put(COLUMN_CALORIES, calories);
        contentValues.put(COLUMN_DATE, date);
        contentValues.put(COLUMN_TIME, time);

        Log.d("ADD_MEAL", "id - " + db.insert(DB_TABLE_NAME, null, contentValues));

    }

}
