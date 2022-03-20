package com.example.economysce;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.economysce.Adapters.UserAdapter;
import com.example.economysce.Class.Data;
public class DataFragment extends Fragment {
    private RecyclerView recyclerView;
    private UserAdapter userAdapter;
    private Data userList = new Data();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data,container,false);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        readUsers();
        return view;
    }
    private void readUsers(){
        userAdapter = new UserAdapter(getContext(), userList.getUserList());
        recyclerView.setAdapter(userAdapter);
    }
}