package com.example.economysce.Class;

public class DiscountRate {
    int Year;
    double Percent;
    public DiscountRate(int year, double percent) {
        Year = year;
        Percent = percent;
    }
    public int getYear() { return Year; }
    public double getPercent() { return Percent; }
    public void setYear(int year) {
        Year = year;
    }
    public void setPercent(double percent) {
        Percent = percent;
    }
}
