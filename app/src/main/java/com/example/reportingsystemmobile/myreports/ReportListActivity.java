package com.example.reportingsystemmobile.myreports;

import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.reportingsystemmobile.R;

import java.util.ArrayList;
import java.util.List;

public class ReportListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ReportListService reportListService;
    List<ReportListResponse> reportListResponseList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportslist);

        reportListService = new ReportListService(this);

        reportListService.getReportList();

        recyclerView = findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        reportListResponseList = new ArrayList<>();
        adapter = new ReportListAdapter(reportListResponseList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    public void setReportListResponseList(List<ReportListResponse> reportListResponseList) {
        this.reportListResponseList = reportListResponseList;
        adapter = new ReportListAdapter(reportListResponseList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    public void displayToast(String message) {
        runOnUiThread(() -> Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show());
    }
}
