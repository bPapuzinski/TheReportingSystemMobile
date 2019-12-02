package com.example.reportingsystemmobile.report;

import android.preference.PreferenceManager;
import com.example.reportingsystemmobile.RestServiceBuilder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReportService {

    private ReportFragment reportFragment;

    private ReportApiInterface reportApiInterface;

    public ReportService(ReportFragment reportFragment) {
        this.reportFragment = reportFragment;
        reportApiInterface = RestServiceBuilder.getClient(reportFragment.getContext()).create(ReportApiInterface.class);
    }

    public void sendNewReport(ReportData reportData) {

        int preferences = PreferenceManager.getDefaultSharedPreferences(reportFragment.getContext()).getInt ("USER_ID", 1);

        reportData.setAuthorId(preferences);
            reportApiInterface.addNewReport(reportData).enqueue(new Callback<ReportResponse>() {
                @Override
                public void onResponse(Call<ReportResponse> call, Response<ReportResponse> response) {
                    if (response.code() == 201) {
                        reportFragment.displayToast("Report added");
                    }
                    if (response.code() == 400) {
                        reportFragment.displayToast("Report not added");
                    }
                }

                @Override
                public void onFailure(Call<ReportResponse> call, Throwable t) {
                    reportFragment.displayToast("Error while sending request");
                }
            });
    }
}
