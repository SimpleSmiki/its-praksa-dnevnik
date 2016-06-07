package com.example.ucenik.itspraksadnevnik;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class LogActivity extends AppCompatActivity {

    Spinner mealType;
    EditText name;
    EditText calorie;
    Button setTime;
    Button setDate;
    TextView time;
    TextView date;
    Button save;

    MealSQLOpenHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        mealType = (Spinner) findViewById(R.id.setType);
        name = (EditText) findViewById(R.id.editName);
        calorie = (EditText) findViewById(R.id.editCalorie);
        setTime = (Button) findViewById(R.id.setTime);
        setDate = (Button) findViewById(R.id.setDate);
        time = (TextView) findViewById(R.id.txtTime);
        date = (TextView) findViewById(R.id.txtDate);
        save = (Button) findViewById(R.id.saveLog);

        dbHelper = new MealSQLOpenHelper(this);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                dbHelper.addMeal();
            }
        });
    }
}
