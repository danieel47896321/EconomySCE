package com.example.economysce.Class;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class User {
    private String Id;
    private String Name;
    private String LastName;
    private String Gender;
    private  Date BirthDay;
    private Date StartWork;
    private  Double Salary;
    private Date AcceptSection14;
    private double PercentSection14;
    private Double AssetValue;
    private Date LeftDate;
    private int Vetek;
    private int Retirement = 67;
    private List<LeavingProbability> leavingProbabilityList = new ArrayList<>();
    private double Fired;
    private double Leaving;
    private double Death = 0.0001;
    private int Age = 1;
    public User (){}
    public User(String id, String name, String lastName, String gender, Date birthDay, Date startWork, Double salary, Date acceptSection14, int percentSection14, Double assetValue, Date leftDate) {
        Id = id;
        Name = name;
        LastName = lastName;
        Gender = gender;
        BirthDay = birthDay;
        StartWork = startWork;
        Salary = salary;
        AcceptSection14 = acceptSection14;
        PercentSection14 = percentSection14;
        PercentSection14 /= 100;
        AssetValue = assetValue;
        LeftDate = leftDate;
        Age = getYears(BirthDay);
        Retirement -= Age;
        if(Gender.equals("F"))
            Retirement -=3;
        Vetek = getYears(StartWork);
        AddLeavingProbability();
        getFiredAndResign();
    }
    public int getAge() { return Age; }
    public double getDeath() { return Death; }
    public double getFired() { return Fired; }
    public double getLeaving() { return Leaving; }
    public int getVetek() { return Vetek; }
    public int getRetirement() { return Retirement; }
    public String getId() { return Id; }
    public void setId(String id) { Id = id; }public String getName() { return Name; }
    public String getLastName() { return LastName; }
    public String getGender() { return Gender; }
    public Date getBirthDay() { return BirthDay; }
    public Date getStartWork() { return StartWork; }
    public Double getSalary() { return Salary; }
    public Date getAcceptSection14() { return AcceptSection14; }
    public double getPercentSection14() { return PercentSection14; }
    public Double getAssetValue() { return AssetValue; }
    public Date getLeftDate() { return LeftDate; }
    private int getYears(Date date){
        int years = Calendar.getInstance().get(Calendar.YEAR) - date.getYear();
        if(date.getMonth() >  Calendar.getInstance().get(Calendar.MONTH) ||
                (date.getMonth() ==  Calendar.getInstance().get(Calendar.MONTH) &&
                        date.getDate() > Calendar.getInstance().get(Calendar.DAY_OF_WEEK)))
            years -=1;
        return years;
    }
    private void getFiredAndResign(){
        for(int j=0; j< leavingProbabilityList.size();j++)
            if(leavingProbabilityList.get(j).getFromAge() < Age && Age < leavingProbabilityList.get(j).getToAge()){
                Fired = leavingProbabilityList.get(j).getFiredPercent();
                Leaving = leavingProbabilityList.get(j).getResignPercent();
            }
        Fired/=100;
        Leaving/=100;
    }
    private void AddLeavingProbability(){
        leavingProbabilityList.add(new LeavingProbability(18,29,7,20));
        leavingProbabilityList.add(new LeavingProbability(30,39,5,13));
        leavingProbabilityList.add(new LeavingProbability(40,49,4,10));
        leavingProbabilityList.add(new LeavingProbability(50,59,3,7));
        leavingProbabilityList.add(new LeavingProbability(60,67,2,3));
    }
}
