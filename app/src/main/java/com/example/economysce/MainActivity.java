package com.example.economysce;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button Part1Button, Part2Button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    private void init(){
        setID();
        Part1();
        Part2();
    }
    private void setID(){
        Part1Button = findViewById(R.id.Part1Button);
        Part2Button = findViewById(R.id.Part2Button);
    }
    private void Part1(){
        Part1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Part1.class));
                finish();
            }
        });
    }
    private void Part2(){
        Part2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Part2.class));
                finish();
            }
        });
    }
}