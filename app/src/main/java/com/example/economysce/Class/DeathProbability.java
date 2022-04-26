package com.example.economysce.Class;

public class DeathProbability {
    private double age;
    private double getqMan;
    private double getqWoman;
    public DeathProbability(double age, double getqWoman, double getqMan) {
        this.age = age;
        this.getqMan = getqMan;
        this.getqWoman = getqWoman;
    }
    public double getAge() {
        return age;
    }
    public double getqMan() {
        return getqMan;
    }
    public double getqWomen() {
        return getqWoman;
    }
}
