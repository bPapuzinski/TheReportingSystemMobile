package com.example.reportingsystemmobile.reportdetails;

import android.os.Build;
import androidx.annotation.RequiresApi;
import com.example.reportingsystemmobile.RestServiceBuilder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReportDetailsService {

    ReportDetailsFragment reportDetailsFragment;

    ReportDetailsApiInterface reportDetailsApiInterface;

    public ReportDetailsService(ReportDetailsFragment reportDetailsFragment) {
        this.reportDetailsFragment = reportDetailsFragment;
        reportDetailsApiInterface = RestServiceBuilder.getClient(reportDetailsFragment.getContext()).create(ReportDetailsApiInterface.class);
    }

    public void getReportDetails(int reportId) {

        reportDetailsApiInterface.getReportDetails(reportId).enqueue(new Callback<ReportDetailsResponse>() {

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(Call<ReportDetailsResponse> call, Response<ReportDetailsResponse> response) {
                if(response.code()==200) {
                    reportDetailsFragment.updateData(response.body());
                } else {
                    reportDetailsFragment.displayToast("Error");
                }
            }

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onFailure(Call<ReportDetailsResponse> call, Throwable t) {
                reportDetailsFragment.displayToast("Error");
            }
        });
    }
}
