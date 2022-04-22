package com.example.economysce.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.economysce.Class.User;
import com.example.economysce.R;

import java.util.List;

public class CalculationAdapter extends RecyclerView.Adapter<CalculationAdapter.ViewHolder> {
    private Context context;
    private List<User> users;
    private List<Double> reductions;
    public CalculationAdapter(Context context, List<User> users, List<Double> reductions){
        this.context = context;
        this.users = users;
        this.reductions = reductions;
    }
    @NonNull
    @Override
    public CalculationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_calculation_view, parent, false);
        return new CalculationAdapter.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull CalculationAdapter.ViewHolder holder, int position) {
        User user = users.get(position);
        Double reduction = reductions.get(position);
        holder.fragmentUserId.setText("."+user.getId());
        holder.fragmentUserName.setText("שם: " +user.getName());
        holder.fragmentUserLastName.setText(", שם משפחה: "+user.getLastName());
        holder.fragmentUserCompensation.setText("סכום הפיצויים: "+(int)reduction.doubleValue());
    }
    @Override
    public int getItemCount() { return users.size(); }
    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView fragmentUserId;
        public TextView fragmentUserName;
        public TextView fragmentUserLastName;
        public TextView fragmentUserCompensation;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            fragmentUserId = itemView.findViewById(R.id.fragmentUserId);
            fragmentUserName = itemView.findViewById(R.id.fragmentUserName);
            fragmentUserLastName = itemView.findViewById(R.id.fragmentUserLastName);
            fragmentUserCompensation = itemView.findViewById(R.id.fragmentUserCompensation);
        }
    }
}
