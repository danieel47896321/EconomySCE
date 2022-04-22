package com.example.economysce.Class;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.temporal.ChronoUnit;
import java.util.Date;

public class User {
    private String Id;
    private String Name;
    private String LastName;
    private String Gender;
    private Date BirthDay;
    private Date StartWork;
    private double Salary;
    private Date AcceptSection14;
    private double PercentSection14;
    private double AssetValue;
    private Date LeftDate;
    private double Vetek;
    private int Age = 1;
    private String ReasonForLeaving;
    @RequiresApi(api = Build.VERSION_CODES.O)
    public User(String id, String name, String lastName, String gender, Date birthDay, Date startWork, double salary, Date acceptSection14, double percentSection14, double assetValue, Date leftDate, String reasonForLeaving) {
        Id = id;
        Name = name;
        LastName = lastName;
        Gender = gender;
        BirthDay = birthDay;
        StartWork = startWork;
        Salary = salary;
        AcceptSection14 = acceptSection14;
        PercentSection14 = percentSection14 / 100;
        AssetValue = assetValue;
        LeftDate = leftDate;
        Age = getYears(BirthDay);
        Vetek = getVetek(StartWork);
        ReasonForLeaving = reasonForLeaving;
    }
    public String getReasonForLeaving() {
        return ReasonForLeaving;
    }
    public void setReasonForLeaving(String reasonForLeaving) { ReasonForLeaving = reasonForLeaving; }
    public void setPercentSection14(double percentSection14) { PercentSection14 = percentSection14; }
    public int getAge() { return Age; }
    public double getVetek() { return Vetek; }
    public String getId() { return Id; }
    public void setId(String id) { Id = id; }public String getName() { return Name; }
    public String getLastName() { return LastName; }
    public String getGender() { return Gender; }
    public Date getBirthDay() { return BirthDay; }
    public Date getStartWork() { return StartWork; }
    public double getSalary() { return Salary; }
    public Date getAcceptSection14() { return AcceptSection14; }
    public double getPercentSection14() { return PercentSection14; }
    public double getAssetValue() { return AssetValue; }
    public Date getLeftDate() { return LeftDate; }
    private int getYears(Date date){
        int years = 2021 - date.getYear();
        if(date.getMonth() >  12 || (date.getMonth() ==  12 && date.getDate() > 31))
            years -=1;
        return years;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private double getVetek(Date date){
        Date date1 = new Date(2021,12,31);
        return ChronoUnit.DAYS.between(date.toInstant(), date1.toInstant())/365.25;
    }
}
