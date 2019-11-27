package com.example.reportingsystemmobile.report;

import android.preference.PreferenceManager;
import com.example.reportingsystemmobile.RestServiceBuilder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReportService {

    private ReportActivity reportActivity;

    private ReportApiInterface reportApiInterface;

    public ReportService(ReportActivity reportActivity) {
        this.reportActivity = reportActivity;
        reportApiInterface = RestServiceBuilder.getClient(reportActivity.getApplicationContext()).create(ReportApiInterface.class);
    }

    public void sendNewReport(ReportData reportData) {

        int preferences = PreferenceManager.getDefaultSharedPreferences(reportActivity.getApplicationContext()).getInt ("USER_ID", 1);

        reportData.setAuthorId(preferences);
        reportData.setCoordinate("11111");
            reportApiInterface.addNewReport(reportData).enqueue(new Callback<ReportResponse>() {
                @Override
                public void onResponse(Call<ReportResponse> call, Response<ReportResponse> response) {
                    if (response.code() == 201) {
                        reportActivity.displayToast("Report added");
                    }
                    if (response.code() == 400) {
                        reportActivity.displayToast("Report not added");
                    }
                }

                @Override
                public void onFailure(Call<ReportResponse> call, Throwable t) {
                    reportActivity.displayToast("Error while sending request");
                }
            });
    }
}
