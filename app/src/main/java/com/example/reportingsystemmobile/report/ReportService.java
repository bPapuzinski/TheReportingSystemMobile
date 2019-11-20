package com.example.reportingsystemmobile.report;

import com.example.reportingsystemmobile.RestServiceBuilder;

import java.io.IOException;

public class ReportService {

    private ReportActivity reportActivity;

    private ReportApiInterface reportApiInterface = RestServiceBuilder.getClient().create(ReportApiInterface.class);

    public ReportService(ReportActivity reportActivity) {
        this.reportActivity = reportActivity;
    }

    public void sendNewReport(ReportData reportData) {

        reportData.setAuthorId(1);
        reportData.setCoordinate("11111");
        try {
            int response = reportApiInterface.addNewReport(reportData).execute().code();
            if (response == 201) {
                reportActivity.displayToast("Report added");
            }
            if (response == 400) {
                reportActivity.displayToast("Report not added");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
