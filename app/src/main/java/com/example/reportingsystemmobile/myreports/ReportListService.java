package com.example.reportingsystemmobile.myreports;

import android.preference.PreferenceManager;
import com.example.reportingsystemmobile.RestServiceBuilder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class ReportListService {

    ReportListFragment reportListFragment;

    ReportListApiInterface reportListApiInterface;

    public ReportListService(ReportListFragment reportListFragment) {
        this.reportListFragment = reportListFragment;
        reportListApiInterface = RestServiceBuilder.getClient(reportListFragment.getContext()).create(ReportListApiInterface.class);
    }

    public void getReportList() {
        int preferences = PreferenceManager.getDefaultSharedPreferences(reportListFragment.getContext()).getInt ("USER_ID", 1);

        reportListApiInterface.getReportList(preferences).enqueue(new Callback<List<ReportListResponse>>() {
            @Override
            public void onResponse(Call<List<ReportListResponse>> call, Response<List<ReportListResponse>> response) {
                if(response.isSuccessful()) {
                    List<ReportListResponse> reportListResponses = response.body();
                    reportListFragment.setReportListResponseList(reportListResponses);
                } else {
                    reportListFragment.displayToast("Error");
                }
            }

            @Override
            public void onFailure(Call<List<ReportListResponse>> call, Throwable t) {
                reportListFragment.displayToast("Error");
            }
        });
    }
}
