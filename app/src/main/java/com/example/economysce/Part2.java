package com.example.economysce;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Part2 extends AppCompatActivity {
    private TextView Title;
    private ImageView BackIcon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part2);
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
        Title.setText("חלק 2");
    }
    private void BackIcon(){
        BackIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Part2.this, MainActivity.class));
                finish();
            }
        });
    }
}