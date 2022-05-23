package com.example.economysce.Class;

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
    private double AssetPayment;
    private double CheckCompletion;
    private double RestOpen;
    private double RestAsset;
    public Employee(String id, String firstName, String lastName, String gender, myDate birthDay, myDate startWork, double salary, myDate section14StartDate, double section14Percent, double assetValue, double deposits, myDate leftDate, String reasonForLeaving,double AssetPayment, double CheckCompletion,double RestOpen, double RestAsset) {
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
        this.RestOpen = RestOpen;
        this.RestAsset = RestAsset;
        this.CheckCompletion = CheckCompletion;
        this.AssetPayment = AssetPayment;
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
    private double CalculateSeniority(Date date){
        Date date1 = new Date(2021,12,31);
        return ChronoUnit.DAYS.between(date.toInstant(), date1.toInstant())/365.25;
    }
    public double getAssetPayment() {
        return AssetPayment;
    }
    public void setAssetPayment(double assetPayment) {
        AssetPayment = assetPayment;
    }
    public double getCheckCompletion() {
        return CheckCompletion;
    }
    public void setCheckCompletion(double checkCompletion) {
        CheckCompletion = checkCompletion;
    }
    public double getRestOpen() {
        return RestOpen;
    }
    public void setRestOpen(double restOpen) {
        RestOpen = restOpen;
    }
    public double getRestAsset() {
        return RestAsset;
    }
    public void setRestAsset(double restAsset) {
        RestAsset = restAsset;
    }
}
