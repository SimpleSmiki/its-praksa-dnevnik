package com.example.ucenik.itspraksadnevnik;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

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
    Meal meal;

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

        setDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                DatePickerDialog dpd = new DatePickerDialog(LogActivity.this, dateSetListener, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
                dpd.show();
            }
        });

        setTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                TimePickerDialog tpd = new TimePickerDialog(LogActivity.this, timeSetListener, c.get(Calendar.HOUR), c.get(Calendar.MINUTE), true);
                tpd.show();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()){
                    dbHelper.addMeal(meal.getMealType(), meal.getName(), meal.getCalorie(),
                        meal.getDate(), meal.getTime());
                    finish();
                }
            }
        });
    }

    private boolean validate() {
        meal = new Meal();
        boolean valid = true;

        meal.setMealType(mealType.getSelectedItem().toString());

        String mName = name.getText().toString();
        if (mName.length() > 3) {
            meal.setName(mName);
        } else {
            name.setError("Name must contain 4 letters");
            valid = false;
        }

        String numCal = calorie.getText().toString();
        if (numCal.equals("")) {
            calorie.setError("Calories must be entered");
            valid = false;
        } else {
            int numC = Integer.parseInt(numCal);
            if (numC == 0) {
                calorie.setError("Calories must be greater than zero");
            } else {
                meal.setCalorie(numC);
            }
        }

        String d = date.getText().toString();
        if (d.equals("")) {
            date.setError("Please pick date");
            valid = false;
        } else {
            meal.setDate(d);
            date.setError(null);
        }

        String t = time.getText().toString();
        if (t.equals("")) {
            time.setError("Please pick time");
            valid = false;
        } else {
            meal.setTime(t);
            time.setError(null);
        }

        return valid;
    }

    DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            date.setText(dayOfMonth + "/" + (monthOfYear + 1)+ "/" + year);
        }
    };


    private TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            time.setText(hourOfDay + ":" + minute);
        }
    };
}
