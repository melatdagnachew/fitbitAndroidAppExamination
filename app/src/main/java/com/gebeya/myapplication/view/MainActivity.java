package com.gebeya.myapplication.view;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.gebeya.myapplication.R;
import com.gebeya.myapplication.Sevices.EmployeService;
import com.gebeya.myapplication.ViewModel.EmployeeViewModel;
import com.gebeya.myapplication.adapter.EmployeeAdapter;
import com.gebeya.myapplication.core.App;
import com.gebeya.myapplication.core.Employee;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    View v;
    RecyclerView recyclerView ;
    Employee em= new Employee();
    EditText searchview;
    EmployeeAdapter adapter;
    List<Employee> savedEmployees;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchview =findViewById(R.id.search_view);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

       EmployeeViewModel employeeViewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(EmployeeViewModel.class);
       employeeViewModel.loadEmployeeData();
       employeeViewModel.getEmployee().observe(this, new Observer<List<Employee>>() {
           @Override
           public void onChanged(List<Employee> employees) {
               savedEmployees =Employee.listAll(Employee.class);
               adapter = new EmployeeAdapter(MainActivity.this, savedEmployees);
               recyclerView.setAdapter(adapter);

           }
       });



        searchview.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!savedEmployees.isEmpty()) {
                    filter(editable.toString());
                }
            }
        });

}

    private void filter(String text) {
        ArrayList<Employee> filteredlist = new ArrayList<>();

        // running a for loop to compare elements.
        for (Employee item : savedEmployees) {
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
            Toast.makeText(this,"Employees not found",Toast.LENGTH_LONG).show();
        } else {
            // at last we are passing that filtered
            // list to our adapter class.
            adapter.setfilter(filteredlist);
        }
    }



    }



