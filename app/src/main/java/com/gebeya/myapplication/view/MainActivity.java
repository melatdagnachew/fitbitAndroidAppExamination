package com.gebeya.myapplication.view;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.gebeya.myapplication.R;
import com.gebeya.myapplication.Sevices.EmployeService;
import com.gebeya.myapplication.ViewModel.EmployeeViewModel;
import com.gebeya.myapplication.adapter.EmployeeAdapter;
import com.gebeya.myapplication.core.App;
import com.gebeya.myapplication.core.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    View v;
    RecyclerView recyclerView ;
    Employee em= new Employee();
    EditText searchview;
    EmployeeAdapter adapter;
    List<Employee> employees;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchview =findViewById(R.id.search_view);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

       EmployeService employeeService = App.getConnection().create(EmployeService.class);
       employeeService.FetchEmployeeData();

       employees =Employee.listAll(Employee.class);
       System.out.println(employees.toString() + "Hello Hello");
       adapter = new EmployeeAdapter(MainActivity.this, employees);
       recyclerView.setAdapter(adapter);

        searchview.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });

}

    private void filter(String text) {
        ArrayList<Employee> filteredlist = new ArrayList<>();

        // running a for loop to compare elements.
        for (Employee item : employees) {
            // checking if the entered string matched with any item of our recycler view.
            if (item.getEmployeeName().toLowerCase().contains(text.toLowerCase())) {
                // if the item is matched we are
                // adding it to our filtered list.
                filteredlist.add(item);
            }
        }
        if (filteredlist.isEmpty()) {
            // if no item is added in filtered list we are
            // displaying a toast message as no data found.
            Log.e("No Data Found..", " Toast.LENGTH_SHORT");
        } else {
            // at last we are passing that filtered
            // list to our adapter class.
            adapter.setfilter(filteredlist);
        }
    }
////        em.enqueue(new Callback<EmployeeApi>() {
////            @Override
////            public void onResponse(@NonNull Call<EmployeeApi> call, @NonNull Response<EmployeeApi> response) {
////
////
////                if (response.isSuccessful()) {
////                    assert response.body() != null;
////                        Log.e("test", "nowStatusIs: " + response.body().toString());
////
////                } else {
////
////                    Log.e("test", "Failed" + response.code());
////                }
////
////            }
////
////            @Override
////            public void onFailure(Call<EmployeeApi> call, Throwable t) {
////                Log.e("test", "OnFailure" +" " + t.getMessage());
////            }
////
////        });



    }



