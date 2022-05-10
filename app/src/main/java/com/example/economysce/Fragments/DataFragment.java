package com.example.economysce.Fragments;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.economysce.Adapters.UserAdapter;
import com.example.economysce.Class.Data;
import com.example.economysce.R;

@RequiresApi(api = Build.VERSION_CODES.O)
public class DataFragment extends Fragment {
    private RecyclerView recyclerView;
    private UserAdapter userAdapter;
    private Data data = Data.getData();
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data,container,false);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        readUsers();
        return view;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void readUsers(){
        userAdapter = new UserAdapter(getContext(), data.getEmployeeList());
        recyclerView.setAdapter(userAdapter);
    }
}