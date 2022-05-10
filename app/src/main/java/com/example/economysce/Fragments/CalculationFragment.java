package com.example.economysce.Fragments;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.economysce.Adapters.CalculationAdapter;
import com.example.economysce.Class.Data;
import com.example.economysce.Class.Employee;
import com.example.economysce.Class.EmployeeCalculation;
import com.example.economysce.R;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.O)
public class CalculationFragment extends Fragment {
    private RecyclerView recyclerView;
    private Button buttonCreateExcel;
    private Data data = Data.getData();
    private Context context;
    private List<EmployeeCalculation> employeeCalculationList;
    private double SalaryGrowthRate = 0.05;
    private CalculationAdapter calculationAdapter;
    private File filePath = new File(Environment.getExternalStorageDirectory() + "/Demo.xls");
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calculation,container,false);
        ActivityCompat.requestPermissions(this.getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);
        context = view.getContext();
        buttonCreateExcel = view.findViewById(R.id.buttonCreateExcel);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        employeeCalculationList = new ArrayList<>();
        EmployeeCalculation();
        return view;
    }

    private void EmployeeCalculation(){
        double compensation, costOfOnGoingService, costOfServiceExpectation, actuarialLossGainInLiability, expectedAssetsReturns, actuarialLossGainInAssets;
        for(int i=0; i< data.getEmployeeList().size(); i++){
            Employee employee = data.getEmployeeList().get(i);
            if(i % 2 == 0)
                SalaryGrowthRate = 0.05;
            else
                SalaryGrowthRate = 0.03;
            compensation = Compensation(employee);
            costOfOnGoingService = CostOfOnGoingService(employee, ActuarialFactory(employee,compensation));
            costOfServiceExpectation = CostOfServiceExpectation(employee, compensation);
            actuarialLossGainInLiability = ActuarialLossGainInLiability(employee, compensation);
            expectedAssetsReturns = ExpectedAssetsReturns(employee);
            actuarialLossGainInAssets = ActuarialLossGainInAssets(employee);
            employeeCalculationList.add(new EmployeeCalculation(employee.getId(),employee.getFirstName(),employee.getLastName(),compensation,costOfOnGoingService,costOfServiceExpectation,actuarialLossGainInLiability,expectedAssetsReturns,actuarialLossGainInAssets));
        }
        calculationAdapter = new CalculationAdapter(getContext(), employeeCalculationList);
        recyclerView.setAdapter(calculationAdapter);
        CreateExcel();
    }

    private double Compensation(Employee employee) {
        //have left date
        if(!employee.getReasonForLeaving().equals(""))
            return 0.0;
        //age higher than retirement
        else if(CheckRetirement(employee))
            return employee.getSalary() * employee.getSeniority();
        //Workers A,B,C
        else if(employee.getSection14StartDate() == null)
            return CalcWorkerAorB(employee);
        //worker B
        else if(employee.getSection14StartDate() != null && employee.getSection14StartDate().getYear() == employee.getStartWork().getYear() && employee.getSection14StartDate().getMonth() == employee.getStartWork().getMonth() && employee.getSection14StartDate().getDay() == employee.getStartWork().getDay()) // Worker B
            return CalcWorkerAorB(employee);
        //Worker C
        return CalcWorkerC(employee);
    }
    private double CostOfOnGoingService(Employee employee, double actuarialFactory) {
        double lastSalary, partialOfTheYearEmployeeWorked = 1, section14Percent;
        lastSalary = employee.getSalary();
        if(employee.getLeftDate() != null)
            if(employee.getLeftDate().getYear() < 2021)
                partialOfTheYearEmployeeWorked = 0;
            else
                partialOfTheYearEmployeeWorked = (double)employee.getLeftDate().getMonth() / 12;
        section14Percent = employee.getSection14Percent();
        return lastSalary * partialOfTheYearEmployeeWorked * (1-section14Percent) * actuarialFactory;
    }

    private double ActuarialFactory(Employee employee, double compensation) {
        double lastSalary, seniority, section14Percent;
        lastSalary = employee.getSalary();
        seniority = employee.getSeniority();
        section14Percent = employee.getSection14Percent();
        if(compensation > 0)
            return compensation / ( lastSalary * seniority * (1-section14Percent) );
        return 0;
    }

    private double CostOfServiceExpectation(Employee employee, double compensation) {
        return 0;
    }

    private double ActuarialLossGainInLiability(Employee employee, double compensation) {
        return 0;
    }

    private double ExpectedAssetsReturns(Employee employee) {
        return 0;
    }

    private double ActuarialLossGainInAssets(Employee employee) {
        return 0;
    }
    public void CreateExcel(){
        buttonCreateExcel.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.R)
            @Override
            public void onClick(View v) {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if(context.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                        String[] permissions ={ Manifest.permission.WRITE_EXTERNAL_STORAGE};
                        requestPermissions(permissions,1);
                    }
                    else {
                        saveFile();
                    }
                }
                else
                    saveFile();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:{
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    saveFile();
                else{ }
            }
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.R)
    private void saveFile(){
        // Create a new workbook
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        HSSFSheet hssfSheet = hssfWorkbook.createSheet("CalculationFragment Sol");
        //headers
        HSSFRow title_Row = hssfSheet.createRow(0);
        HSSFCell title_id = title_Row.createCell(0);
        HSSFCell title_name = title_Row.createCell(1);
        HSSFCell title_lastname = title_Row.createCell(2);
        HSSFCell title_payment = title_Row.createCell(3);
        title_id.setCellValue("מסד");
        title_name.setCellValue("שם");
        title_lastname.setCellValue("שם משפחה");
        title_payment.setCellValue("פיצויים");
        //insert data
        for(int i = 0; i< employeeCalculationList.size(); i++) {
            HSSFRow hssfRow = hssfSheet.createRow(i+1);
            HSSFCell id = hssfRow.createCell(0);
            HSSFCell name = hssfRow.createCell(1);
            HSSFCell lastname = hssfRow.createCell(2);
            HSSFCell payment = hssfRow.createCell(3);
            id.setCellValue(employeeCalculationList.get(i).getId());
            name.setCellValue(employeeCalculationList.get(i).getName());
            lastname.setCellValue(employeeCalculationList.get(i).getLastName());
            payment.setCellValue((int)employeeCalculationList.get(i).getCompensation());
        }
        File root = android.os.Environment.getExternalStorageDirectory();
        File dir = new File (root.getAbsolutePath() + "/download");
        dir.mkdirs();
        File file = new File(dir, "Sol.xls");
        try {
            if (!file.exists()){
                file.createNewFile();
            }
            FileOutputStream fileOutputStream= new FileOutputStream(file);
            hssfWorkbook.write(fileOutputStream);
            if (fileOutputStream!=null){
                fileOutputStream.flush();
                fileOutputStream.close();
            }
            Toast.makeText(context, "קובץ אקסל נוצר בהצלחה!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean CheckRetirement(Employee employee){
        if(employee.getGender().equals("M"))
            if( employee.getAge() > 67)
                return true;
            else if( employee.getAge() > 64)
                return true;
        return false;
    }
    private int getW(Employee employee){
        int w = 67;
        if (employee.getGender().equals("F"))
            w -= 3;
        return w;
    }
    private double getSalaryGrowthRate(double year){
        if (year % 2 == 0)
            return SalaryGrowthRate;
        return 0;
    }
    private double CalcFired(Employee employee, int t, int x){
        return UpperPart( employee.getSalary(), employee.getSeniority(), employee.getSection14Percent(), t - 1, P(employee, t +1), getFired(x + t + 1)) / LowerPart(data.getDiscountRateList().get(t-1).getPercent(), t - 1);
    }
    private double CalcLeaving(Employee employee, int t, int x){
        return employee.getAssetValue() * P(employee, t + 1) * getLeaving(x + t + 1);
    }
    private double CalcDeath(Employee employee, int t, int x){
        return UpperPart(employee.getSalary(), employee.getSeniority(), employee.getSection14Percent(), t - 1, P(employee, t +1), getDeath(x + t + 1, employee.getGender())) / LowerPart(data.getDiscountRateList().get(t-1).getPercent(), t-1);
    }
    private double UpperPart(double LastSalary, double Seniority, double Section14Rate, double t, double p, double q){
        return LastSalary * Seniority * (1 - Section14Rate) * Math.pow(1 + getSalaryGrowthRate(t), t + 0.5) * p * q;
    }
    private double LowerPart(double discountRate, double t){ return Math.pow(1 + discountRate, t + 0.5); }
    private double P(Employee employee, int t){
        double sol = 1;
        for(int i=1; i < t  ;i++)
            sol *= (1 - getLeaving(employee.getAge() + i ) - getFired(employee.getAge() + i ) - getDeath(employee.getAge() + i, employee.getGender()));
        return sol;
    }
    private double CalcWorkerAorB(Employee employee){
        double sol = 0;
        int w = getW(employee), x = employee.getAge(), t;
        for (t = 0; t <= w - x - 2 ; t++)
            sol += CalcFired(employee, t + 1, x) + CalcLeaving(employee, t + 1, x) + CalcDeath(employee, t + 1, x);
        double  p = P(employee, t + 1 );
        sol += ( ( employee.getSalary() * employee.getSeniority() * (1 - employee.getSection14Percent() ) * Math.pow(1 + getSalaryGrowthRate(t), t + 0.5) * p * getFired(w - 1) ) / Math.pow(1 + data.getDiscountRateList().get(t).getPercent(), w - x + 0.5) ) +
               ( ( employee.getSalary() * employee.getSeniority() * (1 - employee.getSection14Percent() ) * Math.pow(1 + getSalaryGrowthRate(t), t + 0.5) * p *getDeath(w - 1, employee.getGender()) ) / Math.pow(1 + data.getDiscountRateList().get(t).getPercent(), w - x + 0.5) ) +
               ( employee.getAssetValue() * p * getLeaving(w - 1) ) +
               ( ( employee.getSalary() * employee.getSeniority() * (1 - employee.getSection14Percent() ) * Math.pow(1 + getSalaryGrowthRate(t), t + 0.5) * p * ( 1 - getFired(w - 1) - getDeath(w - 1, employee.getGender()) - getLeaving(w - 1) ) ) / Math.pow(1 + data.getDiscountRateList().get(t).getPercent(), w - x + 0.5) );
        return sol;
    }
    private double CalcWorkerC(Employee employee){ return Section1(employee) + Section2(employee) + Section3(employee) + Section4(employee) + Section5(employee) + Section6(employee) + Section7(employee); }
    private double Section1(Employee employee) {
        double sum = 0;
        double LastSalary = employee.getSalary();
        double Seniority = employee.getSeniority();
        double Section14Rate = employee.getSection14Percent() / 100;
        int W = getW(employee);
        int X = employee.getAge();
        double YearsToSection14 = getYearsToSection14(employee);
        for( int t = 0; t <= W - X - 2 ; t++){
            double Px = P(employee, t + 1);
            double Qx1 = getFired( X + t + 1);
            double discountRate = data.getDiscountRateList().get(t).getPercent() ;
            sum += LastSalary *  Seniority * (1 - Section14Rate)  * ( Math.pow(1 + getSalaryGrowthRate(t), t + 0.5) * Px * Qx1 / Math.pow(1 + discountRate, t + 0.5) );
        }
        for( int t = 0; t <= W - X - 2 ; t++){
            double Px = P(employee, t + 1);
            double Qx1 = getFired( X + t + 1 );
            double discountRate = data.getDiscountRateList().get(t).getPercent() ;
            sum += LastSalary * (Seniority - YearsToSection14) * (1 - Section14Rate) * ( Math.pow(1 + getSalaryGrowthRate(t), t + 0.5) * Px * Qx1 / Math.pow(1 + discountRate, t + 0.5) );
        }
        return sum;
    }
    private double Section2(Employee employee) {
        double sum = 0;
        double LastSalary = employee.getSalary();
        double Seniority = employee.getSeniority();
        double Section14Rate = employee.getSection14Percent() / 100;
        int W = getW(employee);
        int X = employee.getAge();
        double YearsToSection14 = getYearsToSection14(employee);
        for( int t = 0; t <= W - X - 2 ; t++){
            double Px = P(employee, t + 1);
            double Qx3 = getDeath( X + t + 1 , employee.getGender());
            double discountRate = data.getDiscountRateList().get(t).getPercent() ;
            sum += LastSalary * YearsToSection14  * ( Math.pow(1 + getSalaryGrowthRate(t), t + 0.5) * Px * Qx3 / Math.pow(1 + discountRate, t + 0.5) );
        }
        for( int t = 0; t <= W - X - 2 ; t++){
            double Px = P(employee, t + 1);
            double Qx3 = getDeath( X + t + 1 , employee.getGender());
            double discountRate = data.getDiscountRateList().get(t).getPercent() ;
            sum += LastSalary * (Seniority - YearsToSection14) * (1 - Section14Rate) * ( Math.pow(1 + getSalaryGrowthRate(t), t + 0.5) * Px * Qx3 / Math.pow(1 + discountRate, t + 0.5) );
        }
        return sum;
    }
    private double Section3(Employee employee) {
        double sum = 0;
        double AssetValue = employee.getAssetValue();
        int W = getW(employee);
        int X = employee.getAge();
        for( int t = 0; t <= W - X - 2 ; t++){
            double Px = P(employee, t + 1);
            double Qx2 = getLeaving( X + t + 1);
            sum += AssetValue * Px * Qx2;
        }
        return sum;
    }
    private double Section4(Employee employee) {
        double sum = 0;
        double LastSalary = employee.getSalary();
        double Seniority = employee.getSeniority();
        double Section14Rate = employee.getSection14Percent() / 100;
        int W = getW(employee);
        int X = employee.getAge();
        double YearsToSection14 = getYearsToSection14(employee);
        double Px = P(employee, W - X - 1);
        double Qx1 = getFired( W - 1 );
        double discountRate = data.getDiscountRateList().get(W - X - 1).getPercent() ;
        sum += LastSalary * YearsToSection14  * ( Math.pow(1 + getSalaryGrowthRate(W - X - 1), W - X + 0.5) * Px * Qx1 / Math.pow(1 + discountRate, W - X + 0.5));
        sum += LastSalary * (Seniority - YearsToSection14) * (1 - Section14Rate)  * ( Math.pow(1 + getSalaryGrowthRate(W - X - 1), W - X + 0.5) * Px * Qx1 / Math.pow(1 + discountRate, W - X + 0.5));
        return sum;
    }
    private double Section5(Employee employee) {
        double sum = 0;
        double LastSalary = employee.getSalary();
        double Seniority = employee.getSeniority();
        double Section14Rate = employee.getSection14Percent() / 100;
        int W = getW(employee);
        int X = employee.getAge();
        double YearsToSection14 = getYearsToSection14(employee);
        double Px = P(employee, W - X - 1);
        double Qx3 = getDeath( W - 1, employee.getGender() );
        double discountRate = data.getDiscountRateList().get(W - X - 1).getPercent() ;
        sum += LastSalary * YearsToSection14  * ( Math.pow(1 + getSalaryGrowthRate(W - X - 1), W - X - 1 + 0.5) * Px * Qx3 / Math.pow(1 + discountRate, W - X - 1 + 0.5));
        sum += LastSalary * (Seniority - YearsToSection14) * (1 - Section14Rate)  * ( Math.pow(1 + getSalaryGrowthRate(W - X - 1), W - X - 1 + 0.5) * Px * Qx3 / Math.pow(1 + discountRate, W - X - 1 + 0.5));
        return sum;
    }
    private double Section6(Employee employee) {
        double sum = 0;
        double AssetValue = employee.getAssetValue();
        int W = getW(employee);
        int X = employee.getAge();
        double Px = P(employee, W - X - 1);
        double Qx2 = getLeaving( W - 1);
        sum += AssetValue * Px * Qx2;
        return sum;
    }
    private double Section7(Employee employee) {
        double sum = 0;
        double LastSalary = employee.getSalary();
        double Seniority = employee.getSeniority();
        double Section14Rate = employee.getSection14Percent() / 100;
        int W = getW(employee);
        int X = employee.getAge();
        double Qx1 = getFired( W - 1 );
        double Qx2 = getLeaving( W - 1 );
        double Qx3 = getDeath( W - 1 , employee.getGender());
        double Px = P(employee, W - X - 1);
        double discountRate = data.getDiscountRateList().get(W - X - 1).getPercent() ;
        double YearsToSection14 = getYearsToSection14(employee);
        sum += LastSalary * YearsToSection14  * ( Math.pow(1 + getSalaryGrowthRate(W - X - 1), W - X) * Px * ( 1 - Qx1 - Qx2 - Qx3 ) / Math.pow(1 + discountRate, W - X + 0.5));
        sum += LastSalary * (Seniority - YearsToSection14) * (1 - Section14Rate)  * ( Math.pow(1 + getSalaryGrowthRate(W - X - 1), W - X) * Px * ( 1 - Qx1 - Qx2 - Qx3 ) / Math.pow(1 + discountRate, W - X));
        return sum;
    }
    private double getFired(int age){
        double Fired = 0;
        for(int j=0; j< data.getLeavingProbabilityList().size();j++)
            if(data.getLeavingProbabilityList().get(j).getFromAge() <= age && age <= data.getLeavingProbabilityList().get(j).getToAge())
                Fired = data.getLeavingProbabilityList().get(j).getFiredPercent() / 100;
        return Fired;
    }
    private double getLeaving(int age){
        double Leaving = 0;
        for(int j=0; j< data.getLeavingProbabilityList().size();j++)
            if(data.getLeavingProbabilityList().get(j).getFromAge() <= age && age <= data.getLeavingProbabilityList().get(j).getToAge())
                Leaving = data.getLeavingProbabilityList().get(j).getResignPercent() / 100;
        return Leaving;
    }
    private double getDeath(int age , String gender){
        double Death = 0;
        for(int j=0; j< data.getDeathProbabilityList().size();j++)
            if(data.getDeathProbabilityList().get(j).getAge() == age) {
                if (gender.equals("M"))
                    Death = data.getDeathProbabilityList().get(j).getqMan();
                else
                    Death = data.getDeathProbabilityList().get(j).getqWomen();
            }
        return Death;
    }
    private int getYearsToSection14(Employee employee){
        int YearToSection14 = employee.getSection14StartDate().getYear() - employee.getStartWork().getYear();
        if(employee.getStartWork().getMonth() > employee.getSection14StartDate().getMonth())
            YearToSection14 -= 1;
        else if(employee.getSection14StartDate().getMonth() == employee.getSection14StartDate().getMonth())
            if(employee.getSection14StartDate().getDay() > employee.getSection14StartDate().getDay())
                YearToSection14 -= 1;
        return YearToSection14;
    }
}
