package com.example.economysce;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.economysce.Adapters.CalculationAdapter;
import com.example.economysce.Class.Data;
import com.example.economysce.Class.DeathProbability;
import com.example.economysce.Class.LeavingProbability;
import com.example.economysce.Class.User;

import java.util.ArrayList;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.O)
public class CalculationFragment extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<LeavingProbability> leavingProbabilityList = new ArrayList<>();
    private ArrayList<DeathProbability> deathProbabilityArrayList = new ArrayList<>();
    private Data data = new Data();
    private double SalaryGrowthRate = 0.05;
    private CalculationAdapter calculationAdapter;
    private ArrayList<Double> reductionList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calculation,container,false);
        AddLeavingProbability();
        AddDeathProbability();
        for(int i=0; i<data.getDiscountRateList().size() ; i++)
            data.getDiscountRateList().get(i).setPercent(data.getDiscountRateList().get(i).getPercent() / 100);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        reductionList = new ArrayList<>();
        readUsers();
        return view;
    }
    private void readUsers(){
        List<User> users = new ArrayList<>();
        int index = 0;
        for (User user : data.getUserList()) {
            if(index % 2 == 0)
                SalaryGrowthRate = 0.05;
            else
                SalaryGrowthRate = 0.03;
            users.add(user);
            reductionList.add(getUserPayment(user));
        }
        calculationAdapter = new CalculationAdapter(getContext(), users, reductionList);
        recyclerView.setAdapter(calculationAdapter);




    }
    private Double getUserPayment(User user){
        //have left date
        if(!user.getReasonForLeaving().equals(""))
            return 0.0;
        //Worker A
        else if(user.getAcceptSection14() == null)
            return CalcWorkerAorB(user);
        //worker B
        else if(user.getAcceptSection14() != null && user.getAcceptSection14().getYear() == user.getStartWork().getYear() && user.getAcceptSection14().getMonth() == user.getStartWork().getMonth() && user.getAcceptSection14().getDate() == user.getStartWork().getDate()) // Worker B
            return CalcWorkerAorB(user);
        //Worker C
        return CalcWorkerC(user);
    }
    private int getW(User user){
        int w = 67;
        if (user.getGender().equals("F"))
            w -= 3;
        return w;
    }
    private double getSalaryGrowthRate(double year){
        if (year % 2 == 0)
            return SalaryGrowthRate;
        return 0;
    }
    private double CalcFired(User user, int t, int x){
        return UpperPart( user.getSalary(), user.getVetek(), user.getPercentSection14(), t - 1, P(user, t +1), getFired(x + t + 1)) / LowerPart(data.getDiscountRateList().get(t-1).getPercent(), t - 1);
    }
    private double CalcLeaving(User user, int t, int x){
        return user.getAssetValue() * P(user, t + 1) * getLeaving(x + t + 1);
    }
    private double CalcDeath(User user, int t, int x){
        return UpperPart(user.getSalary(), user.getVetek(), user.getPercentSection14(), t - 1, P(user, t +1), getDeath(x + t + 1, user.getGender())) / LowerPart(data.getDiscountRateList().get(t-1).getPercent(), t-1);
    }
    private double UpperPart(double LastSalary, double Seniority, double Section14Rate, double t, double p, double q){
        return LastSalary * Seniority * (1 - Section14Rate) * Math.pow(1 + getSalaryGrowthRate(t), t + 0.5) * p * q;
    }
    private double LowerPart(double discountRate, double t){ return Math.pow(1 + discountRate, t + 0.5); }
    private double P(User user , int t){
        double sol = 1;
        for(int i=1; i < t  ;i++)
            sol *= (1 - getLeaving(user.getAge() + i ) - getFired(user.getAge() + i ) - getDeath(user.getAge() + i, user.getGender()));
        return sol;
    }
    private double CalcWorkerAorB(User user){
        double sol = 0;
        int w = getW(user), x = user.getAge(), t;
        for (t = 1; t <= w - x - 2 ; t++)
            sol += CalcFired(user, t, x) + CalcLeaving(user, t, x) + CalcDeath(user, t, x);
        double  p = P(user, t + 1 );
        sol += ( ( user.getSalary() * user.getVetek() * (1 - user.getPercentSection14() ) * Math.pow(1 + getSalaryGrowthRate(t), t + 0.5) * p * getFired(w - 1) ) / Math.pow(1 + data.getDiscountRateList().get(t-1).getPercent(), w - x + 0.5) ) +
               ( ( user.getSalary() * user.getVetek() * (1 - user.getPercentSection14() ) * Math.pow(1 + getSalaryGrowthRate(t), t + 0.5) * p *getDeath(w - 1, user.getGender()) ) / Math.pow(1 + data.getDiscountRateList().get(t-1).getPercent(), w - x + 0.5) ) +
               ( user.getAssetValue() * p * getLeaving(w - 1) ) +
               ( ( user.getSalary() * user.getVetek() * (1 - user.getPercentSection14() ) * Math.pow(1 + getSalaryGrowthRate(t), t + 0.5) * p * ( 1 - getFired(w - 1) - getDeath(w - 1, user.getGender()) - getLeaving(w - 1) ) ) / Math.pow(1 + data.getDiscountRateList().get(t-1).getPercent(), w - x + 0.5) );
        return sol;
    }
    private double CalcWorkerC(User user){
        double sol = 0, YearToSection14 = getYearsToSection14(user), Section14Percent = user.getPercentSection14() ;
        user.setPercentSection14(0);
        int w = getW(user), x = user.getAge(), t;
        for (t = 1; t <= w - x - 2; t++) {
            if(YearToSection14 < 0)
                user.setPercentSection14(Section14Percent);
            sol += CalcFired(user, t, x) + CalcLeaving(user, t, x) + CalcDeath(user, t, x);
            YearToSection14--;
        }
        double  p = P(user, t + 1 );
        sol += ( ( user.getSalary() * user.getVetek() * (1 - user.getPercentSection14() ) * Math.pow(1 + getSalaryGrowthRate(t), t + 0.5) * p * getFired(w - 1) ) / Math.pow(1 + data.getDiscountRateList().get(t-1).getPercent(), w - x + 0.5) ) +
               ( ( user.getSalary() * user.getVetek() * (1 - user.getPercentSection14() ) * Math.pow(1 + getSalaryGrowthRate(t), t + 0.5) * p *getDeath(w - 1, user.getGender()) ) / Math.pow(1 + data.getDiscountRateList().get(t-1).getPercent(), w - x + 0.5) ) +
               ( user.getAssetValue() * p * getLeaving(w - 1) ) +
               ( ( user.getSalary() * user.getVetek() * (1 - user.getPercentSection14() ) * Math.pow(1 + getSalaryGrowthRate(t), t + 0.5) * p * ( 1 - getFired(w - 1) - getDeath(w - 1, user.getGender()) - getLeaving(w - 1) ) ) / Math.pow(1 + data.getDiscountRateList().get(t-1).getPercent(), w - x + 0.5) );
        return sol;
    }
    private double getFired(int age){
        double Fired = 0;
        for(int j=0; j< leavingProbabilityList.size();j++)
            if(leavingProbabilityList.get(j).getFromAge() <= age && age <= leavingProbabilityList.get(j).getToAge())
                Fired = leavingProbabilityList.get(j).getFiredPercent() / 100;
        return Fired;
    }
    private double getLeaving(int age){
        double Leaving = 0;
        for(int j=0; j< leavingProbabilityList.size();j++)
            if(leavingProbabilityList.get(j).getFromAge() <= age && age <= leavingProbabilityList.get(j).getToAge())
                Leaving = leavingProbabilityList.get(j).getResignPercent() / 100;
        return Leaving;
    }
    private double getDeath(int age , String gender){
        double Death = 0;
        for(int j=0; j< deathProbabilityArrayList.size();j++)
            if(deathProbabilityArrayList.get(j).getAge() == age) {
                if (gender.equals("M"))
                    Death = deathProbabilityArrayList.get(j).getqMan();
                else
                    Death = deathProbabilityArrayList.get(j).getqWomen();
            }
        return Death;
    }
    private void AddLeavingProbability(){
        leavingProbabilityList.add(new LeavingProbability(18,29,10,25));
        leavingProbabilityList.add(new LeavingProbability(30,39,8,20));
        leavingProbabilityList.add(new LeavingProbability(40,49,6,10));
        leavingProbabilityList.add(new LeavingProbability(50,59,3,7));
        leavingProbabilityList.add(new LeavingProbability(60,67,2,3));
    }
    private void AddDeathProbability(){
        deathProbabilityArrayList.add(new DeathProbability(18,0.000045,0.000140));
        deathProbabilityArrayList.add(new DeathProbability(19,0.000049,0.000155));
        deathProbabilityArrayList.add(new DeathProbability(20,0.000051,0.000166));
        deathProbabilityArrayList.add(new DeathProbability(21,0.000055,0.000176));
        deathProbabilityArrayList.add(new DeathProbability(22,0.000059,0.000182));
        deathProbabilityArrayList.add(new DeathProbability(23,0.000063,0.000187));
        deathProbabilityArrayList.add(new DeathProbability(24,0.000066,0.000190));
        deathProbabilityArrayList.add(new DeathProbability(25,0.000071,0.000191));
        deathProbabilityArrayList.add(new DeathProbability(26,0.000076,0.000192));
        deathProbabilityArrayList.add(new DeathProbability(27,0.000080,0.000192));
        deathProbabilityArrayList.add(new DeathProbability(28,0.000082,0.000193));
        deathProbabilityArrayList.add(new DeathProbability(29,0.000085,0.000196));
        deathProbabilityArrayList.add(new DeathProbability(30,0.000089,0.000200));
        deathProbabilityArrayList.add(new DeathProbability(31,0.000093,0.000205));
        deathProbabilityArrayList.add(new DeathProbability(32,0.000096,0.000213));
        deathProbabilityArrayList.add(new DeathProbability(33,0.000102,0.000223));
        deathProbabilityArrayList.add(new DeathProbability(34,0.000108,0.000234));
        deathProbabilityArrayList.add(new DeathProbability(35,0.000114,0.000246));
        deathProbabilityArrayList.add(new DeathProbability(36,0.000123,0.000261));
        deathProbabilityArrayList.add(new DeathProbability(37,0.000133,0.000279));
        deathProbabilityArrayList.add(new DeathProbability(38,0.000144,0.000300));
        deathProbabilityArrayList.add(new DeathProbability(39,0.000158,0.000324));
        deathProbabilityArrayList.add(new DeathProbability(40,0.000173,0.000353));
        deathProbabilityArrayList.add(new DeathProbability(41,0.000192,0.000390));
        deathProbabilityArrayList.add(new DeathProbability(42,0.000213,0.000430));
        deathProbabilityArrayList.add(new DeathProbability(43,0.000237,0.000477));
        deathProbabilityArrayList.add(new DeathProbability(44,0.000262,0.000530));
        deathProbabilityArrayList.add(new DeathProbability(45,0.000291,0.000587));
        deathProbabilityArrayList.add(new DeathProbability(46,0.000322,0.000649));
        deathProbabilityArrayList.add(new DeathProbability(47,0.000357,0.000717));
        deathProbabilityArrayList.add(new DeathProbability(48,0.000395,0.000790));
        deathProbabilityArrayList.add(new DeathProbability(49,0.000437,0.000872));
        deathProbabilityArrayList.add(new DeathProbability(50,0.000483,0.000961));
        deathProbabilityArrayList.add(new DeathProbability(51,0.000533,0.001062));
        deathProbabilityArrayList.add(new DeathProbability(52,0.000589,0.001175));
        deathProbabilityArrayList.add(new DeathProbability(53,0.000650,0.001302));
        deathProbabilityArrayList.add(new DeathProbability(54,0.000716,0.001445));
        deathProbabilityArrayList.add(new DeathProbability(55,0.000788,0.001604));
        deathProbabilityArrayList.add(new DeathProbability(56,0.000865,0.001778));
        deathProbabilityArrayList.add(new DeathProbability(57,0.000949,0.001967));
        deathProbabilityArrayList.add(new DeathProbability(58,0.001040,0.002175));
        deathProbabilityArrayList.add(new DeathProbability(59,0.001137,0.002401));
        deathProbabilityArrayList.add(new DeathProbability(60,0.001240,0.002647));
        deathProbabilityArrayList.add(new DeathProbability(61,0.001350,0.002909));
        deathProbabilityArrayList.add(new DeathProbability(62,0.001466,0.003185));
        deathProbabilityArrayList.add(new DeathProbability(63,0.001587,0.003475));
        deathProbabilityArrayList.add(new DeathProbability(64,0.002723,0.003774));
        deathProbabilityArrayList.add(new DeathProbability(65,0.003132,0.004085));
        deathProbabilityArrayList.add(new DeathProbability(66,0.003642,0.004407));
        deathProbabilityArrayList.add(new DeathProbability(67,0.004232,0.008113));
        deathProbabilityArrayList.add(new DeathProbability(68,0.004912,0.008957));
        deathProbabilityArrayList.add(new DeathProbability(69,0.005699,0.009874));
        deathProbabilityArrayList.add(new DeathProbability(70,0.006606,0.010869));
        deathProbabilityArrayList.add(new DeathProbability(71,0.007652,0.011945));
        deathProbabilityArrayList.add(new DeathProbability(72,0.008848,0.013236));
        deathProbabilityArrayList.add(new DeathProbability(73,0.010303,0.014654));
        deathProbabilityArrayList.add(new DeathProbability(74,0.011900,0.016368));
        deathProbabilityArrayList.add(new DeathProbability(75,0.013842,0.018447));
        deathProbabilityArrayList.add(new DeathProbability(76,0.015746,0.020677));
        deathProbabilityArrayList.add(new DeathProbability(77,0.018052,0.023612));
        deathProbabilityArrayList.add(new DeathProbability(78,0.020694,0.027641));
        deathProbabilityArrayList.add(new DeathProbability(79,0.023737,0.032286));
        deathProbabilityArrayList.add(new DeathProbability(80,0.027247,0.037575));
        deathProbabilityArrayList.add(new DeathProbability(81,0.031596,0.043838));
        deathProbabilityArrayList.add(new DeathProbability(82,0.036651,0.050852));
        deathProbabilityArrayList.add(new DeathProbability(83,0.042533,0.058666));
        deathProbabilityArrayList.add(new DeathProbability(84,0.049293,0.067313));
        deathProbabilityArrayList.add(new DeathProbability(85,0.057019,0.076832));
        deathProbabilityArrayList.add(new DeathProbability(86,0.065777,0.087263));
        deathProbabilityArrayList.add(new DeathProbability(87,0.075663,0.098645));
        deathProbabilityArrayList.add(new DeathProbability(88,0.086746,0.111019));
        deathProbabilityArrayList.add(new DeathProbability(89,0.099229,0.124413));
        deathProbabilityArrayList.add(new DeathProbability(90,0.113196,0.138893));
        deathProbabilityArrayList.add(new DeathProbability(91,0.126997,0.154491));
        deathProbabilityArrayList.add(new DeathProbability(92,0.142238,0.170162));
        deathProbabilityArrayList.add(new DeathProbability(93,0.159050,0.185534));
        deathProbabilityArrayList.add(new DeathProbability(94,0.177498,0.201369));
        deathProbabilityArrayList.add(new DeathProbability(95,0.197745,0.217557));
        deathProbabilityArrayList.add(new DeathProbability(96,0.219823,0.234041));
        deathProbabilityArrayList.add(new DeathProbability(97,0.245017,0.250693));
        deathProbabilityArrayList.add(new DeathProbability(98,0.268079,0.267389));
        deathProbabilityArrayList.add(new DeathProbability(99,0.284614,0.284001));
        deathProbabilityArrayList.add(new DeathProbability(100,0.302162,0.301638));
        deathProbabilityArrayList.add(new DeathProbability(101,0.320821,0.320365));
        deathProbabilityArrayList.add(new DeathProbability(102,0.340591,0.340212));
        deathProbabilityArrayList.add(new DeathProbability(103,0.361574,0.361319));
        deathProbabilityArrayList.add(new DeathProbability(104,0.383882,0.383728));
        deathProbabilityArrayList.add(new DeathProbability(105,0.407519,0.407519));
        deathProbabilityArrayList.add(new DeathProbability(106,0.430880,0.430880));
        deathProbabilityArrayList.add(new DeathProbability(107,0.455580,0.455580));
        deathProbabilityArrayList.add(new DeathProbability(108,0.481697,0.481697));
        deathProbabilityArrayList.add(new DeathProbability(109,0.509311,0.509311));
        deathProbabilityArrayList.add(new DeathProbability(110,1.000000,1.000000));
    }
    private int getYearsToSection14(User user){
        int YearToSection14 = user.getAcceptSection14().getYear() - user.getStartWork().getYear();
        if(user.getStartWork().getMonth() > user.getAcceptSection14().getMonth())
            YearToSection14 -= 1;
        else if(user.getAcceptSection14().getMonth() == user.getAcceptSection14().getMonth())
            if(user.getAcceptSection14().getDate() > user.getAcceptSection14().getDate())
                YearToSection14 -= 1;
        return YearToSection14;
    }
}