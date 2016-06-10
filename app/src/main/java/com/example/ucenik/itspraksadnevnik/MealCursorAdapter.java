package com.example.ucenik.itspraksadnevnik;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by Ucitelj on 10/06/2016.
 */
public class MealCursorAdapter extends CursorAdapter {

    public MealCursorAdapter(Context context, Cursor c) {
        super(context, c);
    }

    public MealCursorAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    public MealCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.meal_list_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView tvName = (TextView) view.findViewById(R.id.list_item_name);
        tvName.setText(cursor.getString(cursor.getColumnIndex(MealSQLOpenHelper.COLUMN_NAME)));

        TextView tvCalories = (TextView) view.findViewById(R.id.list_item_calories);
        tvCalories.setText(String.valueOf(cursor.getInt(cursor.getColumnIndex(MealSQLOpenHelper.COLUMN_CALORIES))));
//        Log.d("CURSOR", cursor.getString(cursor.getColumnIndex(MealSQLOpenHelper.COLUMN_TIME)));

    }
}
