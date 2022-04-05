package com.example.economysce;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.economysce.Adapters.CalculationAdapter;
import com.example.economysce.Class.Data;
import com.example.economysce.Class.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CalculationFragment extends Fragment {
    private RecyclerView recyclerView;
    private Data data = new Data();
    private double AddSalary = 0.03;
    private Date DateAddSalary = new Date(2022,9,1);;
    private CalculationAdapter calculationAdapter;
    private List<Double> reductionList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calculation,container,false);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        reductionList = new ArrayList<>();
        readUsers();
        return view;
    }
    private void readUsers(){
        List<User> users = new ArrayList<>();
        int i=-1;
        for (User user : data.getUserList()) {
            i++;
            users.add(user);
            reductionList.add(getUserPayment(user));
        }
        calculationAdapter = new CalculationAdapter(getContext(), users, reductionList);
        recyclerView.setAdapter(calculationAdapter);
    }
    private Double getUserPayment(User user){
        if(user.getAcceptSection14().getYear() == 1 && user.getPercentSection14() == 0)
            return CalcWorkerA(user);
        else if(user.getAcceptSection14().getYear() == user.getStartWork().getYear() && user.getAcceptSection14().getMonth() == user.getStartWork().getMonth() && user.getAcceptSection14().getDate() == user.getStartWork().getDate())
            return CalcWorkerB(user);
        return CalcWorkerC(user);
    }
    private double CalcWorkerA(User user){
        double sol = 0;
        int Retirement = 67 - user.getAge() - 1;
        if(user.getGender().equals("F"))
            Retirement -= 3;
        for(int i=0; i< Retirement ; i++) {
                double keepWorking = getPx(user, i);
                sol +=  UpperPart(user.getSalary(), user.getVetek(), user.getPercentSection14(), i, keepWorking , user.getFired()) / LowerPart(i) +
                        user.getAssetValue() * keepWorking * user.getLeaving() +
                        UpperPart(user.getSalary(), user.getVetek(), user.getPercentSection14(), i, keepWorking , user.getDeath()) / LowerPart(i);
                user.setAge(user.getAge() + 1);
                user.getFiredAndResign();
        }
        return sol;

    }
    private double CalcWorkerB(User user){
        double sol = 0, keepWorking = 1 - user.getLeaving() - user.getFired() - user.getDeath();
        for(int i=0; i< user.getRetirement() - 1; i++) {
            sol +=  (user.getSalary() * user.getVetek()) * (1-user.getPercentSection14()) * ((Math.pow(1 + AddSalary, i + 0.5) * Math.pow(keepWorking, i) * user.getFired()) / (Math.pow(1 + (data.getDiscountRateList().get(i).getPercent() / 100), i + 0.5))) +
                    (user.getSalary() * user.getVetek()) * (1-user.getPercentSection14()) * ((Math.pow(1 + AddSalary, i + 0.5) * Math.pow(keepWorking, i) * user.getDeath()) / (Math.pow(1 + (data.getDiscountRateList().get(i).getPercent() / 100), i + 0.5)));
        }
        return sol;
    }
    private double CalcWorkerC(User user){
        double sol = 0, keepWorking = 1 - user.getLeaving() - user.getFired() - user.getDeath();
        for(int i=0; i< user.getRetirement() - 1; i++) {
            sol +=  (user.getSalary() * user.getVetek()) * (1-user.getPercentSection14()) * ((Math.pow(1 + AddSalary, i + 0.5) * Math.pow(keepWorking, i) * user.getFired()) / (Math.pow(1 + (data.getDiscountRateList().get(i).getPercent() / 100), i + 0.5))) +
                    user.getAssetValue() * Math.pow(keepWorking, i) * user.getLeaving() +
                    (user.getSalary() * user.getVetek()) * (1-user.getPercentSection14()) * ((Math.pow(1 + AddSalary, i + 0.5) * Math.pow(keepWorking, i) * user.getDeath()) / (Math.pow(1 + (data.getDiscountRateList().get(i).getPercent() / 100), i + 0.5)));
        }
        return sol;
    }
    private double UpperPart(double Salary, double Vetek, double PercentSection14, double Power, double p, double q){
        return Salary * Vetek * (1 - PercentSection14) * Math.pow(1 + AddSalary, Power + 0.5) * p * q;
    }
    private double LowerPart(int index){
        double discountRate = data.getDiscountRateList().get(index).getPercent() / 100 ;
        return Math.pow(1 + discountRate, index + 0.5);
    }
    private double getPx(User user , int index){
        double sol = 1;
        for(int i=0; i < index ;i++) {
            Log.v("TAG", "index: " + sol + "");
            sol *= (1 - user.getLeaving() - user.getFired() - 0.0001);
            user.setAge(user.getAge() + 1);
            user.getFiredAndResign();
        }
        user.setAge(user.getAge()-index);
        return sol;
    }
}