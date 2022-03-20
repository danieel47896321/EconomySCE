package com.example.economysce.Class;

public class LeavingProbability {
    int FromAge;
    int ToAge;
    int FiredPercent;
    int ResignPercent;
    public LeavingProbability(int fromAge, int toAge, int firedPercent, int resignPercent) {
        FromAge = fromAge;
        ToAge = toAge;
        FiredPercent = firedPercent;
        ResignPercent = resignPercent;
    }
    public int getFromAge() { return FromAge; }
    public int getToAge() { return ToAge; }
    public int getFiredPercent() { return FiredPercent; }
    public int getResignPercent() { return ResignPercent; }
}
