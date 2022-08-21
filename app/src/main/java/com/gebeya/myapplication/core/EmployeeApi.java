package com.gebeya.myapplication.core;

import com.gebeya.myapplication.core.Employee;
import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.List;

public class EmployeeApi extends SugarRecord {
    @SerializedName("status")
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Employee> getData() {
        return data;
    }

    public void setData(ArrayList<Employee> data) {
        this.data = data;
    }

    @SerializedName("data")
    private List<Employee> data;

}
