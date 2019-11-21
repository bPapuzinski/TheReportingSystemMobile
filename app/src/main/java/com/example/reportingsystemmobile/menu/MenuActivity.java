package com.example.reportingsystemmobile.menu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.reportingsystemmobile.R;
import com.example.reportingsystemmobile.myreports.ReportListActivity;
import com.example.reportingsystemmobile.report.ReportActivity;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button reportButton = findViewById(R.id.report_button);
        Button reportListButton = findViewById(R.id.reportList_button);

        reportButton.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(), ReportActivity.class);
            startActivity(i);

        });

        reportListButton.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(), ReportListActivity.class);
            startActivity(i);
        });
    }
}
