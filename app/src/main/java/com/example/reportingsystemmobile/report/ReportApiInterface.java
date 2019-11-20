package com.example.reportingsystemmobile.report;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ReportApiInterface {

    @POST("/report")
    Call<ReportResponse> addNewReport(@Body ReportData reportData);
}
