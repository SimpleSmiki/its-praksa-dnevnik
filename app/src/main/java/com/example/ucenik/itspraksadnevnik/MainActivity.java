package com.example.ucenik.itspraksadnevnik;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    Button log;

    ListView list;

    MealSQLOpenHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        log = (Button)findViewById(R.id.log);
        list = (ListView) findViewById(R.id.mealList);

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LogActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        dbHelper = new MealSQLOpenHelper(this);
        Cursor cursor = dbHelper.getReadableDatabase().rawQuery("SELECT *, id as _id FROM meals", null);
//        cursor.moveToNext();
//        while (!cursor.isAfterLast()){
//            Log.d("cursor", "" + cursor.getInt(cursor.getColumnIndex(MealSQLOpenHelper.COLUMN_CALORIES)));
//            cursor.moveToNext();
//        }
        MealCursorAdapter adapter = new MealCursorAdapter(this, cursor);
        list.setAdapter(adapter);
    }
}
