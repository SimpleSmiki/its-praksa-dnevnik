package com.example.ucenik.itspraksadnevnik;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button log;

    Spinner typeFilter;

    TextView sum;

    ListView list;

    MealSQLOpenHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        log = (Button)findViewById(R.id.log);
        typeFilter = (Spinner)findViewById(R.id.filterType);
        list = (ListView) findViewById(R.id.mealList);
        sum = (TextView) findViewById(R.id.calorieSum);

        list.setEmptyView(findViewById(R.id.empty));

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LogActivity.class);
                startActivity(intent);
            }
        });

        typeFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    filterDataByType();
                } else {
                    refreshData();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        dbHelper = new MealSQLOpenHelper(this);

        refreshData();

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, LogActivity.class);
                intent.putExtra("id", (int) id);
                startActivity(intent);
            }
        });

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final int mealId = (int) id;
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("Delete log?");
                dialog.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dbHelper.deleteMeal(mealId);
                        dialog.dismiss();
                        refreshData();
                    }
                });
                dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
                return true;
            }
        });

    }

    private void refreshData() {
        MealCursorAdapter adapter = new MealCursorAdapter(this, dbHelper.getMealsCursor());
        list.setAdapter(adapter);
        calculateSum();
    }

    private void filterDataByType() {
        MealCursorAdapter adapter = new MealCursorAdapter(this, dbHelper.getFilteredMeals(typeFilter.getSelectedItem().toString()));
        list.setAdapter(adapter);
        calculateSum();
    }

    private void calculateSum() {
        sum.setText(dbHelper.getCaloriesSum(typeFilter.getSelectedItem().toString()));
    }

}
