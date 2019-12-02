package com.example.reportingsystemmobile.reportdetails;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ReportDetailsApiInterface {

    @GET("/report/{id}")
    Call<ReportDetailsResponse> getReportDetails(@Path("id") int id);
}
