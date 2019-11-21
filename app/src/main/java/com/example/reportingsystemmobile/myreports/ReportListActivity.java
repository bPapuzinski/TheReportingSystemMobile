package com.example.reportingsystemmobile.myreports;

import android.os.Bundle;
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

        new Thread(() -> reportListService.getReportList(1)).start();

        recyclerView = findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        while(reportListResponseList == null) {}
        adapter = new ReportListAdapter(reportListResponseList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    public void setReportListResponseList(List<ReportListResponse> reportListResponseList) {
        this.reportListResponseList = reportListResponseList;
    }
}
