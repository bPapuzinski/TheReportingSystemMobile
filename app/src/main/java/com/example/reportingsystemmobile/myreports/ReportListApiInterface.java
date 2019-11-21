package com.example.reportingsystemmobile.myreports;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

public interface ReportListApiInterface {

    @GET("/report/user/{id}")
    Call<List<ReportListResponse>> getReportList(@Path("id") int id);

}
