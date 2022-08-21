package com.gebeya.myapplication.Sevices;

import com.gebeya.myapplication.core.EmployeeApi;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EmployeService {

    @GET("media/employees.json")
    Call<EmployeeApi> FetchEmployeeData();
}
