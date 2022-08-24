package com.gebeya.myapplication.ViewModel;

import android.util.Log;

import com.gebeya.myapplication.core.EmployeeApi;
import com.gebeya.myapplication.core.App;
import com.gebeya.myapplication.Sevices.EmployeService;
import com.gebeya.myapplication.core.Employee;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmployeeViewModel extends ViewModel {


    MutableLiveData<List<Employee>> EmployeeList = new MutableLiveData<>();
    EmployeService employeeService = App.getConnection().create(EmployeService.class);

    public LiveData<List<Employee>> getEmployee() {
//        if (EmployeeList == null) {
//            EmployeeList = new MutableLiveData<List<Employee>>();
//           loadEmployeeData();
//        }

        return EmployeeList;
    }

    public void loadEmployeeData() {

        EmployeService employeeService = App.getConnection().create(EmployeService.class);
        final Call<EmployeeApi> em = employeeService.FetchEmployeeData();

        em.enqueue(new Callback<EmployeeApi>() {
            @Override
            public void onResponse(Call<EmployeeApi> call, Response<EmployeeApi> response) {


                if (response.isSuccessful()) {

                    EmployeeApi employeeApi = response.body();
                    assert employeeApi != null;
                    List<Employee> Employeelist = employeeApi.getData();
                    Log.e("test", "nowStatusIs: " + "Hello" + employeeApi.getData());
                    for (Employee employee : Employeelist) {

//                        Employee e = new Employee(employee.getEmployeeName(),employee.getEmployeeSalary(),employee.getEmployeeAge());
                        List<Employee> result = Employee.find(Employee.class, "employee_name = ?", employee.getEmployeeName());
                        if (result.isEmpty()) {
                            employee.save();
                        }

                        Log.e("test", "nowStatusIs: " + employeeApi.getData() + "yess" + employee);
                    }
                    EmployeeList.setValue(Employeelist);

                    Log.e("SAVED TO DATABASE", "SUCcesful");
                } else {

                    Log.e("test", "Failed" + response.code());
                }

            }

            /**
             * Invoked when a network exception occurred talking to the server or when an unexpected exception
             * occurred creating the request or processing the response.
             *
             * @param call
             * @param t
             */
            @Override
            public void onFailure(Call<EmployeeApi> call, Throwable t) {

            }

        });
    }


}