package com.example.economysce;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.economysce.Adapters.DiscountRateAdapter;
import com.example.economysce.Adapters.LeavingProbabilityAdapter;
import com.example.economysce.Class.Data;

public class ReductionFragment extends Fragment {
    private RecyclerView recyclerViewDiscountRate,recyclerViewLeavingProbability;
    private Data data = new Data();
    private DiscountRateAdapter discountRateAdapter;
    private LeavingProbabilityAdapter leavingProbabilityAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reduction,container,false);
        recyclerViewDiscountRate = view.findViewById(R.id.recyclerViewDiscountRate);
        recyclerViewLeavingProbability = view.findViewById(R.id.recyclerViewLeavingProbability);
        recyclerViewDiscountRate.setHasFixedSize(true);
        recyclerViewDiscountRate.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewLeavingProbability.setHasFixedSize(true);
        recyclerViewLeavingProbability.setLayoutManager(new LinearLayoutManager(getContext()));
        readDiscountRate();
        readLeavingProbability();
        return view;
    }
    private void readDiscountRate(){
        discountRateAdapter = new DiscountRateAdapter(getContext(), data.getDiscountRateList());
        recyclerViewDiscountRate.setAdapter(discountRateAdapter);
    }
    private void readLeavingProbability(){
        leavingProbabilityAdapter = new LeavingProbabilityAdapter(getContext(), data.getLeavingProbabilityList());
        recyclerViewLeavingProbability.setAdapter(leavingProbabilityAdapter);
    }
}