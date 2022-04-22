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

import com.example.economysce.Adapters.DiscountRateAdapter;
import com.example.economysce.Adapters.LeavingProbabilityAdapter;
import com.example.economysce.Class.DiscountRate;
import com.example.economysce.Class.LeavingProbability;

import java.util.ArrayList;

@RequiresApi(api = Build.VERSION_CODES.O)
public class ReductionFragment extends Fragment {
    private RecyclerView recyclerViewDiscountRate,recyclerViewLeavingProbability;
    private DiscountRateAdapter discountRateAdapter;
    private LeavingProbabilityAdapter leavingProbabilityAdapter;
    private ArrayList<LeavingProbability> leavingProbabilityList;
    private ArrayList<DiscountRate> discountRateList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reduction,container,false);
        this.leavingProbabilityList = new ArrayList<>();
        this.discountRateList = new ArrayList<>();
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
        AddDiscountRate();
        discountRateAdapter = new DiscountRateAdapter(getContext(), discountRateList);
        recyclerViewDiscountRate.setAdapter(discountRateAdapter);
    }
    private void readLeavingProbability(){
        AddLeavingProbability();
        leavingProbabilityAdapter = new LeavingProbabilityAdapter(getContext(),leavingProbabilityList);
        recyclerViewLeavingProbability.setAdapter(leavingProbabilityAdapter);
    }
    private void AddLeavingProbability(){
        leavingProbabilityList.add(new LeavingProbability(18,29,10,25));
        leavingProbabilityList.add(new LeavingProbability(30,39,8,20));
        leavingProbabilityList.add(new LeavingProbability(40,49,6,10));
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
}