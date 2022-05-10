package com.example.economysce.Class;

public class EmployeeCalculation {
    private String Id;
    private String Name;
    private String LastName;
    private double Compensation;
    private double OngoingServiceCost;
    private double DiscountCost;
    private double ActuarialLossGainInLiability;
    private double ExpectedAssetsReturns;
    private double ActuarialLossGainInAssets;
    public EmployeeCalculation(String id, String name, String lastName, double compensation, double ongoingServiceCost, double discountCost, double actuarialLossGainInLiability, double expectedAssetsReturns, double actuarialLossGainInAssets) {
        Id = id;
        Name = name;
        LastName = lastName;
        Compensation = compensation;
        OngoingServiceCost = ongoingServiceCost;
        DiscountCost = discountCost;
        ActuarialLossGainInLiability = actuarialLossGainInLiability;
        ExpectedAssetsReturns = expectedAssetsReturns;
        ActuarialLossGainInAssets = actuarialLossGainInAssets;
    }
    public String getId() {
        return Id;
    }
    public String getName() {
        return Name;
    }
    public String getLastName() {
        return LastName;
    }
    public double getCompensation() {
        return Compensation;
    }
    public double getOngoingServiceCost() {
        return OngoingServiceCost;
    }
    public double getDiscountCost() {
        return DiscountCost;
    }
    public double getActuarialLossGainInLiability() {
        return ActuarialLossGainInLiability;
    }
    public double getExpectedAssetsReturns() {
        return ExpectedAssetsReturns;
    }
    public double getActuarialLossGainInAssets() {
        return ActuarialLossGainInAssets;
    }
}
