package com.example.economysce;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

import jxl.WorkbookSettings;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class MainActivity extends AppCompatActivity {
    private Button Part1Button, Part2Button;
    WritableWorkbook workbook;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            init();
        } catch (WriteException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void init() throws WriteException, IOException {
        setID();
        Part1();
        Part2();
        WorkbookSettings ws = new WorkbookSettings();
        ws.setLocale(new Locale("en", "EN"));
        workbook = Workbook.createWorkbook(new File(filename), ws);
        WritableSheet s2 = workbook.createSheet("Number Formats", 0);
        WritableSheet s3 = workbook.createSheet("Date Formats", 1);
        WritableSheet s1 = workbook.createSheet("Label Formats", 2);
        WritableSheet s4 = workbook.createSheet("Borders", 3);
        WritableSheet s5 = workbook.createSheet("Labels", 4);
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