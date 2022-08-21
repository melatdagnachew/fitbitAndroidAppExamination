package com.gebeya.myapplication.core;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.orm.SugarRecord;
import com.orm.dsl.Ignore;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;

public class Employee extends SugarRecord  {



    @SerializedName("employee_name")
    private String EmployeeName;

    @SerializedName("employee_salary")
    private String EmployeeSalary;

    @SerializedName("employee_age")
    private String EmployeeAge;

    public Employee(){

    }
    public Employee(String EmployeeName, String EmployeeSalary,String EmployeeAge) {
        this.EmployeeName = EmployeeName;
        this.EmployeeSalary = EmployeeSalary;
        this.EmployeeAge = EmployeeAge;
    }

    public String getEmployeeName() {
        return EmployeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.EmployeeName = employeeName;
    }

    public String getEmployeeSalary() {
        return EmployeeSalary;
    }

    public void setEmployeeSalary(String employeeSalary) {
        this.EmployeeSalary = employeeSalary;
    }

    public void setEmployeeAge(String employeeAge) {
        this.EmployeeAge = employeeAge;
    }

    public String getEmployeeAge() {
        return EmployeeAge;
    }


    @NonNull
    @Override
    public String toString() {
        return new  Gson().toJson(this);
    }

}