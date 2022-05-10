package com.example.economysce.Class;
import java.util.Date;

public class myDate {
    private int day;
    private int month;
    private int year;
    public myDate(int year, int month, int day) {
        this.day = day;
        this.month = month;
        this.year = year;
    }
    public Date getDate() {
        return new Date(year,month,day);
    }
    public int getDay() {
        return day;
    }
    public int getMonth() {
        return month;
    }
    public int getYear() {
        return year;
    }
}
