package com.example.economysce;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.economysce.Fragments.CalculationFragment;
import com.example.economysce.Fragments.DataFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    private ViewPagerAdapter fragmentPager;
    private String[] titles;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void init(){
        setID();
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setID(){
        tabLayout = findViewById(R.id.tabLayout);
        fragmentPager = new ViewPagerAdapter(MainActivity.this);
        viewPager2 = findViewById(R.id.viewPager);
        viewPager2.setAdapter(fragmentPager);
        titles = new String[2];
        titles[0] = "נתונים";
        titles[1] = "חישוב";
        new TabLayoutMediator(tabLayout,viewPager2,((tab, position) -> tab.setText(titles[position]))).attach();
    }
    public static class ViewPagerAdapter extends FragmentStateAdapter {
        private String[] titles = {"נתונים","חישוב"};
        @RequiresApi(api = Build.VERSION_CODES.O)
        public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
            createFragment(2);
        }
        @RequiresApi(api = Build.VERSION_CODES.O)
        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position){
                case 0:
                    return  new DataFragment();
                case 1:
                    return new CalculationFragment();
            }
            return new DataFragment();
        }
        @Override
        public int getItemCount() { return titles.length; }
    }
}