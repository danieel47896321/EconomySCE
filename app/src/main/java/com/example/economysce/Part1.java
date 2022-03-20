package com.example.economysce;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class Part1 extends AppCompatActivity {
    private TextView Title;
    private ImageView BackIcon;
    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    private ViewPagerAdapter fragmentPager;
    private String[] titles;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part1);
        init();
    }
    private void init(){
        setID();
        BackIcon();
    }
    private void setID(){
        BackIcon = findViewById(R.id.BackIcon);
        BackIcon.setVisibility(View.VISIBLE);
        Title = findViewById(R.id.Title);
        Title.setText("חלק 1");
        tabLayout = findViewById(R.id.tabLayout);
        fragmentPager = new ViewPagerAdapter(Part1.this);
        viewPager2 = findViewById(R.id.viewPager);
        viewPager2.setAdapter(fragmentPager);
        titles = new String[3];
        titles[0] = "נתונים";
        titles[1] = "הנחות";
        titles[2] = "חישוב";
        new TabLayoutMediator(tabLayout,viewPager2,((tab, position) -> tab.setText(titles[position]))).attach();
    }
    private void BackIcon(){
        BackIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Part1.this, MainActivity.class));
                finish();
            }
        });
    }
    public static class ViewPagerAdapter extends FragmentStateAdapter {
        private String[] titles = {"נתונים","הנחות","חישוב"};
        public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
            createFragment(2);
        }
        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position){
                case 0:
                    return  new DataFragment();
                case 1:
                    return new ReductionFragment();
                case 2:
                    return new CalculationFragment();
            }
            return new DataFragment();
        }
        @Override
        public int getItemCount() { return titles.length; }
    }
}