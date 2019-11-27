package com.example.reportingsystemmobile.myreports;

import android.preference.PreferenceManager;
import com.example.reportingsystemmobile.RestServiceBuilder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class ReportListService {

    ReportListActivity reportListActivity;

    ReportListApiInterface reportListApiInterface;

    public ReportListService(ReportListActivity reportListActivity) {
        this.reportListActivity = reportListActivity;
        reportListApiInterface = RestServiceBuilder.getClient(reportListActivity.getContext()).create(ReportListApiInterface.class);
    }

    public void getReportList() {
        int preferences = PreferenceManager.getDefaultSharedPreferences(reportListActivity.getContext()).getInt ("USER_ID", 1);

        reportListApiInterface.getReportList(preferences).enqueue(new Callback<List<ReportListResponse>>() {
            @Override
            public void onResponse(Call<List<ReportListResponse>> call, Response<List<ReportListResponse>> response) {
                if(response.isSuccessful()) {
                    List<ReportListResponse> reportListResponses = response.body();
                    reportListActivity.setReportListResponseList(reportListResponses);
                } else {
                    reportListActivity.displayToast("Error");
                }
            }

            @Override
            public void onFailure(Call<List<ReportListResponse>> call, Throwable t) {
                reportListActivity.displayToast("Error");
            }
        });
    }
}
