package com.example.economysce.Class;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Data {
    private List<LeavingProbability> leavingProbabilityList;
    private List<DiscountRate> discountRateList;
    private List<User> userList;
    public Data( ) {
        this.userList = new ArrayList<>();
        this.leavingProbabilityList = new ArrayList<>();
        this.discountRateList = new ArrayList<>();
        AddUsers();
        AddLeavingProbability();
        AddDiscountRate();
    }
    private void AddUsers(){
        userList.add(new User("1","חיים","גלוקמן","M", new Date(1985,6,9) ,new Date(2013,12,10),new Double(7000),new Date(2013,12,10),100,new Double(0),new Date(1,1,1)));
        userList.add(new User("2","שרה","אברג'ל","F", new Date(1985,5,23) ,new Date(2014,7,10),new Double(21000),new Date(2014,7,10),72,new Double(0),new Date(1,1,1)));
        userList.add(new User("3","מאיר","טרבלסי","M", new Date(1965,10,17) ,new Date(1991,3,10),new Double(23000),new Date(1,1,1),0,new Double(493533),new Date(2020,6,1)));
        userList.add(new User("4","אירית","אשכנזי","F", new Date(1967,5,6) ,new Date(2002,7,7),new Double(10000),new Date(1,1,1),0,new Double(128799),new Date(1,1,1)));
        userList.add(new User("5","נתנאל","קיופמן","M", new Date(1967,5,28) ,new Date(2016,9,1),new Double(11500),new Date(2016,9,1),100,new Double(0),new Date(2021,10,1)));
        userList.add(new User("13","מיכאל","גולד","M", new Date(1973,8,27) ,new Date(2000,11,7),new Double(24500),new Date(2016,1,1),100,new Double(224888),new Date(1,1,1)));
    }
    private void AddLeavingProbability(){
        leavingProbabilityList.add(new LeavingProbability(18,29,7,20));
        leavingProbabilityList.add(new LeavingProbability(30,39,5,13));
        leavingProbabilityList.add(new LeavingProbability(40,49,4,10));
        leavingProbabilityList.add(new LeavingProbability(50,59,3,7));
        leavingProbabilityList.add(new LeavingProbability(60,67,2,3));
    }
    private void AddDiscountRate(){
        discountRateList.add(new DiscountRate(1,1.81));
        discountRateList.add(new DiscountRate(2,1.99));
        discountRateList.add(new DiscountRate(3,2.11));
        discountRateList.add(new DiscountRate(4,2.21));
        discountRateList.add(new DiscountRate(5,2.30));
        discountRateList.add(new DiscountRate(6,2.39));
        discountRateList.add(new DiscountRate(7,2.46));
        discountRateList.add(new DiscountRate(8,2.53));
        discountRateList.add(new DiscountRate(9,2.60));
        discountRateList.add(new DiscountRate(10,2.67));
        discountRateList.add(new DiscountRate(11,2.74));
        discountRateList.add(new DiscountRate(12,2.80));
        discountRateList.add(new DiscountRate(13,2.86));
        discountRateList.add(new DiscountRate(14,2.92));
        discountRateList.add(new DiscountRate(15,2.99));
        discountRateList.add(new DiscountRate(16,3.05));
        discountRateList.add(new DiscountRate(17,3.11));
        discountRateList.add(new DiscountRate(18,3.17));
        discountRateList.add(new DiscountRate(19,3.23));
        discountRateList.add(new DiscountRate(20,3.29));
        discountRateList.add(new DiscountRate(21,3.35));
        discountRateList.add(new DiscountRate(22,3.41));
        discountRateList.add(new DiscountRate(23,3.48));
        discountRateList.add(new DiscountRate(24,3.54));
        discountRateList.add(new DiscountRate(25,3.60));
        discountRateList.add(new DiscountRate(26,3.66));
        discountRateList.add(new DiscountRate(27,3.72));
        discountRateList.add(new DiscountRate(28,3.78));
        discountRateList.add(new DiscountRate(29,3.84));
        discountRateList.add(new DiscountRate(30,3.91));
        discountRateList.add(new DiscountRate(31,3.97));
        discountRateList.add(new DiscountRate(32,4.03));
        discountRateList.add(new DiscountRate(33,4.09));
        discountRateList.add(new DiscountRate(34,4.15));
        discountRateList.add(new DiscountRate(35,4.21));
        discountRateList.add(new DiscountRate(36,4.27));
        discountRateList.add(new DiscountRate(37,4.34));
        discountRateList.add(new DiscountRate(38,4.40));
        discountRateList.add(new DiscountRate(39,4.46));
        discountRateList.add(new DiscountRate(40,4.52));
        discountRateList.add(new DiscountRate(41,4.58));
        discountRateList.add(new DiscountRate(42,4.64));
        discountRateList.add(new DiscountRate(43,4.70));
        discountRateList.add(new DiscountRate(44,4.76));
        discountRateList.add(new DiscountRate(45,4.83));
        discountRateList.add(new DiscountRate(46,4.89));
        discountRateList.add(new DiscountRate(47,4.95));
    }
    public List<User> getUserList() { return userList; }
    public List<LeavingProbability> getLeavingProbabilityList() { return leavingProbabilityList; }
    public List<DiscountRate> getDiscountRateList() { return discountRateList; }
}
