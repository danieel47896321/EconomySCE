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
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

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
            if(i % 2 == 1)
                SalaryGrowthRate = 0.05;
            else
                SalaryGrowthRate = 0.03;
            compensation = Compensation(employee);
            costOfOnGoingService = CostOfOnGoingService(employee, ActuarialFactory(employee,compensation));
            costOfServiceExpectation = CostOfServiceExpectation(employee, compensation, costOfOnGoingService);
            actuarialLossGainInLiability = ActuarialLossGainInLiability(employee, compensation,costOfOnGoingService,costOfServiceExpectation);
            expectedAssetsReturns = ExpectedAssetsReturns(employee);
            actuarialLossGainInAssets = ActuarialLossGainInAssets(employee,expectedAssetsReturns);
            employeeCalculationList.add(new EmployeeCalculation(employee.getId(),employee.getFirstName(),employee.getLastName(),compensation,costOfOnGoingService,costOfServiceExpectation,actuarialLossGainInLiability,expectedAssetsReturns,actuarialLossGainInAssets));
        }
        calculationAdapter = new CalculationAdapter(getContext(), employeeCalculationList);
        recyclerView.setAdapter(calculationAdapter);
        CreateExcel();
    }
    private double Compensation(Employee employee) {
        //have left date
        if(!employee.getReasonForLeaving().equals("")) {
            return 0.0;
        }
        //age higher than retirement
        else if(CheckRetirement(employee)) {
            return employee.getSalary() * employee.getSeniority();
        }
        //Workers A,B,C
        return CalcWorkerCompensation(employee);
    }
    private boolean CheckRetirement(Employee employee){
        if(employee.getGender().equals("M")) {
            if (employee.getAge() > 67)
                return true;
        }
        else if(employee.getGender().equals("F")) {
            if(employee.getAge() > 64)
                return true;
        }
        return false;
    }
    private double CalcWorkerCompensation(Employee employee){
        return Section1(employee) + Section2(employee) + Section3(employee) + Section4(employee) + Section5(employee) + Section6(employee) + Section7(employee);
    }
    private int getW(Employee employee){
        int w = 67;
        if (employee.getGender().equals("F"))
            w -= 3;
        return w;
    }
    private double getSalaryGrowthRate(double year){
        if(year % 2 == 0)
            return SalaryGrowthRate;
        return 0;
    }
    private double P(Employee employee, int t){
        double sol = 1;
        int Age = employee.getAge();
        for(int i=1; i < t  ;i++)
            sol *= (1 - getLeaving(Age + i ) - getFired(Age + i ) - getDeath(Age + i, employee.getGender()));
        return sol;
    }
    private double getFired(int age){
        double Fired = 0;
        for(int j=0; j< data.getLeavingProbabilityList().size();j++)
            if(data.getLeavingProbabilityList().get(j).getFromAge() <= age && age <= data.getLeavingProbabilityList().get(j).getToAge())
                Fired = data.getLeavingProbabilityList().get(j).getFiredPercent();
        return Fired;
    }
    private double getLeaving(int age){
        double Leaving = 0;
        for(int j=0; j< data.getLeavingProbabilityList().size();j++)
            if(data.getLeavingProbabilityList().get(j).getFromAge() <= age && age <= data.getLeavingProbabilityList().get(j).getToAge())
                Leaving = data.getLeavingProbabilityList().get(j).getResignPercent();
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
    private double getYearsToSection14(Employee employee){
        LocalDate startSection14Date =  LocalDate.of(employee.getSection14StartDate().getYear(), employee.getSection14StartDate().getMonth(), employee.getSection14StartDate().getDay());
        LocalDate startWorkDate = LocalDate.of(employee.getStartWork().getYear(),employee.getStartWork().getMonth(),employee.getStartWork().getDay());
        long daysBetween =  ChronoUnit.DAYS.between( startWorkDate,startSection14Date);
        return daysBetween/365;
    }
    private double Section1(Employee employee) {
        double sum = 0;
        double LastSalary = employee.getSalary();
        double Seniority = employee.getSeniority();
        double Section14Rate = employee.getSection14Percent() ;
        int W = getW(employee);
        int X = employee.getAge();
        for (int t = 1; t <= W - X - 2; t++) {
            double Px = P(employee, t + 1);
            double Q1x = getFired(X + t + 1);
            double discountRate = data.getDiscountRateList().get(t).getPercent();
            if( employee.getSection14StartDate() == null || (employee.getStartWork().getYear() == employee.getSection14StartDate().getYear() && employee.getStartWork().getMonth() == employee.getSection14StartDate().getMonth() && employee.getStartWork().getDay() == employee.getSection14StartDate().getDay()) ) {
                sum += ( LastSalary * Seniority * (1 - Section14Rate) * Math.pow(1 + getSalaryGrowthRate(t), t + 0.5) * Px * Q1x ) / Math.pow(1 + discountRate, t + 0.5);
            } else {
                double YearsToSection14 = getYearsToSection14(employee);
                sum += ( LastSalary * YearsToSection14 * (1 - Section14Rate) * Math.pow(1 + getSalaryGrowthRate(t), t + 0.5) * Px * Q1x ) / Math.pow(1 + discountRate, t + 0.5);
                sum += ( LastSalary * (Seniority - YearsToSection14) * (1 - Section14Rate) * Math.pow(1 + getSalaryGrowthRate(t), t + 0.5) * Px * Q1x ) / Math.pow(1 + discountRate, t + 0.5);
            }
        }
        return sum;
    }
    private double Section2(Employee employee) {
        double sum = 0;
        double LastSalary = employee.getSalary();
        double Seniority = employee.getSeniority();
        double Section14Rate = employee.getSection14Percent();
        int W = getW(employee);
        int X = employee.getAge();
        for (int t = 1; t <= W - X - 2; t++) {
            double Px = P(employee, t + 1);
            double Q3x = getDeath( X + t + 1 , employee.getGender());
            double discountRate = data.getDiscountRateList().get(t).getPercent();
            if ( employee.getSection14StartDate() == null || (employee.getStartWork().getYear() == employee.getSection14StartDate().getYear() && employee.getStartWork().getMonth() == employee.getSection14StartDate().getMonth() && employee.getStartWork().getDay() == employee.getSection14StartDate().getDay()) ) {
                sum += ( LastSalary * Seniority * (1 - Section14Rate) * Math.pow(1 + getSalaryGrowthRate(t), t + 0.5) * Px * Q3x ) / Math.pow(1 + discountRate, t + 0.5);
            } else {
                double YearsToSection14 = getYearsToSection14(employee);
                sum += ( LastSalary * YearsToSection14 * (1 - Section14Rate) * Math.pow(1 + getSalaryGrowthRate(t), t + 0.5) * Px * Q3x ) / Math.pow(1 + discountRate, t + 0.5);
                sum += ( LastSalary * (Seniority - YearsToSection14) * (1 - Section14Rate) * Math.pow(1 + getSalaryGrowthRate(t), t + 0.5) * Px * Q3x ) / Math.pow(1 + discountRate, t + 0.5);
            }
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
        double Section14Rate = employee.getSection14Percent();
        int W = getW(employee);
        int X = employee.getAge();
        int t = W - X - 1;
        if(t<0)
            t=0;
        double Px = P(employee, t);
        double Q1x = getFired( W - 1 );
        double discountRate = data.getDiscountRateList().get(t).getPercent() ;
        double LowerPart = Math.pow(1 + discountRate,  W - X + 0.5);
        if( employee.getSection14StartDate() == null || (employee.getStartWork().getYear() == employee.getSection14StartDate().getYear() && employee.getStartWork().getMonth() == employee.getSection14StartDate().getMonth() && employee.getStartWork().getDay() == employee.getSection14StartDate().getDay()) ) {
            sum += ( LastSalary * Seniority * (1 - Section14Rate) * Math.pow(1 + getSalaryGrowthRate(t), W - X + 0.5) * Px * Q1x ) / LowerPart;
        } else {
            double YearsToSection14 = getYearsToSection14(employee);
            sum += ( LastSalary * YearsToSection14 * (1 - Section14Rate) * Math.pow(1 + getSalaryGrowthRate(t),  W - X + 0.5) * Px * Q1x ) / LowerPart;
            sum += ( LastSalary * (Seniority - YearsToSection14) * (1 - Section14Rate) * Math.pow(1 + getSalaryGrowthRate(t),  W - X + 0.5) * Px * Q1x ) / LowerPart;
        }
        return sum;
    }
    private double Section5(Employee employee) {
        double sum = 0;
        double LastSalary = employee.getSalary();
        double Seniority = employee.getSeniority();
        double Section14Rate = employee.getSection14Percent();
        int W = getW(employee);
        int X = employee.getAge();
        int t = W - X - 1;
        if(t<0)
            t=0;
        double Px = P(employee, t);
        double Qx3 = getDeath( W - 1, employee.getGender() );
        double discountRate = data.getDiscountRateList().get(t).getPercent() ;
        double LowerPart = Math.pow(1 + discountRate,  t + 0.5);
        if( employee.getSection14StartDate() == null || (employee.getStartWork().getYear() == employee.getSection14StartDate().getYear() && employee.getStartWork().getMonth() == employee.getSection14StartDate().getMonth() && employee.getStartWork().getDay() == employee.getSection14StartDate().getDay()) ) {
            sum += ( LastSalary * Seniority * (1 - Section14Rate) * Math.pow(1 + getSalaryGrowthRate(t), t + 0.5) * Px * Qx3 ) / LowerPart;
        } else {
            double YearsToSection14 = getYearsToSection14(employee);
            sum += ( LastSalary * YearsToSection14 * (1 - Section14Rate) * Math.pow(1 + getSalaryGrowthRate(t),  t + 0.5) * Px * Qx3 ) / LowerPart;
            sum += ( LastSalary * (Seniority - YearsToSection14) * (1 - Section14Rate) * Math.pow(1 + getSalaryGrowthRate(t),  t + 0.5) * Px * Qx3 ) / LowerPart;
        }
        return sum;
    }
    private double Section6(Employee employee) {
        double sum = 0;
        double AssetValue = employee.getAssetValue();
        int W = getW(employee);
        int X = employee.getAge();
        int t = W - X - 1;
        if(t<0)
            t=0;
        double Px = P(employee, t);
        double Qx2 = getLeaving( W - 1);
        sum += AssetValue * Px * Qx2;
        return sum;
    }
    private double Section7(Employee employee) {
        double sum = 0;
        double LastSalary = employee.getSalary();
        double Seniority = employee.getSeniority();
        double Section14Rate = employee.getSection14Percent();
        int W = getW(employee);
        int X = employee.getAge();
        int t = W - X - 1;
        if(t<0)
            t=0;
        double Q1x = getFired( W - 1 );
        double Q2x = getLeaving( W - 1 );
        double Q3x = getDeath( W - 1 , employee.getGender());
        double Px = P(employee, t);
        double discountRate = data.getDiscountRateList().get(t).getPercent() ;
        double LowerPart = Math.pow(1 + discountRate,  W - X);
        if ( employee.getSection14StartDate() == null || (employee.getStartWork().getYear() == employee.getSection14StartDate().getYear() && employee.getStartWork().getMonth() == employee.getSection14StartDate().getMonth() && employee.getStartWork().getDay() == employee.getSection14StartDate().getDay()) ) {
            sum += ( LastSalary * Seniority * (1 - Section14Rate) * Math.pow(1 + getSalaryGrowthRate(t), W - X) * Px * (1 - Q1x - Q2x - Q3x) ) / LowerPart;
        } else {
            double YearsToSection14 = getYearsToSection14(employee);
            sum += ( LastSalary * YearsToSection14 * (1 - Section14Rate) * Math.pow(1 + getSalaryGrowthRate(t), W - X) * Px * (1 - Q1x - Q2x - Q3x) ) / LowerPart;
            sum += ( LastSalary * (Seniority - YearsToSection14) * (1 - Section14Rate) * Math.pow(1 + getSalaryGrowthRate(t), W - X) * Px * (1 - Q1x - Q2x - Q3x) ) / LowerPart;
        }
        return sum;
    }
    private double getPartOfYear(Employee employee){
        LocalDate leftDate =  LocalDate.of(employee.getLeftDate().getYear(), employee.getLeftDate().getMonth(), employee.getLeftDate().getDay());
        LocalDate startOf2021 = LocalDate.of(2021,1,1);
        long daysBetween =  ChronoUnit.DAYS.between( leftDate,startOf2021);
        daysBetween = Math.abs(daysBetween);
        daysBetween -=1;
        return (double)daysBetween/365;
    }
    private double CostOfOnGoingService(Employee employee, double actuarialFactory) {
        double lastSalary, partialOfTheYearEmployeeWorked = 1, section14Percent;
        lastSalary = employee.getSalary();
        section14Percent = employee.getSection14Percent();
        if(employee.getLeftDate() != null) {
            if (employee.getLeftDate().getYear() < 2021)
                partialOfTheYearEmployeeWorked = 0;
            else
                partialOfTheYearEmployeeWorked = getPartOfYear(employee);
        }
        return lastSalary * partialOfTheYearEmployeeWorked * (1-section14Percent) * actuarialFactory;
    }
    private double ActuarialFactory(Employee employee, double compensation) {
        double lastSalary, seniority, section14Percent;
        lastSalary = employee.getSalary();
        seniority = employee.getSeniority();
        section14Percent = employee.getSection14Percent();
        if(employee.getLeftDate() != null || section14Percent == 1)
            return 1;
        return compensation / (lastSalary * seniority * (1 - section14Percent));
    }
    private double CostOfServiceExpectation(Employee employee, double compensation, double costOfOnGoingService) {
        int i = CalcEx(employee);
        double discountRate = data.getDiscountRateList().get(i).getPercent();
        double AssetPayment = employee.getAssetPayment();
        double CheckCompletion = employee.getCheckCompletion();
        return employee.getRestOpen() * discountRate  + (costOfOnGoingService - AssetPayment - CheckCompletion) * ( discountRate /2);
    }
    private int CalcEx(Employee employee) {
        double sum = 0;
        int Age = employee.getAge();
        int W = getW(employee);
        for(int i = Age+1 ; i < W  ;i++) {
            double sol = 1;
            for (int j = Age + 1; j <= i; j++)
                sol *= (1 - getLeaving(j) - getFired(j) - getDeath(j, employee.getGender()));
            sum += sol;
        }
        return round(sum);
    }
    private int round(double d){
        double dAbs = Math.abs(d);
        int i = (int) dAbs;
        double result = dAbs - (double) i;
        if(result<0.5)
            return d < 0 ? -i : i;
        else
            return d < 0 ? -(i+1) : i+1;
    }
    private double ActuarialLossGainInLiability(Employee employee, double compensation, double costOfOnGoingService, double costOfServiceExpectation) {
        double RestOpen = employee.getRestOpen();
        double AssetPayment = employee.getAssetPayment();
        return compensation - RestOpen - costOfOnGoingService - costOfServiceExpectation + AssetPayment;
    }
    private double ExpectedAssetsReturns(Employee employee) {
        int i = CalcEx(employee);
        double discountRate = data.getDiscountRateList().get(i).getPercent();
        double Deposits = employee.getDeposits();
        double AssetPayment = employee.getAssetPayment();
        return employee.getRestAsset() * discountRate + (Deposits-AssetPayment) * (discountRate/2);
    }
    private double ActuarialLossGainInAssets(Employee employee,double expectedAssetsReturns) {
        double AssetValue = employee.getAssetValue();
        double RestAsset = employee.getRestAsset();
        double Deposits = employee.getDeposits();
        double AssetPayment = employee.getAssetPayment();
        return AssetValue - RestAsset - expectedAssetsReturns - Deposits + AssetPayment;
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
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:{
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    saveFile();
                else{ }
            }
        }
    }
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
        HSSFCell title_costOfOnGoingService = title_Row.createCell(4);
        HSSFCell title_costOfServiceExpectation = title_Row.createCell(5);
        HSSFCell title_actuarialLossGainInLiability = title_Row.createCell(6);
        HSSFCell title_expectedAssetsReturns = title_Row.createCell(7);
        HSSFCell title_actuarialLossGainInAssets = title_Row.createCell(8);
        title_id.setCellValue("מסד");
        title_name.setCellValue("שם");
        title_lastname.setCellValue("שם משפחה");
        title_payment.setCellValue("יתרת סגירה(האקטון חלק 1)");
        title_costOfOnGoingService.setCellValue("עלות שירות שוטף");
        title_costOfServiceExpectation.setCellValue("עלות היוון");
        title_actuarialLossGainInLiability.setCellValue("הפסד אקטוארי");
        title_expectedAssetsReturns.setCellValue("תשואה צפויה");
        title_actuarialLossGainInAssets.setCellValue("רווח אקטוארי");
        //insert data
        for(int i = 0; i< employeeCalculationList.size(); i++) {
            HSSFRow hssfRow = hssfSheet.createRow(i+1);
            HSSFCell id = hssfRow.createCell(0);
            HSSFCell name = hssfRow.createCell(1);
            HSSFCell lastname = hssfRow.createCell(2);
            HSSFCell payment = hssfRow.createCell(3);
            HSSFCell costOfOnGoingService = hssfRow.createCell(4);
            HSSFCell costOfServiceExpectation = hssfRow.createCell(5);
            HSSFCell actuarialLossGainInLiability = hssfRow.createCell(6);
            HSSFCell expectedAssetsReturns = hssfRow.createCell(7);
            HSSFCell actuarialLossGainInAssets = hssfRow.createCell(8);
            id.setCellValue(employeeCalculationList.get(i).getId());
            name.setCellValue(employeeCalculationList.get(i).getName());
            lastname.setCellValue(employeeCalculationList.get(i).getLastName());
            payment.setCellValue((int)employeeCalculationList.get(i).getCompensation());
            costOfOnGoingService.setCellValue((int)employeeCalculationList.get(i).getOngoingServiceCost());
            costOfServiceExpectation.setCellValue((int)employeeCalculationList.get(i).getDiscountCost());
            actuarialLossGainInLiability.setCellValue((int)employeeCalculationList.get(i).getActuarialLossGainInLiability());
            expectedAssetsReturns.setCellValue((int)employeeCalculationList.get(i).getExpectedAssetsReturns());
            actuarialLossGainInAssets.setCellValue((int)employeeCalculationList.get(i).getActuarialLossGainInAssets());
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
}
