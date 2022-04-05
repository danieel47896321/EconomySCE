package com.example.economysce.Class;

public class LeavingProbability {
    int FromAge;
    int ToAge;
    double FiredPercent;
    double ResignPercent;
    public LeavingProbability(int fromAge, int toAge, double firedPercent, double resignPercent) {
        FromAge = fromAge;
        ToAge = toAge;
        FiredPercent = firedPercent;
        ResignPercent = resignPercent;
    }
    public int getFromAge() { return FromAge; }
    public int getToAge() { return ToAge; }
    public double getFiredPercent() { return FiredPercent; }
    public double getResignPercent() { return ResignPercent; }
}
