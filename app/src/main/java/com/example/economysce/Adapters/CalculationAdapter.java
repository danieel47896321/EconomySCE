package com.example.economysce.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.economysce.Class.EmployeeCalculation;
import com.example.economysce.R;

import java.util.List;

public class CalculationAdapter extends RecyclerView.Adapter<CalculationAdapter.ViewHolder> {
    private Context context;
    private List<EmployeeCalculation> employeeCalculationList;
    public CalculationAdapter(Context context, List<EmployeeCalculation> employeeCalculationList){
        this.context = context;
        this.employeeCalculationList = employeeCalculationList;
    }
    @NonNull
    @Override
    public CalculationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_calculation_view, parent, false);
        return new CalculationAdapter.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull CalculationAdapter.ViewHolder holder, int position) {
        EmployeeCalculation employeeCalculation = employeeCalculationList.get(position);
        holder.Id.setText("." + employeeCalculation.getId());
        holder.FirstName.setText("שם: " + employeeCalculation.getName());
        holder.LastName.setText(", שם משפחה: " + employeeCalculation.getLastName());
        holder.Compensation.setText("סכום הפיצויים: " + String.format("%.02f",employeeCalculation.getCompensation()));
        holder.CostOfOnGoingService.setText("עלות שירות שוטף: " + String.format("%.02f",employeeCalculation.getOngoingServiceCost()));
        holder.CostOfServiceExpectation.setText("עלות יוון שוטף: " + String.format("%.02f",employeeCalculation.getDiscountCost()));
        if(employeeCalculation.getActuarialLossGainInLiability() < 0)
            holder.ActuarialLossGainInLiability.setText("הפסד/רווח אקטוארי בהתחייבות: " + String.format("%.02f",Math.abs(employeeCalculation.getActuarialLossGainInLiability())) + "-");
        else
            holder.ActuarialLossGainInLiability.setText("הפסד/רווח אקטוארי בהתחייבות: " + String.format("%.02f",employeeCalculation.getActuarialLossGainInLiability()));
        holder.ExpectedAssetsReturns.setText("תשואה צפויה על נכסי התוכנית: " + String.format("%.02f",employeeCalculation.getExpectedAssetsReturns()));
        if(employeeCalculation.getActuarialLossGainInLiability() < 0)
            holder.ActuarialLossGainInAssets.setText("רווחים/הפסדים אקטוארים נכסים: " + String.format("%.02f",Math.abs(employeeCalculation.getActuarialLossGainInAssets())) + "-");
        else
            holder.ActuarialLossGainInAssets.setText("רווחים/הפסדים אקטוארים נכסים: " + String.format("%.02f",employeeCalculation.getActuarialLossGainInAssets()));
    }
    @Override
    public int getItemCount() { return employeeCalculationList.size(); }
    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView Id, FirstName, LastName, Compensation, CostOfOnGoingService, CostOfServiceExpectation, ActuarialLossGainInLiability, ExpectedAssetsReturns, ActuarialLossGainInAssets;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Id = itemView.findViewById(R.id.Id);
            FirstName = itemView.findViewById(R.id.FirstName);
            LastName = itemView.findViewById(R.id.LastName);
            Compensation = itemView.findViewById(R.id.Compensation);
            CostOfOnGoingService = itemView.findViewById(R.id.CostOfOnGoingService);
            CostOfServiceExpectation = itemView.findViewById(R.id.CostOfServiceExpectation);
            ActuarialLossGainInLiability = itemView.findViewById(R.id.ActuarialLossGainInLiability);
            ExpectedAssetsReturns = itemView.findViewById(R.id.ExpectedAssetsReturns);
            ActuarialLossGainInAssets = itemView.findViewById(R.id.ActuarialLossGainInAssets);
        }
    }
}
