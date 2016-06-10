package com.example.ucenik.itspraksadnevnik;

import java.io.Serializable;

/**
 * Created by Ucenik on 10/06/2016.
 */
public class Meal implements Serializable {
    int id;
    String name;
    String mealType;
    int calorie;
    String date;
    String time;

    public Meal() {
    }

    public Meal(int id, String name, String mealType, int calorie, String date, String time) {
        this.id = id;
        this.name = name;
        this.mealType = mealType;
        this.calorie = calorie;
        this.date = date;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public int getCalorie() {
        return calorie;
    }

    public void setCalorie(int calorie) {
        this.calorie = calorie;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
