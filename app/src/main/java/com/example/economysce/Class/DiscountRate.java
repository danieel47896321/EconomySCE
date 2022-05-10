package com.example.economysce.Class;

public class DiscountRate {
    private int Year;
    private double Percent;
    public DiscountRate(int year, double percent) {
        Year = year;
        Percent = percent;
    }
    public double getPercent() { return Percent; }
    public void setPercent(double percent) {
        Percent = percent;
    }
}
