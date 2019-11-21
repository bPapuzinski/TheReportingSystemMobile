package com.example.reportingsystemmobile.myreports;

import com.example.reportingsystemmobile.RestServiceBuilder;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

public class ReportListService {

    ReportListApiInterface reportListApiInterface = RestServiceBuilder.getClient().create(ReportListApiInterface.class);

    ReportListActivity reportListActivity;

    public ReportListService(ReportListActivity reportListActivity) {
        this.reportListActivity = reportListActivity;
    }

    public void getReportList(int id) {

        try {
            Response<List<ReportListResponse>> response = reportListApiInterface.getReportList(id).execute();
            reportListActivity.setReportListResponseList(response.body());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
