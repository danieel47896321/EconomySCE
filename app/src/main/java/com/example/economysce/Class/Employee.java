package com.example.economysce.Class;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.temporal.ChronoUnit;
import java.util.Date;

public class Employee {
    private String Id;
    private String FirstName;
    private String LastName;
    private String Gender;
    private myDate BirthDay;
    private myDate StartWork;
    private double Salary;
    private myDate Section14StartDate;
    private double Section14Percent;
    private double AssetValue;
    private myDate LeftDate;
    private double Seniority;
    private double Deposits;
    private int Age;
    private String ReasonForLeaving;
    @RequiresApi(api = Build.VERSION_CODES.O)
    public Employee(String id, String firstName, String lastName, String gender, myDate birthDay, myDate startWork, double salary, myDate section14StartDate, double section14Percent, double assetValue, myDate leftDate, String reasonForLeaving, double deposits) {
        Id = id;
        FirstName = firstName;
        LastName = lastName;
        Gender = gender;
        BirthDay = birthDay;
        StartWork = startWork;
        Salary = salary;
        Section14StartDate = section14StartDate;
        Section14Percent = section14Percent / 100;
        AssetValue = assetValue;
        LeftDate = leftDate;
        ReasonForLeaving = reasonForLeaving;
        Deposits = deposits;
        Age = CalculateAge(BirthDay.getDate());
        Seniority = CalculateSeniority(StartWork.getDate());
    }
    public String getReasonForLeaving() {
        return ReasonForLeaving;
    }
    public int getAge() { return Age; }
    public double getSeniority() { return Seniority; }
    public String getId() { return Id; }
    public void setId(String id) { Id = id; }public String getFirstName() { return FirstName; }
    public String getLastName() { return LastName; }
    public String getGender() { return Gender; }
    public myDate getBirthDay() { return BirthDay; }
    public myDate getStartWork() { return StartWork; }
    public double getSalary() { return Salary; }
    public double getDeposits() {
        return Deposits;
    }
    public myDate getSection14StartDate() { return Section14StartDate; }
    public double getSection14Percent() { return Section14Percent; }
    public double getAssetValue() { return AssetValue; }
    public myDate getLeftDate() { return LeftDate; }
    private int CalculateAge(Date date){
        int years = 2021 - date.getYear();
        if(date.getMonth() >  12 || (date.getMonth() ==  12 && date.getDate() > 31))
            years -=1;
        return years;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private double CalculateSeniority(Date date){
        Date date1 = new Date(2021,12,31);
        return ChronoUnit.DAYS.between(date.toInstant(), date1.toInstant())/365.25;
    }
}
