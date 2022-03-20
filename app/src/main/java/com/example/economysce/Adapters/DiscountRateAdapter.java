package com.example.economysce.Adapters;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.economysce.Class.DiscountRate;
import com.example.economysce.R;

import java.util.List;

public class DiscountRateAdapter extends RecyclerView.Adapter<DiscountRateAdapter.ViewHolder> {
    private Context context;
    private List<DiscountRate> discountRateList;
    public DiscountRateAdapter(Context context, List<DiscountRate> discountRateList){
        this.context = context;
        this.discountRateList = discountRateList;
    }
    @NonNull
    @Override
    public DiscountRateAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_reduction_discount_rate_view, parent, false);
        return new DiscountRateAdapter.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull DiscountRateAdapter.ViewHolder holder, int position) {
        DiscountRate discountRate = discountRateList.get(position);
        holder.Year.setText(discountRate.getYear()+"");
        holder.Percent.setText(discountRate.getPercent()+"%");
    }
    @Override
    public int getItemCount() { return discountRateList.size(); }
    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView Year;
        public TextView Percent;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Year = itemView.findViewById(R.id.Year);
            Percent = itemView.findViewById(R.id.Percent);
        }
    }
}